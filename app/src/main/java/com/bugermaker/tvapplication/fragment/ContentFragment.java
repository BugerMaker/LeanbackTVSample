package com.bugermaker.tvapplication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.leanback.widget.ListRow;

import com.bugermaker.tvapplication.R;
import com.bugermaker.tvapplication.base.BaseLazyLoadFragment;
import com.bugermaker.tvapplication.bean.Content;
import com.bugermaker.tvapplication.presenter.ContentListRowPresenter;
import com.bugermaker.tvapplication.presenter.TypeOneContentPresenter;
import com.bugermaker.tvapplication.utils.FontDisplayUtil;
import com.bugermaker.tvapplication.utils.LocalJsonResolutionUtil;
import com.bugermaker.tvapplication.widgets.TabVerticalGridView;

import java.util.ArrayList;
import java.util.List;

public class ContentFragment extends BaseLazyLoadFragment {
    private static final String TAG = "ContentFragment";

    private View mRootView;

    private TabVerticalGridView mVerticalGridView;
    private ProgressBar mPbLoading;

    private int mCurPosition;
    private String mCurTabCode;

    private ArrayObjectAdapter mArrayMainAdapter;

    private static final String BUNDLE_KEY_POSITION = "bundleKeyPosition";
    private static final String BUNDLE_KEY_TAB_CODE = "bundleKeyTabCode";

    private Handler mHandler = new Handler();
    //创建实体的静态方法
    public static ContentFragment newInstance(int position, String tabCode) {
        ContentFragment fragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_KEY_POSITION, position);
        bundle.putString(BUNDLE_KEY_TAB_CODE, tabCode);
        fragment.setArguments(bundle);
        return fragment;
    }

    //在这里执行数据的加载
    @Override
    protected void loadData() {
        Log.d(TAG, "当前的位置为" + mCurPosition);
        mPbLoading.setVisibility(View.VISIBLE);
        mVerticalGridView.setVisibility(View.INVISIBLE);
        //通过线程进行json数据的加载
        mThread.start();
    }

    //创建一个线程进行耗时操作
    private Thread mThread = new Thread(new Runnable() {
        @Override
        public void run() {
            //根据不同的页面加载不同的内容
            FragmentActivity activity = getActivity();
            String json = null;
            switch(mCurPosition){
                case 0:
                    json = LocalJsonResolutionUtil.getJson(activity, "My.json");
                    Content content = LocalJsonResolutionUtil.JsonToObject(json, Content.class);
                    List<Content.DataBean> dataBeanList = content.getData();
                    List<Content.DataBean.WidgetsBean> widgetsBeans = new ArrayList<>();
                    for (int i=0; i<dataBeanList.size(); i++){
                        widgetsBeans.addAll(dataBeanList.get(i).getWidgets());
                    }
                    if (widgetsBeans.size() > 2){
                        widgetsBeans = widgetsBeans.subList(0, 2);
                    }
                    ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(new TypeOneContentPresenter());

                    arrayObjectAdapter.addAll(0, widgetsBeans);

                    ListRow listRow = new ListRow(arrayObjectAdapter);
                    mArrayMainAdapter.add(listRow);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mPbLoading.setVisibility(View.INVISIBLE);
                            mVerticalGridView.setVisibility(View.VISIBLE);
                        }
                    });
                    break;

                case 1:
                    break;
            }
        }
    });

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null)
            return;
        mCurPosition = bundle.getInt(BUNDLE_KEY_POSITION);
        mCurTabCode = bundle.getString(BUNDLE_KEY_TAB_CODE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //由于ViewPager的缓存机制，在此进行view的复用，防止onCreateView()重复创建view
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_content, container, false);
            initView();
        }

        return mRootView;
    }

    private void initView() {
        mVerticalGridView = mRootView.findViewById(R.id.hg_content);
        mPbLoading = mRootView.findViewById(R.id.pb_loading);

        //设置行内各个元素之间的水平间距
        mVerticalGridView.setHorizontalSpacing(FontDisplayUtil.dip2px(getActivity(), 24));
        //开始填充VerticalGridView，需要自定义PresenterSelector，对不同的数据进行不同的处理

        //这里先暂时使用自定义的ListRowPresenter做简单效果展示
        ContentListRowPresenter  listRowPresenter= new ContentListRowPresenter();
        listRowPresenter.setShadowEnabled(false);
        listRowPresenter.setSelectEffectEnabled(false);
        listRowPresenter.setKeepChildForeground(false);
        mArrayMainAdapter = new ArrayObjectAdapter(listRowPresenter);
        ItemBridgeAdapter itemBridgeAdapter = new ItemBridgeAdapter(mArrayMainAdapter);
        mVerticalGridView.setAdapter(itemBridgeAdapter);
    }
}
