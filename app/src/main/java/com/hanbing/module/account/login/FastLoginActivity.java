package com.hanbing.module.account.login;

import android.os.Bundle;

import com.hanbing.module.account.verify.VerifyCodeActivity;
import com.hanbing.module.account.verify.VerifyCodeType;

public class FastLoginActivity extends VerifyCodeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getNextTitle() {
        return "Login";
    }

    @Override
    protected VerifyCodeType getVerifyCodeType() {
        return VerifyCodeType.FAST_LOGIN;
    }
}
