package com.bugermaker.tvapplication;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HorizontalGridView;
import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bugermaker.tvapplication.bean.Title;
import com.bugermaker.tvapplication.controller.TitlePresenter;
import com.bugermaker.tvapplication.utils.LocalJsonResolutionUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/*
 * Main Activity class that loads {@link MainFragment}.
 */
public class MainActivity extends FragmentActivity {

    private ArrayObjectAdapter mArrayObjectAdapter;
    private HorizontalGridView mHorizontalGridView;

    private ViewPager viewPager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
//
        initView();
        initData();
    }

    private void initView(){
        mHorizontalGridView = findViewById(R.id.hg_title);
        mHorizontalGridView.setHorizontalSpacing(10);
        mArrayObjectAdapter = new ArrayObjectAdapter(new TitlePresenter());
        ItemBridgeAdapter itemBridgeAdapter = new ItemBridgeAdapter(mArrayObjectAdapter);
        mHorizontalGridView.setAdapter(itemBridgeAdapter);

        viewPager = findViewById(R.id.vp_content);
    }

    private void initData(){
        String json = LocalJsonResolutionUtil.getJson(MainActivity.this, "MyTitle.json");
        Title title = LocalJsonResolutionUtil.JsonToObject(json, Title.class);

        List<Title.DataBean> data = title.getData();
        mArrayObjectAdapter.addAll(0, data);
    }
}