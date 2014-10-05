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
package org.yaaic.view;

import org.yaaic.R;
import org.yaaic.activity.ConversationActivity;
import org.yaaic.activity.MessageActivity;
import org.yaaic.adapter.MessageListAdapter;
import org.yaaic.model.Extra;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * A customized ListView for Messages
 *
 * @author Sebastian Kaspari <sebastian@yaaic.org>
 */
public class MessageListView extends ListView
{
    /**
     * Create a new MessageListView
     *
     * @param context
     */
    public MessageListView(Context context)
    {
        super(context);

        setOnItemLongClickListener(messageListener);

        setDivider(null);

        setCacheColorHint(0x000000);
        setVerticalFadingEdgeEnabled(false);
        setBackgroundResource(R.drawable.conversation_background);
        setScrollBarStyle(SCROLLBARS_OUTSIDE_INSET);

        // Scale padding by screen density
        float density = context.getResources().getDisplayMetrics().density;
        int padding = (int) (5 * density);
        setPadding(padding, padding, padding, padding);

        setTranscriptMode(TRANSCRIPT_MODE_NORMAL);
    }

    /**
     * Get the adapter of this MessageListView
     * (Helper to avoid casting)
     *
     * @return The MessageListAdapter
     */
    @Override
    public MessageListAdapter getAdapter()
    {
        return (MessageListAdapter) super.getAdapter();
    }

    private ListView.OnItemLongClickListener messageListener = new ListView.OnItemLongClickListener() {
        public boolean onItemLongClick(AdapterView<?> group, View view, int position, long id) {
            MessageListAdapter adapter = (MessageListAdapter) group.getAdapter();

            Intent intent = new Intent(group.getContext(), MessageActivity.class);
            intent.putExtra(Extra.MESSAGE, adapter.getItem(position));
            ((Activity)group.getContext()).startActivityForResult(intent, ConversationActivity.REQUEST_CODE_MESSAGE);

            return true;
        }
    };
}
