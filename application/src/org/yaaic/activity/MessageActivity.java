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
package org.yaaic.activity;

import org.yaaic.R;
import org.yaaic.model.Extra;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

/**
 * Activity for single message view
 * 
 * @author Sebastian Kaspari <sebastian@yaaic.org>
 */
public class MessageActivity extends SherlockFragmentActivity
{
    private CharSequence message;
    
    /**
     * On create
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.message);

        message = getIntent().getCharSequenceExtra(Extra.MESSAGE);
        ((TextView) findViewById(R.id.message)).setText(message);

        startActionMode(actionModeCallback);
    }

    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() 
    {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) 
        {
            mode.setTitle(getString(R.string.mode_message_title));
            mode.setSubtitle(null);

            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.message, menu);

            setResult(Activity.RESULT_CANCELED, null);

            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) 
        {
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) 
        {
            Intent data = new Intent();
            data.putExtra(Extra.MESSAGE, message);
            data.putExtra(Extra.ACTION, item.getItemId());
            setResult(Activity.RESULT_OK, data);

            finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) 
        {
            finish();
        }
    };
}
