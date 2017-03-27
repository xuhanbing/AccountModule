package com.hanbing.module.account.bind;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hanbing on 2017/3/21
 */

@Module
public class BindPresenterModule {


    Activity mContext;
    BindContract.View mView;

    public BindPresenterModule(Activity context, BindContract.View view) {
        mContext = context;
        mView = view;
    }

    @Provides
    public Activity provideContext() {
        return mContext;
    }

    @Provides
    public BindContract.View provideBindContractView(){
        return mView;
    }
}
