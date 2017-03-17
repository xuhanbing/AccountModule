package com.hanbing.module.account.reset;

import com.hanbing.module.account.BasePresenter;
import com.hanbing.module.account.BaseView;

/**
 * Created by hanbing on 2017/3/6
 */

public interface ResetPwdContract  {


    interface View extends BaseView<Presenter> {
        String getUserId();
        String getOldPwd();
        String getNewPwd();
        String getNewPwdConfirm();


        void showResetPwdStarted();
        void showResetPwdSuccess();
        void showResetPwdError(String msg);
        void showResetPwdCompleted();
    }

    interface Presenter extends BasePresenter{
        void reset();
    }
}
