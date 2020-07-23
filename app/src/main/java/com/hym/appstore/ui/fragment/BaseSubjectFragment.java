package com.hym.appstore.ui.fragment;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hym.appstore.R;
import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.bean.PageBean;
import com.hym.appstore.bean.Subject;
import com.hym.appstore.bean.SubjectDetail;
import com.hym.appstore.common.apkparset.AndroidApk;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.dagger2.component.DaggerAppManagerComponent;
import com.hym.appstore.dagger2.module.AppManagerModule;
import com.hym.appstore.presenter.AppManagerPresent;
import com.hym.appstore.presenter.SubjectPresenter;
import com.hym.appstore.presenter.contract.AppManagerContract;
import com.hym.appstore.presenter.contract.SubjectContract;

import java.util.List;

import butterknife.BindView;
import zlc.season.rxdownload2.entity.DownloadRecord;

public abstract class BaseSubjectFragment extends ProgressFragment<SubjectPresenter> implements SubjectContract.SubjectView {


    @Override
    public void showSubject(PageBean<Subject> pageBean) {

    }

    @Override
    public void onLoadMoreComplete() {

    }

    @Override
    public void showSubjectDetail(SubjectDetail detail) {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {

    }
}
