package com.hym.appstore.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.hym.appstore.R;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.presenter.SubjectPresenter;
import com.hym.appstore.ui.fragment.SubjectDetailFragment;
import com.hym.appstore.ui.fragment.SubjectFragment;

public class SubjectActivity extends BaseActivity {

    private FragmentManager mFragmentManager;
    SubjectFragment mSubjectFragment;
    SubjectDetailFragment mSubjectDetailFragment;


    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_subject;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    public void initView() {
        showSubjectFragment();
    }

    @Override
    public void initEvent() {

    }

    private void showSubjectFragment(){
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (mSubjectFragment == null) {
            mSubjectFragment = new SubjectFragment();
            fragmentTransaction.add(R.id.content,mSubjectFragment);
        }else {
            fragmentTransaction.show(mSubjectFragment);
        }
        fragmentTransaction.commit();
    }
}
