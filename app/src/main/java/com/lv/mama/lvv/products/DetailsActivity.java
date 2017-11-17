package com.lv.mama.lvv.products;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lv.mama.lvv.R;
import com.lv.mama.lvv.cart.Bean.CartBean;
import com.lv.mama.lvv.products.bean.DetailsBean;
import com.lv.mama.lvv.products.bean.Xproduct;
import com.lv.mama.lvv.products.common.PlayerManager;
import com.lv.mama.lvv.products.widget.media.IjkVideoView;
import com.lv.mama.lvv.system.MyApplication;
import com.lv.mama.lvv.utils.Api;
import com.lv.mama.lvv.utils.ApiServer;
import com.lv.mama.lvv.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DetailsActivity extends AppCompatActivity implements PlayerManager.PlayerStateListener {


    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.video_view)
    IjkVideoView videoView;

    private String pid;

    private String url4 = "http://ips.ifeng.com/video19.ifeng.com/video09/2014/06/16/1989823-102-086-0009.mp4";
    private PlayerManager player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        //注册事件
        EventBus.getDefault().register(this);
        Map<String, String> map = new HashMap<>();
        map.put("pid", pid);
        Retrofit build = new Retrofit.Builder().baseUrl(Api.TYPE_SORT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        ApiServer apishopservice = build.create(ApiServer.class);
        Observable<DetailsBean> details = apishopservice.getDetails("product/getProductDetail", map);
        details.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailsBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("onCompleted", "成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("33333", "1111111");
                    }

                    @Override
                    public void onNext(DetailsBean detailsBean) {
                        int price = detailsBean.getData().getPrice();
                        textView3.setText(detailsBean.getData().getSubhead());
                        textView4.setText(detailsBean.getData().getPrice());
                        String images = detailsBean.getData().getImages();
                        String[] split = images.split("[|]");
                        String[] split1 = split[0].split("[!]");
                        Log.d("onNext6666", price + "");
                    }
                });

        initPlayer();

    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void ononMoonStickyEvent(Xproduct xproduct) {
        pid = xproduct.getPid();
        Log.d("22222", pid);

    }

    private void initPlayer() {

        //初始化播放器
        player = new PlayerManager(this);
        player.setFullScreenOnly(true);
        player.setScaleType(PlayerManager.SCALETYPE_FILLPARENT);
        player.playInFullScreen(true);
        player.setPlayerStateListener(this);
        player.play(url4);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (player.gestureDetector.onTouchEvent(event))
            return true;
        return super.onTouchEvent(event);
    }

    @Override
    public void onComplete() {

    }

    @Override
    protected void onStop() {
        super.onStop();
        player.stop();
    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onPlay() {

    }

    @OnClick({R.id.button3, R.id.button4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button3:

                break;
            case R.id.button4:
           int  uid= (int) SPUtils.get(MyApplication.getmContext(), "uid",1035);
                Map<String, String> map = new HashMap<>();
                map.put("pid", pid);
                map.put("uid",uid+"");
                Log.d("888888888",map.toString());
                Retrofit build = new Retrofit.Builder().baseUrl(Api.TYPE_SORT)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
                ApiServer apishopservice = build.create(ApiServer.class);
                Observable<CartBean> getchuan = apishopservice.getCart("product/addCart", map);
                getchuan.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<CartBean>() {
                                       @Override
                                       public void onCompleted() {

                                       }

                                       @Override
                                       public void onError(Throwable e) {

                                       }

                                       @Override
                                       public void onNext(CartBean cartBean) {

                                           String msg = cartBean.getMsg();
                                           if ("加购成功".equals(msg))
                                           {
                                               Toast.makeText(DetailsActivity.this,"成功了",Toast.LENGTH_SHORT).show();
                                           }
                                       }
                                   }
                        );

                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }
}
