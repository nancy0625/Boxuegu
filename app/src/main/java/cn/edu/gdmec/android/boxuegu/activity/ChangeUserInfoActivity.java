package cn.edu.gdmec.android.boxuegu.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.R;

public class ChangeUserInfoActivity extends AppCompatActivity {
    private TextView tv_main_title,tv_save;
    private RelativeLayout rl_title_bar;
    private TextView tv_back;
    private String title,content;
    private  int flag;
    private EditText et_content;
    private ImageView iv_delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_info);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    private void init() {
        //从个人资料页面传递过来的标题和内容
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        flag = getIntent().getIntExtra("flag",0);
        tv_main_title = (TextView)findViewById(R.id.tv_main_title);
        tv_main_title.setText(title);
        rl_title_bar = (RelativeLayout)findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back = (TextView)findViewById(R.id.tv_back);
        tv_save = (TextView)findViewById(R.id.tv_save);
        tv_save.setVisibility(View.VISIBLE);
        et_content = (EditText)findViewById(R.id.et_content);
        if (flag==3){
            et_content.setInputType(InputType.TYPE_CLASS_NUMBER);
        }

        iv_delete = (ImageView)findViewById(R.id.iv_delete);
        if (!TextUtils.isEmpty(content)){
            et_content.setText(content);
            et_content.setSelection(content.length());
        }
        contentListener();
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeUserInfoActivity.this.finish();
            }
        });
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_content.setText("");
            }
        });
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                String etContent = et_content.getText().toString().trim();
                switch (flag){
                    case 1:
                        if (!TextUtils.isEmpty(etContent)){
                            data.putExtra("nickName",etContent);
                            setResult(RESULT_OK,data);
                            Toast.makeText(ChangeUserInfoActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                            ChangeUserInfoActivity.this.finish();
                        }else {
                            Toast.makeText(ChangeUserInfoActivity.this,"昵称不能为空",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        if (!TextUtils.isEmpty(etContent)){
                            data.putExtra("signature",etContent);
                            setResult(RESULT_OK,data);
                            Toast.makeText(ChangeUserInfoActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                            ChangeUserInfoActivity.this.finish();
                        }else {
                            Toast.makeText(ChangeUserInfoActivity.this,"签名不能为空",Toast.LENGTH_SHORT).show();

                        }
                        break;
                    case 3:


                        if (!TextUtils.isEmpty(etContent)){
                            data.putExtra("qq",etContent);
                            setResult(RESULT_OK,data);
                            Toast.makeText(ChangeUserInfoActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                            ChangeUserInfoActivity.this.finish();
                        }else {
                            Toast.makeText(ChangeUserInfoActivity.this,"qq号不能为空",Toast.LENGTH_SHORT).show();

                        }
                        break;
                }
            }
        });

    }

    /**
     * 监听个人资料修改界面输入的文字
     */

    private void contentListener() {
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Editable editable = et_content.getText();
                int len = editable.length();
                if (len > 0){
                    iv_delete.setVisibility(View.VISIBLE);

                }else {
                    iv_delete.setVisibility(View.GONE);
                }
                switch (flag){
                    case 1: //昵称
                        //昵称限制最多8个文字，超过8个需要截取多余的文字
                        if (len > 8){
                        int selEndIndex = Selection.getSelectionEnd(editable);
                        String str = editable.toString();
                        //截取新字符串
                            String newStr = str.substring(0,8);
                            et_content.setText(newStr);
                            editable = et_content.getText();
                            //新字符串的长度
                            int newLen = editable.length();
                            //旧光标位置超过新字符串的长度
                            if (selEndIndex > newLen){
                                selEndIndex = editable.length();
                            }
                            //设置光标所在的位置
                            Selection.setSelection(editable,selEndIndex);
                        }
                        break;
                    case 2:
                        //签名最多是16个文字，超过16个需要截取掉多余的文字
                        if (len > 16){
                            int selEndIndex = Selection.getSelectionEnd(editable);
                            String str = editable.toString();
                            //截取新字符串
                            String newStr = editable.toString();
                            et_content.setText(newStr);
                            editable = et_content.getText();
                            //新字符串的长度
                            int newLen = editable.length();
                            //旧光标位置超过新字符串的长度
                            if (selEndIndex > newLen){
                                selEndIndex = editable.length();
                            }
                            //设置新光标所在的位置
                            Selection.setSelection(editable,selEndIndex);
                        }
                        break;
                    case 3:

                        //签名最多是16个文字，超过16个需要截取掉多余的文字
                        if (len > 12){
                            int selEndIndex = Selection.getSelectionEnd(editable);
                            String str = editable.toString();
                            //截取新字符串
                            String newStr = editable.toString();
                            et_content.setText(newStr);
                            editable = et_content.getText();
                            //新字符串的长度
                            int newLen = editable.length();
                            //旧光标位置超过新字符串的长度
                            if (selEndIndex > newLen){
                                selEndIndex = editable.length();
                            }
                            //设置新光标所在的位置
                            Selection.setSelection(editable,selEndIndex);
                        }
                        break;
                        default:
                            break;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
