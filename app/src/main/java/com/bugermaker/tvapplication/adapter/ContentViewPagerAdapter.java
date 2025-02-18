package com.bugermaker.tvapplication.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.bugermaker.tvapplication.bean.Title;
import com.bugermaker.tvapplication.fragment.ContentFragment;

import java.util.List;

public class ContentViewPagerAdapter extends FragmentStatePagerAdapter  {

    private List<Title.DataBean> dataBeans;

    public ContentViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ContentFragment.newInstance(position, dataBeans.get(position).getTabCode());
    }

    @Override
    public int getCount() {
        return dataBeans.size() == 0? 0:dataBeans.size();
    }

    public void setDataBeans(List<Title.DataBean> rowBeans){
        dataBeans = rowBeans;
    }
}
