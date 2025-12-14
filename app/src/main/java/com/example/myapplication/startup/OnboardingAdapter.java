package com.example.myapplication.startup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.ViewHolder> {

    Context context;

    int[] images = {
            R.drawable.community,
            R.drawable.lost_and_found,
            R.drawable.board,
            R.drawable.studym
    };

    String[] titles = {
            "Community Posts",
            "Lost & Found",
            "Digital Notices",
            "Study Materials"
    };

    String[] desc = {
            "Connect with students",
            "Recover lost items",
            "Never miss updates",
            "Access study resources"
    };

    public OnboardingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_onboarding, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imgFeature.setImageResource(images[position]);
        holder.txtTitle.setText(titles[position]);
        holder.txtDesc.setText(desc[position]);
    }

    @Override
    public int getItemCount() {
        return titles.length; // 4 onboarding pages
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgFeature;
        TextView txtTitle, txtDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFeature = itemView.findViewById(R.id.imgFeature);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDesc = itemView.findViewById(R.id.txtDesc);
        }
    }
}
