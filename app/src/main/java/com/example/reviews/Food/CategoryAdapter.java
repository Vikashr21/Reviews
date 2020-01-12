package com.example.reviews.Food;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.reviews.FoodcategoryActivity;
import com.example.reviews.R;

import java.util.List;

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {








    List<Category> category;
    public CategoryAdapter(List<Category> category)
    {
        this.category=category;
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoryfoodview, parent, false);
        return new CategoryAdapter.CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bind(category.get(position));
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public void appendCategory(List<Category> categoryToAppend) {
        category.addAll(categoryToAppend);
        notifyDataSetChanged();
    }


    public class CategoryViewHolder extends RecyclerView.ViewHolder {


        ImageView poster;
        TextView title;

      Category category;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            poster=itemView.findViewById(R.id.thumbnail);
            title=itemView.findViewById(R.id.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(v.getContext(), FoodcategoryActivity.class);
                    intent.putExtra(FoodcategoryActivity.CATEGORY,category.getCategory());
                    // Toast.makeText(v.getContext(),""+meal.getStrMeal(),Toast.LENGTH_LONG).show();
                    v.getContext().startActivity(intent);
                }
            });

        }

        public void bind(Category category) {

            title.setText(category.getCategory());

            Glide.with(itemView)
                    .load(category.getThumb())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(poster);
            this.category=category;
        }
    }
}
