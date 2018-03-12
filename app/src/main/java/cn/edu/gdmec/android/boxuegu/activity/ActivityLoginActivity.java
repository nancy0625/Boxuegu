package cn.edu.gdmec.android.boxuegu.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import cn.edu.gdmec.android.boxuegu.R;

public class ActivityLoginActivity extends Activity implements View.OnClickListener {

    private ImageView ivHead;
    private TextView tvRegister;
    private TextView tvFindPsw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ivHead = (ImageView) findViewById(R.id.iv_head);
        findViewById(R.id.btn_login).setOnClickListener(this);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        tvFindPsw = (TextView) findViewById(R.id.tv_find_psw);
    }
    private void init(){

    }

    private EditText getEtUserName(){
        return (EditText) findViewById(R.id.et_user_name);
    }

    private EditText getEtPsw(){
        return (EditText) findViewById(R.id.et_psw);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                //TODO implement
                break;
        }
    }
}
