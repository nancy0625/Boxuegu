package cn.edu.gdmec.android.boxuegu.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;
import cn.edu.gdmec.android.boxuegu.utils.MD5Utils;

public class FindPswActivity extends AppCompatActivity {
    private EditText et_validate_name,et_user_name;
    private Button btn_validate;
    private TextView tv_main_title;
    private TextView tv_back;

    private String from;
    private TextView tv_reset_psw,tv_user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_psw);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        from = getIntent().getStringExtra("from");
        init();
    }

    private void init() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_back = (TextView) findViewById(R.id.tv_back);
        et_validate_name = (EditText) findViewById(R.id.et_validate_name);
        btn_validate = (Button) findViewById(R.id.btn_validate);
        tv_reset_psw = (TextView) findViewById(R.id.tv_reset_name);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
        if ("security".equals(from)) {
            tv_main_title.setText("设置密保");
        } else {
            tv_main_title.setText("找回密码");
            tv_user_name.setVisibility(View.VISIBLE);
            et_user_name.setVisibility(View.VISIBLE);
        }
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FindPswActivity.this.finish();
            }
        });
        btn_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String validateName = et_validate_name.getText().toString().trim();
                if ("security".equals(from)) {
                    //設置密保
                    if (TextUtils.isEmpty(validateName)) {
                        Toast.makeText(FindPswActivity.this, "请输入要验证的姓名", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Toast.makeText(FindPswActivity.this, "密保设置成功", Toast.LENGTH_SHORT).show();
                        saveSecurity(validateName);
                        FindPswActivity.this.finish();
                    }
                } else {//找回密碼
                    String userName = et_user_name.getText().toString().trim();
                    String sp_security = readSecurity(userName);
                    if (TextUtils.isEmpty(userName)) {
                        Toast.makeText(FindPswActivity.this, "请输入您的用户名", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (!isExistUsername(userName)) {
                        Toast.makeText(FindPswActivity.this, "您输入的用户名不存在", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (TextUtils.isEmpty(validateName)) {
                        Toast.makeText(FindPswActivity.this, "请输入要验证的姓名", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!validateName.equals(sp_security)) {
                        Toast.makeText(FindPswActivity.this, "输入的密保不正确", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        //輸入的密保正確，重新給用戶設置一個密碼
                        tv_reset_psw.setVisibility(View.VISIBLE);
                        tv_reset_psw.setText("初始密码：123456");
                        savePsw(userName);
                    }

                }
            }
        });
    }
    /**
     * 保存初始密碼
     */
    private void savePsw(String userName){
        String md5Psw = MD5Utils.md5("123456");
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(userName,md5Psw);
        editor.commit();
    }
    /**
     * 保存密保到SharedPreferences中
     */
    private void saveSecurity(String validateName){
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(AnalysisUtils.readLoginUserName(this)+"_security",validateName);
        editor.commit();
    }
    /**
     * 從SharedPreferences中讀取密保
     */
    private String readSecurity(String userName){
        SharedPreferences sp = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        String security = sp.getString(userName+"_security","");
        return security;
    }
    /**
     * 從SharedPreferences中根據用戶輸入的用戶名來判斷是否有此用戶
     *
     */
    private boolean isExistUsername(String userName){
        boolean hasUserName = false;
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw = sp.getString(userName,"");
        if (!TextUtils.isEmpty(spPsw)){
            hasUserName = true;
        }
        return hasUserName;
    }
}
