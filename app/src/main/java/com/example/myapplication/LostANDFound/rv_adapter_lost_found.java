package com.example.myapplication.LostANDFound;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.example.myapplication.R;

import java.util.List;

public class rv_adapter_lost_found extends RecyclerView.Adapter<rv_adapter_lost_found.ViewHolder> {

    List<rv_model_lost_found> list;
    Context context;
    rv_adapter_lost_found(List<rv_model_lost_found> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_lost_found_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        rv_model_lost_found model = list.get(position);

        if (model == null) return;

        holder.type.setText(model.getType());
        holder.cat.setText(model.getCat());
        holder.data_time.setText(model.getDate_time());

        String imgurl = model.getImg();

        if (holder.img != null && imgurl != null && !imgurl.isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(imgurl)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.img);
        } else if (holder.img != null) {
            holder.img.setImageResource(R.drawable.ic_launcher_foreground);
        }
    }


    @Override
    public int getItemCount() {
        Log.d("Adapter ", "getItemCount: "+list.size());
        if(list!=null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView type,cat,data_time,contact;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.rv_type);
            cat = itemView.findViewById(R.id.rv_catagory);
            data_time = itemView.findViewById(R.id.rv_date_time);
            img = itemView.findViewById(R.id.rv_images);
        }
    }
}
