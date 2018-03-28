package cn.edu.gdmec.android.boxuegu;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.edu.gdmec.android.boxuegu.Fragment.FragmentCourseFragment;
import cn.edu.gdmec.android.boxuegu.Fragment.FragmentExercisesFragment;
import cn.edu.gdmec.android.boxuegu.Fragment.FragmentMyinfoFragment;
import cn.edu.gdmec.android.boxuegu.R;

/**
 * Created by asus on 2018/3/27.
 */

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private RelativeLayout main_body;
    private TextView bottom_bar_text_course;
    private ImageView bottom_bar_image_course;
    private RelativeLayout bottom_bar_course_btn;
    private TextView bottom_bar_text_exercises;
    private ImageView bottom_bar_image_exercises;
    private RelativeLayout bottom_bar_exercises_btn;
    private TextView bottom_bar_text_myinfo;
    private ImageView bottom_bar_image_myinfo;
    private RelativeLayout bottom_bar_myinfo_btn;
    private LinearLayout main_bottom_bar;
    private RelativeLayout rl_title_bar;
    private TextView tv_main_title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       /* FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.main_body,new FragmentCourseFragment()).commit();*/
        setMain();
    }
    private void setMain(){
        this.getSupportFragmentManager().beginTransaction().add(R.id.main_body,new FragmentMyinfoFragment()).commit();
        setSelectedStatus(0);
    }

    private void initView() {
        main_body = (RelativeLayout) findViewById(R.id.main_body);
        bottom_bar_text_course = (TextView) findViewById(R.id.bottom_bar_text_course);
        bottom_bar_image_course = (ImageView) findViewById(R.id.bottom_bar_image_course);
        bottom_bar_course_btn = (RelativeLayout) findViewById(R.id.bottom_bar_course_btn);
        bottom_bar_text_exercises = (TextView) findViewById(R.id.bottom_bar_text_exercises);
        bottom_bar_image_exercises = (ImageView) findViewById(R.id.bottom_bar_image_exercises);
        bottom_bar_exercises_btn = (RelativeLayout) findViewById(R.id.bottom_bar_exercises_btn);
        bottom_bar_text_myinfo = (TextView) findViewById(R.id.bottom_bar_text_myinfo);
        bottom_bar_image_myinfo = (ImageView) findViewById(R.id.bottom_bar_image_myinfo);
        bottom_bar_myinfo_btn = (RelativeLayout) findViewById(R.id.bottom_bar_myinfo_btn);
        main_bottom_bar = (LinearLayout) findViewById(R.id.main_bottom_bar);
        bottom_bar_course_btn.setOnClickListener(this);
        bottom_bar_exercises_btn.setOnClickListener(this);
        bottom_bar_myinfo_btn.setOnClickListener(this);
        tv_main_title = (TextView)findViewById(R.id.tv_main_title);
        tv_main_title.setText("博学谷课程");
        rl_title_bar = (RelativeLayout)findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
    }
    private void setSelectedStatus(int index){
        switch (index){
            case 0:
                //bottom_bar_course_btn.setSelected(true);
                bottom_bar_image_course.setImageResource(R.drawable.main_course_icon_selected);
                bottom_bar_text_course.setTextColor(Color.parseColor("#0097F7"));

                bottom_bar_image_exercises.setImageResource(R.drawable.main_exercises_icon);
                bottom_bar_text_exercises.setTextColor(Color.parseColor("#666666"));

                bottom_bar_image_myinfo.setImageResource(R.drawable.main_my_icon);
                bottom_bar_text_myinfo.setTextColor(Color.parseColor("#666666"));
                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("博学谷课程");
                break;
            case 1:
                //bottom_bar_exercises_btn.setSelected(true);
                bottom_bar_image_exercises.setImageResource(R.drawable.main_exercises_icon_selected);
                bottom_bar_text_exercises.setTextColor(Color.parseColor("#0097F7"));

                bottom_bar_image_course.setImageResource(R.drawable.main_course_icon);
                bottom_bar_text_course.setTextColor(Color.parseColor("#666666"));

                bottom_bar_image_myinfo.setImageResource(R.drawable.main_my_icon);
                bottom_bar_text_myinfo.setTextColor(Color.parseColor("#666666"));
                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("博学谷习题");
                break;
            case 2:
               // mMyInfoBtn.setSelected(true);
                bottom_bar_image_myinfo.setImageResource(R.drawable.main_my_icon_selected);
                bottom_bar_text_myinfo.setTextColor(Color.parseColor("#0097F7"));

                bottom_bar_image_course.setImageResource(R.drawable.main_course_icon);
                bottom_bar_text_course.setTextColor(Color.parseColor("#666666"));

                bottom_bar_image_exercises.setImageResource(R.drawable.main_exercises_icon);
                bottom_bar_text_exercises.setTextColor(Color.parseColor("#666666"));
                rl_title_bar.setVisibility(View.GONE);
                break;

        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bottom_bar_course_btn:
                this.getSupportFragmentManager().beginTransaction().add(R.id.main_body,new FragmentCourseFragment()).commit();
                setSelectedStatus(0);
                break;
            case R.id.bottom_bar_exercises_btn:
                this.getSupportFragmentManager().beginTransaction().add(R.id.main_body,new FragmentExercisesFragment()).commit();
                setSelectedStatus(1);
                break;
            case R.id.bottom_bar_myinfo_btn:
                this.getSupportFragmentManager().beginTransaction().add(R.id.main_body,new FragmentMyinfoFragment()).commit();
                setSelectedStatus(2);
                break;
        }

    }
}
