package com.hym.appstore.ui;

public interface BaseView {
    void showLoading();
    void showError(String msg);
    void dismissLoading();
}
