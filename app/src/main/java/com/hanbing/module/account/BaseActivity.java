package com.hanbing.module.account;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.umeng.socialize.UMShareAPI;

/**
 * Created by hanbing on 2017/3/3
 */

public class BaseActivity extends AppCompatActivity {


    ProgressDialog mProgressDialog;
    Snackbar mSnackbar;

    protected void showProgress(CharSequence msg) {
        showProgress(null, msg);
    }

    protected void showProgress(CharSequence title, CharSequence msg) {
        dismissProgress();
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        dialog.show();
        mProgressDialog = dialog;
    }

    protected void dismissProgress() {
        if (null != mProgressDialog && mProgressDialog.isShowing())
            mProgressDialog.dismiss();

        mProgressDialog = null;
    }

    protected void showSnackbar(CharSequence msg) {
        showSnackbar(msg, null, null);
    }

    protected void showSnackbar(CharSequence msg, CharSequence btnText, View.OnClickListener listener) {
        dismissSnackbar();
        dismissKeyboard();

        mSnackbar = Snackbar.make(getWindow().getDecorView(), msg, Snackbar.LENGTH_LONG).setAction(btnText, listener);
        mSnackbar.show();
    }

    private void dismissKeyboard() {

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
    }


    protected void dismissSnackbar() {
        if (null != mSnackbar && mSnackbar.isShownOrQueued())
            mSnackbar.dismiss();
        mSnackbar = null;
    }

    @Override
    protected void onDestroy() {
        dismissProgress();
        dismissSnackbar();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
