package com.hym.appstore.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.core.view.ActionProvider;

import com.hym.appstore.R;
import com.hym.appstore.presenter.contract.RecommendContract;

public class BadgeActionProvider extends ActionProvider {

    private ImageView mIcon;
    private TextView mTxtBadge;

    private View.OnClickListener mOnClickListener;

    public BadgeActionProvider(Context context) {
        super(context);
    }

    @Override
    public View onCreateActionView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.menu_badge_provider, null, false);
        mIcon = view.findViewById(R.id.img_app_icon);
        mTxtBadge = view.findViewById(R.id.txt_badge);

        view.setOnClickListener(new BadgeMenuClickListener());
        return view;
    }

    public void setIcon(Drawable drawable){
        mIcon.setImageDrawable(drawable);
    }

    public void setIcon(@DrawableRes int res){
        mIcon.setImageResource(res);
    }

    public void setText(CharSequence c){

    }

    public void setBadge(int num){
        mTxtBadge.setText(num +"");
    }

    private class BadgeMenuClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if (mOnClickListener != null) {
                mOnClickListener.onClick(v);
            }
        }
    }


}
