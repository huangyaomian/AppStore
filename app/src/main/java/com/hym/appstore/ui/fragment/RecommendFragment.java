package com.hym.appstore.ui.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.toast.ToastUtils;
import com.hym.appstore.R;
import com.hym.appstore.bean.RecommendBean;
import com.hym.appstore.dagger2.component.DaggerRecommendComponent;
import com.hym.appstore.dagger2.module.RecommendModule;
import com.hym.appstore.presenter.contract.RecommendContract;
import com.hym.appstore.ui.adapter.RecommendRVAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;


public class RecommendFragment extends BaseFragment implements RecommendContract.View {



    @BindView(R.id.recommend_rv)
    RecyclerView mRecommendRv;
    @BindView(R.id.recommend_refreshLayout)
    RefreshLayout recommendRefreshLayout;

    /**
     * 自定义的容器
     **/
    private List<RecommendBean.DataBean.ItemsBean> mGameList;

    @Inject
    RecommendContract.Presenter mPresenter;

    private String recommendNextURL = null;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecommendRv.setLayoutManager(layoutManager);
    }

    @Override
    protected void initData() {
      /**推薦遊戲的请求**/
      mPresenter.requestRecommendData(true,recommendURL);
    }

    @Override
    protected void init() {
        mGameList = new ArrayList<>();
//        mPresenter = new RecommendPresenter(this,getActivity());
        DaggerRecommendComponent.builder()
                .recommendModule(new RecommendModule(this,getActivity())).build().inject(this);
    }

    @Override
    protected void initEvent() {
        recommendRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));//设置Header
        recommendRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.requestRecommendData(false,recommendURL);
            }
        });
//        recommendRefreshLayout.setRefreshFooter(new RefreshFooter() {
//        });

    }




    /**
     * 定义RecyclerView选项单击事件的回调接口
     */
    public interface OnItemClickListener {
        //也可以不在这个activity或者是fragment中来声明接口，可以在项目中单独创建一个interface，就改成static就OK
        //参数（父组件，当前单击的View,单击的View的位置，数据）
        void onItemClick(RecyclerView parent, View view, int position, Map data);
        // void onItemLongClick(View view);类似，我这里没用就不写了
        //
        //这个data是List中放的数据类型，因为我这里是private List<Map> mapList;这样一个
        //然后我的每个item是这样的：
        //        HashMap map =new HashMap();
        //        map.put("img",R.drawable.delete);
        //        map.put("text","x1");
        //所以我的是map类型的，那如果是item中只有text的话比如List<String>，那么data就改成String类型
    }

    @Override
    public void showResult(RecommendBean recommendBean) {
        recommendRefreshLayout.finishRefresh();//结束刷新
        mGameList.clear();
        List<RecommendBean.DataBean.ItemsBean> items = recommendBean.getData().getItems();
        recommendNextURL = recommendBean.getData().getPager().getNext();
        mGameList.addAll(items);
        RecommendRVAdapter recommendRVAdapter = new RecommendRVAdapter(mGameList, getActivity());
        mRecommendRv.setAdapter(recommendRVAdapter);
        // 设置数据后就要给RecyclerView设置点击事件
        recommendRVAdapter.setOnItemClickListener(new RecommendRVAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // 这里本来是跳转页面 ，我们就在这里直接让其弹toast来演示
                ToastUtils.show(mGameList.get(position).getApp_name());
            }
        });

    }


    @Override
    public void showNoData() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
