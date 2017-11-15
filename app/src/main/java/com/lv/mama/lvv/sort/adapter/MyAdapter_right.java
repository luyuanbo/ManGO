package com.lv.mama.lvv.sort.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.lv.mama.lvv.R;
import com.lv.mama.lvv.sort.bean.DatarightBean;
import com.lv.mama.lvv.sort.bean.DateGridBean;
import com.lv.mama.lvv.utils.RetroFactorySort;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * autour: 樊彦龙
 * date: 2017/10/20 13:21
 * update: 2017/10/20
 */

public class MyAdapter_right extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<DatarightBean.DatasBean.ClassListBean> list;
    String url="mobile/index.php?act=goods_class";
    public MyAdapter_right(Context context, List<DatarightBean.DatasBean.ClassListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.typeson_item, parent, false);
        final MyLeftViewHolder leftViewHolder = new MyLeftViewHolder(view);
        return leftViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //设置种类标题
        final MyLeftViewHolder myHolder = new MyLeftViewHolder(holder.itemView);
        //设置标题
        myHolder.tv_left_type.setText(list.get(position).getGc_name());
        Observable<DateGridBean> gridUrl = RetroFactorySort.getInstance().getSortGrid(url+"&gc_id=" + list.get(position).getGc_id());
        gridUrl.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DateGridBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DateGridBean dateGridBean) {
                        myHolder.gv.setAdapter(new MyAdapter_TypeGridView(context,dateGridBean.getDatas().getClass_list()));
                    }
                });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyLeftViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_left_type;
        private GridView gv;
        public MyLeftViewHolder(View itemView) {
            super(itemView);
            tv_left_type = (TextView) itemView.findViewById(R.id.tv_type);
            gv = (GridView) itemView.findViewById(R.id.type_son);
        }
    }

    //public interface get

    //声明成员变量
    public OnRecycleViewItemClickListener recycleViewItemClickListener;

    //定义点击接口
    public interface OnRecycleViewItemClickListener{
        void recycleViewItemClickListener(int position, View view, RecyclerView.ViewHolder viewHolder);
    }

    //提供set方法
    public void setRecycleViewItemClickListener(OnRecycleViewItemClickListener recycleViewItemClickListener) {
        this.recycleViewItemClickListener = recycleViewItemClickListener;
    }
}
