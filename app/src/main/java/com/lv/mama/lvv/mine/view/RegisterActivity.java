package com.lv.mama.lvv.mine.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lv.mama.lvv.R;
import com.lv.mama.lvv.bean.LoginBean;
import com.lv.mama.lvv.mine.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements ILoginView{

    @BindView(R.id.re_Username)
    EditText reUsername;
    @BindView(R.id.re_PassWord)
    EditText rePassWord;
    @BindView(R.id.re_PassWord2)
    EditText rePassWord2;
    @BindView(R.id.re_email)
    EditText reEmail;
    @BindView(R.id.re_btn)
    Button reBtn;

    String url="user/reg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.re_btn)
    public void onViewClicked() {
        String reUname = reUsername.getText().toString().trim();
        String repass = rePassWord.getText().toString().trim();
        String repass2 = rePassWord2.getText().toString().trim();
        String reemail = reEmail.getText().toString().trim();
        LoginPresenter loginPresenter = new LoginPresenter(this);
        loginPresenter.getUrl(url,reUname,repass);
        if ("".equals(reUname) || "".equals(repass)||"".equals(repass2)||"".equals(reemail)) {
            Toast.makeText(RegisterActivity.this, "不能为空哦~", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            Toast.makeText(RegisterActivity.this, "成功了~", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void getState(LoginBean loginBean) {
        String code = loginBean.getCode();
        String msg = loginBean.getMsg();
        if("0".equals(code)){

            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

        }else{
            Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
