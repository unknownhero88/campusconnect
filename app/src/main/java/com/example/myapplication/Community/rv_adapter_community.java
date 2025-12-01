package com.example.myapplication.Community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class rv_adapter_community extends RecyclerView.Adapter<rv_adapter_community.ViewHolder> {

    List<rv_model_community> list;
    Context context;

    public rv_adapter_community(List<rv_model_community> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public rv_adapter_community.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_community_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rv_adapter_community.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView desc,date,rating;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            desc = itemView.findViewById(R.id.rv_desc);
            date = itemView.findViewById(R.id.rv_date);
            rating = itemView.findViewById(R.id.rv_rating);
            img = itemView.findViewById(R.id.rv_images);
        }
    }
}
