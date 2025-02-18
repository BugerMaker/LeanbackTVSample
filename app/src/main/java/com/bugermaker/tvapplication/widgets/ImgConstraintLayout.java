package com.bugermaker.tvapplication.widgets;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;

public class ImgConstraintLayout extends ConstraintLayout implements View.OnFocusChangeListener{
    public ImgConstraintLayout(Context context) {
        this(context, null);
    }

    public ImgConstraintLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImgConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            v.bringToFront();
        }
    }
}
