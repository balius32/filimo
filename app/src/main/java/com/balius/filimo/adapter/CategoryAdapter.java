package com.balius.filimo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.balius.filimo.R;
import com.balius.filimo.activities.CategoryActivity;
import com.balius.filimo.model.category.VideoCategories;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryVH> {
    List<VideoCategories> categoriesList;
    Context context;

    public CategoryAdapter(List<VideoCategories> categoriesList, Context context) {
        this.categoriesList = categoriesList;
        this.context = context;

    }

    @NonNull
    @Override
    public CategoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.cat_row, null);

        return new CategoryVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryVH holder, int position) {


        VideoCategories categories = categoriesList.get(position);
        holder.txt_title.setText(categories.getCategoryName());


        Picasso.get().load(categories.getCategoryImageThumb()).into(holder.img_categories);

        holder.view.bringToFront();
        holder.txt_title.bringToFront();


        holder.img_categories.setOnClickListener(view -> {
            Intent intent = new Intent(context, CategoryActivity.class);
            intent.putExtra("catId", categories.getCid());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    static class CategoryVH extends RecyclerView.ViewHolder {
        AppCompatImageView img_categories;
        AppCompatTextView txt_title;
        View view;

        public CategoryVH(@NonNull View itemView) {
            super(itemView);
            img_categories = itemView.findViewById(R.id.img_categories);
            txt_title = itemView.findViewById(R.id.txt_title);
            view = itemView.findViewById(R.id.view);

        }
    }


}