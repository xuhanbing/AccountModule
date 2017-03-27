package com.hanbing.module.account.bind;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.hanbing.module.account.R;
import com.hanbing.module.account.verify.VerifyCodeActivity;
import com.hanbing.module.account.verify.VerifyCodeType;

public class BindMobileActivity extends VerifyCodeActivity {


    public static Intent newIntent(Context context, String mobile, boolean bind ) {

        Intent intent = new Intent(context, BindMobileActivity.class);

        intent.putExtra("mobile", mobile);
        intent.putExtra("bind", bind);
        return intent;
    }


    String mMobile;
    boolean mIsBind = true;


    EditText mMobileEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (null != getIntent()) {
            mMobile = getIntent().getStringExtra("mobile");
            mIsBind = getIntent().getBooleanExtra("bind", true);
        }
        super.onCreate(savedInstanceState);

        mMobileEt = (EditText) findViewById(R.id.mobile_et);
        mMobileEt.setText(mMobile);
        mMobileEt.setEnabled(mIsBind);

    }


    @Override
    protected VerifyCodeType getVerifyCodeType() {
        return mIsBind ? VerifyCodeType.BIND : VerifyCodeType.UNBIND;
    }

    @Override
    protected String getNextTitle() {
        return mIsBind ? "Bind" : "UnBind";
    }

    @Override
    public void goNext() {
        Intent data =  new Intent(getIntent());


        if (mIsBind) {
            data.putExtra("mobile", getMobile());
        }

        setResult(RESULT_OK, data);

        finish();
    }

}
