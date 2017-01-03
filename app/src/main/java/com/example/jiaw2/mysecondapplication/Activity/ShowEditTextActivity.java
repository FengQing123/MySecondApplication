package com.example.jiaw2.mysecondapplication.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.jiaw2.mysecondapplication.R;

/**
 * Created by jiaw2 on 2016/10/17.
 */
public class ShowEditTextActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_text);
        EditText editText = (EditText) findViewById(R.id.edit_show);
        editText.setFocusable(true);
    }
}
