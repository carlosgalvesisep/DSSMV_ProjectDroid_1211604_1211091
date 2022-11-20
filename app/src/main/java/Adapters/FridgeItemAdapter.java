
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


public class FridgeItemAdapter extends RecyclerView.Adapter<FridgeItemAdapter.FridgeItemViewHolder> {
    private ArrayList<IngredientModel> mIngredientsList;
    private OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener){
        listener = clickListener;
    }

    public static class FridgeItemViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewLine1;
        public TextView mTextViewLine2;
        private ImageView imageViewDelete;



        public FridgeItemViewHolder(@NonNull @NotNull View itemView, OnItemClickListener listener) {
            super(itemView);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
            mTextViewLine1 = itemView.findViewById(R.id.textview_ingName);
            mTextViewLine2 = itemView.findViewById(R.id.textview_ingQuantity);

            imageViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    public FridgeItemAdapter(ArrayList<IngredientModel> ingredientsList) {
        mIngredientsList = ingredientsList;
    }

    @Override
    public FridgeItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_fridge_item, parent, false);
        FridgeItemViewHolder fvh = new FridgeItemViewHolder(v, listener);
        return fvh;
    }

    @Override
    public void onBindViewHolder(FridgeItemViewHolder holder, int position) {
        IngredientModel currentItem = mIngredientsList.get(position);

        holder.mTextViewLine1.setText(currentItem.getName());
        holder.mTextViewLine2.setText(currentItem.getQuantity());
    }

    @Override
    public int getItemCount() {
        return mIngredientsList.size();
    }
}
