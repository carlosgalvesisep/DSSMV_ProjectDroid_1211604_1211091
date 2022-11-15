package Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.whatcanicook.R;
import com.example.whatcanicook.ShoppingListActivity;

import java.util.ArrayList;

public class ShoppingListAdapter extends ArrayAdapter<String> {
    ArrayList<String> list;
    Context context;

    public ShoppingListAdapter(Context context, ArrayList<String> ingredientes){
        super(context, R.layout.list_row, ingredientes);
        this.context = context;
        list = ingredientes;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_row, null);

           /* TextView number = convertView.findViewById(R.id.number);
            number.setText(position + 1 + ".");*/

            TextView name = convertView.findViewById(R.id.name);
            name.setText(list.get(position));

            ImageView duplicate = convertView.findViewById(R.id.copy);
            ImageView remove = convertView.findViewById(R.id.remove);
            ImageView bought = convertView.findViewById(R.id.bought);
            duplicate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShoppingListActivity.addIngredient(list.get(position));
                }
            });

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                ShoppingListActivity.removeIngredient(position);
                }
            });

            bought.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
        return convertView;
    }
}