package com.example.day11huanxin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.day11huanxin.adapter.RcyAdapter;
import com.example.day11huanxin.utils.SpUtils;
import com.hyphenate.chat.EMClient;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private RecyclerView rcy;
    private ArrayList<String> list;
    private RcyAdapter rcyAdapter;
    private String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EMClient.getInstance().groupManager().loadAllGroups();
        EMClient.getInstance().chatManager().loadAllConversations();
        initView();
        initData();
    }

    private void initData() {
        String str = (String) SpUtils.getParam(this, "name", "未登录");
        tv.setText("当前登陆人是：" + str);

        rcy.setLayoutManager(new LinearLayoutManager(this));
        rcy.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        rcyAdapter = new RcyAdapter(list, this);
        rcy.setAdapter(rcyAdapter);
        rcyAdapter.setOnJianTing(new RcyAdapter.OnJianTing() {
            @Override
            public void onClick(int position) {
                String s = list.get(position);
                if (string.equals(s)){
                    Toast.makeText(MainActivity.this, "不能和自己聊天", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(MainActivity.this, ChetActivity.class);
                    intent.putExtra("toname",s);
                    startActivity(intent);
                }
            }
        });
    }

    private void initView() {
        tv = (TextView) findViewById(R.id.tv);
        rcy = (RecyclerView) findViewById(R.id.rcy);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "退出登录");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == 1) {
            EMClient.getInstance().logout(true);
            Toast.makeText(this, "已推出登录", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
