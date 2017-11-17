package com.lv.mama.lvv.sort.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.lv.mama.lvv.R;
import com.lv.mama.lvv.details.DetailsActivity;
import com.lv.mama.lvv.details.bean.product;
import com.lv.mama.lvv.sort.bean.RightBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


/**
 * Created by admin on 2017/11/14.
 */

public class GridAdapter extends BaseAdapter{
    List<RightBean.DataBean.ListBean> data;
    Context context;

    public GridAdapter(List<RightBean.DataBean.ListBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=View.inflate(context, R.layout.type_grid_item,null);
            holder.button= (Button) convertView.findViewById(R.id.tv_gv_type);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.button.setText(data.get(position).getName());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DetailsActivity.class));
                int pscid = data.get(position).getPscid();

                Toast.makeText(context,pscid+"", Toast.LENGTH_SHORT).show();
                //事件发布者发布事件
                EventBus.getDefault().postSticky(new product(pscid));

            }
        });
        return convertView;
    }
    class ViewHolder{
        private Button button;
    }
}
