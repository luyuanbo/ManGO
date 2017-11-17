package com.lv.mama.lvv.sort.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lv.mama.lvv.R;
import com.lv.mama.lvv.sort.bean.KindBean;

import java.util.List;


/**
 * Created by admin on 2017/11/14.
 */

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.MyViewHolder> {
    List<KindBean.DataBean> data;
    Context context;
    private OnItemClicks onItemClicks;
    public interface OnItemClicks{
        void itemclick(int position, View view);
    }

    public void setOnItemClicks(OnItemClicks onItemClicks) {
        this.onItemClicks = onItemClicks;
    }

    public LeftAdapter(List<KindBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder=new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.type_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.left.setText(data.get(position).getName());
        if (onItemClicks!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    onItemClicks.itemclick(layoutPosition,holder.itemView);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView left;
        public MyViewHolder(View itemView) {
            super(itemView);
            left= (TextView) itemView.findViewById(R.id.tv_type);
        }
    }


}
