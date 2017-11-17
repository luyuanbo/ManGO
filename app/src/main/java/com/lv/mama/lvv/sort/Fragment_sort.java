package com.lv.mama.lvv.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lv.mama.lvv.R;
import com.lv.mama.lvv.sort.adapter.LeftAdapter;
import com.lv.mama.lvv.sort.adapter.RightAdapter;
import com.lv.mama.lvv.sort.bean.KindBean;
import com.lv.mama.lvv.sort.bean.RightBean;
import com.lv.mama.lvv.sort.presenter.Kpresent;
import com.lv.mama.lvv.sort.view.Kview;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by 卢总 on 2017/11/9.
 */

public class Fragment_sort extends Fragment implements Kview {

    @BindView(R.id.left)
    RecyclerView left;
    @BindView(R.id.right)
    RecyclerView right;
    Unbinder unbinder;
    private Kpresent kpresent;
    private LeftAdapter leftAdapter;
    private RightAdapter rightadapter;
    private int count=1;
    private HashMap<String, String> rMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sort, null);
        unbinder = ButterKnife.bind(this, view);
        kpresent=new Kpresent(this);
        kpresent.getpleft("");
        rMap = new HashMap<>();
        rMap.put("cid","1");
        kpresent.getpright(rMap,"product/getProductCatagory");

        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void getleft(final List<KindBean.DataBean> data) {

        leftAdapter=new LeftAdapter(data,getActivity());
        left.setLayoutManager(new LinearLayoutManager(getActivity()));
        left.setAdapter(leftAdapter);
        leftAdapter.setOnItemClicks(new LeftAdapter.OnItemClicks() {
            @Override
            public void itemclick(int position, View view) {

                rMap.clear();
                int cid = data.get(position).getCid();
                rMap.put("cid",cid+"");
                kpresent.getpright(rMap,"product/getProductCatagory");
                Toast.makeText(getActivity(),cid+"",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void getright(List<RightBean.DataBean> data) {

        rightadapter=new RightAdapter(data,getActivity());
        right.setLayoutManager(new LinearLayoutManager(getActivity()));
        right.setAdapter(rightadapter);
    }
}
