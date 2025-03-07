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
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.leanback.widget.ListRow;

import com.bugermaker.tvapplication.R;
import com.bugermaker.tvapplication.base.BaseLazyLoadFragment;
import com.bugermaker.tvapplication.base.BasePresenterSelector;
import com.bugermaker.tvapplication.bean.Content;
import com.bugermaker.tvapplication.bean.Footer;
import com.bugermaker.tvapplication.presenter.ContentListRowPresenter;
import com.bugermaker.tvapplication.presenter.TypeFiveContentPresenter;
import com.bugermaker.tvapplication.presenter.TypeFooterPresenter;
import com.bugermaker.tvapplication.presenter.TypeFourContentPresenter;
import com.bugermaker.tvapplication.presenter.TypeOneContentPresenter;
import com.bugermaker.tvapplication.presenter.TypeSixContentPresenter;
import com.bugermaker.tvapplication.presenter.TypeThreeContentPresenter;
import com.bugermaker.tvapplication.presenter.TypeTwoContentPresenter;
import com.bugermaker.tvapplication.presenter.TypeZeroContentPresenter;
import com.bugermaker.tvapplication.utils.Constants;
import com.bugermaker.tvapplication.utils.FontDisplayUtil;
import com.bugermaker.tvapplication.utils.LocalJsonResolutionUtil;
import com.bugermaker.tvapplication.widgets.TabVerticalGridView;

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
                    //首先对不同页面的数据进行分离
                    json = LocalJsonResolutionUtil.getJson(activity, "My.json");
                    break;
                case 1:
                    json = LocalJsonResolutionUtil.getJson(activity, "WatchTv.json");
                    break;
                case 2:
                    json = LocalJsonResolutionUtil.getJson(activity, "Clear4k.json");
                    break;
                case 3:
                    json = LocalJsonResolutionUtil.getJson(activity, "Children.json");
                    break;
                case 4:
                    json = LocalJsonResolutionUtil.getJson(activity, "Featured.json");
                    break;
                case 5:
                    json = LocalJsonResolutionUtil.getJson(activity, "Years70.json");
                    break;
                case 6:
                    json = LocalJsonResolutionUtil.getJson(activity, "Everything.json");
                    break;
                case 7:
                    json = LocalJsonResolutionUtil.getJson(activity, "VIP.json");
                    break;
                case 8:
                    json = LocalJsonResolutionUtil.getJson(activity, "TVSeries.json");
                    break;
                case 9:
                    json = LocalJsonResolutionUtil.getJson(activity, "Movie.json");
                    break;
                case 10:
                    json = LocalJsonResolutionUtil.getJson(activity, "Variety.json");
                    break;
                case 11:
                    json = LocalJsonResolutionUtil.getJson(activity, "Classroom.json");
                    break;
                case 12:
                    json = LocalJsonResolutionUtil.getJson(activity, "Anime.json");
                    break;
                case 13:
                    json = LocalJsonResolutionUtil.getJson(activity, "Basketball.json");
                    break;
                case 14:
                    json = LocalJsonResolutionUtil.getJson(activity, "Physical.json");
                    break;
                case 15:
                    json = LocalJsonResolutionUtil.getJson(activity, "Game.json");
                    break;
                case 16:
                    json = LocalJsonResolutionUtil.getJson(activity, "Documentary.json");
                    break;
                case 17:
                    json = LocalJsonResolutionUtil.getJson(activity, "Life.json");
                    break;
                case 18:
                    json = LocalJsonResolutionUtil.getJson(activity, "OrientalTheatre.json");
                    break;
                case 19:
                    json = LocalJsonResolutionUtil.getJson(activity, "Car.json");
                    break;
                case 20:
                    json = LocalJsonResolutionUtil.getJson(activity, "Funny.json");
                    break;
            }
            if (json == null)
                return;
            Content content = LocalJsonResolutionUtil.JsonToObject(json, Content.class);
            List<Content.DataBean> dataBeanList = content.getData();

            //从这里开始对具体页面的不同数据进行处理
            for (Content.DataBean dataBean : dataBeanList){
                addItem(dataBean);
            }
            //最后为每个页面单独添加一个页脚
            mArrayMainAdapter.add(new Footer());

            //每个页面加载完成后，在这里延迟1s，模拟数据加载，否则看不到Loading的变化
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPbLoading.setVisibility(View.INVISIBLE);
                    mVerticalGridView.setVisibility(View.VISIBLE);
                }
            }, 1000);
        }
    });

    private void addItem(Content.DataBean list){
        List<Content.DataBean.WidgetsBean> widgetsBeans = list.getWidgets();
        switch (list.getContentCode()){
            case Constants.TYPE_ZERO:
                //这里只保留两个，如需显示更多，需要自定义ViewPager通过禁止滑动切页 来完全显示
                if (widgetsBeans != null && widgetsBeans.size() > 2){
                    Log.d(TAG, "Constants.TYPE_ZERO ==>widgetsBeans.size():::" + widgetsBeans.size());
                    widgetsBeans = widgetsBeans.subList(0, 2);
                }
                ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(new TypeZeroContentPresenter());
                arrayObjectAdapter.addAll(0, widgetsBeans);
                ListRow listRow = new ListRow(arrayObjectAdapter);
                addWithTryCatch(listRow);
                break;
            case Constants.TYPE_ONE:
                if (widgetsBeans != null && widgetsBeans.size() > 4){
                    Log.d(TAG, "Constants.TYPE_ONE ==>widgetsBeans.size():::" + widgetsBeans.size());
                    widgetsBeans = widgetsBeans.subList(0, 4);
                }
                ArrayObjectAdapter adapterOne = new ArrayObjectAdapter(new TypeOneContentPresenter());
                adapterOne.addAll(0, widgetsBeans);
                HeaderItem headerItem = null;
                if (list.getShowTitle()){
                    headerItem = new HeaderItem(list.getTitle());
                }
                addWithTryCatch(new ListRow(headerItem, adapterOne));
                break;
            case Constants.TYPE_TWO:
                if (widgetsBeans != null && widgetsBeans.size() > 3){
                    Log.d(TAG, "Constants.TYPE_TWO ==>widgetsBeans.size():::" + widgetsBeans.size());
                    widgetsBeans = widgetsBeans.subList(0, 3);
                }
                ArrayObjectAdapter adapterTwo = new ArrayObjectAdapter(new TypeTwoContentPresenter());
                adapterTwo.addAll(0, widgetsBeans);
                HeaderItem headerItemTwo = null;
                if (list.getShowTitle()){
                    headerItemTwo = new HeaderItem(list.getTitle());
                }
                addWithTryCatch(new ListRow(headerItemTwo, adapterTwo));
                break;
            case Constants.TYPE_THREE:
                if (widgetsBeans != null && widgetsBeans.size() > 6){
                    Log.d(TAG, "Constants.TYPE_THREE ==>widgetsBeans.size():::" + widgetsBeans.size());
                    widgetsBeans = widgetsBeans.subList(0, 6);
                }
                ArrayObjectAdapter adapterThree = new ArrayObjectAdapter(new TypeThreeContentPresenter());
                adapterThree.addAll(0, widgetsBeans);
                HeaderItem headerItemThree = null;
                if (list.getShowTitle()){
                    headerItemThree = new HeaderItem(list.getTitle());
                }
                addWithTryCatch(new ListRow(headerItemThree, adapterThree));
                break;
            case Constants.TYPE_FOUR:
                if (widgetsBeans != null && widgetsBeans.size() > 6){
                    Log.d(TAG, "Constants.TYPE_FOUR ==>widgetsBeans.size():::" + widgetsBeans.size());
                    widgetsBeans = widgetsBeans.subList(0, 6);
                }
                ArrayObjectAdapter adapterFour = new ArrayObjectAdapter(new TypeFourContentPresenter());
                adapterFour.addAll(0, widgetsBeans);

                addWithTryCatch(new ListRow(adapterFour));
                break;
            case Constants.TYPE_FIVE:
                if (widgetsBeans != null && widgetsBeans.size() > 3){
                    Log.d(TAG, "Constants.TYPE_FIVE ==>widgetsBeans.size():::" + widgetsBeans.size());
                    widgetsBeans = widgetsBeans.subList(0, 3);
                }
                ArrayObjectAdapter adapterFive = new ArrayObjectAdapter(new TypeFiveContentPresenter());
                adapterFive.addAll(0, widgetsBeans);
                HeaderItem headerItemFive = null;
                if (list.getShowTitle()){
                    headerItemFive = new HeaderItem(list.getTitle());
                }
                addWithTryCatch(new ListRow(headerItemFive, adapterFive));
                break;
            case Constants.TYPE_SIX:
                if (widgetsBeans != null && widgetsBeans.size() > 6){
                    Log.d(TAG, "Constants.TYPE_SIX ==>widgetsBeans.size():::" + widgetsBeans.size());
                    widgetsBeans = widgetsBeans.subList(0, 6);
                }
                ArrayObjectAdapter adapterSix= new ArrayObjectAdapter(new TypeSixContentPresenter());
                adapterSix.addAll(0, widgetsBeans);
                HeaderItem headerItemSix = null;
                if (list.getShowTitle()){
                    headerItemSix = new HeaderItem(list.getTitle());
                }
                addWithTryCatch(new ListRow(headerItemSix, adapterSix));
                break;
        }
    }


    //java.lang.IllegalStateException: Cannot call this method while RecyclerView is computing a layout or scrolling com.bugermaker.tvapplication.widgets.TabVerticalGridView{5f8d986 IFE...... ......ID 0,0-1920,1080 #7f08007d app:id/hg_content}, adapter:androidx.leanback.widget.ItemBridgeAdapter@dfa247, layout:androidx.leanback.widget.GridLayoutManager@c93ea74, context:com.bugermaker.tvapplication.MainActivity@111a524
    //由于添加的时候会报错，所以需要单独处理一下
    private void addWithTryCatch(ListRow listRow){
        try{
            if (!mVerticalGridView.isComputingLayout()){
                mArrayMainAdapter.add(listRow);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

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
        //看了项目源码，觉得这里用不上自定义PresenterSelector，暂时不做修改
//        ContentListRowPresenter  listRowPresenter= new ContentListRowPresenter();
//        listRowPresenter.setShadowEnabled(false);
//        listRowPresenter.setSelectEffectEnabled(false);
//        listRowPresenter.setKeepChildForeground(false);
        mArrayMainAdapter = new ArrayObjectAdapter(new BasePresenterSelector());
        ItemBridgeAdapter itemBridgeAdapter = new ItemBridgeAdapter(mArrayMainAdapter);
        mVerticalGridView.setAdapter(itemBridgeAdapter);
    }
}
