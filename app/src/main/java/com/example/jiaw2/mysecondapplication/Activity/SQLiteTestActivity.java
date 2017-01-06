package com.example.jiaw2.mysecondapplication.Activity;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import com.example.jiaw2.mysecondapplication.R;
import com.example.jiaw2.mysecondapplication.bean.Person;
import com.example.jiaw2.mysecondapplication.db.DBManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiaw2 on 2017/1/3.
 */
public class SQLiteTestActivity extends Activity {

    private DBManager dbManager;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_test_layout);

        listView = (ListView) findViewById(R.id.sqlite_listview);
        dbManager = new DBManager(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDB();
    }

    public void add(View view) {
        ArrayList<Person> persons = new ArrayList<Person>();

        Person person1 = new Person("Ella", 22, "lively girl");
        Person person2 = new Person("Jenny", 22, "beautiful girl");
        Person person3 = new Person("Jessica", 23, "sexy girl");
        Person person4 = new Person("Kelly", 23, "hot baby");
        Person person5 = new Person("Jane", 25, "a pretty woman");

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);
        persons.add(person5);

        dbManager.add(persons);
    }

    public void update(View view) {
        Person person = new Person();
        person.name = "Jane";
        person.age = 30;
        dbManager.updateAge(person);
    }

    public void delete(View view) {
        Person person = new Person();
        person.age = 30;
        dbManager.deleteOldPerson(person);
    }

    public void query(View view) {
        List<Person> persons = dbManager.query();
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (Person person : persons) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", person.name);
            map.put("info", person.age + " years old, " + person.info);
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, list, android.R.layout.simple_list_item_2,
                new String[]{"name", "info"}, new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);
    }

    public void queryTheCursor(View view) {
        Cursor c = dbManager.queryTheCursor();
        startManagingCursor(c); //托付给activity根据自己的生命周期去管理Cursor的生命周期
        CursorWrapper cursorWrapper = new CursorWrapper(c) {
            @Override
            public String getString(int columnIndex) {
                //将简介前加上年龄
                if (getColumnName(columnIndex).equals("info")) {
                    int age = getInt(getColumnIndex("age"));
                    return age + " years old, " + super.getString(columnIndex);
                }
                return super.getString(columnIndex);
            }
        };
        //确保查询结果中有"_id"列
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                cursorWrapper, new String[]{"name", "info"}, new int[]{android.R.id.text1, android.R.id.text2});
        ListView listView = (ListView) findViewById(R.id.sqlite_listview);
        listView.setAdapter(adapter);
    }
}
