package com.bugermaker.tvapplication.widgets;

import android.content.Context;
import android.util.AttributeSet;

import androidx.leanback.widget.HorizontalGridView;

public class TabHorizontalGridView extends HorizontalGridView {
    public TabHorizontalGridView(Context context) {
        this(context, null);
    }

    public TabHorizontalGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabHorizontalGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
