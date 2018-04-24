package cn.edu.gdmec.android.boxuegu.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.Fragment.FragmentMyinfoFragment;
import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.adapter.ActivityExercisesDetailAdapter;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;

public class ActivityExercisesDetailActivity extends Activity {

    private TextView tv_back;
    private TextView tv_main_title;
    private TextView tv_save;
    private RelativeLayout title_bar;
    private RecyclerView rv_list;
    private TextView tv_di;
    private int id;
    private String title;
    public  int ff = 0;

    private List<ExercisesBean> ebl;
    private ActivityExercisesDetailAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_detail);
        id = getIntent().getIntExtra("id",0);
        title = getIntent().getStringExtra("title");
        ebl = new ArrayList<ExercisesBean>();
        initData();
        initView();
    }

    private void initData() {
        try {
            InputStream is = getResources().getAssets().open("chapter" + id + ".xml");
            ebl = AnalysisUtils.getExercisesInfos(is);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_save = (TextView) findViewById(R.id.tv_save);
        title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rv_list = (RecyclerView) findViewById(R.id.rv_list);
        tv_di = (TextView) findViewById(R.id.tv_di);
        title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));



        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityExercisesDetailActivity.this.finish();


            }
        });

        adapter = new ActivityExercisesDetailAdapter(ActivityExercisesDetailActivity.this, new ActivityExercisesDetailAdapter.OnSelectListener() {
            @Override
            public void onSelectA(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
                if (ebl.get(position).answer != 1){
                    ebl.get(position).select = 1;
                }else {
                    ebl.get(position).select = 0;
                }
                switch (ebl.get(position).answer){
                    case 1:
                        iv_a.setImageResource(R.drawable.exercises_right_icon);

                        break;
                    case 2:
                        iv_a.setImageResource(R.drawable.exercises_error_icon);
                        iv_b.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 3:
                        iv_a.setImageResource(R.drawable.exercises_error_icon);
                        iv_c.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 4:
                        iv_a.setImageResource(R.drawable.exercises_error_icon);
                        iv_d.setImageResource(R.drawable.exercises_right_icon);

                        break;
                }
                AnalysisUtils.setABCDEnable(false,iv_a,iv_b,iv_c,iv_d);
            }

            @Override
            public void onSelectB(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {

                if (ebl.get(position).answer != 2){
                    ebl.get(position).select = 2;
                }else {
                    ebl.get(position).select = 0;
                }
                switch (ebl.get(position).answer){
                    case 1:
                        iv_a.setImageResource(R.drawable.exercises_right_icon);
                        iv_b.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        iv_b.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 3:
                        iv_b.setImageResource(R.drawable.exercises_error_icon);
                        iv_c.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 4:
                        iv_b.setImageResource(R.drawable.exercises_error_icon);
                        iv_d.setImageResource(R.drawable.exercises_right_icon);

                        break;
                }
                AnalysisUtils.setABCDEnable(false,iv_a,iv_b,iv_c,iv_d);
            }

            @Override
            public void onSelectC(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
                if (ebl.get(position).answer != 3){
                    ebl.get(position).select = 3;
                }else {
                    ebl.get(position).select = 0;
                }
                switch (ebl.get(position).answer){
                    case 1:
                        iv_a.setImageResource(R.drawable.exercises_right_icon);
                        iv_c.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        iv_c.setImageResource(R.drawable.exercises_error_icon);
                        iv_b.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 3:
                        iv_c.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 4:
                        iv_c.setImageResource(R.drawable.exercises_error_icon);
                        iv_d.setImageResource(R.drawable.exercises_right_icon);

                        break;
                }
                AnalysisUtils.setABCDEnable(false,iv_a,iv_b,iv_c,iv_d);

            }

            @Override
            public void onSelectD(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {

                if (ebl.get(position).answer != 4){
                    ebl.get(position).select = 4;
                }else {
                    ebl.get(position).select = 0;
                }
                switch (ebl.get(position).answer){
                    case 1:
                        iv_a.setImageResource(R.drawable.exercises_right_icon);
                        iv_d.setImageResource(R.drawable.exercises_error_icon);
                        break;
                    case 2:
                        iv_d.setImageResource(R.drawable.exercises_error_icon);
                        iv_b.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 3:
                        iv_d.setImageResource(R.drawable.exercises_error_icon);
                        iv_c.setImageResource(R.drawable.exercises_right_icon);
                        break;
                    case 4:
                        iv_d.setImageResource(R.drawable.exercises_right_icon);

                        break;
                }
                AnalysisUtils.setABCDEnable(false,iv_a,iv_b,iv_c,iv_d);
            }
        });

        adapter.setData(ebl);
        rv_list = (RecyclerView)findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));



        adapter.setItemClickListener(new ActivityExercisesDetailAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                ff=ff+1;
                tv_di.setText("第"+(position+1)+"题完成，共5题");

                if (ff==5){
                    AnalysisUtils.saveExerciseStatus(ActivityExercisesDetailActivity.this,id);
                    Log.i("DD",id+"");
                    setResult(RESULT_OK);
                    //ff = 0;
                }


            }

        });
        rv_list.setAdapter(adapter);


    }
}
