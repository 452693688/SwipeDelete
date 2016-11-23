package com.example.swipedelete.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swipedelete.R;
import com.example.swipedelete.bean.TestBean;
import com.example.swipedelete.view.SwipeMenuLayout;

import java.util.List;

/**
 * 通用ListViewAdapter
 * Created by zhangxutong .
 * Date: 16/03/11
 */
public class ListAdapter extends BaseAdapter {
    protected Context mContext;
    protected List<TestBean> mDatas;

    public ListAdapter(Context context, List<TestBean> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    @Override
    public TestBean getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent,
                    false);
            viewHolder.ontentLl = (LinearLayout) convertView.findViewById(R.id.ll_content);
            viewHolder.iconIv = (ImageView) convertView.findViewById(R.id.listview_iv);
            viewHolder.nameTv = (TextView) convertView.findViewById(R.id.listview_tv);
            viewHolder.bzBtn = (Button) convertView.findViewById(R.id.btn_zd);
            viewHolder.deleteBtn = (Button) convertView.findViewById(R.id.btn_delete);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ((SwipeMenuLayout) convertView).setIos(true).setLeftSwipe(position % 2 == 0 ? true : false);

        TestBean testBean = mDatas.get(position);
        viewHolder.nameTv.setText(testBean.getName());
        viewHolder.iconIv.setImageResource(testBean.getImgRes());
        viewHolder.ontentLl.setOnClickListener(new Click(position));
        viewHolder.bzBtn.setOnClickListener(new Click(position));
        viewHolder.deleteBtn.setOnClickListener(new Click(position));
        return convertView;
    }

    class ViewHolder {
        ImageView iconIv;
        TextView nameTv;
        Button bzBtn;
        Button deleteBtn;
        LinearLayout ontentLl;
    }

    class Click implements View.OnClickListener {
        private int index;

        public Click(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_content:
                    Toast.makeText(mContext, "点击了：" + index, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_zd:
                    Toast.makeText(mContext, "点击了置顶选项", Toast.LENGTH_SHORT).show();
                    //在ListView里，点击侧滑菜单上的选项时，如果想让侧滑菜单同时关闭，调用这句话
                    ((SwipeMenuLayout) v.getParent()).quickClose();

                    break;
                case R.id.btn_delete:
                    Toast.makeText(mContext, "点击了删除选项", Toast.LENGTH_SHORT).show();
                    //在ListView里，点击侧滑菜单上的选项时，如果想让侧滑菜单同时关闭，调用这句话
                    ((SwipeMenuLayout) v.getParent()).quickClose();
                    //删除操作
                    mDatas.remove(index);
                    notifyDataSetChanged();
                    break;
            }
        }
    }


}

