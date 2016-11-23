package com.example.swipedelete.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.swipedelete.R;
import com.example.swipedelete.adapter.RecyclerAdapter;
import com.example.swipedelete.bean.TestBean;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private List<TestBean> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_rrecycler_view);
        data();
        RecyclerView listView = (RecyclerView) findViewById(R.id.rv);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(new RecyclerAdapter(this, lists));
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
