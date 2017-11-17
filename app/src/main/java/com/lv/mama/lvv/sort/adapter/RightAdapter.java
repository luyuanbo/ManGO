package com.lv.mama.lvv.sort.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lv.mama.lvv.R;
import com.lv.mama.lvv.sort.bean.RightBean;
import com.lv.mama.lvv.sort.view.MyGridView;

import java.util.List;


/**
 * Created by admin on 2017/11/14.
 */

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.MyViewHolder> {
    List<RightBean.DataBean> data;
    Context context;


    public RightAdapter(List<RightBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder=new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.typeson_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.right.setText(data.get(position).getName());
        holder.gridView.setAdapter(new GridAdapter(data.get(position).getList(),context));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView right;
        private MyGridView gridView;
        public MyViewHolder(View itemView) {
            super(itemView);
            right= (TextView) itemView.findViewById(R.id.tv_type);
            gridView= (MyGridView) itemView.findViewById(R.id.type_son);
        }
    }
}
