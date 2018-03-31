package cn.edu.gdmec.android.boxuegu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.jar.Attributes;

import cn.edu.gdmec.android.boxuegu.R;

/**
 * Created by asus on 2018/3/31.
 */

public class ViewPagerIndicator extends LinearLayout{
    private int mCount;
    private int mIndex;
    private Context context;
    public ViewPagerIndicator(Context context, AttributeSet attrs) {
     super(context,attrs);
     this.context = context;
     setGravity(Gravity.CENTER);//设置布局居中
    }
    public ViewPagerIndicator(Context context){
        this(context,null);
    }
    public void setCurrentPosition(int currentIndex){
        mIndex = currentIndex; //当前小圆点
        removeAllViews();//移除界面上存在的view
        int pex = 5;
        for (int i = 0;i < mCount; i++){
            //创建一个ImageView空间来放置小圆点
            ImageView imageView = new ImageView(context);
            if (mIndex == i){//滑动到当前界面
                //设置小圆点的图片为蓝色图片
                imageView.setImageResource(R.drawable.indicator_on);
            }else {
                //设置小圆点的图片为灰色
                imageView.setImageResource(R.drawable.indicator_off);
            }
            imageView.setPadding(pex,0,pex,0);
            addView(imageView);
        }
    }
    /**
     * 设置小圆点的数目
     */
    public void setCount(int count){
        this.mCount = count;
    }
}
