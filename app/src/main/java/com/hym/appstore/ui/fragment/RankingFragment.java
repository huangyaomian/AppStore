package com.hym.appstore.ui.fragment;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.hym.appstore.R;
import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.bean.PageBean;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.dagger2.component.DaggerAppInfoComponent;
import com.hym.appstore.dagger2.module.AppInfoModule;
import com.hym.appstore.presenter.AppInfoPresenter;
import com.hym.appstore.presenter.contract.AppInfoContract;
import com.hym.appstore.ui.adapter.AppInfoAdapter;

import butterknife.BindView;


public class RankingFragment extends AppInfoFragment {



    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appComponent(appComponent).appInfoModule(new AppInfoModule(this)).build().inject(this);
    }



    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder().showPosition(true).showCategoryName(true).showBrief(false).build();
    }










}
