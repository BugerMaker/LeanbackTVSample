package com.bugermaker.tvapplication.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.leanback.widget.Presenter;

import com.bugermaker.tvapplication.R;
import com.bugermaker.tvapplication.bean.Content;
import com.bugermaker.tvapplication.utils.FontDisplayUtil;
import com.bumptech.glide.Glide;

public class TypeThreeContentPresenter extends Presenter{
    private static final String TAG = "TypeOneContentPresenter";

    private Context mContext;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_type_three_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        if (item instanceof Content.DataBean.WidgetsBean){
            ViewHolder holder = (ViewHolder) viewHolder;
            //使用Glide来加载图片
            Glide.with(mContext)
                    .load(((Content.DataBean.WidgetsBean) item).getUrl())
                    .centerCrop()
                    .override(FontDisplayUtil.dip2px(mContext, 124),
                            FontDisplayUtil.dip2px(mContext, 186))
                    .placeholder(R.drawable.bg_shape_default)
                    .into(holder.imageView);
        }
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

    }

    public static class ViewHolder extends Presenter.ViewHolder{
        private final ImageView imageView;
        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.iv_type_three_poster);
        }
    }
}
