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
import android.widget.LinearLayout;

import com.hym.appstore.R;
import com.hym.appstore.common.utils.DensityUtil;
import com.hym.appstore.dagger2.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppDetailsActivity extends BaseActivity {


    @BindView(R.id.app_details_fl)
    FrameLayout frameLayout;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_app_details;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        View view = mMyApplication.getView();
        Bitmap bitmap = getViewImageCache(view);
        if (bitmap != null) {
            frameLayout.setBackground(new BitmapDrawable(bitmap));
        }

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];

        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(frameLayout.getLayoutParams());
        marginLayoutParams.topMargin = top;
        marginLayoutParams.leftMargin = left;
        marginLayoutParams.width = view.getWidth();
        marginLayoutParams.height = view.getHeight();


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(marginLayoutParams);
        frameLayout.setLayoutParams(params);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void open(){
        int h = DensityUtil.getScreenH(this);
        ObjectAnimator animator = ObjectAnimator.ofFloat(frameLayout, "scaleY", 1f, (float) h);
        animator.setStartDelay(500);
//        animator.setDuration(500);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                frameLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                frameLayout.setBackgroundColor(getResources().getColor(R.color.theme_while));
            }
        });
        animator.start();
    }
}
