package cn.edu.gdmec.android.boxuegu.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

import cn.edu.gdmec.android.boxuegu.Fragment.AdBannerFragment;
import cn.edu.gdmec.android.boxuegu.activity.CourseBean;
import cn.edu.gdmec.android.boxuegu.view.CourseView;

/**
 * Created by asus on 2018/4/2.
 */

public class AdBannerAdapter extends FragmentStatePagerAdapter implements View.OnTouchListener{
  private Handler mHandler;
  private List<CourseBean> cadl;
  public AdBannerAdapter(FragmentManager fm){
      super(fm);
      cadl = new ArrayList<CourseBean>();
  }
  public AdBannerAdapter(FragmentManager fm,Handler handler){
      super(fm);
      mHandler = handler;
      cadl = new ArrayList<CourseBean>();
  }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
      mHandler.removeMessages(CourseView.MSG_AD_SLID);
        return false;
    }
    /**
     * 设置数据更新界面
     */
    public void setDatas(List<CourseBean> cadl){
        this.cadl = cadl;
        notifyDataSetChanged();
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        if (cadl.size() > 0){
            args.putString("ad",cadl.get(position % cadl.size()).icon);
        }
        return AdBannerFragment.newInstance(args);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
    /**
     * 返回数据集的真实容量大小
     */
    public int getSize(){
        return cadl == null ? 0 : cadl.size();
    }

    @Override
    public int getItemPosition(Object object) {
        //防止刷新结果显示列表时出翔缓存数据，重载这个函数，使之默认返回POSITION_NONE
        return POSITION_NONE;
    }
}
