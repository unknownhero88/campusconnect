package com.example.myapplication.StudyMaterial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class StudyMaterialAdapter extends RecyclerView.Adapter<StudyMaterialAdapter.ViewHolder> {

    private final List<StudyMaterialModel> list;

    public StudyMaterialAdapter(List<StudyMaterialModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_study_material, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudyMaterialModel model = list.get(position);

        holder.txtTitle.setText(model.title);
        holder.txtSubtitle.setText(model.subtitle);

        // OPTIONAL: change icon based on file type
        if (model.subtitle.contains("PDF")) {
            holder.imgFileType.setImageResource(R.drawable.ic_pdf);
        } else if (model.subtitle.contains("PPT")) {
            holder.imgFileType.setImageResource(R.drawable.ic_ppt);
        } else {
            holder.imgFileType.setImageResource(R.drawable.ic_file);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // ViewHolder
    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgFileType;
        TextView txtTitle, txtSubtitle;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFileType = itemView.findViewById(R.id.imgFileType);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtSubtitle = itemView.findViewById(R.id.txtSubtitle);
        }
    }
}
