package com.bugermaker.tvapplication.presenter;

import com.bugermaker.tvapplication.base.BasePresenterSelector;

public class ContentPresenterSelector extends BasePresenterSelector {
    //创建无参构造方法，初始化所有的Presenter
    public ContentPresenterSelector(){

        //
        ContentListRowPresenter listRowPresenter = new ContentListRowPresenter();
        listRowPresenter.setShadowEnabled(false);
        listRowPresenter.setSelectEffectEnabled(false);
        listRowPresenter.setKeepChildForeground(false);
    }

}
