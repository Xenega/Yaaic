/*
Yaaic - Yet Another Android IRC Client

Copyright 2009-2013 Sebastian Kaspari

This file is part of Yaaic.

Yaaic is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Yaaic is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Yaaic.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.yaaic.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.yaaic.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;

/**
 * Class for handling graphical smilies in text messages.
 *
 * @author Liato
 */
public abstract class Smilies
{
    private static final LinkedHashMap<String, Integer> mappings;

    private static final Pattern smiliesPattern;

    /*
     * Pre-compile the pattern to find smilies
     */
    static {
        mappings = new LinkedHashMap<>(); // LinkedHashMap, keep the order
        mappings.put(">:o", R.drawable.smiley_yell);
        mappings.put(">:-o", R.drawable.smiley_yell);
        mappings.put("O:)", R.drawable.smiley_innocent);
        mappings.put("O:-)", R.drawable.smiley_innocent);
        mappings.put(":)", R.drawable.smiley_smile);
        mappings.put(":-)", R.drawable.smiley_smile);
        mappings.put(":(", R.drawable.smiley_frown);
        mappings.put(":-(", R.drawable.smiley_frown);
        mappings.put(";)", R.drawable.smiley_wink);
        mappings.put(";-)", R.drawable.smiley_wink);
        mappings.put(":p", R.drawable.smiley_tongue_out);
        mappings.put(":-p", R.drawable.smiley_tongue_out);
        mappings.put(":P", R.drawable.smiley_tongue_out);
        mappings.put(":-P", R.drawable.smiley_tongue_out);
        mappings.put(":D", R.drawable.smiley_laughing);
        mappings.put(":-D", R.drawable.smiley_laughing);
        mappings.put(":[", R.drawable.smiley_embarassed);
        mappings.put(":-[", R.drawable.smiley_embarassed);
        mappings.put(":\\", R.drawable.smiley_undecided);
        mappings.put(":-\\", R.drawable.smiley_undecided);
        mappings.put(":o", R.drawable.smiley_surprised);
        mappings.put(":-o", R.drawable.smiley_surprised);
        mappings.put(":O", R.drawable.smiley_surprised);
        mappings.put(":-O", R.drawable.smiley_surprised);
        mappings.put(":*", R.drawable.smiley_kiss);
        mappings.put(":-*", R.drawable.smiley_kiss);
        mappings.put("8)", R.drawable.smiley_cool);
        mappings.put("8-)", R.drawable.smiley_cool);
        mappings.put(":$", R.drawable.smiley_money_mouth);
        mappings.put(":-$", R.drawable.smiley_money_mouth);
        mappings.put(":!", R.drawable.smiley_foot_in_mouth);
        mappings.put(":-!", R.drawable.smiley_foot_in_mouth);
        mappings.put(":'(", R.drawable.smiley_cry);
        mappings.put(":'-(", R.drawable.smiley_cry);
        mappings.put(":�(", R.drawable.smiley_cry);
        mappings.put(":�-(", R.drawable.smiley_cry);
        mappings.put(":X", R.drawable.smiley_sealed);
        mappings.put(":-X", R.drawable.smiley_sealed);
        mappings.put("o_O", R.drawable.smiley_wtf);
        mappings.put("O_o", R.drawable.smiley_wtf);

        StringBuilder regex = new StringBuilder("(");

        for (Entry<String, Integer> smilie : mappings.entrySet()) {
            regex.append(Pattern.quote(smilie.getKey()));
            regex.append("|");
        }

        regex.replace(regex.length() - 1, regex.length(), ")");
        smiliesPattern = Pattern.compile(regex.toString());
    }

    /**
     * Converts all smilies in a string to graphical smilies.
     *
     * @param text  A Spannable with smilies.
     */
    public static void setSpan(Spannable text, Context context)
    {
        Matcher m = smiliesPattern.matcher(text);

        while (m.find()) {
            Drawable smilie = ContextCompat.getDrawable(context, mappings.get(m.group(1)));
            smilie.setBounds(0, 0, smilie.getIntrinsicWidth(), smilie.getIntrinsicHeight());
            ImageSpan span = new ImageSpan(smilie, ImageSpan.ALIGN_BOTTOM);
            text.setSpan(span, m.start(), m.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    /**
     * Converts all smilies in a string to graphical smilies.
     *
     * @param text  A Spannable with smilies.
     * @return      A Spannable with graphical smilies.
     */
    public static Spannable toSpannable(Spannable text, Context context)
    {
        setSpan(text, context);
        return text;
    }

    /**
     * Converts all smilies in a string to graphical smilies.
     *
     * @param text  A string with smilies.
     * @return      A Spannable with graphical smilies.
     */
    public static Spannable toSpannable(String text, Context context)
    {
        return toSpannable(new SpannableString(text), context);
    }
}
