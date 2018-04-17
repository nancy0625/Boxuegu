package cn.edu.gdmec.android.boxuegu.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.activity.ExercisesBean;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;

public class ActivityExercisesDetailAdapter extends RecyclerView.Adapter<ActivityExercisesDetailAdapter.ViewHolder> {

    private List<ExercisesBean> objects = new ArrayList<ExercisesBean>();

    private Context context;
    private LayoutInflater layoutInflater;
    private OnSelectListener onSelectListener;
    private ArrayList<String> selectedPosition = new ArrayList<String>();
    private MyItemClickListener mItemClickListener;

    public ActivityExercisesDetailAdapter(Context context,OnSelectListener onSelectListener) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.onSelectListener = onSelectListener;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.exercises_detail_list_item,parent,false);
        //ViewHolder holder = new ViewHolder(view,mItemClickListener);
        return new ViewHolder(view,mItemClickListener);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        initializeViews(objects.get(position),holder,position);

    }
    public void setData(List<ExercisesBean> ebl){
        this.objects = ebl;

    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }



    private void initializeViews(ExercisesBean object,final ViewHolder vh,final int position) {
        //TODO implement
        ExercisesBean bean = object;

        if (bean != null){
            vh.subject.setText(bean.subject);
            vh.tv_a.setText(bean.a);
            vh.tv_b.setText(bean.b);
            vh.tv_c.setText(bean.c);
            vh.tv_d.setText(bean.d);
        }
        if (!selectedPosition.contains("" + position)){
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
         MyItemClickListener mListener;
        //当用户点击A选项的点击事件
        vh.iv_a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //判断selectPosition中是否含有此时点击的position
                if (mItemClickListener != null){
                    mItemClickListener.onItemClick(v,position);
                }
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
                if (mItemClickListener != null){
                    mItemClickListener.onItemClick(v,position);
                }
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
                if (mItemClickListener != null){
                    mItemClickListener.onItemClick(v,position);
                }
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
                if (mItemClickListener != null){
                    mItemClickListener.onItemClick(v,position);
                }
                //判断selectPosition中是否含有此时点击的position
                if (selectedPosition.contains(""+position)){
                    selectedPosition.remove(""+position);
                }else {
                    selectedPosition.add(position+"");
                }
                onSelectListener.onSelectD(position,vh.iv_a,vh.iv_b,vh.iv_c,vh.iv_d);

            }
        });

    }


    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView subject,tv_a,tv_b,tv_c,tv_d;
        public ImageView iv_a,iv_b,iv_c,iv_d;


        public ViewHolder(View view,MyItemClickListener myItemClickListener) {
            super(view);

            subject = (TextView)view.findViewById(R.id.tv_subject);
            iv_a = (ImageView)view.findViewById(R.id.iv_a);
            iv_b = (ImageView)view.findViewById(R.id.iv_b);
            iv_c = (ImageView)view.findViewById(R.id.iv_c);
            iv_d = (ImageView)view.findViewById(R.id.iv_d);
            tv_a = (TextView)view.findViewById(R.id.tv_a);
            tv_b = (TextView)view.findViewById(R.id.tv_b);
            tv_c = (TextView)view.findViewById(R.id.tv_c);
            tv_d = (TextView)view.findViewById(R.id.tv_d);



        }

        @Override
        public void onClick(View view) {

        }
    }
    public interface MyItemClickListener{
        void onItemClick(View view,int position);
    }
    public void setItemClickListener(MyItemClickListener myItemClickListener){
        this.mItemClickListener = myItemClickListener;
    }
    public interface OnSelectListener{
        void onSelectA(int position, ImageView iv_a, ImageView iv_b,
                       ImageView iv_c, ImageView iv_d);
        void onSelectB(int position,ImageView iv_a,ImageView iv_b,
                       ImageView iv_c,ImageView iv_d);
        void onSelectC(int position,ImageView iv_a,ImageView iv_b,
                       ImageView iv_c,ImageView iv_d);
        void onSelectD(int position,ImageView iv_a,ImageView iv_b,
                       ImageView iv_c,ImageView iv_d);

    }
}
