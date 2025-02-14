package com.bugermaker.tvapplication.controller;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.RowHeaderPresenter;

import com.bugermaker.tvapplication.R;

public class IconHeaderItemPresenter extends RowHeaderPresenter {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View view = inflater.inflate(R.layout.icon_header_item, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object o) {
        HeaderItem headerItem = ((ListRow) o).getHeaderItem();
        View rootView = viewHolder.view;

        ImageView iconView = (ImageView) rootView.findViewById(R.id.header_icon);
        Drawable icon = rootView.getResources().getDrawable(R.drawable.vip, null);
        iconView.setImageDrawable(icon);

        TextView label = (TextView) rootView.findViewById(R.id.header_label);
        label.setText(headerItem.getName());
    }

    //当然你也可以在这里自定义viewHolder，使样式更加丰富
    static class HeaderViewHolder extends Presenter.ViewHolder{

        public HeaderViewHolder(View view) {
            super(view);
        }
    }
    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        // no-op
    }
}