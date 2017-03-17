package com.hanbing.module.account.login;

import android.text.TextUtils;
import android.util.Log;

import com.hanbing.module.account.Utils;
import com.hanbing.module.account.base.AccountManager;
import com.hanbing.module.account.base.ErrorCode;
import com.hanbing.module.account.base.ErrorCodeManager;
import com.hanbing.module.account.base.HttpRequestCallback;
import com.hanbing.module.account.base.HttpService;

import javax.inject.Inject;

/**
 * Created by hanbing on 2017/3/3
 */

public class LoginPresenter implements LoginContract.Presenter {


    LoginContract.View mView;


    HttpService mHttpService;
    HttpService.Task mTask;

    public LoginPresenter(LoginContract.View view) {
        assert view != null;
        mView = view;
    }

    @Inject
    public LoginPresenter(LoginContract.View view, HttpService httpService) {
        assert view != null;
        mView = view;
        assert null != httpService;
        mHttpService = httpService;
    }

    @Inject
    public void setListeners(){
        mView.setPresenter(this);
    }

    @Override
    public void login() {

        final String username = mView.getUsername();
        final String pwd = mView.getPwd();



        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)) {
            mView.showLoginError(
                    ErrorCodeManager.parseCode(ErrorCode.USERNAME_OR_PWD_NO_EMPTY));
        } else if (username.length() < 3) {
            mView.showLoginError(
                    ErrorCodeManager.parseCode(ErrorCode.USERNAME_LENGTH_INVALID));
        } else {
            mView.showLoginStarted();
//            int code = AccountManager.getInstance().login(username, pwd);
//           Utils.queueTask(()->{
//
//                if (code == ErrorCode.OK) {
//                    mView.showLoginSuccess(null);
//                } else {
//                    mView.showLoginError(ErrorCodeManager.parseCode(code));
//                }
//
//
//                mView.showLoginCompleted();
//            });

            mHttpService.getUser(username, new HttpRequestCallback() {
                @Override
                public void onSuccess(Object object) {
                    mView.showLoginSuccess(null);
                    mView.showLoginCompleted();
                }

                @Override
                public void onError(int code, String msg) {
                    mView.showLoginError(ErrorCodeManager.parseCode(code, msg));
                    mView.showLoginCompleted();
                }
            });
        }


    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {
        cancelTask();
        mView = null;
        mHttpService = null;
    }

    private void cancelTask() {
        if (null != mTask) mTask.cancel();
        mTask = null;
    }
}
