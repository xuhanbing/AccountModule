package com.hanbing.module.account.login;

import android.os.Bundle;

import com.hanbing.module.account.App;
import com.hanbing.module.account.BaseActivity;
import com.hanbing.module.account.R;
import com.hanbing.module.account.base.AppComponent;
import com.hanbing.module.account.base.AppModule;
import com.hanbing.module.account.base.DaggerAppComponent;
import com.hanbing.module.account.base.HttpService;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity  {



    @Inject
    LoginPresenter mPresenter;


    LoginComponent mLoginComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.login_activity);

        if (null == loginFragment) {
            loginFragment = new LoginFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.login_activity, loginFragment)
                    .commit();
        }

        mLoginComponent =  DaggerLoginComponent.builder()
//                .appModule(new AppModule(getApplication()))
                .appComponent(App.getAppComponent(this))
                .loginPresenterModule(new LoginPresenterModule(this, loginFragment))
                .build();

        mLoginComponent.inject(this);

//
//        AppComponent appComponent = DaggerAppComponent.builder()
//                .appModule(new AppModule(getApplication()))
//                .build();
//        appComponent.plus(new LoginPresenterModule(loginFragment)).inject(this);



    }


    @Override
    protected void onDestroy() {
        mLoginComponent = null;
        super.onDestroy();
    }
}
