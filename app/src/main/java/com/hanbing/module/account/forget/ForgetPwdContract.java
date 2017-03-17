package com.hanbing.module.account.forget;

import com.hanbing.module.account.BasePresenter;
import com.hanbing.module.account.BaseView;

/**
 * Created by hanbing on 2017/3/6
 */

public interface ForgetPwdContract {


    interface View extends BaseView<Presenter> {

        String getUsername();//Always mobile.
        String getPwd();
        String getPwdConfirm();

        void showForgetPwdStarted();
        void showForgetPwdSuccess();
        void showForgetPwdError(String msg);
        void showForgetPwdCompleted();

    }

    interface Presenter extends BasePresenter{
        void forget();
    }

}
