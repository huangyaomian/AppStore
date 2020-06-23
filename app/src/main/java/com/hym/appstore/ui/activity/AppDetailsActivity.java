package com.hym.appstore.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hym.appstore.R;
import com.hym.appstore.dagger2.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppDetailsActivity extends BaseActivity {


    @BindView(R.id.imgView)
    ImageView imgView;

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
            imgView.setImageBitmap(bitmap);
        }

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];

        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(imgView.getLayoutParams());
        marginLayoutParams.topMargin = top;
        marginLayoutParams.leftMargin = left;
        marginLayoutParams.width = view.getWidth();
        marginLayoutParams.height = view.getHeight();


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(marginLayoutParams);
        imgView.setLayoutParams(params);

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
}
