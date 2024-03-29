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
import models.Recipe;
import models.RecipeResponse;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class IngredientRecipeAdapter extends RecyclerView.Adapter<IngredientRecipeAdapter.RecipeViewHolder>{
    List<RecipeResponse> recipes = new ArrayList<>();
    Context context;

    public IngredientRecipeAdapter (Context context, List<RecipeResponse> recipes){
        this.context=context;
        this.recipes=recipes;
    }

    @NonNull
    @NotNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new RecipeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_in_list, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull IngredientRecipeAdapter.RecipeViewHolder holder, int position) {
        holder.recipe_name.setText(recipes.get(position).title);
        holder.recipe_name.setSelected(true);
        //https://stackoverflow.com/questions/58003399/how-to-load-an-image-into-an-android-app-using-picasso-and-android-studio
        Picasso.get().load(recipes.get(position).image).into(holder.recipe_image);

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {

        private Context context;

        CardView recipe_card;
        TextView recipe_name;
        ImageView recipe_image;

        public RecipeViewHolder(@NonNull @NotNull View recipeView) {
            super(recipeView);
            recipe_card = itemView.findViewById(R.id.recipe_card);
            recipe_name = itemView.findViewById(R.id.recipe_name);
            recipe_image = itemView.findViewById(R.id.recipe_image);
            context = recipeView.getContext();
        }
    }
}
