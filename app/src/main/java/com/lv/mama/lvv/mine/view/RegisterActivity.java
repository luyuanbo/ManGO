package com.lv.mama.lvv.mine.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lv.mama.lvv.R;

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
        if ("".equals(reUname) || "".equals(repass)||"".equals(repass2)||"".equals(reemail)) {
            Toast.makeText(RegisterActivity.this, "不能为空哦~", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        }

    }

    @Override
    public void getState(String code,String msg) {
        if ("0".equals(code)) {
            Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
