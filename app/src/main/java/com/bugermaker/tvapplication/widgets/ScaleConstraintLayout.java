package com.bugermaker.tvapplication.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.bugermaker.tvapplication.R;

public class ScaleConstraintLayout extends ConstraintLayout {
    private String tv_content;
    private Drawable iv_image;


    private ImageView imageView;
    private TextView textView;

    public ScaleConstraintLayout(Context context) {
        this(context, null);
    }

    public ScaleConstraintLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFocusable(true);
        setClickable(true);
        setFocusableInTouchMode(true);

        initTypeValue(context, attrs);

        initView(context);
    }

    public void initTypeValue(Context context, AttributeSet atts){
        TypedArray a = context.obtainStyledAttributes(atts, R.styleable.ScaleConstraintLayout);
        tv_content = a.getString(R.styleable.ScaleConstraintLayout_tv_content);
        iv_image = a.getDrawable(R.styleable.ScaleConstraintLayout_iv_image);
        a.recycle();
    }

    public void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.custom_titlebar, this, true);
        textView = findViewById(R.id.tv_content);
        imageView = findViewById(R.id.iv_image);
        if (tv_content != null){
            textView.setText(tv_content);
        }
        if (iv_image != null){
            imageView.setImageDrawable(iv_image);
        }

    }
}
