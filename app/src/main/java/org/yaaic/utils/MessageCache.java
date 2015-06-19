package org.yaaic.utils;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.util.LruCache;
import android.text.SpannableString;

import org.yaaic.model.Message;

import java.util.Locale;

/**
 */
public class MessageCache extends LruCache<Message, SpannableString> implements ComponentCallbacks2 {

    public static MessageCache instance;

    public static synchronized MessageCache getCache(Context context) {
        if (instance == null) {
            instance = new MessageCache(context);
        }
        return instance;
    }

    public static int CACHE_SIZE = 500;

    private final Context context;
    private Locale locale;

    private MessageCache(Context context) {
        super(CACHE_SIZE);
        this.context = context.getApplicationContext();
        this.context.registerComponentCallbacks(this);
        this.locale = context.getResources().getConfiguration().locale;
    }

    protected SpannableString create(Message message) {
        return message.render(context);
    }

    @Override
    public void onLowMemory() {
        // Handled by onTrimMemory()
    }

    @Override
    public void onTrimMemory(int level) {
        switch (level) {
            case TRIM_MEMORY_RUNNING_LOW:
            case TRIM_MEMORY_UI_HIDDEN:
            default:
                // Do nothing
                break;

            case TRIM_MEMORY_BACKGROUND:
            case TRIM_MEMORY_RUNNING_MODERATE:
                trimToSize(size() * 3 / 4);
                break;

            case TRIM_MEMORY_MODERATE:
            case TRIM_MEMORY_RUNNING_CRITICAL:
                trimToSize(size() / 2);
                break;

            case TRIM_MEMORY_COMPLETE:
                evictAll();
                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (locale != newConfig.locale && (locale == null || !locale.equals(newConfig.locale))) {
            // Remove the cache to load message with the new locale.
            locale = newConfig.locale;
            evictAll();
        }
    }
}
