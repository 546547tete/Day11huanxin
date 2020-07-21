package com.example.day11huanxin;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name2;
    private EditText et_psw2;
    private Button bt_zhuce2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        et_name2 = (EditText) findViewById(R.id.et_name2);
        et_psw2 = (EditText) findViewById(R.id.et_psw2);
        bt_zhuce2 = (Button) findViewById(R.id.bt_zhuce2);

        bt_zhuce2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_zhuce2:
                regist();
                break;
        }
    }

    private void regist() {
        String name = et_name2.getText().toString().trim();
        String psw = et_psw2.getText().toString().trim();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(psw)){
            new Thread(){
                @Override
                public void run() {
                    try {
                        EMClient.getInstance().createAccount(name,psw);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                RegisterActivity.this.finish();
                            }
                        });
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }.start();
        }else {
            Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
        }
    }
}
