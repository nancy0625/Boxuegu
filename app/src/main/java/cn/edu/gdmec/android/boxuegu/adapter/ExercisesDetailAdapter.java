package cn.edu.gdmec.android.boxuegu.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.activity.ExercisesBean;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;

/**
 * Created by asus on 2018/3/29.
 */

public class ExercisesDetailAdapter extends BaseAdapter {
    private Context mContext;
    private List<ExercisesBean> ebl;
    private OnSelectListener onSelectListener;
    public ExercisesDetailAdapter(Context context, OnSelectListener onSelectListener){
        this.mContext = context;
        this.onSelectListener = onSelectListener;
    }
    public void setData(List<ExercisesBean> ebl){
        this.ebl = ebl;
        notifyDataSetChanged();
    }
    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return ebl == null ? 0 : ebl.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return ebl == null ? null : ebl.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    private ArrayList<String> selectedPosition = new ArrayList<String>();
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null){
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.exercises_detail_list_item,null);
            vh.subject = (TextView) convertView.findViewById(R.id.tv_subject);
            vh.tv_a = (TextView)convertView.findViewById(R.id.tv_a);
            vh.tv_b = (TextView)convertView.findViewById(R.id.tv_b);
            vh.tv_c = (TextView)convertView.findViewById(R.id.tv_c);
            vh.tv_d = (TextView)convertView.findViewById(R.id.tv_d);
            vh.iv_a = (ImageView) convertView.findViewById(R.id.iv_a);
            vh.iv_b = (ImageView) convertView.findViewById(R.id.iv_b);
            vh.iv_c = (ImageView) convertView.findViewById(R.id.iv_c);
            vh.iv_d = (ImageView) convertView.findViewById(R.id.iv_d);
            convertView.setTag(vh);

        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        final  ExercisesBean bean = (ExercisesBean) getItem(position);
        if (bean != null){
            vh.subject.setText(bean.subject);
            vh.tv_a.setText(bean.a);
            vh.tv_b.setText(bean.b);
            vh.tv_c.setText(bean.c);
            vh.tv_d.setText(bean.d);
        }
        if (!selectedPosition.contains(""+position)){
            vh.iv_a.setImageResource(R.drawable.exercises_a);
            vh.iv_b.setImageResource(R.drawable.exercises_b);
            vh.iv_c.setImageResource(R.drawable.exercises_c);
            vh.iv_d.setImageResource(R.drawable.exercises_d);
            AnalysisUtils.setABCDEnable(true,vh.iv_a,vh.iv_b,vh.iv_c,vh.iv_d);
        }else {
            AnalysisUtils.setABCDEnable(false,vh.iv_a,vh.iv_b,vh.iv_c,vh.iv_d);
            switch (bean.select){
                case 0:
                    //用户所选项是正确的
                    if (bean.answer == 1){
                        vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);

                    }else if (bean.answer == 2){
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    }else if (bean.answer == 3){
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    }else if (bean.answer == 4){
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 1:
                    //用户所选项A是错误的
                    vh.iv_a.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.answer == 2){
                        vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    }else  if (bean.answer == 3){
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    }else  if (bean.answer == 4){
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 2:
                    //用户所选项B是错误的
                    vh.iv_b.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.answer == 1){
                        vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    }else  if (bean.answer == 3){
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    }else  if (bean.answer == 4){
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                        vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 3:
                    //用户所选项C是错误的
                    vh.iv_c.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.answer == 1){
                        vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    }else  if (bean.answer == 2){
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_d.setImageResource(R.drawable.exercises_d);
                    }else  if (bean.answer == 4){
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_d.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 4:
                    //用户所选项D是错误的
                    vh.iv_d.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.answer == 1){
                        vh.iv_a.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                    }else  if (bean.answer == 2){
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_right_icon);
                        vh.iv_c.setImageResource(R.drawable.exercises_c);
                    }else  if (bean.answer == 3){
                        vh.iv_a.setImageResource(R.drawable.exercises_a);
                        vh.iv_b.setImageResource(R.drawable.exercises_b);
                        vh.iv_c.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                    default:
                        break;



            }
        }
        //当用户点击A选项的点击事件
        vh.iv_a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //判断selectPosition中是否含有此时点击的position
                if (selectedPosition.contains(""+position)){
                    selectedPosition.remove(""+position);
                }else {
                    selectedPosition.add(position+"");
                }
                onSelectListener.onSelectA(position,vh.iv_a,vh.iv_b,vh.iv_c,vh.iv_d);

            }
        });
        //点击B选项
        vh.iv_b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //判断selectPosition中是否含有此时点击的position
                if (selectedPosition.contains(""+position)){
                    selectedPosition.remove(""+position);
                }else {
                    selectedPosition.add(position+"");
                }
                onSelectListener.onSelectB(position,vh.iv_a,vh.iv_b,vh.iv_c,vh.iv_d);

            }
        });
        //点击选项C
        vh.iv_c.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //判断selectPosition中是否含有此时点击的position
                if (selectedPosition.contains(""+position)){
                    selectedPosition.remove(""+position);
                }else {
                    selectedPosition.add(position+"");
                }
                onSelectListener.onSelectC(position,vh.iv_a,vh.iv_b,vh.iv_c,vh.iv_d);

            }
        });
        //点击选项D
        vh.iv_d.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //判断selectPosition中是否含有此时点击的position
                if (selectedPosition.contains(""+position)){
                    selectedPosition.remove(""+position);
                }else {
                    selectedPosition.add(position+"");
                }
                onSelectListener.onSelectD(position,vh.iv_a,vh.iv_b,vh.iv_c,vh.iv_d);

            }
        });
        return convertView;
    }
    class ViewHolder{
        public TextView subject,tv_a,tv_b,tv_c,tv_d;
        public ImageView iv_a,iv_b,iv_c,iv_d;
    }
    public interface OnSelectListener{
        void onSelectA(int position, ImageView iv_a,ImageView iv_b,ImageView iv_c,ImageView iv_d);
        void onSelectB(int position,ImageView iv_a,ImageView iv_b,
                       ImageView iv_c,ImageView iv_d);
        void onSelectC(int position,ImageView iv_a,ImageView iv_b,
                       ImageView iv_c,ImageView iv_d);
        void onSelectD(int position,ImageView iv_a,ImageView iv_b,
                       ImageView iv_c,ImageView iv_d);

    }
}
