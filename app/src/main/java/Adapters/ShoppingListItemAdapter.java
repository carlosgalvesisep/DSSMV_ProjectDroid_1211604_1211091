package Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.whatcanicook.R;
import models.IngredientModel;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class ShoppingListItemAdapter extends RecyclerView.Adapter<ShoppingListItemAdapter.ShoppingListItemViewHolder> {
    private ArrayList<IngredientModel> mIngredientsList;
    private OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener){
        listener = clickListener;
    }

    public static class ShoppingListItemViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewLine1;
        public TextView mTextViewLine2;
        private ImageView imageViewDelete;



        public ShoppingListItemViewHolder(@NonNull @NotNull View itemView, ShoppingListItemAdapter.OnItemClickListener listener) {
            super(itemView);
            imageViewDelete = itemView.findViewById(R.id.removeSL);
            mTextViewLine1 = itemView.findViewById(R.id.nameSL);
            mTextViewLine2 = itemView.findViewById(R.id.ingQuantitySL);

            imageViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    public ShoppingListItemAdapter(ArrayList<IngredientModel> ingredientsList) {
        mIngredientsList = ingredientsList;
    }


    @Override
    public ShoppingListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoppinglist_item_activity, parent, false);
        ShoppingListItemViewHolder svh = new ShoppingListItemViewHolder(v,listener);
        return svh;
    }

    @Override
    public void onBindViewHolder(ShoppingListItemViewHolder holder, int position) {
        IngredientModel currentItem = mIngredientsList.get(position);

        holder.mTextViewLine1.setText(currentItem.getName());
        holder.mTextViewLine2.setText(currentItem.getQuantity());
    }

    @Override
    public int getItemCount() {
        return mIngredientsList.size();
    }
}