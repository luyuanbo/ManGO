package com.lv.mama.lvv.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lv.mama.lvv.R;
import com.lv.mama.lvv.bean.HomeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 卢总 on 2017/10/13.
 */

public class MasonryAdapter extends RecyclerView.Adapter<MasonryAdapter.MasonryView>{




    /**
     * ItemClick的回调接口
     *
     * @author zhy
     */
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    private Context context;
    private List<HomeBean.DataBean.DefaultGoodsListBean> defaultGoodsList;

    public MasonryAdapter(Context context, List<HomeBean.DataBean.DefaultGoodsListBean> defaultGoodsList) {
        this.context = context;
        this.defaultGoodsList = defaultGoodsList;
    }


    @Override
    public MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {
        MasonryView masonryView = null;
        masonryView = new MasonryView(LayoutInflater.from(context).inflate(R.layout.masonry_item, parent, false));
        return masonryView;
    }

    @Override
    public void onBindViewHolder(final MasonryView masonryView, final int position) {

        masonryView.homeQgImg.setImageURI(defaultGoodsList.get(position).getGoods_img());
        masonryView.masonryItemTitle.setText(defaultGoodsList.get(position).getEfficacy());


        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            masonryView.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(masonryView.itemView, position);
                }
            });

            masonryView.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = masonryView.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(masonryView.itemView, pos);
                    return false;
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return defaultGoodsList.size();
    }


    class MasonryView extends RecyclerView.ViewHolder {

        @BindView(R.id.home_qg_img)
        SimpleDraweeView homeQgImg;
        @BindView(R.id.masonry_item_title)
        TextView masonryItemTitle;


        public MasonryView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }


}
