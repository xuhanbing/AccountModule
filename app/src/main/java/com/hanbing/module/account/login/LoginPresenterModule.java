package com.hanbing.module.account.login;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hanbing on 2017/3/6
 */

@Module
public class LoginPresenterModule {

    LoginContract.View mView;


    public LoginPresenterModule(LoginContract.View view) {
        assert null != view;
        mView = view;
    }



    @Provides
    LoginContract.View provideLoginContractView(){
        return mView;
    }

}
