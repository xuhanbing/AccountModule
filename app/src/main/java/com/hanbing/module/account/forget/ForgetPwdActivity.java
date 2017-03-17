package com.hanbing.module.account.forget;

import android.content.Intent;
import android.os.Bundle;

import com.hanbing.module.account.verify.VerifyCodeActivity;
import com.hanbing.module.account.verify.VerifyCodeType;

public class ForgetPwdActivity extends VerifyCodeActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void goNext() {
        String mobile = getMobile();

        Intent intent = ForgetPwdSecondActivity.newIntent(this, mobile);

        startActivity(intent);
    }

    @Override
    protected VerifyCodeType getVerifyCodeType() {
        return VerifyCodeType.FORGET_PWD;
    }
}
