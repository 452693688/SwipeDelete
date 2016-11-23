package com.example.swipedelete.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swipedelete.R;
import com.example.swipedelete.bean.TestBean;
import com.example.swipedelete.view.SwipeMenuLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    protected List<TestBean> mDatas = new ArrayList<>();
    private Context context;

    public RecyclerAdapter(Context context, List<TestBean> mDatas) {
        this.mDatas = mDatas;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent,
                false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((SwipeMenuLayout) holder.itemView).setIos(true).setLeftSwipe(position % 2 == 0 ? true : false);

        TestBean testBean = mDatas.get(position);
        holder.nameTv.setText(testBean.getName());
        holder.iconIv.setImageResource(testBean.getImgRes());
        holder.ontentLl.setOnClickListener(new Click(position));
        holder.bzBtn.setOnClickListener(new Click(position));
        holder.deleteBtn.setOnClickListener(new Click(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconIv;
        TextView nameTv;
        Button bzBtn;
        Button deleteBtn;
        LinearLayout ontentLl;

        public ViewHolder(View itemView) {
            super(itemView);
            ontentLl = (LinearLayout) itemView.findViewById(R.id.ll_content);
            iconIv = (ImageView) itemView.findViewById(R.id.listview_iv);
            nameTv = (TextView) itemView.findViewById(R.id.listview_tv);
            bzBtn = (Button) itemView.findViewById(R.id.btn_zd);
            deleteBtn = (Button) itemView.findViewById(R.id.btn_delete);
        }
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
                    Toast.makeText(context, "点击了：" + index, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_zd:
                    Toast.makeText(context, "点击了置顶选项", Toast.LENGTH_SHORT).show();
                    //在ListView里，点击侧滑菜单上的选项时，如果想让侧滑菜单同时关闭，调用这句话
                    ((SwipeMenuLayout) v.getParent()).quickClose();

                    break;
                case R.id.btn_delete:
                    Toast.makeText(context, "点击了删除选项", Toast.LENGTH_SHORT).show();
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
