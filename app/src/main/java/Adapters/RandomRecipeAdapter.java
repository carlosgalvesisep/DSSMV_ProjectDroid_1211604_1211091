package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.whatcanicook.R;
import com.squareup.picasso.Picasso;
import listeners.RecipeClickListener;
import models.RandomRecipeResponse;
import models.Recipe;
import models.RecipeResponse;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//copianço: https://stackoverflow.com/questions/60634260/how-to-create-a-custom-adapter-for-a-recyclerview

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeAdapter.RandomRecipeViewHolder>{
    //RandomRecipeResponse response;
    List<Recipe> response;
    Context context;
    RecipeClickListener listener;

    public RandomRecipeAdapter (Context context, List<Recipe> response, RecipeClickListener listener){
        this.context=context;
        this.response=response;
        this.listener=listener;
    }



    @NonNull
    @NotNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.recipe_in_list, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RandomRecipeViewHolder holder, int position) {
        holder.recipe_name.setText(response.get(position).title);
        holder.recipe_name.setSelected(true);
        //https://stackoverflow.com/questions/58003399/how-to-load-an-image-into-an-android-app-using-picasso-and-android-studio
        Picasso.get().load(response.get(position).image).into(holder.recipe_image);

        holder.recipe_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecipeClick(String.valueOf(response.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return response.size();
    }

    class RandomRecipeViewHolder extends RecyclerView.ViewHolder {

        private Context context;

        CardView recipe_card;
        TextView recipe_name;
        ImageView recipe_image;

        public RandomRecipeViewHolder(@NonNull @NotNull View recipeView) {
            super(recipeView);
            recipe_card = itemView.findViewById(R.id.recipe_card);
            recipe_name = itemView.findViewById(R.id.recipe_name);
            recipe_image = itemView.findViewById(R.id.recipe_image);
            context = recipeView.getContext();
        }
    }
}
