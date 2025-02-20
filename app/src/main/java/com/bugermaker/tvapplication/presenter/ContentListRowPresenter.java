package com.bugermaker.tvapplication.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.leanback.R;
import androidx.leanback.widget.HorizontalGridView;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.RowPresenter;

import com.bugermaker.tvapplication.utils.FontDisplayUtil;

public class ContentListRowPresenter extends ListRowPresenter {
    private static final String TAG = "ContentListRowPresenter";

    @SuppressLint("RestrictedApi")
    @Override
    protected void initializeRowViewHolder(RowPresenter.ViewHolder holder) {
        super.initializeRowViewHolder(holder);

        final ViewHolder rowViewHolder = (ViewHolder) holder;
        //设置每行项目的水平间距为24dp
        rowViewHolder.getGridView().setHorizontalSpacing(FontDisplayUtil.dip2px(rowViewHolder.getGridView().getContext(), 24));
        rowViewHolder.getGridView().setFocusScrollStrategy(HorizontalGridView.FOCUS_SCROLL_ITEM);
//        TextView textView = holder.getHeaderViewHolder().view.findViewById(R.id.row_header);
//        textView.setPadding(0, 0, 0, 20);
//        Log.d(TAG, "当前页面的textView为" + textView.getText());
        /**
         * 这里有个问题是，ListRowPresenter会修改Padding，
         * 将它强制设为0
         */
//        int paddingLeft = rowViewHolder.getGridView().getPaddingLeft();
//        Log.i(TAG, "" +
//                "当前页面的左边距PaddingLeft为" + paddingLeft);
        ((ViewHolder) holder).getGridView().setPadding(0, 0, 0, 40);

    }
}
