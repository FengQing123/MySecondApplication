package com.example.jiaw2.mysecondapplication.Activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.jiaw2.mysecondapplication.R;
import com.example.jiaw2.mysecondapplication.util.LicenseKeyboardUtil;

/**
 * Created by jiaw2 on 2016/10/17.
 */
public class LicenseKeyboardActivity extends Activity implements View.OnClickListener{
    public static final String INPUT_LICENSE_COMPLETE = "me.kevingo.licensekeyboard.input.comp";
    public static final String INPUT_LICENSE_KEY = "LICENSE";

    private EditText inputbox1, inputbox2,
            inputbox3, inputbox4,
            inputbox5, inputbox6, inputbox7;
    private LinearLayout boxesContainer;
    private Button btn_add_car;
    private LicenseKeyboardUtil keyboardUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.license_keyboard);
        inputbox1 = (EditText) this.findViewById(R.id.et_car_license_inputbox1);
        inputbox2 = (EditText) this.findViewById(R.id.et_car_license_inputbox2);
        inputbox3 = (EditText) this.findViewById(R.id.et_car_license_inputbox3);
        inputbox4 = (EditText) this.findViewById(R.id.et_car_license_inputbox4);
        inputbox5 = (EditText) this.findViewById(R.id.et_car_license_inputbox5);
        inputbox6 = (EditText) this.findViewById(R.id.et_car_license_inputbox6);
        inputbox7 = (EditText) this.findViewById(R.id.et_car_license_inputbox7);
        btn_add_car = (Button) this.findViewById(R.id.btn_add_car);
        btn_add_car.setOnClickListener(this);
        boxesContainer = (LinearLayout) this.findViewById(R.id.ll_car_license_inputbox_content);

        //输入车牌完成后的intent过滤器
        IntentFilter finishFilter = new IntentFilter(INPUT_LICENSE_COMPLETE);

        final BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String license = intent.getStringExtra(INPUT_LICENSE_KEY);
                if (license != null && license.length() > 0) {
                    boxesContainer.setVisibility(View.GONE);
                    if (keyboardUtil != null) {
                        keyboardUtil.hideKeyboard();
                    }

                    AlertDialog alertDialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(LicenseKeyboardActivity.this);
                    builder.setMessage("车牌号为:" + license);
                    alertDialog = builder.create();
                    alertDialog.setCancelable(true);
                    alertDialog.show();
                }
                LicenseKeyboardActivity.this.unregisterReceiver(this);
            }
        };
        this.registerReceiver(receiver, finishFilter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_car:
                boxesContainer.setVisibility(View.VISIBLE);
                btn_add_car.setVisibility(View.GONE);
                keyboardUtil = new LicenseKeyboardUtil(this, new EditText[]{inputbox1, inputbox2, inputbox3,
                        inputbox4, inputbox5, inputbox6, inputbox7});
                keyboardUtil.showKeyboard();
                break;
        }
    }

}
