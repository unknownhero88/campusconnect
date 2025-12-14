package com.example.myapplication.startup;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.myapplication.R;
import com.example.myapplication.authActivity.Login;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.ViewHolder> {

    Context context;

    int[] animations = {
            R.raw.community,
            R.raw.lostandfound,
            R.raw.digitalboard,
            R.raw.studym
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

        holder.animationView.setAnimation(animations[position]);
        holder.animationView.playAnimation();

        holder.txtTitle.setText(titles[position]);
        holder.txtDesc.setText(desc[position]);

        holder.btnNext.setOnClickListener(v -> {
            int next = holder.getAdapterPosition() + 1;

            if (next < titles.length) {
                ((OnboardingActivity) context).viewPager.setCurrentItem(next);
            } else {
                context.startActivity(new Intent(context, Login.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LottieAnimationView animationView;
        TextView txtTitle, txtDesc;
        Button btnNext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            animationView = itemView.findViewById(R.id.animationView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            btnNext = itemView.findViewById(R.id.btnNext);
        }
    }
}
