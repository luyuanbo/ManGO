package com.lv.mama.lvv.home.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lv.mama.lvv.R;
import com.lv.mama.lvv.bean.HomeBean;
import com.lv.mama.lvv.utils.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;


public class XRAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    HomeBean.DataBean data;
    Context mcontext;
    ArrayList mlist;
    ArrayList bannerlist;



    //枚举类型
    private enum Item_Type {

        Typeone, Typetwo, Typethree, Typefour, Typefive

    }

    //构造方法
    public XRAdapter(HomeBean.DataBean data, Context mcontext) {
        this.data = data;
        this.mcontext = mcontext;
    }

    /**
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType :不同ItemView的类型加载布局
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Item_Type.Typeone.ordinal()) {
            View mView = LayoutInflater.from(mcontext).inflate(R.layout.recycle_item_a, null);
            ViewHolderA viewHolder = new ViewHolderA(mView);
            return viewHolder;

        } else if (viewType == Item_Type.Typetwo.ordinal()) {

            View mView = LayoutInflater.from(mcontext).inflate(R.layout.recycle_item_b, null);
            ViewHolderB viewHolder = new ViewHolderB(mView);
            return viewHolder;
        } else if (viewType == Item_Type.Typethree.ordinal()) {
            View mView = LayoutInflater.from(mcontext).inflate(R.layout.recycle_item_c, null);
            ViewHolderC viewHolder = new ViewHolderC(mView);
            return viewHolder;
        } else if (viewType == Item_Type.Typefour.ordinal()) {
            View mView = LayoutInflater.from(mcontext).inflate(R.layout.recycle_item_d, null);
            ViewHolderD viewHolder = new ViewHolderD(mView);
            return viewHolder;
        } else if (viewType == Item_Type.Typefive.ordinal()) {
            View mView = LayoutInflater.from(mcontext).inflate(R.layout.recycle_item_e, null);
            ViewHolderE viewHolder = new ViewHolderE(mView);
            return viewHolder;
        }
        return null;
    }

    /**
     * 绑定数据：可以直接拿到已经绑定控件的Viewholder对象
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //判断条目类型
        if (holder instanceof ViewHolderA) {
            mlist = new ArrayList();
            for (int i = 0; i < data.getAd1().size(); i++) {
                mlist.add(data.getAd1().get(i).getImage());
            }
            //设置banner图片加载器
            ((ViewHolderA) holder).mbanner.setImageLoader(new GlideImageLoader());
            ((ViewHolderA) holder).mbanner.setImages(mlist);
            ((ViewHolderA) holder).mbanner.start();

        } else if (holder instanceof ViewHolderB) {

            //给子布局的控件传值
            ((ViewHolderB) holder).myImageView.setImageURI(data.getAd5().get(0).getImage());
            ((ViewHolderB) holder).homeClassifiledText1.setText(data.getAd5().get(0).getTitle());
            ((ViewHolderB) holder).myImageView2.setImageURI(data.getAd5().get(1).getImage());
            ((ViewHolderB) holder).homeClassifiledText2.setText(data.getAd5().get(1).getTitle());
            ((ViewHolderB) holder).myImageView3.setImageURI(data.getAd5().get(2).getImage());
            ((ViewHolderB) holder).homeClassifiledText3.setText(data.getAd5().get(2).getTitle());
            ((ViewHolderB) holder).myImageView4.setImageURI(data.getAd5().get(3).getImage());
            ((ViewHolderB) holder).homeClassifiledText4.setText(data.getAd5().get(3).getTitle());
        } else if (holder instanceof ViewHolderC) {

            ((ViewHolderC) holder).countdownView.start(555555555);
            ((ViewHolderC) holder).tvc.setText("热门活动");
            ((ViewHolderC) holder).homeQgImg.setImageURI(data.getActivityInfo().getActivityInfoList().get(0).getActivityImg());
            ((ViewHolderC) holder).homeQgImg2.setImageURI(data.getActivityInfo().getActivityInfoList().get(1).getActivityImg());

        } else if (holder instanceof ViewHolderD) {
            bannerlist = new ArrayList<>();
            for (int i = 0; i < data.getSubjects().size(); i++) {
                bannerlist.add(data.getSubjects().get(i).getImage());
            }
            ((ViewHolderD) holder).reItemdText.setText("热门专题");
            ((ViewHolderD) holder).itemdMybanner.setImageLoader(new GlideImageLoader());

            ((ViewHolderD) holder).itemdMybanner.setImages(bannerlist);
            ((ViewHolderD) holder).itemdMybanner.start();

        } else if (holder instanceof ViewHolderE) {

            //设置layoutManager
            ((ViewHolderE) holder).recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            // 设置item动画
            ((ViewHolderE) holder).recyclerView.setItemAnimator(new DefaultItemAnimator());
            //设置item之间的间隔
            SpacesItemDecoration decoration=new SpacesItemDecoration(16);
           ((ViewHolderE) holder).recyclerView.addItemDecoration(decoration);
            List<HomeBean.DataBean.DefaultGoodsListBean> defaultGoodsList = data.getDefaultGoodsList();
            ((ViewHolderE) holder).recyclerView.setAdapter( new MasonryAdapter(mcontext,defaultGoodsList));

        }

    }

    //得到item的数量
    @Override
    public int getItemCount() {
        return 5;
    }

    //得到viewType类型 返回值赋值给onCreateViewHolder的参数
    @Override
    public int getItemViewType(int position) {
        // return super.getItemViewType(position);

        //根据下标判断类型返回布局
        if (position == 0) {
            return Item_Type.Typeone.ordinal();
        } else if (position == 1) {
            return Item_Type.Typetwo.ordinal();
        } else if (position == 2) {
            return Item_Type.Typethree.ordinal();
        } else if (position == 3) {
            return Item_Type.Typefour.ordinal();
        } else if (position == 4) {
            return Item_Type.Typefive.ordinal();
        }
        return -1;
    }

    class ViewHolderA extends RecyclerView.ViewHolder {
        public Banner mbanner;

        public ViewHolderA(View itemView) {
            super(itemView);
            mbanner = (Banner) itemView.findViewById(R.id.mybanner);
        }
    }


    class ViewHolderB extends RecyclerView.ViewHolder {
        @BindView(R.id.my_image_view)
        SimpleDraweeView myImageView;
        @BindView(R.id.my_image_view2)
        SimpleDraweeView myImageView2;
        @BindView(R.id.my_image_view3)
        SimpleDraweeView myImageView3;
        @BindView(R.id.my_image_view4)
        SimpleDraweeView myImageView4;
        @BindView(R.id.home_classifiled_text4)
        TextView homeClassifiledText4;
        @BindView(R.id.home_classifiled_text1)
        TextView homeClassifiledText1;
        @BindView(R.id.home_classifiled_text2)
        TextView homeClassifiledText2;
        @BindView(R.id.home_classifiled_text3)
        TextView homeClassifiledText3;

        public ViewHolderB(View itemView) {
            super(itemView);
            //初始化ButterKnife
            ButterKnife.bind(this, itemView);

        }
    }

    class ViewHolderC extends RecyclerView.ViewHolder {

        @BindView(R.id.tvc)
        TextView tvc;
        @BindView(R.id.countdownView)
        CountdownView countdownView;
        @BindView(R.id.home_qg_img)
        SimpleDraweeView homeQgImg;
        @BindView(R.id.home_qg_img2)
        SimpleDraweeView homeQgImg2;

        public ViewHolderC(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }

    class ViewHolderD extends RecyclerView.ViewHolder {

        @BindView(R.id.itemd_mybanner)
        Banner itemdMybanner;
        @BindView(R.id.re_itemd_text)
        TextView reItemdText;

        public ViewHolderD(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewHolderE extends RecyclerView.ViewHolder {

        @BindView(R.id.id_recyclerview)
        RecyclerView recyclerView;

        public ViewHolderE(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
