package com.lv.mama.lvv.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.lv.mama.lvv.R;
import com.lv.mama.lvv.sort.adapter.MyAdapter_left;
import com.lv.mama.lvv.sort.adapter.MyAdapter_right;
import com.lv.mama.lvv.sort.bean.DataleftBean;
import com.lv.mama.lvv.sort.bean.DatarightBean;
import com.lv.mama.lvv.sort.presenter.SortPresenter;
import com.lv.mama.lvv.sort.view.ISortView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by 卢总 on 2017/11/9.
 */

public class Fragment_sort extends Fragment implements ISortView{

    @BindView(R.id.type_rvleft)
    RecyclerView rv_left;
    @BindView(R.id.type_rvright)
    RecyclerView rv_right;
    Unbinder unbinder;

    String Sortlefturl="mobile/index.php?act=goods_class";
    private SortPresenter sortPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sort, null);
        unbinder = ButterKnife.bind(this, view);

        sortPresenter = new SortPresenter(this);
        //得到左边数据
        sortPresenter.setSortLeftUrl(Sortlefturl);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        //得到WindowManager
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        //得到屏幕宽
        int width = display.getWidth();
        //将RecyclerView宽设置为屏幕宽的1/5
        params.width = width * 1 / 4;
        rv_left.setLayoutParams(params);
        //得到RecyclerView布局管理器
        LinearLayoutManager leftLayoutManager = new LinearLayoutManager(getActivity());
        //RecyclerView设置布局管理器
        rv_left.setLayoutManager(leftLayoutManager);
        //得到RecyclerView布局管理器
        LinearLayoutManager rightLayoutManager = new LinearLayoutManager(getActivity());
        //RecyclerView设置布局管理器
        rv_right.setLayoutManager(rightLayoutManager);
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 设置左边的数据
     * @param class_list
     */
    @Override
    public void getleft(final List<DataleftBean.DatasBean.ClassListBean> class_list) {
        final MyAdapter_left myAdapter_left = new MyAdapter_left(getActivity(), class_list);
        //子条目点击监听
        myAdapter_left.setRecycleViewItemClickListener(new MyAdapter_left.OnRecycleViewItemClickListener() {
            @Override
            public void recycleViewItemClickListener(int position, View view, RecyclerView.ViewHolder viewHolder) {

                myAdapter_left.setTagPosition(position);
                myAdapter_left.notifyDataSetChanged();

                sortPresenter.setSortRightUrl(Sortlefturl,class_list.get(position).getGc_id());
            }
        });
        rv_left.setAdapter(myAdapter_left);
    }

    /**
     * 设置右边得数据
     * @param class_right
     */
    @Override
    public void getRight(List<DatarightBean.DatasBean.ClassListBean> class_right) {
        MyAdapter_right myAdapter_right = new MyAdapter_right(getActivity(), class_right);
        rv_right.setAdapter(myAdapter_right);
    }


}
