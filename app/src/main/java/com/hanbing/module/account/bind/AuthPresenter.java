package com.hanbing.module.account.bind;

import android.app.Activity;

import com.hanbing.module.account.AccountType;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * Created by hanbing on 2017/3/20
 */

public class AuthPresenter implements AuthContract.Presenter {

    Activity mContext;
    AuthContract.View mView;

    UMShareAPI mUMShareAPI;

    protected AuthPresenter(){

    }

    public AuthPresenter(Activity context, AuthContract.View view) {
        assert  null != context;
        mContext = context;
        assert null != view;
        mView = view;

        mUMShareAPI = UMShareAPI.get(context);
    }

    @Override
    public void auth(AccountType accountType, boolean needUserInfo) {

        SHARE_MEDIA shareMedia = accountType.toShareMedia();

        mUMShareAPI.getPlatformInfo(mContext, shareMedia, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                if (null == mView)
                    return;
                mView.showAuthStarted(accountType);
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                if (null == mView)
                    return;

                String uid = map.get("uid")+"";
                if (needUserInfo) {
                    mView.showAuthSuccess(accountType, uid, null);

                } else {
                    mView.showAuthSuccess(accountType, uid, null);
                }
                mView.showAuthCompleted(accountType);

            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                if (null == mView)
                    return;
                mView.showAuthError(accountType, throwable.toString());
                mView.showAuthCompleted(accountType);
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                if (null == mView)
                    return;
                mView.showAuthCompleted(accountType);
            }
        });

    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {
        mContext = null;
        mView = null;
        mUMShareAPI = null;

    }

}
