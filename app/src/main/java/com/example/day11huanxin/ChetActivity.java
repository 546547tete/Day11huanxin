package com.example.day11huanxin;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.day11huanxin.adapter.EMmessageAdapter;
import com.example.day11huanxin.utils.SpUtils;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChetActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_chet;
    private EditText et_message;
    private Button bt_deng;
    private RecyclerView rcy_chat;
    private String toname;
    private String myname;
    private EMMessageListener emMessageListener;
    private ArrayList<EMMessage> emMessages;
    private EMmessageAdapter eMmessageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chet);
        initView();
        initData();
        initRcy();
        initMessage();
    }

    private void initRcy() {
        rcy_chat.setLayoutManager(new LinearLayoutManager(this));
        rcy_chat.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        emMessages = new ArrayList<>();
        eMmessageAdapter = new EMmessageAdapter(emMessages, this);
        rcy_chat.setAdapter(eMmessageAdapter);
    }

    private void initMessage() {
        emMessageListener = new EMMessageListener() {
            @Override
            public void onMessageReceived(List<EMMessage> list) {

            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> list) {

            }

            @Override
            public void onMessageRead(List<EMMessage> list) {

            }

            @Override
            public void onMessageDelivered(List<EMMessage> list) {

            }

            @Override
            public void onMessageRecalled(List<EMMessage> list) {

            }

            @Override
            public void onMessageChanged(EMMessage emMessage, Object o) {

            }
        };
        EMClient.getInstance().chatManager().addMessageListener(emMessageListener);
    }

    private void initData() {
        toname = getIntent().getStringExtra("toname");
        myname = (String) SpUtils.getParam(this, "name", "未登录");
        tv_chet.setText(myname+"和"+toname+"在尬聊");
    }

    private void initView() {
        tv_chet = (TextView) findViewById(R.id.tv_chet);
        et_message = (EditText) findViewById(R.id.et_message);
        bt_deng = (Button) findViewById(R.id.bt_deng);
        rcy_chat = (RecyclerView) findViewById(R.id.rcy_chat);

        bt_deng.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_deng:
                sendText();
                break;
        }
    }

    private void sendText() {
        String trim = et_message.getText().toString().trim();
        
    }
}
