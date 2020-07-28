package com.hym.appstore.ui.activity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hym.appstore.R;
import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.bean.FragmentInfo;
import com.hym.appstore.common.Constant;
import com.hym.appstore.common.utils.ACache;
import com.hym.appstore.common.utils.JsonUtils;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.ui.adapter.AppInfoAdapter;
import com.hym.appstore.ui.adapter.MyViewPagerAdapter;
import com.hym.appstore.ui.fragment.DownloadedFragment;
import com.hym.appstore.ui.fragment.DownloadingFragment;
import com.hym.appstore.ui.fragment.InstalledAppAppFragment;
import com.hym.appstore.ui.fragment.UpgradeAppFragment;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Cache;

public class AppHomeActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;


    private AppInfoAdapter mAdapter;


    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_app_game_home;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        toolbar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.theme_black)
                        )
        );

    }


    @Override
    public void initView() {
        mAdapter = AppInfoAdapter.builder().updateStatus(true).showBrief(true).rxDownload(null).build();
        String asString = ACache.get(this).getAsString(Constant.APP_HOME_LIST);
        Type type = new TypeToken<List<AppInfoBean>>() {}.getType();
        Gson gson = new Gson();;
        List<AppInfoBean> list = gson.fromJson(asString, type);
        mAdapter.addData(list);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void initEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


}
