package com.hanbing.module.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hanbing.module.account.base.AccountManager;
import com.hanbing.module.account.base.ErrorCodeManager;
import com.hanbing.module.account.base.HttpService;
import com.hanbing.module.account.verify.VerifyCodeActivity;
import com.hanbing.module.account.forget.ForgetPwdActivity;
import com.hanbing.module.account.login.LoginActivity;
import com.hanbing.module.account.register.RegisterByMobileActivity;
import com.hanbing.module.account.register.RegisterByUsernameActivity;
import com.hanbing.module.account.reset.ResetPwdActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.login_btn)
    Button mLoginBtn;
    @BindView(R.id.register_btn)
    Button mRegisterBtn;
    @BindView(R.id.forget_btn)
    Button mForgetBtn;
    @BindView(R.id.fast_login_btn)
    Button mFastLoginBtn;
    @BindView(R.id.third_login_btn)
    Button mThirdLoginBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        ErrorCodeManager.getInstance().init(this);
        AccountManager.getInstance().register("15968879083", "123");
        HttpService.getInstance().init(this);


        View view1 = getWindow().getDecorView();
        View view2 = findViewById(R.id.activity_main);




    }


    @OnClick({R.id.login_btn, R.id.register_btn, R.id.forget_btn, R.id.fast_login_btn, R.id.third_login_btn, R.id.register_mobile_btn, R.id.reset_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                startActivity(new Intent(this, LoginActivity.class));
            break;
            case R.id.register_btn:
                startActivity(new Intent(this, RegisterByUsernameActivity.class));
                break;
            case R.id.register_mobile_btn:
                startActivity(new Intent(this, RegisterByMobileActivity.class));
                break;
            case R.id.forget_btn:
                startActivity(new Intent(this, ForgetPwdActivity.class));
                break;
            case R.id.fast_login_btn:
                startActivity(new Intent(this, VerifyCodeActivity.class));
                break;
            case R.id.third_login_btn:
                break;

            case R.id.reset_btn:
                startActivity(new Intent(this, ResetPwdActivity.class));

                break;
        }
    }
}
