package com.hanbing.module.account.bind;

import android.app.Activity;
import android.text.TextUtils;

import com.hanbing.module.account.AccountType;
import com.hanbing.module.account.Utils;
import com.hanbing.module.account.base.ErrorCode;
import com.hanbing.module.account.base.ErrorCodeManager;
import com.hanbing.module.account.bean.BindItem;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by hanbing on 2017/3/20
 */

public class BindPresenter implements BindContract.Presenter {

    Activity mContext;
    BindContract.View mView;

    UMShareAPI mUMShareAPI;

    BindManager mBindManager;


    @Inject
    public BindPresenter(Activity context, BindContract.View view) {
        assert null != context;
        assert null != view;
        mContext = context;
        mView = view;
        mUMShareAPI = UMShareAPI.get(context);
        mBindManager = BindManager.getInstance();
    }


    @Override
    public void bindOrUnbind(AccountType accountType, String uid, boolean bind) {

        if (AccountType.MOBILE == accountType) {
            if (bind)
                mView.goBindMobileActivity();
            else
                mView.goUnbindMobileActivity(uid);
            return;
        }

        if (TextUtils.isEmpty(uid)) {
            bindOrUnbindAfterAuth(accountType, bind);
        } else {
            bindOrUnbindReal(accountType, uid, bind);
        }
    }

    @Override
    public void bindOrUnbindMobile(String mobile, boolean bind) {
        bindOrUnbindReal(AccountType.MOBILE, mobile, bind);
    }

    private void bindOrUnbindAfterAuth(final AccountType accountType, final boolean bind) {
        mUMShareAPI.doOauthVerify(mContext, accountType.toShareMedia(), new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                if (bind) {
                    mView.showBindStarted(accountType);
                } else {
                    mView.showUnbindStarted(accountType);
                }
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

                String uid = map.get("uid");
                bindOrUnbindReal(accountType, uid, bind);
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                if (bind) {
                    mView.showBindError(accountType, throwable.toString());
                } else {
                    mView.showBindError(accountType, throwable.toString());
                }

                onCompleted();
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }

            private void onCompleted() {
                if (bind) {
                    mView.showBindCompleted(accountType);
                } else {
                    mView.showUnbindCompleted(accountType);
                }
            }
        });
    }


    private void bindOrUnbindReal(AccountType accountType, String uid, boolean bind) {

        if (bind) {

            int code = mBindManager.bind(mView.getUserId(), accountType, uid);

            if (ErrorCode.OK == code) {
                mView.showBindSuccess(accountType, uid);
            } else {
                mView.showBindError(accountType, ErrorCodeManager.parseCode(code));
            }

            mView.showBindCompleted(accountType);

        } else {
            int code = mBindManager.unbind(mView.getUserId(), accountType, uid);

            if (ErrorCode.OK == code) {
                mView.showUnbindSuccess(accountType);
            } else {
                mView.showUnbindError(accountType, ErrorCodeManager.parseCode(code));
            }

            mView.showUnbindCompleted(accountType);


            mUMShareAPI.deleteOauth(mContext, accountType.toShareMedia(), null);
        }
    }

    @Override
    public void getBindList() {

        mView.showGetBindListStarted();


        Utils.queueTask(() -> {

            if (mView.getUserId().length() == 0) {
                mView.showGetBindListError("User id length must > 0");
            } else {
                List<BindItem> bindList = mBindManager.getBindList(mView.getUserId());

                mView.showGetBindListSuccess(bindList);
            }

            mView.showGetBindListCompleted();
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
        mBindManager = null;
    }


}
