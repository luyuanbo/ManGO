package com.lv.mama.lvv.mine.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lv.mama.lvv.MainActivity;
import com.lv.mama.lvv.R;
import com.lv.mama.lvv.bean.LoginBean;
import com.lv.mama.lvv.bean.StateBean;
import com.lv.mama.lvv.mine.presenter.LoginPresenter;
import com.lv.mama.lvv.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.login_btn)
    Button loginBtn;

    String url="user/login";
    private String uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.textView, R.id.register, R.id.login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textView:
                break;
            case R.id.register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.login_btn:
                uname = userName.getText().toString().trim();
                String pswd = password.getText().toString().trim();
                LoginPresenter loginPresenter = new LoginPresenter(this);
                loginPresenter.getUrl(url, uname,pswd);
                if ("".equals(uname) || "".equals(pswd)) {
                    Toast.makeText(LoginActivity.this, "不能为空哦~", Toast.LENGTH_SHORT).show();
                } else {
                   new LoginPresenter(this).getUrl(url, uname,pswd);

                }

                break;
        }
    }

    @Override
    public void getState(LoginBean loginBean) {
        String code = loginBean.getCode();
        String msg = loginBean.getMsg();
        if("0".equals(code)){
            //事件发布者发布事件
            EventBus.getDefault().postSticky(new StateBean(uname));
            SPUtils.put(LoginActivity.this,"state",code);
            int uid = loginBean.getData().getUid();
            SPUtils.put(LoginActivity.this,"uid",""+uid);

            startActivity(new Intent(LoginActivity.this,MainActivity.class));

        }else{
            Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
