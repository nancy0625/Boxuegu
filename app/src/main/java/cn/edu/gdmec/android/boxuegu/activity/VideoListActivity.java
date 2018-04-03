package cn.edu.gdmec.android.boxuegu.activity;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.adapter.VideoListAdapter;

public class VideoListActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_intro,tv_video,tv_chapter_intro;
    private ListView lv_video_list;
    private ScrollView sv_chapter_intro;
    private VideoListAdapter adapter;
    private List<VideoBean> videoList;
    private DBUtils db;
    private int chapterId;
    private String intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //从课程界面传递过来的章节
        chapterId = getIntent().getIntExtra("id",0);
        //从课程界面传递过来的章简介
        intro = getIntent().getStringExtra("intro");
        //创建数据库工具类的对象
        db = DBUtils.getInstance(VideoListActivity.this);
        //initData();
        init();
    }
    private void init(){
        tv_intro = (TextView)findViewById(R.id.tv_intro);
        tv_video = (TextView)findViewById(R.id.tv_video);
        lv_video_list = (ListView)findViewById(R.id.lv_video_list);
        tv_chapter_intro = (TextView)findViewById(R.id.tv_chapter_intro);
        sv_chapter_intro = (ScrollView)findViewById(R.id.sv_chapter_intro);
        adapter = new VideoListAdapter(this, new VideoListAdapter.OnSelectListener() {
            @Override
            public void onSelect(int position, ImageView iv) {
                adapter.setSelectedPosition(position);
                VideoBean bean = videoList.get(position);
                String videoPath = bean.videoPath;
                adapter.notifyDataSetChanged();//更新列表框
                if (TextUtils.isEmpty(videoPath)){
                    Toast.makeText(VideoListActivity.this,"本地没有此视频，暂时无法播放",Toast.LENGTH_LONG).show();
                    return;
                }else{
                    //判断用户是否登录，若登录则吧此视频添加到数据库

                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
