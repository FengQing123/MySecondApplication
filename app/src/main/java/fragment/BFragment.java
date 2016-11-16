package fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class BFragment extends Fragment {

    private TextView tv;
    private ListView listView;
    SimpleAdapter simpleAdapter;
    List<Map<String, Object>> mapList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", "name" + i);
            mapList.add(map);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        tv = (TextView) view.findViewById(R.id.id_tv);
        listView = (ListView) view.findViewById(R.id.id_lv);
        simpleAdapter = new SimpleAdapter(getActivity(), mapList, android.R.layout.simple_list_item_1, new String[]{"name"}, new int[]{android.R.id.text1});
        listView.setAdapter(simpleAdapter);
        return view;
    }

    public static BFragment newInstance() {
        BFragment bFragment = new BFragment();
        return bFragment;
    }

    public void CallBFragment(Object o) {
        getNewData(o.toString());
        simpleAdapter.notifyDataSetChanged();
    }

    private void getNewData(String key) {
        mapList.clear();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", key + "name" + i);
            mapList.add(map);
        }
    }
}
