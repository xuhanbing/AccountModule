package com.hanbing.module.account.reset;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;

import com.hanbing.module.account.BaseActivity;
import com.hanbing.module.account.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPwdActivity extends BaseActivity implements ResetPwdContract.View {

    @BindView(R.id.old_pwd_et)
    TextInputEditText mOldPwdEt;
    @BindView(R.id.new_pwd_et)
    TextInputEditText mNewPwdEt;
    @BindView(R.id.new_pwd_confirm_et)
    TextInputEditText mNewPwdConfirmEt;
    @BindView(R.id.reset_btn)
    Button mResetBtn;

    @Inject
    ResetPwdPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_pwd_activity);
        ButterKnife.bind(this);

//        mPresenter = new ResetPwdPresenter(this);

        DaggerResetPwdComponent.builder()
                .resetPwdPresenterModule(new ResetPwdPresenterModule(this))
                .build()
                .inject(this);

    }

    @Override
    public String getUserId() {
        return "13800138000";
    }

    @Override
    public String getOldPwd() {
        return mOldPwdEt.getText().toString();
    }

    @Override
    public String getNewPwd() {
        return mNewPwdEt.getText().toString();
    }

    @Override
    public String getNewPwdConfirm() {
        return mNewPwdConfirmEt.getText().toString();
    }

    @Override
    public void showResetPwdStarted() {
        showProgress("Reset password...");
    }

    @Override
    public void showResetPwdSuccess() {
        showSnackbar("Reset password success.");

    }

    @Override
    public void showResetPwdError(String msg) {
        showSnackbar(msg);

    }

    @Override
    public void showResetPwdCompleted() {
        dismissProgress();
    }

    @Override
    public void setPresenter(ResetPwdContract.Presenter presenter) {
        mPresenter = (ResetPwdPresenter) presenter;
    }

    @OnClick(R.id.reset_btn)
    public void onClick() {
        mPresenter.reset();
    }
}
