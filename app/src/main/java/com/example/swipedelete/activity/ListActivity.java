package com.example.swipedelete.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.swipedelete.R;
import com.example.swipedelete.adapter.ListAdapter;
import com.example.swipedelete.bean.TestBean;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private List<TestBean> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        data();
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ListAdapter(this, lists));
    }


    private void data() {
        lists = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TestBean bean = new TestBean();
            bean.setName("name：" + i);
            bean.setImgRes(R.mipmap.ic_launcher);
            lists.add(bean);
        }
    }
}
