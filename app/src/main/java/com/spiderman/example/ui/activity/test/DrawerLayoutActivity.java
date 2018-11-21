package com.spiderman.example.ui.activity.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.spiderman.example.R;
import com.spiderman.example.ui.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2018/5/20.
 */

public class DrawerLayoutActivity extends BaseActivity {
    private ListView listView;

    private DrawerLayout drawerLayout;

    private TextView textView;
    @Override
    protected void loadLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_drawerlayout);
    }

    @Override
    protected void initView() {
        listView=(ListView) findViewById(R.id.v4_listview);
        drawerLayout=(DrawerLayout) findViewById(R.id.v4_drawerlayout);
        textView=(TextView) findViewById(R.id.v4_text);
    }

    @Override
    protected void loadData() {
        final List<String> list = new ArrayList<String>();
        list.add("网易");
        list.add("腾讯");
        list.add("新浪");
        list.add("搜狐");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(list.get(position));
                showDrawerLayout();
            }
        });
        drawerLayout.openDrawer(Gravity.LEFT);//侧滑打开  不设置则不会默认打开
    }

    @Override
    public Context getContext() {
        return null;
    }

    private void showDrawerLayout() {
        if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.openDrawer(Gravity.LEFT);
        } else {
            drawerLayout.closeDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
