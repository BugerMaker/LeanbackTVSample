package com.bugermaker.tvapplication.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bugermaker.tvapplication.R;
import com.bugermaker.tvapplication.base.BaseLazyLoadFragment;
import com.bugermaker.tvapplication.widgets.TabVerticalGridView;

public class ContentFragment extends BaseLazyLoadFragment {
    private static final String TAG = "ContentFragment";

    private View mRootView;

    private TabVerticalGridView mVerticalGridView;
    private ProgressBar mPbLoading;

    private int mCurPostion;
    //创建实体的静态方法
    public static ContentFragment newInstance(int position){
        ContentFragment fragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    //在这里执行数据的加载
    @Override
    protected void loadData() {
        Log.d(TAG, "当前的位置为" + mCurPostion);
        if (mCurPostion % 2 == 0){
            mPbLoading.setVisibility(View.VISIBLE);
            mVerticalGridView.setVisibility(View.INVISIBLE);
        }else{
            mPbLoading.setVisibility(View.INVISIBLE);
            mVerticalGridView.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mCurPostion = bundle.getInt("position");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //由于ViewPager的缓存机制，在此进行view的复用，防止onCreateView()重复创建view
        if (mRootView == null){
            mRootView = inflater.inflate(R.layout.fragment_content, container, false);
            initView();
        }

        return mRootView;
    }

    private void initView() {
        mVerticalGridView = mRootView.findViewById(R.id.hg_content);
        mPbLoading = mRootView.findViewById(R.id.pb_loading);
    }
}
