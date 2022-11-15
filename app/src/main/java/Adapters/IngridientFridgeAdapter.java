package Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.whatcanicook.R;
import models.IngredientModel;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class IngridientFridgeAdapter extends RecyclerView.Adapter<IngridientFridgeAdapter.IngredienteView> {

    ArrayList<IngredientModel> ingredientsList = new ArrayList<>();

    public IngridientFridgeAdapter(ArrayList<IngredientModel> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    @NonNull
    @NotNull
    @Override
    public IngredienteView onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_ingredientfridge, parent, false);


        return new IngredienteView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull IngredienteView holder, int position) {

        IngredientModel ingredient = ingredientsList.get(position);
        holder.textIngredientName.setText(ingredient.getName());
        holder.textIngredientQuantity.setText(ingredient.getQuantity());


    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    public class IngredienteView extends RecyclerView.ViewHolder {

        TextView textIngredientName, textIngredientQuantity;
        public IngredienteView(@NonNull @NotNull View itemView) {
            super(itemView);

            textIngredientName = (TextView) itemView.findViewById(R.id.txt_ingredientName);
            textIngredientQuantity = (TextView) itemView.findViewById(R.id.txt_ingredientQuantity);

        }
    }

}
