package com.hanbing.module.account.register;

import android.content.Intent;
import android.os.Bundle;

import com.hanbing.module.account.verify.VerifyCodeActivity;
import com.hanbing.module.account.verify.VerifyCodeType;

public class RegisterByMobileActivity extends VerifyCodeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getNextTitle() {
        return "Register";
    }


    @Override
    public void goNext() {
        String mobile = getMobile();

        Intent intent = RegisterByMobileSecondActivity.newIntent(this, mobile);

        startActivity(intent);
    }

    @Override
    protected VerifyCodeType getVerifyCodeType() {
        return VerifyCodeType.REGISTER;
    }
}
