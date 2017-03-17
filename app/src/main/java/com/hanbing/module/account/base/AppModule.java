package com.hanbing.module.account.base;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hanbing on 2017/3/8
 */

@Module
public class AppModule {


    final Context mContext;



    public AppModule(Application context) {
        mContext = context;
    }

    @AccountScoped
    @Provides
    Context applicationContext(){
        return mContext;
    }


    @Singleton
    @Provides
    HttpService provideHttpService() {
        return new HttpService();
    }
}
