package com.example.day11huanxin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.day11huanxin.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RcyAdapter extends RecyclerView.Adapter {
    private ArrayList<String> list;
    private Context context;

    public RcyAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vie = LayoutInflater.from(context).inflate(R.layout.item_rcy, parent, false);
        ViewHolder holder = new ViewHolder(vie);
        vie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onJianTing!=null){
                    onJianTing.onClick(holder.getLayoutPosition());
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1= (ViewHolder) holder;
        String s = list.get(position);
        holder1.tv_rcy.setText(s);
    }

    private OnJianTing onJianTing;
    public interface OnJianTing{
        void onClick(int position);
    }

    public void setOnJianTing(OnJianTing onJianTing) {
        this.onJianTing = onJianTing;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_rcy;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_rcy = (TextView) rootView.findViewById(R.id.tv_rcy);
        }

    }
}
