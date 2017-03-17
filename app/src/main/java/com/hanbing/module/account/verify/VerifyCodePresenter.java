package com.hanbing.module.account.verify;

import android.os.CountDownTimer;
import android.text.TextUtils;

import com.hanbing.module.account.Utils;
import com.hanbing.module.account.base.AccountManager;
import com.hanbing.module.account.base.ErrorCode;
import com.hanbing.module.account.base.ErrorCodeManager;
import com.hanbing.module.account.base.HttpService;

/**
 * Created by hanbing on 2017/3/3
 */

public class VerifyCodePresenter implements VerifyCodeContract.Presenter {


    VerifyCodeContract.View mView;

    String mCacheMobile;
    String mCacheVerifyCode;

    CountDownTimer mCountDownTimer;

    HttpService.Task mTask;

    public VerifyCodePresenter(VerifyCodeContract.View view) {
        assert view != null;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void requestVerifyCode(VerifyCodeType verifyCodeType) {
        final String mobile = mView.getMobile();
        mCacheMobile = null;

        if (TextUtils.isEmpty(mobile)) {
            mView.showRequestVerifyCodeError(ErrorCodeManager.parseCode(ErrorCode.MOBILE_NO_EMPTY));
        } else if (Utils.isMobile(mobile)) {


            mView.setRequestBtnEnabled(false);

            mView.showRequestVerifyCodeStarted();
            startCountDownTimer();
            Utils.queueTask(() -> {

                switch (verifyCodeType) {
                    case REGISTER:
                        if (AccountManager.getInstance().has(mobile)) {
                            mView.showRequestVerifyCodeError(ErrorCodeManager.parseCode(ErrorCode.MOBILE_EXIST));
                            mView.showRequestVerifyCodeCompleted();
                            return;

                        }
                        break;

                    case FORGET_PWD:
                        if (!AccountManager.getInstance().has(mobile)) {
                            mView.showRequestVerifyCodeError(ErrorCodeManager.parseCode(ErrorCode.MOBILE_NOT_EXIST));
                            mView.showRequestVerifyCodeCompleted();
                            return;
                        }
                        break;
                    case FAST_LOGIN:
                        break;
                }

                mCacheMobile = mobile;
                mCacheVerifyCode = String.format("%06d", ((int) (Math.random() * (1e6))));
                mView.showRequestVerifyCodeSuccess(mCacheVerifyCode);
                mView.showRequestVerifyCodeCompleted();

            });

        } else {
            mView.showRequestVerifyCodeError(ErrorCodeManager.parseCode(ErrorCode.MOBILE_INVALID));

        }
    }

    @Override
    public void checkVerifyCode() {
        final String mobile = mView.getMobile();
        final String verifyCode = mView.getVerifyCode();


        int code = ErrorCode.OK;
        if (TextUtils.isEmpty(mobile)) {
            code = ErrorCode.MOBILE_NO_EMPTY;
        } else if (!Utils.isMobile(mobile)) {
            code = ErrorCode.MOBILE_INVALID;
        } else if (!mobile.equals(mCacheMobile)) {
            code = ErrorCode.MOBILE_NOT_MATCH;
        } else if (TextUtils.isEmpty(verifyCode)) {
            code = ErrorCode.VERIFY_CODE_NO_EMPTY;
        } else if (verifyCode.length() < 6) {
            code = ErrorCode.VERIFY_CODE_LENGTH_INVALID;
        }

        if (ErrorCode.OK != code) {
            mView.showCheckVerifyCodeError(ErrorCodeManager.parseCode(code));
            return;
        }

        mView.showCheckVerifyCodeStarted();

        Utils.queueTask(() -> {
            if (verifyCode.equals(mCacheVerifyCode)) {
                mView.showCheckVerifyCodeSuccess();
                mView.showCheckVerifyCodeCompleted();

                mView.goNext();
            } else {
                mView.showCheckVerifyCodeError(ErrorCodeManager.parseCode(ErrorCode.VERIFY_CODE_ERROR) + verifyCode + " != " + mCacheVerifyCode);
                mView.showCheckVerifyCodeCompleted();
            }


        });

    }


    void startCountDownTimer() {
        stopCountDownTimer();
        mCountDownTimer = new CountDownTimer(mView.getCountDownMillis(), 300) {
            @Override
            public void onTick(long millisUntilFinished) {
                mView.showCountDownMillis(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                mView.showCountDownMillis(0);
                mView.setRequestBtnEnabled(true);

            }
        };

        mCountDownTimer.start();
    }

    void stopCountDownTimer() {
        if (null != mCountDownTimer)
            mCountDownTimer.cancel();
        mCountDownTimer = null;
    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {
        cancelTask();
        stopCountDownTimer();
        mView = null;
    }

    private void cancelTask() {
        if (null != mTask) mTask.cancel();
        mTask = null;
    }
}
