package com.hanbing.module.account.verify;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hanbing.module.account.BaseActivity;
import com.hanbing.module.account.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerifyCodeActivity extends BaseActivity implements VerifyCodeContract.View {

    private static final String TAG = "VerifyCodeActivity";

    @BindView(R.id.mobile_et)
    EditText mMobileEt;
    @BindView(R.id.verify_code_et)
    EditText mVerifyCodeEt;
    @BindView(R.id.get_verify_code_btn)
    Button mGetVerifyCodeBtn;
    @BindView(R.id.next_btn)
    protected Button mNextBtn;


    VerifyCodeContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_code_activity);
        ButterKnife.bind(this);

        mPresenter = new VerifyCodePresenter(this);

        mNextBtn.setText(getNextTitle());
    }

    @Override
    public String getMobile() {
        return mMobileEt.getText().toString();
    }

    @Override
    public String getVerifyCode() {
        return mVerifyCodeEt.getText().toString();
    }

    @Override
    public long getCountDownMillis() {
        return 20 * 1000;
    }

    @Override
    public void showCountDownMillis(long timeInMillis) {

        Log.e(TAG, "timeInMillis=" + timeInMillis);
        timeInMillis /= 1000;
        if (0 >= timeInMillis) {
            mGetVerifyCodeBtn.setText("Get Verify Code");
        } else {
            mGetVerifyCodeBtn.setText(String.format("Count Down %ds", timeInMillis));
        }
    }

    @Override
    public void showRequestVerifyCodeStarted() {
        showProgress("Request verification code...");
    }

    @Override
    public void showRequestVerifyCodeSuccess(String verifyCode) {
        showSnackbar("Request verification code success.Verification Code = " + verifyCode);
    }

    @Override
    public void showRequestVerifyCodeError(String msg) {
        showSnackbar(msg);
    }

    @Override
    public void showRequestVerifyCodeCompleted() {
        dismissProgress();

    }

    @Override
    public void showCheckVerifyCodeStarted() {
        showProgress("Check verification code...");

    }

    @Override
    public void showCheckVerifyCodeSuccess() {
        showSnackbar("Check verification code success.");

        goNext();
    }

    @Override
    public void showCheckVerifyCodeError(String msg) {
        showSnackbar(msg);
    }

    @Override
    public void showCheckVerifyCodeCompleted() {
        dismissProgress();

    }

    @Override
    public void goNext() {

    }


    @Override
    public void setRequestBtnEnabled(boolean enabled) {
        mGetVerifyCodeBtn.setEnabled(enabled);
    }

    @Override
    public void setPresenter(VerifyCodeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @OnClick({R.id.get_verify_code_btn, R.id.next_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_verify_code_btn:
                mPresenter.requestVerifyCode(getVerifyCodeType());
                break;
            case R.id.next_btn:
                mPresenter.checkVerifyCode();
                break;
        }
    }

    protected String getNextTitle() {
        return "Next";
    }

    protected VerifyCodeType getVerifyCodeType(){
        return VerifyCodeType.DEFAULT;
    }
}
