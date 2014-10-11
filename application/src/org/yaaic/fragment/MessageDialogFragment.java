package org.yaaic.fragment;

import org.yaaic.R;
import org.yaaic.model.Extra;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockDialogFragment;

public class MessageDialogFragment extends SherlockDialogFragment {

    public static MessageDialogFragment newInstance(CharSequence message) {
        MessageDialogFragment f = new MessageDialogFragment();

        Bundle args = new Bundle();
        args.putCharSequence(Extra.MESSAGE, message);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.message, container, false);

        ((TextView) v.findViewById(R.id.message)).setText(
                getArguments().getCharSequence(Extra.MESSAGE)
        );

        return v;
    }
}
