package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.whatcanicook.R;
import com.squareup.picasso.Picasso;
import models.ExtendedIngredient;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientViewHolder>{

    Context context;
    List<ExtendedIngredient> list;

    public IngredientAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new IngredientViewHolder(LayoutInflater.from(context).inflate(R.layout.list_recipe_ingredients, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull IngredientViewHolder holder, int position) {
        holder.ingredient_name.setText(list.get(position).name);
        holder.ingredient_name.setSelected(true);
        holder.ingredient_quantity.setText((list.get(position).original));
        holder.ingredient_quantity.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/" + list.get(position).image).into(holder.ingredient_image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}


class IngredientViewHolder extends RecyclerView.ViewHolder {
    TextView ingredient_quantity, ingredient_name;
    ImageView ingredient_image;

    public IngredientViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        ingredient_quantity = itemView.findViewById(R.id.ingredient_quantity);
        ingredient_name = itemView.findViewById(R.id.ingredient_name);
        ingredient_image = itemView.findViewById(R.id.ingredient_image);
    }
}
