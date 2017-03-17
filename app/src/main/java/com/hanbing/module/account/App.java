package com.hanbing.module.account;

import android.app.Application;
import android.content.Context;

import com.hanbing.module.account.base.AppComponent;
import com.hanbing.module.account.base.AppModule;
import com.hanbing.module.account.base.DaggerAppComponent;

/**
 * Created by hanbing on 2017/3/9
 */

public class App extends Application {


    AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();


        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static AppComponent getAppComponent(Context context) {
        return ((App)context.getApplicationContext()).getAppComponent();
    }
}
