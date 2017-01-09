package com.example.jiaw2.mysecondapplication.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.jiaw2.mysecondapplication.R;
import com.example.jiaw2.mysecondapplication.api.Url;
import com.example.jiaw2.mysecondapplication.base.BaseActivity;
import com.example.jiaw2.mysecondapplication.util.ToolImage;
import com.example.jiaw2.mysecondapplication.util.UIHelper;

import butterknife.BindView;

/**
 * Created by jiaw2 on 2017/1/6.
 */
public class WidgetTestActivity extends BaseActivity {

    @BindView(R.id.plain_cb)
    CheckBox plain_cb;

    @BindView(R.id.bold_cb)
    CheckBox bold_cb;

    @BindView(R.id.serif_cb)
    CheckBox serif_cb;

    @BindView(R.id.get_view_btn)
    Button get_view_btn;

    @BindView(R.id.img_background)
    ImageView img_background;

    private String value;

    @Override
    public void initValue(Bundle bundle) {
        value = bundle.getString("value");
    }

    @Override
    public void initView(View view) {
        get_view_btn.setText(value);
        get_view_btn.setOnClickListener(this);
    }

    @Override
    public void initData() {
        ToolImage.UseGlideShowPicture(getActivity(), Url.imgUrl1, img_background);
    }

    @Override
    public int bindLayout() {
        return R.layout.widget_test_layout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_view_btn:
                String r = "";
                if (plain_cb.isChecked()) {
                    r = plain_cb.getText() + "";
                }
                if (bold_cb.isChecked()) {
                    r = r + "," + bold_cb.getText();
                }
                if (serif_cb.isChecked()) {
                    r = r + "," + serif_cb.getText();
                }
                UIHelper.ToastMessage(getActivity(), "r=" + r);
                break;
        }
    }
}
