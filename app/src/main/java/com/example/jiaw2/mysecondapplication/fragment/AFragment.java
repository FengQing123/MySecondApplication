package com.example.jiaw2.mysecondapplication.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.jiaw2.mysecondapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiaw2 on 2016/10/20.
 */
public class AFragment extends Fragment {

    private static final String PARAM = "param";
    private String params;
    private TextView tv;
    private ListView listView;
    List<Map<String, Object>> mapList = new ArrayList<>();
    private AFragment2ActListener listener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            params = getArguments().getString(PARAM);
        }

        for (int i = 0; i < 25; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", "pa_name" + i);
            mapList.add(map);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        tv = (TextView) view.findViewById(R.id.id_tv);
        tv.setText(tv.getText() + "--" + params);
        listView = (ListView) view.findViewById(R.id.id_lv);

        listView = (ListView) view.findViewById(R.id.id_lv);
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), mapList, android.R.layout.simple_list_item_1, new String[]{"name"}, new int[]{android.R.id.text1});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                passValue(position);
            }
        });
        return view;
    }

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        OnAttachToContext(context);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            OnAttachToContext(activity);
        }
    }

    public void OnAttachToContext(Context context) {
        Log.e("--------------", "Afragment attach");
        if (context instanceof AFragment2ActListener) {
            listener = (AFragment2ActListener) context;
        } else {
            throw new IllegalArgumentException("FragmentContainActivity must implements AFragment2ActListener");
        }
    }

    public static AFragment newInstance(String params) {
        AFragment aFragment = new AFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PARAM, params);
        aFragment.setArguments(bundle);
        return aFragment;
    }

    public interface AFragment2ActListener {
        void AFragment_a(Object o);
    }

    public void passValue(int pos) {
        listener.AFragment_a(pos);
    }
}
