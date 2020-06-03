package com.hym.appstore.ui.fragment;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;
import com.hym.appstore.R;
import com.hym.appstore.bean.RecommendBean;
import com.hym.appstore.nohttp.CallServer;
import com.hym.appstore.ui.adapter.RecommendRVAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


public class RecommendFragment extends BaseFragment {


    @BindView(R.id.recommend_rv)
    RecyclerView mRecommendRv;
    @BindView(R.id.recommend_refreshLayout)
    RefreshLayout recommendRefreshLayout;

    private Request<String> recommendRequest;
    /**
     * 自定义的容器
     **/
    private List<RecommendBean.DataBean.ItemsBean> mGamelist;

    private Gson mGson;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
//        mRecommendRv.setAdapter(new RecommendRVAdapter(mGamelist, getActivity()));
    }

    @Override
    protected void initData() {
        /**猜你喜欢的请求**/
        recommendRequest = NoHttp.createStringRequest(recommendURL, RequestMethod.GET);
        CallServer.getInstance().add(getActivity(), 0, recommendRequest, this, true, true);
    }

    @Override
    protected void init() {
        mGamelist = new ArrayList<>();
    }

    @Override
    protected void initEvent() {
        recommendRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));//设置Header
        recommendRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                CallServer.getInstance().add(getActivity(), 0, recommendRequest, RecommendFragment.this, true, true);
            }
        });
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        mGson = new Gson();
        LinearLayoutManager layoutManager;
        switch (what) {
            case 0:
                Log.d("onSucceed", response.get());
                RecommendBean recommendBean = mGson.fromJson(response.get(), RecommendBean.class);
                List<RecommendBean.DataBean.ItemsBean> items = recommendBean.getData().getItems();
                mGamelist.clear();
                mGamelist.addAll(items);
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                mRecommendRv.setLayoutManager(layoutManager);
                RecommendRVAdapter recommendRVAdapter = new RecommendRVAdapter(mGamelist, getActivity());
                mRecommendRv.setAdapter(recommendRVAdapter);
                // 设置数据后就要给RecyclerView设置点击事件
                recommendRVAdapter.setOnItemClickListener(new RecommendRVAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        // 这里本来是跳转页面 ，我们就在这里直接让其弹toast来演示
                        ToastUtils.show(mGamelist.get(position).getApp_name());
                    }
                });
                break;

        }
        recommendRefreshLayout.finishRefresh();//结束刷新
    }

    @Override
    public void onFailed(int what, Response<String> response) {

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
}
