package com.example.task_internship;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {
    Context context;
    List<Image_model> imageModels;

    public ImageAdapter(Context context, List<Image_model> imageModels) {
        this.context = context;
        this.imageModels = imageModels;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item_view,parent,false);
        return new ImageHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ImageAdapter.ImageHolder holder, int position) {

        Image_model imageModel = imageModels.get(position);

        Glide.with(context)
                .load(imageModel.getRegular())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return imageModels.size();
    }

    public class  ImageHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ImageHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_list);
        }
    }
}
