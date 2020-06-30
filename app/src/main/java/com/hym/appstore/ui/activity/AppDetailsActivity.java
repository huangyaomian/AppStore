package com.hym.appstore.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.hym.appstore.R;
import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.common.utils.DensityUtil;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.ui.fragment.AppDetailFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppDetailsActivity extends BaseActivity {


    @BindView(R.id.app_details_fl)
    FrameLayout frameLayout;
    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.view_temp)
    View viewTemp;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_coordinator)
    CoordinatorLayout viewCoordinator;


    private AppInfoBean mAppInfoBean;


    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_app_details;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {

        mAppInfoBean = (AppInfoBean) getIntent().getSerializableExtra("appInfo");
        initFragment();

        View view = mMyApplication.getView();
        Bitmap bitmap = getViewImageCache(view);
        if (bitmap != null) {
            viewTemp.setBackground(new BitmapDrawable(bitmap));
        }

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];

        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(viewTemp.getLayoutParams());
        marginLayoutParams.topMargin = top;
        marginLayoutParams.leftMargin = left;
        marginLayoutParams.width = view.getWidth();
        marginLayoutParams.height = view.getHeight();


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(marginLayoutParams);
        viewTemp.setLayoutParams(params);

        open();

    }


    @Override
    public void initView() {

    }

    @Override
    public void initEvent() {

    }

    private Bitmap getViewImageCache(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();

        Bitmap bitmap = view.getDrawingCache();
        if (bitmap == null) {
            return null;
        }

        bitmap = Bitmap.createBitmap(bitmap);

        view.destroyDrawingCache();

        return bitmap;
    }


    private void open() {
        int h = DensityUtil.getScreenH(this);
        ObjectAnimator animator = ObjectAnimator.ofFloat(viewTemp, "scaleY", 1f, (float) h);
        animator.setStartDelay(500);
        animator.setDuration(2000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                viewTemp.setVisibility(View.GONE);
                viewCoordinator.setVisibility(View.VISIBLE);
                initFragment();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                viewTemp.setBackgroundColor(getResources().getColor(R.color.theme_while));
            }
        });
        animator.start();
    }

    private void initFragment() {
        AppDetailFragment fragment = new AppDetailFragment(mAppInfoBean.getId());
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.app_details_fl, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }


}
