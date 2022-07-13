package com.balius.filimo.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.balius.filimo.R;
import com.balius.filimo.model.category.VideoCategories;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryVH> {
    List<VideoCategories> categoriesList;
    Context context;
    LayoutInflater inflater;
    public CategoryAdapter(List<VideoCategories> categoriesList, Context context) {
        this.categoriesList=categoriesList;
        this.context=context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CategoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =inflater.inflate(R.layout.cat_row,null);

        return new CategoryVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryVH holder, int position) {


        VideoCategories categories = categoriesList.get(position);

        holder.txt_title.setText(categories.getCategoryName());
        holder.txt_title.setTypeface(null, Typeface.BOLD);

        Picasso.get().load(categories.getCategoryImageThumb()).into(holder.img_categories);

    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    class CategoryVH extends RecyclerView.ViewHolder{
        AppCompatImageView img_categories;
        AppCompatTextView txt_title;

        public CategoryVH(@NonNull View itemView) {
            super(itemView);
            img_categories = itemView.findViewById(R.id.img_categories);
            txt_title = itemView.findViewById(R.id.txt_title);
        }
    }
}
