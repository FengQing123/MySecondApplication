package com.example.jiaw2.mysecondapplication.Activity;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jiaw2.mysecondapplication.R;
import com.example.jiaw2.mysecondapplication.util.AutoText;

/**
 * Created by jiaw2 on 2016/11/10.
 */
public class DefinedMarqueeActivity extends Activity {
    private AutoText auto_text;
    private TextView textView;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.defined_marquee);
        auto_text = (AutoText) findViewById(R.id.auto_text);
        textView = (TextView) findViewById(R.id.text);
    }

    public int getTextLength() {
        return textView.getText().length();
    }

    private int getTextWidth() {
        Paint paint = new Paint();
        String str = textView.getText().toString();
        return (int) paint.measureText(str);
    }

    public void scroll(View v) {
        Log.e("----", "text宽度==" + getTextWidth());
        i += 2;
        textView.scrollTo(i, 0);
        if (i >= getTextWidth()) {
            textView.setVisibility(View.GONE);
        }
    }

    public void start(View v) {
        auto_text.startScroll();
//      auto_text.startScroll();
    }

    public void stop(View v) {
        auto_text.stopScroll();
//      auto_text.stopScroll();
    }

    public void startFor0(View v) {
        auto_text.startFor0();
//      auto_text.startFor0();
    }
}
