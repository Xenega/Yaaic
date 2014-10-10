package org.yaaic.fragment;

import org.yaaic.R;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockDialogFragment;

public class JoinDialogFragment extends SherlockDialogFragment {

    public static JoinDialogFragment newInstance() {
        JoinDialogFragment f = new JoinDialogFragment();

        Bundle args = new Bundle();
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
        View v = inflater.inflate(R.layout.join, container, false);

        ((Button) v.findViewById(R.id.join)).setOnClickListener(joinClick);
        ((EditText) v.findViewById(R.id.channel)).setSelection(1);

        return v;
    }

    public final View.OnClickListener joinClick = new View.OnClickListener() {
        public void onClick(View v) {
//            Intent intent = new Intent();
//            intent.putExtra("channel", ((EditText) findViewById(R.id.channel)).getText().toString());
//            setResult(RESULT_OK, intent);
//            finish();
        }
    };
}
