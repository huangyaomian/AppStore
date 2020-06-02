package com.hym.appstore.ui.fragment;

import androidx.recyclerview.widget.RecyclerView;

import com.hym.appstore.R;

import butterknife.BindView;


public class RecommendFragment extends BaseFragment {


    @BindView(R.id.recommend_rv)
    RecyclerView mRecommendRv;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        mRecommendRv.setAdapter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected void initEvent() {

    }
}
