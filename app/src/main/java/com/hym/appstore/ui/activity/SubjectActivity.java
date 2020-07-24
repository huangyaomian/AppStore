package com.hym.appstore.ui.activity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.hym.appstore.R;
import com.hym.appstore.dagger2.component.AppComponent;
import com.hym.appstore.ui.fragment.SubjectDetailFragment;
import com.hym.appstore.ui.fragment.SubjectFragment;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import butterknife.BindView;

public class SubjectActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    private FragmentManager mFragmentManager;
    SubjectFragment mSubjectFragment;
    SubjectDetailFragment mSubjectDetailFragment;

    public static final int FRAGMENT_SUBJECT = 0;
    public static final int FRAGMENT_DETAIL = 1;

    private int fragmentIndex = FRAGMENT_SUBJECT;


    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_subject;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        mToolBar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.theme_black)
                        )
        );

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handNavigation();
            }
        });
        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    public void initView() {
        showSubjectFragment();
    }

    @Override
    public void initEvent() {

    }

    private void showSubjectFragment(){
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (mSubjectFragment == null) {
            mSubjectFragment = new SubjectFragment();
            fragmentTransaction.add(R.id.content,mSubjectFragment);
        }else {
            fragmentTransaction.show(mSubjectFragment);
        }
        fragmentTransaction.commit();
    }

    private void handNavigation() {
        if (fragmentIndex == FRAGMENT_SUBJECT) {
            finish();
        } else {
            showSubjectFragment();
        }
    }
}
