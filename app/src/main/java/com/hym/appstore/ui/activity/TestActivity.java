package com.hym.appstore.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hym.appstore.R;
import com.hym.appstore.dagger2.component.AppComponent;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_test;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initEvent() {

    }
}
