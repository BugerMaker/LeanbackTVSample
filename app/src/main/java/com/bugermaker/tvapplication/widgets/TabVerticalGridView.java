package com.bugermaker.tvapplication.widgets;

import android.content.Context;
import android.util.AttributeSet;

import androidx.leanback.widget.VerticalGridView;

public class TabVerticalGridView extends VerticalGridView {

    public TabVerticalGridView(Context context) {
        this(context, null);
    }

    public TabVerticalGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabVerticalGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
