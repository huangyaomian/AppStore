package com.hym.appstore.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
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
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.xuexiang.xui.utils.ResUtils.getResources;

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

      /*  GoodsInfoBean.GoodlistBean goodsBean = goodsList.get(position);
//        Log.d("onBindViewHolder",goodsBean.getTitle());
        holder.mItmeGoodsTitle.setText(goodsBean.getTitle());
        holder.mItemGoodsShortTitle.setText(goodsList.get(position).getTitle());
        holder.mItemGoodsPrice.setText("￥" +goodsList.get(position).getPrice());
        holder.mItemGoodsValue.setText("￥" +goodsList.get(position).getValue());
        holder.mItemGoodsValue.setPaintFlags(holder.mItemGoodsValue.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.mItemGoodsBought.setText("已售:" + goodsList.get(position).getBought());
        String[] picUrl = getResources().getStringArray(R.array.pic_url);
        Random random = new Random();
        int i = random.nextInt(16);
        Uri uri = Uri.parse(picUrl[i]);
        holder.mItmeGoodsIv.setImageURI(uri);

        // 点击事件一般都写在绑定数据这里，当然写到上边的创建布局时候也是可以的
        if (mItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 这里利用回调来给RecyclerView设置点击事件
                    mItemClickListener.onItemClick(position);
                }
            });
        }*/
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
