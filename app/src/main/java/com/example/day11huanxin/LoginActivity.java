package com.example.day11huanxin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.day11huanxin.utils.SpUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_psw;
    private Button bt_denglu;
    private Button bt_zhuce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断是否登陆过 如果登陆过并且设置的是自动登录 直接跳转到MainActivity
        boolean b = EMClient.getInstance().isLoggedInBefore();
        if (b){
            startActivity(new Intent(this,MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_psw = (EditText) findViewById(R.id.et_psw);
        bt_denglu = (Button) findViewById(R.id.bt_denglu);
        bt_zhuce = (Button) findViewById(R.id.bt_zhuce);

        bt_denglu.setOnClickListener(this);
        bt_zhuce.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_denglu:
                login();
                break;
            case R.id.bt_zhuce:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
        }
    }

    private void login() {
        String name = et_name.getText().toString().trim();
        String psw = et_psw.getText().toString().trim();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(psw)){
            EMClient.getInstance().login(name, psw, new EMCallBack() {
                @Override
                public void onSuccess() {
                    EMClient.getInstance().groupManager().loadAllGroups();
                    EMClient.getInstance().chatManager().loadAllConversations();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            SpUtils.setParam(LoginActivity.this,"name",name);
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            finish();
                        }
                    });
                }

                @Override
                public void onError(int i, String s) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "登陆失败"+s, Toast.LENGTH_SHORT).show();

                        }
                    });
                }

                @Override
                public void onProgress(int i, String s) {

                }
            });
        }
    }

}
