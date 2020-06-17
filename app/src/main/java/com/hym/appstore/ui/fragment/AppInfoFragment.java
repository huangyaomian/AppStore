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
import com.hym.appstore.presenter.AppInfoPresenter;
import com.hym.appstore.presenter.contract.AppInfoContract;
import com.hym.appstore.ui.adapter.AppInfoAdapter;

import butterknife.BindView;


public abstract class AppInfoFragment extends ProgressFragment<AppInfoPresenter> implements AppInfoContract.AppInfoView {

    @BindView(R.id.home_rv)
    RecyclerView mHomeRv;

    int page = 0;

    protected AppInfoAdapter mAppInfoAdapter;


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
        mAppInfoAdapter = buildAdapter();
        mAppInfoAdapter.setAnimationEnable(true);
        mAppInfoAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.AlphaIn);

        mHomeRv.setAdapter(mAppInfoAdapter);
    }

    abstract AppInfoAdapter buildAdapter();

    @Override
    protected void initEvent() {
        mAppInfoAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.requestRankingData(false,page);
            }
        });
    }


    @Override
    public void showResult(PageBean<AppInfoBean> data) {
        mAppInfoAdapter.addData(data.getDatas());
        if (data.isHasMore()){
            page++;
        }
        mAppInfoAdapter.getLoadMoreModule().setEnableLoadMore(data.isHasMore());
    }

    @Override
    public void onLoadMoreComplete() {
// 当前这次数据加载完毕，调用此方法
        mAppInfoAdapter.getLoadMoreModule().loadMoreComplete();
    }



}
