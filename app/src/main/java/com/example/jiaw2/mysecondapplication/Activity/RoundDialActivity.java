package com.example.jiaw2.mysecondapplication.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jiaw2.mysecondapplication.R;
import com.example.jiaw2.mysecondapplication.widget.RoundDialView;
import com.example.jiaw2.mysecondapplication.widget.TrackDialView;

/**
 * Created by jiaw2 on 2016/12/7.
 */
public class RoundDialActivity extends Activity {

    private RoundDialView roundDialView;
    private EditText editText;
    private Button button;

    private TrackDialView trackDialView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rounddial_layout);

        trackDialView = (TrackDialView) findViewById(R.id.trackDial);
//        roundDialView = (RoundDialView) findViewById(R.id.my_view);

        editText = (EditText) findViewById(R.id.edit);
        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a;
                String s = editText.getText().toString();
                if (s.length() == 0) {
                    a = 0;
                } else {
                    a = Integer.valueOf(s);
                }
                trackDialView.setCurrentNumNameAnim(a, "驾驶指数");
            }
        });
    }
}
