package com.hanbing.module.account.bind;

import com.hanbing.module.account.AccountType;
import com.hanbing.module.account.BasePresenter;
import com.hanbing.module.account.BaseView;
import com.hanbing.module.account.bean.BindItem;

import java.util.List;

/**
 * Created by hanbing on 2017/3/20
 */

public interface BindContract {

    interface View extends BaseView<Presenter> {

        String getUserId();


        void showGetBindListStarted();

        void showGetBindListSuccess(List<BindItem> bindList);

        void showGetBindListError(String msg);

        void showGetBindListCompleted();

        void showBindStarted(AccountType accountType);

        void showBindSuccess(AccountType accountType, String uid);

        void showBindError(AccountType accountType, String msg);

        void showBindCompleted(AccountType accountType);

        void showUnbindStarted(AccountType accountType);

        void showUnbindSuccess(AccountType accountType);

        void showUnbindError(AccountType accountType, String msg);

        void showUnbindCompleted(AccountType accountType);

        void goBindMobileActivity();

        void goUnbindMobileActivity(String mobile);
    }

    interface Presenter extends BasePresenter {

        /**
         * 绑定或解绑
         *
         * @param accountType 账户类型手机号、qq、微信等
         * @param uid         1.绑定。当为手机号时，始终为空；如果是第三方帐号，如果为空先授权，否则直接绑定。
         *                    2.解绑。如果是第三方帐号，为空先授权，否则直接解绑；否则始终为绑定的手机号
         * @param bind
         */
        void bindOrUnbind(AccountType accountType, String uid, boolean bind);

        void bindOrUnbindMobile(String mobile, boolean bind);

        void getBindList();
    }
}
