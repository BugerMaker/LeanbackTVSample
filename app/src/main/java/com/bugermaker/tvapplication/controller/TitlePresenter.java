package com.bugermaker.tvapplication.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.leanback.widget.Presenter;

import com.bugermaker.tvapplication.R;
import com.bugermaker.tvapplication.bean.Title;

public class TitlePresenter extends Presenter {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_title, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        if (item instanceof Title.DataBean){
            ViewHolder holder = (ViewHolder) viewHolder;
            holder.textView.setText(((Title.DataBean) item).getName());
        }
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

    }

    static class ViewHolder extends Presenter.ViewHolder{
        TextView textView;
        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.tv_main_title);
        }
    }
}
