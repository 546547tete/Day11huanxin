package com.example.day11huanxin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.day11huanxin.R;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EMmessageAdapter extends RecyclerView.Adapter {
    private ArrayList<EMMessage> list;
    private Context context;

    public EMmessageAdapter(ArrayList<EMMessage> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EMMessage message = list.get(position);
        ViewHolder holder1= (ViewHolder) holder;
        String body = message.getBody().toString();
        String from = message.getFrom();
        holder1.tv_msg.setText(from+"::"+from);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RcyAdapter.ViewHolder {
        public View rootView;
        public TextView tv_msg;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_msg = (TextView) rootView.findViewById(R.id.tv_msg);
        }

    }
}
