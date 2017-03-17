package com.hanbing.module.account.forget;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hanbing on 2017/3/10
 */

@Module
public class ForgetPwdPresenterModule {

    ForgetPwdContract.View mView;

    public ForgetPwdPresenterModule(ForgetPwdContract.View view) {
        mView = view;
    }

    @Provides
    ForgetPwdContract.View provideForgetPwdPresenterView() {
        return mView;
    }
}
