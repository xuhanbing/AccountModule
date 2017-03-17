package com.hanbing.module.account.forget;

import dagger.Subcomponent;

/**
 * Created by hanbing on 2017/3/10
 */

@Subcomponent(modules = {ForgetPwdPresenterModule.class})
public interface ForgetPwdComponent {

    void inject(ForgetPwdSecondActivity activity);
}
