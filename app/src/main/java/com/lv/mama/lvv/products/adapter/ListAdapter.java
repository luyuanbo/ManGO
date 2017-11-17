package com.lv.mama.lvv.products.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lv.mama.lvv.R;
import com.lv.mama.lvv.products.bean.ProductsBean;
import com.lv.mama.lvv.sort.adapter.LeftAdapter;

import java.util.List;

/**
 * Created by chentong on 2017/11/17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder1>{
    Context context;

    List<ProductsBean.DataBean> data;

    private LeftAdapter.OnItemClicks onItemClicks;
    public interface OnItemClicks{
        void itemclick(int position, View view);
    }

    public void setOnItemClicks(LeftAdapter.OnItemClicks onItemClicks) {
        this.onItemClicks = onItemClicks;
    }

    public ListAdapter(Context context, List<ProductsBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder1 holder = new MyViewHolder1(LayoutInflater.from(
                context).inflate(R.layout.pr_list_item, parent,
                false));
        return holder;
    }
    public void onBindViewHolder(final MyViewHolder1 holder, final int position)
    {
        holder.tv.setText(data.get(position).getTitle());
        String images = data.get(position).getImages();
        String[] split = images.split("[|]");
        String[] split1 = split[0].split("[!]");
        Uri uri= Uri.parse(split1[0]);
        holder.draweeView.setImageURI(uri);//设置给Fresco
        if (onItemClicks!=null){
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClicks.itemclick(position,holder.itemView);
                }
            });
        }


    }
    @Override
    public int getItemCount()
    {
        return data.size();
    }


    class MyViewHolder1 extends RecyclerView.ViewHolder
    {
        TextView tv;
        public SimpleDraweeView draweeView;
        public MyViewHolder1(View view)
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.pr_text);
            draweeView=(SimpleDraweeView) view.findViewById(R.id.pr_img);

        }
    }
}
