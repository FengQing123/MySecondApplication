package com.example.jiaw2.mysecondapplication.Activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.jiaw2.mysecondapplication.R;
import com.example.jiaw2.mysecondapplication.fragment.AFragment;
import com.example.jiaw2.mysecondapplication.fragment.BFragment;

/**
 * Created by jiaw2 on 2016/10/20.
 */
public class FragmentContainActivity extends Activity implements AFragment.AFragment2ActListener {

    private TextView textView;
    private AFragment aFragment;
    private BFragment bFragment;
    private String TAG = FragmentContainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_contain);

        textView = (TextView) findViewById(R.id.id_tv);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        aFragment = AFragment.newInstance("来自Activity的值");
        transaction.replace(R.id.id_ll_left, aFragment);

        bFragment = BFragment.newInstance();
        transaction.replace(R.id.id_ll_right, bFragment);

        transaction.commit();
    }

    @Override
    public void AFragment_a(Object o) {
        textView.setText(o.toString());
        Log.e(TAG, "object==" + o);
        bFragment.CallBFragment(o);
    }
}
