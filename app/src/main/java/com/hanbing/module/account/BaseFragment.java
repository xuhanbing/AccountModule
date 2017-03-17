package com.hanbing.module.account;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by hanbing on 2017/3/6
 */

public class BaseFragment extends Fragment {

    ProgressDialog mProgressDialog;
    Snackbar mSnackbar;

    protected void showProgress(CharSequence msg) {
        showProgress(null, msg);
    }

    protected void showProgress(CharSequence title, CharSequence msg) {
        dismissProgress();
        mProgressDialog = ProgressDialog.show(getContext(), title, msg);
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

        mSnackbar = Snackbar.make(getView(), msg, Snackbar.LENGTH_LONG).setAction(btnText, listener);
        mSnackbar.show();
    }

    private void dismissKeyboard() {

        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }


    protected void dismissSnackbar() {
        if (null != mSnackbar && mSnackbar.isShownOrQueued())
            mSnackbar.dismiss();
        mSnackbar = null;
    }

    @Override
    public void onDestroy() {
        dismissProgress();
        dismissSnackbar();
        super.onDestroy();
    }

}
