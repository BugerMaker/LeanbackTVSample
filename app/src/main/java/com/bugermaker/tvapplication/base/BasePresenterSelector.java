package com.bugermaker.tvapplication.base;

import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;

import java.util.ArrayList;
import java.util.List;

/**
 * 如何根据不同的item.Type进行适配呢？
 */
public class BasePresenterSelector extends PresenterSelector {
    private final List<Presenter> mPresenters = new ArrayList<>();

    @Override
    public Presenter getPresenter(Object item) {
        return null;
    }

    @Override
    public Presenter[] getPresenters() {
        return mPresenters.toArray(new Presenter[]{});
    }
}
