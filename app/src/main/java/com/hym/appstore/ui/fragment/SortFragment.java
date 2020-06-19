package com.hym.appstore.ui.fragment;

import androidx.recyclerview.widget.RecyclerView;

import com.hym.appstore.R;
import com.hym.appstore.dagger2.component.AppComponent;

import butterknife.BindView;


public class SortFragment extends ProgressFragment<> {


    @BindView(R.id.home_rv)
    RecyclerView mHomeRv;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.template_recycler_view;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {

    }
}
