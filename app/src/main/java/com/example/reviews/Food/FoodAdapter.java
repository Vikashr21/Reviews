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
import com.example.reviews.FoodActivity2;
import com.example.reviews.R;

import java.util.List;

public class FoodAdapter  extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {








    List<Food> meals;
    public FoodAdapter(List<Food> meals)
    {
        this.meals=meals;
    }


    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fooditems, parent, false);
        return new FoodAdapter.FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
    holder.bind(meals.get(position));
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public void appendMeals(List<Food> mealsToAppend) {
        meals.addAll(mealsToAppend);
        notifyDataSetChanged();
    }


    public class FoodViewHolder extends RecyclerView.ViewHolder {


        ImageView poster;
        TextView title;
        Food meal;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            poster=itemView.findViewById(R.id.thumbnail);
            title=itemView.findViewById(R.id.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(v.getContext(), FoodActivity2.class);
                    intent.putExtra(FoodActivity2.FOOD_ID,meal.getStrMeal());
                   // Toast.makeText(v.getContext(),""+meal.getStrMeal(),Toast.LENGTH_LONG).show();
                    v.getContext().startActivity(intent);
                }
            });
        }

        public void bind(Food meal) {

            title.setText(meal.getStrMeal());
            Glide.with(itemView)
                    .load(meal.getThumb())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(poster);
            this.meal=meal;
        }
    }
}
