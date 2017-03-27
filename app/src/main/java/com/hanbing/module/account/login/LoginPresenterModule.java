package com.hanbing.module.account.login;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hanbing on 2017/3/6
 */

@Module
public class LoginPresenterModule {

    Activity mContext;
    LoginContract.View mView;


    public LoginPresenterModule(Activity context, LoginContract.View view) {
        assert null != context;
        mContext = context;
        assert null != view;
        mView = view;
    }


    @Provides
    Activity provideContext() {
        return mContext;
    }


    @Provides
    LoginContract.View provideLoginContractView(){
        return mView;
    }

}
