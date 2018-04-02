package cn.edu.gdmec.android.boxuegu.view;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;

import cn.edu.gdmec.android.boxuegu.adapter.AdBannerAdapter;

/**
 * Created by asus on 2018/4/2.
 */

public class CourseView {
    private ListView lv_list;
    private CursorAdapter adapter;
    private FragmentActivity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;
    private ViewPager adPager;
    private View adBannerLay;
    private AdBannerAdapter ada;
    public static final int MSG_AD_SLID = 002;
    private ViewPagerIndicator vpi;

}
