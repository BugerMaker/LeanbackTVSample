package com.bugermaker.tvapplication.base;

import android.util.ArrayMap;

import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;

import com.bugermaker.tvapplication.bean.Footer;
import com.bugermaker.tvapplication.presenter.ContentListRowPresenter;
import com.bugermaker.tvapplication.presenter.TypeFooterPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 如何根据不同的item.Type进行适配呢？
 */
public class BasePresenterSelector extends PresenterSelector {
    private final List<Presenter> mPresenters = new ArrayList<>();

    @Override
    public Presenter getPresenter(Object item) {
        if(item instanceof ListRow){
            ContentListRowPresenter  listRowPresenter= new ContentListRowPresenter();
            listRowPresenter.setShadowEnabled(false);
            listRowPresenter.setSelectEffectEnabled(false);
            listRowPresenter.setKeepChildForeground(false);
            return listRowPresenter;
        } else if (item instanceof Footer){
            TypeFooterPresenter typeFooterPresenter = new TypeFooterPresenter();
            return typeFooterPresenter;
        }

        return null;
    }

    @Override
    public Presenter[] getPresenters() {
        return mPresenters.toArray(new Presenter[]{});
    }
}
