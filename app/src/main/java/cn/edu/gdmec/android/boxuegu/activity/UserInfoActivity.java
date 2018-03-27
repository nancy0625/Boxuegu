package cn.edu.gdmec.android.boxuegu.activity;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_back;
    private TextView tv_main_title;
    private TextView tv_nickName,tv_signature,tv_user_name,tv_sex;
    private RelativeLayout rl_nickName,rl_sex,rl_signature,rl_title_bar;
    private String spUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        spUserName = AnalysisUtils.readLoginUserName(this);
        init();
        initData();
        setListener();
    }
    private void init() {
        tv_back = (TextView)findViewById(R.id.tv_back);
        tv_main_title = (TextView)findViewById(R.id.tv_main_title);
        tv_main_title.setText("个人资料");
        rl_title_bar = (RelativeLayout)findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        rl_nickName = (RelativeLayout)findViewById(R.id.rl_nickName);
        rl_sex = (RelativeLayout)findViewById(R.id.rl_sex);
        rl_signature = (RelativeLayout)findViewById(R.id.rl_signature);
        tv_nickName = (TextView)findViewById(R.id.tv_nickName);
        tv_user_name = (TextView)findViewById(R.id.tv_user_name);
        tv_sex = (TextView)findViewById(R.id.tv_sex);
        tv_signature = (TextView)findViewById(R.id.tv_signature);
    }
    private void setListener() {
        tv_back.setOnClickListener(this);
        rl_nickName.setOnClickListener(this);
        rl_sex.setOnClickListener(this);
        rl_signature.setOnClickListener(this);
    }

    private void initData() {
        UserBean bean = null;
        bean = DBUtils.getInstance(this).getUserInfo(spUserName);
        //首先判斷一下數據庫是否有數據
        if (bean == null){
            bean = new UserBean();
            bean.userName = spUserName;
            bean.nickName = "问答精灵";
            bean.sex = "男";
            bean.signature = "问答精灵";
            //保存用戶信息到數據庫
            DBUtils.getInstance(this).saveUserInfo(bean);
        }
        setValue(bean);

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                this.finish();
            break;
            case R.id.rl_nickName:
                break;
            case R.id.rl_sex:
                String sex = tv_sex.getText().toString();
                sexDialog(sex);
                break;
            case R.id.rl_signature:
                break;
                default:
                    break;
        }

    }

    private void sexDialog(String sex) {
        int sexFlag = 0;
        if ("男".equals(sex)){
            sexFlag = 0;
        }else if ("女".equals(sex)){
            sexFlag = 1;

        }
        final String items[] = {"男","女"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("性別");
        builder.setSingleChoiceItems(items, sexFlag, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast
                        .makeText(UserInfoActivity.this,items[which],Toast.LENGTH_SHORT).show();
                setSex(items[which]);
            }
        });
        builder.create();
    }

    public void setValue(UserBean bean) {
        tv_nickName.setText(bean.nickName);
        tv_user_name.setText(bean.userName);
        tv_sex.setText(bean.sex);
        tv_signature.setText(bean.signature);
    }

    public void setSex(String sex) {
       tv_sex.setText(sex);
       DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("sex",sex,spUserName);
    }
}
