package com.bugermaker.tvapplication.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 懒加载机制的BaseFragment
 */
public abstract class BaseLazyLoadFragment extends Fragment {

    // 标记 Fragment 是否已经加载过数据
    private boolean isDataLoaded = false;

    // 标记 Fragment 当前是否可见
    private boolean isVisibleToUser = false;

    // 标记 View 是否已经初始化完成
    private boolean isViewCreated = false;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        performLazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        performLazyLoad();
    }

/**
 * 由于ViewPager的缓存机制
 * 不能在onResume中设置isVisibleToUser = true;否则将导致懒加载机制失效
 **/
//    @Override
//    public void onResume() {
//        super.onResume();
//        isVisibleToUser = true;
//        performLazyLoad();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        isVisibleToUser = false;
//    }

    /**
     * 执行懒加载逻辑
     */
    private void performLazyLoad() {
        if (isViewCreated && isVisibleToUser && !isDataLoaded) {
            loadData();
            isDataLoaded = true; // 确保数据只加载一次
        }
    }

    /**
     * 子类实现此方法加载数据
     */
    protected abstract void loadData();
}
