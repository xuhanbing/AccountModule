package com.hanbing.module.account.base;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.hanbing.module.account.MainActivity;
import com.hanbing.module.account.forget.ForgetPwdComponent;
import com.hanbing.module.account.forget.ForgetPwdPresenterModule;
import com.hanbing.module.account.login.LoginComponent;
import com.hanbing.module.account.login.LoginPresenterModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by hanbing on 2017/3/8
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {


//    LoginComponent plus(LoginPresenterModule loginPresenterModule);

    ForgetPwdComponent plus(ForgetPwdPresenterModule forgetPwdPresenterModule);

    HttpService getHttpService();
}
