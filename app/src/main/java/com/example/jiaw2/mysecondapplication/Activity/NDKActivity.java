package com.example.jiaw2.mysecondapplication.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jiaw2.mysecondapplication.R;
import com.example.jiaw2.mysecondapplication.util.NdkJniUtils;

/**
 * Created by jiaw2 on 2016/11/21.
 */
public class NDKActivity extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ndk_layout);

        textView = (TextView) findViewById(R.id.text);
        NdkJniUtils jni = new NdkJniUtils();

        textView.setText(jni.getCLanguageString());
    }
}
