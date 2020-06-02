package com.hym.appstore.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hym.appstore.R;
import com.hym.appstore.bean.RecommendBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecommendRVAdapter extends RecyclerView.Adapter<RecommendRVAdapter.MyViewHolder> {

    List<RecommendBean.DataBean.ItemsBean> gameInfoList;

    private Context mContext;

    public RecommendRVAdapter(List<RecommendBean.DataBean.ItemsBean> gameInfoList, Context context) {
        this.gameInfoList = gameInfoList;
        this.mContext = context;
    }

    // 利用接口 -> 给RecyclerView设置点击事件
    private ItemClickListener mItemClickListener;

    public interface ItemClickListener {
        public void onItemClick(int position);
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recommend_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return gameInfoList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recommend_item_iv)
        ImageView mRecommendItemIv;
        @BindView(R.id.recommend_item_game_name)
        TextView mRecommendItemGameName;
        @BindView(R.id.recommend_item_game_tag)
        TextView mRecommendItemGameTag;
        @BindView(R.id.recommend_item_game_review)
        TextView mRecommendItemGameReview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
