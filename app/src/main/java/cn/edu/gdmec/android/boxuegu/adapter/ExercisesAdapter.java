package cn.edu.gdmec.android.boxuegu.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.activity.ActivityExercisesDetailActivity;
import cn.edu.gdmec.android.boxuegu.activity.ExercisesBean;
import cn.edu.gdmec.android.boxuegu.activity.ExercisesDetailActivity;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by asus on 2018/3/28.
 */

public class ExercisesAdapter extends BaseAdapter {
    private Context mContext;
    private List<ExercisesBean> ebl = new ArrayList<ExercisesBean>();
    private LayoutInflater layoutInflater;
    public ExercisesAdapter(Context context){
        this.mContext = context;
        this.layoutInflater = LayoutInflater.from(context);
    }
    /**
     * 设置数据更新界面
     */
    public void setData(List<ExercisesBean> ebl){

        this.ebl = ebl;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return ebl == null ? 0 : ebl.size();
    }


    @Override
    public ExercisesBean getItem(int position) {

        return ebl == null ? null : ebl.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;

        if (convertView == null){
            vh = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.exercises_list_item,null);
            vh.title = (TextView)convertView.findViewById(R.id.tv_title);
            vh.content = (TextView)convertView.findViewById(R.id.tv_content);
            vh.order = (TextView)convertView.findViewById(R.id.tv_order);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder)convertView.getTag();

        }
        //获取position对应的Item的数据对象

        final ExercisesBean bean = (ExercisesBean) getItem(position);

        if (bean != null){
            vh.order.setText(position + 1 + "");
            vh.title.setText(bean.title);
            if (AnalysisUtils.readExerciseStatus(mContext,position+1)){
                vh.content.setText("已完成");

            }else {
                vh.content.setText(bean.content);
            }


            vh.order.setBackgroundResource(bean.background);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean == null){
                    return;
                    //跳转到习题详情页面
                    }

                Intent intent = new Intent(mContext, ActivityExercisesDetailActivity.class);
                intent.putExtra("id",bean.id);
                intent.putExtra("title",bean.title);
                //mContext.startActivity(intent);
                ((Activity)mContext).startActivityForResult(intent,000);

            }
        });


        return convertView;
    }
    class ViewHolder{
        public TextView title,content;
        public TextView order;
    }

}
