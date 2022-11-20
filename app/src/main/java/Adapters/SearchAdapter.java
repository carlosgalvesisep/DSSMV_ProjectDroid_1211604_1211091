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
import models.RecipeResponse;
import models.Result;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.RecipeViewHolder>{
    List<Result> responses ;
    Context context;
    RecipeClickListener listener;


    public SearchAdapter (Context context, List<Result> responses, RecipeClickListener listener){
        this.context=context;
        this.responses=responses;
        this.listener=listener;
    }

    @NonNull
    @NotNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new RecipeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_in_list, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecipeViewHolder holder, int position) {
        holder.recipe_name.setText(responses.get(position).title);
        holder.recipe_name.setSelected(true);
        //https://stackoverflow.com/questions/58003399/how-to-load-an-image-into-an-android-app-using-picasso-and-android-studio
        Picasso.get().load(responses.get(position).image).into(holder.recipe_image);

        holder.recipe_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecipeClick(String.valueOf(responses.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return responses.size();
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
