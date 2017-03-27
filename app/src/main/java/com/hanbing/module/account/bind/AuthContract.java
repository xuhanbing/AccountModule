package com.hanbing.module.account.bind;

import android.accounts.Account;

import com.hanbing.module.account.AccountType;
import com.hanbing.module.account.BasePresenter;
import com.hanbing.module.account.BaseView;
import com.hanbing.module.account.bean.IUser;

/**
 * Created by hanbing on 2017/3/20
 */

public interface AuthContract {

    interface View<T extends BasePresenter> extends BaseView<T> {

        void showAuthStarted(AccountType accountType);

        /**
         * @param accountType
         * @param uid         Third party platform unique id
         * @param user        user info
         */
        void showAuthSuccess(AccountType accountType, String uid, IUser user);

        /**
         * @param accountType
         * @param msg
         */
        void showAuthError(AccountType accountType, String msg);

        void showAuthCompleted(AccountType accountType);

    }

    interface Presenter extends BasePresenter {
        /**
         * Third party platform authorize.
         *
         * @param accountType
         * @param needUserInfo If need user info or not.
         */
        void auth(AccountType accountType, boolean needUserInfo);

    }
}
