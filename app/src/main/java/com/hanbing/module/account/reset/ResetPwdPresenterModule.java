package com.hanbing.module.account.reset;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hanbing on 2017/3/8
 */

@Module
public class ResetPwdPresenterModule {

    ResetPwdContract.View mView;

    public ResetPwdPresenterModule(ResetPwdContract.View view) {
        assert null != view;
        mView = view;
    }

    @Provides
    public ResetPwdContract.View provideResetPwsContractView(){
        return mView;
    }

}
