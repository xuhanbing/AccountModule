package com.hanbing.module.account;

import android.app.Application;
import android.content.Context;

import com.hanbing.module.account.base.AppComponent;
import com.hanbing.module.account.base.AppModule;
import com.hanbing.module.account.base.DaggerAppComponent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by hanbing on 2017/3/9
 */

public class App extends Application {


    {
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }

    AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Config.DEBUG = true;
        UMShareAPI.get(this);

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
