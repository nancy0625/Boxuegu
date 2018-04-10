package cn.edu.gdmec.android.boxuegu.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_back;
    private TextView tv_main_title;
    private TextView tv_nickName, tv_signature, tv_user_name, tv_sex;
    private RelativeLayout rl_nickName, rl_sex, rl_signature, rl_title_bar;
    private String spUserName;
    private static final int CHANGE_NICKNAME = 1;//修改昵称的自定义常量
    private static final int CHANGE_SIGNATURE = 2;//修改签名的自定义常量
    private static final int CHANGE_QQ = 3;//修改qq号的自定义常量
    private TextView tv_qq;
    private RelativeLayout rl_qq;


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
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("个人资料");
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        rl_nickName = (RelativeLayout) findViewById(R.id.rl_nickName);
        rl_sex = (RelativeLayout) findViewById(R.id.rl_sex);
        rl_signature = (RelativeLayout) findViewById(R.id.rl_signature);
        tv_nickName = (TextView) findViewById(R.id.tv_nickName);
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_signature = (TextView) findViewById(R.id.tv_signature);
        tv_qq = (TextView)findViewById(R.id.tv_qq);
        rl_qq = (RelativeLayout)findViewById(R.id.rl_qq);
    }

    private void setListener() {
        tv_back.setOnClickListener(this);
        rl_nickName.setOnClickListener(this);
        rl_sex.setOnClickListener(this);
        rl_signature.setOnClickListener(this);
        rl_qq.setOnClickListener(this);
    }

    private void initData() {
        UserBean bean = null;
        bean = DBUtils.getInstance(this).getUserInfo(spUserName);
        //首先判斷一下數據庫是否有數據
        if (bean == null) {
            bean = new UserBean();
            bean.userName = spUserName;
            bean.nickName = "问答精灵";
            bean.sex = "男";
            bean.signature = "问答精灵";
            bean.qq = "1234567";
            //保存用戶信息到數據庫
            DBUtils.getInstance(this).saveUserInfo(bean);
        }
        setValue(bean);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                this.finish();
                break;
            case R.id.rl_nickName:
                String name = tv_nickName.getText().toString();
                Bundle bdName = new Bundle();
                bdName.putString("content", name);
                bdName.putString("title", "昵称");
                bdName.putInt("flag", 1);
                enterActivityForResult(ChangeUserInfoActivity.class, CHANGE_NICKNAME, bdName);
                break;
            case R.id.rl_sex:
                String sex = tv_sex.getText().toString();
                sexDialog(sex);
                break;
            case R.id.rl_signature:
                String signature = tv_signature.getText().toString();
                Bundle bdSignature = new Bundle();
                bdSignature.putString("content", signature);
                bdSignature.putString("title", "签名");
                bdSignature.putInt("flag", 2);
                enterActivityForResult(ChangeUserInfoActivity.class, CHANGE_SIGNATURE, bdSignature);
                break;
            case R.id.rl_qq:
                String qqstr = tv_qq.getText().toString();
                Bundle qq = new Bundle();
                qq.putString("content", qqstr);
                qq.putString("title", "QQ号");
                qq.putInt("flag", 3);
                enterActivityForResult(ChangeUserInfoActivity.class, CHANGE_QQ, qq);
                break;
            default:
                break;
        }


    }

    /**
     * 回传数据
     *
     * @param sex
     */
    private String new_info;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHANGE_NICKNAME://个人资料修改界面回传过来的昵称数据
                if (data != null) {
                    new_info = data.getStringExtra("nickName");
                    if (TextUtils.isEmpty(new_info)) {
                        return;
                    }
                    tv_nickName.setText(new_info);
                    //更新数据库中的昵称字段
                    DBUtils.getInstance(UserInfoActivity.this).updateUserInfo(
                            "nickName", new_info, spUserName
                    );


                }
                break;
            case CHANGE_SIGNATURE://个人资料修改界面回传过来的签名数据
                if (data != null) {
                    new_info = data.getStringExtra("signature");
                    if (TextUtils.isEmpty(new_info)) {
                        return;
                    }
                    tv_signature.setText(new_info);
                    //更新数据库中的签名字段
                    DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("signature", new_info, spUserName);
                }
                break;
            case CHANGE_QQ://个人资料修改界面回传过来的签名数据
                if (data != null) {
                    new_info = data.getStringExtra("qq");
                    if (TextUtils.isEmpty(new_info)) {
                        return;
                    }
                    tv_qq.setText(new_info);
                    //更新数据库中的签名字段
                    DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("qq", new_info, spUserName);
                }
                break;

        }
    }

    private void sexDialog(String sex) {
        int sexFlag = 0;
        if ("男".equals(sex)) {
            sexFlag = 0;
        } else if ("女".equals(sex)) {
            sexFlag = 1;

        }
        final String items[] = {"男", "女"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("性別");
        builder.setSingleChoiceItems(items, sexFlag, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast
                        .makeText(UserInfoActivity.this, items[which], Toast.LENGTH_SHORT).show();
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
        tv_qq.setText(bean.qq);
    }

    public void setSex(String sex) {
        tv_sex.setText(sex);
        //更新数据库中的性别字段
        DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("sex", sex, spUserName);
    }

    /**
     * 获取传回数据时需要使用的跳转方法，第一个参数to表示需要跳转到的界面
     * 第2个参数requestCode表示一个请求码，第3个参数表示跳转传递的数据
     */
    public void enterActivityForResult(Class<?> to, int requestCode, Bundle b) {
        Intent i = new Intent(this, to);
        i.putExtras(b);
        startActivityForResult(i, requestCode);

    }


}
