package com.lv.mama.lvv.products;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lv.mama.lvv.R;
import com.lv.mama.lvv.products.adapter.ListAdapter;
import com.lv.mama.lvv.products.bean.Product;
import com.lv.mama.lvv.products.bean.ProductsBean;
import com.lv.mama.lvv.products.bean.Xproduct;
import com.lv.mama.lvv.sort.adapter.LeftAdapter;
import com.lv.mama.lvv.utils.Api;
import com.lv.mama.lvv.utils.ApiServer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ProductsActivity extends AppCompatActivity {



    @BindView(R.id.pr_recyclerview)
    RecyclerView prRecyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        //注册事件
        EventBus.getDefault().register(ProductsActivity.this);
    }
        @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
        public void ononMoonStickyEvent (Product product){
            String pscid = product.getPscid();
            Map<String, String> map = new HashMap<>();
            map.put("pscid", pscid);
            map.put("page", "1");
            map.put("sort", "0");
            Log.d("fdfdfdfdfdf", map.toString());
            Retrofit build = new Retrofit.Builder().baseUrl(Api.TYPE_SORT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
            ApiServer apishopservice = build.create(ApiServer.class);
            Observable<ProductsBean> products = apishopservice.getProducts("product/getProducts", map);
            products.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ProductsBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("33333", "1111111");
                        }

                        @Override
                        public void onNext(final ProductsBean productsBean) {

                            ListAdapter listAdapter = new ListAdapter(ProductsActivity.this, productsBean.getData());
                            listAdapter.setOnItemClicks(new LeftAdapter.OnItemClicks() {
                                @Override
                                public void itemclick(int position, View view) {
                                    Intent intent=new Intent(ProductsActivity.this, DetailsActivity.class);
                                    EventBus.getDefault().postSticky(new Xproduct(productsBean.getData().get(position).getPid()+""));
                                    Toast.makeText(ProductsActivity.this, productsBean.getData().get(position).getPid()+"", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);

                                }
                            });
                            prRecyclerview.setLayoutManager(new LinearLayoutManager(ProductsActivity.this));//list
                            prRecyclerview.setAdapter(listAdapter);
                        }


                    });
        }
        @Override
        public void onDestroy () {
            super.onDestroy();
            //取消注册事件
            EventBus.getDefault().unregister(this);
        }
    }
