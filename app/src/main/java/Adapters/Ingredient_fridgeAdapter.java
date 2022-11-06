package Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.recyclerview.widget.RecyclerView;
import com.example.whatcanicook.R;
import com.example.whatcanicook.your_fridge;
import models.IngredientModel;

import java.util.List;

public class Ingredient_fridgeAdapter extends RecyclerView.Adapter<Ingredient_fridgeAdapter.ViewHolder> {

    private List<IngredientModel> IngredientsList;
    private your_fridge activity;

    public Ingredient_fridgeAdapter(your_fridge activity){
        this.activity = activity;
    }

    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingrediente_fridge_layout, parent, false);
        return new ViewHolder(itemView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox ing;

        ViewHolder(View view){
            super(view);
            ing = view.findViewById(R.id.checkbox_ingrediente);
        }
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        IngredientModel ingr = IngredientsList.get(position);
        holder.ing.setText(ingr.getName());
        holder.ing.setChecked(toBoolean(ingr.getStatus()));
    }

    public int getItemCount(){
        return IngredientsList.size();
    }
    private boolean toBoolean(int n){
        return n != 0;
    }

    public void setIngredient(List<IngredientModel> IngredientsList){
        this.IngredientsList = IngredientsList;
        notifyDataSetChanged();
    }

}
