package com.hym.appstore.ui.fragment;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.listener.LoadMoreListenerImp;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.hym.appstore.R;
import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.bean.PageBean;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.dagger2.component.DaggerRankingComponent;
import com.hym.appstore.dagger2.module.RankingModule;
import com.hym.appstore.presenter.RankingPresenter;
import com.hym.appstore.presenter.contract.AppInfoContract;
import com.hym.appstore.ui.adapter.AppInfoAdapter;

import butterknife.BindView;


public class RankingFragment extends ProgressFragment<RankingPresenter> implements AppInfoContract.RankingView , LoadMoreListenerImp {

    @BindView(R.id.home_rv)
    RecyclerView mHomeRv;

    int page = 0;

    AppInfoAdapter mAppInfoAdapter;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerRankingComponent.builder().appComponent(appComponent).rankingModule(new RankingModule(this)).build().inject(this);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.template_recycler_view;
    }

    @Override
    protected void initView() {

    }


    @Override
    protected void init() {
        mPresenter.requestRankingData(true,page);
        initRecyclerView();
    }

    private void initRecyclerView(){
        mHomeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        mHomeRv.addItemDecoration(dividerItemDecoration);
        mAppInfoAdapter = new AppInfoAdapter();
        mHomeRv.setAdapter(mAppInfoAdapter);
    }

    @Override
    protected void initEvent() {

    }


    @Override
    public void showResult(PageBean<AppInfoBean> data) {
        mAppInfoAdapter.addData(data.getDatas());

        if (data.isHasMore()){
            page++;
        }
        mAppInfoAdapter.setFooterWithEmptyEnable(data.isHasMore());
    }

    @Override
    public void onLoadMoreComplete() {
//        mAppInfoAdapter.set;
    }


    @Override
    public void setOnLoadMoreListener(@Nullable OnLoadMoreListener listener) {
        mPresenter.requestRankingData(false,page);
    }
}
