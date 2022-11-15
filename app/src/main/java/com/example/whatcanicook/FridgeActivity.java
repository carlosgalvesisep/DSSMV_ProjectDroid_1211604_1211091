package com.example.whatcanicook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import models.IngredientModel;

import java.util.ArrayList;
import java.util.List;

public class FridgeActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layoutList;
    Button buttonAdd,buttonSubmitList;
    ArrayList<IngredientModel> ingredientModelArrayList = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_fridge);

        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.button_add);
        buttonSubmitList = findViewById(R.id.button_submit_list);

        buttonAdd.setOnClickListener(this);
        buttonSubmitList.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.button_add:

                addView();

                break;

            case R.id.button_submit_list:

                if(checkIfValidAndRead()){

                    Intent intent = new Intent(FridgeActivity.this,IngredientsFridgeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list",ingredientModelArrayList);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }

                break;

        }


    }

    private boolean checkIfValidAndRead() {
        ingredientModelArrayList.clear();
        boolean result = true;

        for (int i = 0; i<layoutList.getChildCount();i++){
            View ingredienteView = layoutList.getChildAt(i);
            EditText editText = ingredienteView.findViewById(R.id.edit_ingredient_name);
            EditText editQuantity = ingredienteView.findViewById(R.id.edit_ingredient_quantity);

            IngredientModel ingredient = new IngredientModel();

            if (!editText.getText().toString().equals("") || !editQuantity.getText().toString().equals("")){
                ingredient.setName(editText.getText().toString());
                ingredient.setQuantity(editQuantity.getText().toString());
            }else{
                result = false;
                break;
            }

            ingredientModelArrayList.add(ingredient);

        }
            if (ingredientModelArrayList.size()==0){
                result = false;
                Toast.makeText(this, "Add ingredients first!", Toast.LENGTH_SHORT).show();
            } else if (!result) {
                Toast.makeText(this, "Enter all details correctly", Toast.LENGTH_SHORT).show();
            }
            return result;
    }


    private void addView() {
        final View ingredienteView = getLayoutInflater().inflate(R.layout.row_add_ingredient,null, false);
        EditText editText = ingredienteView.findViewById(R.id.edit_ingredient_name);
        EditText editQuantity = ingredienteView.findViewById(R.id.edit_ingredient_quantity);
        ImageView imageClose = ingredienteView.findViewById(R.id.image_remove);


        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(ingredienteView);
            }
        });
        layoutList.addView(ingredienteView);
    }

    private void removeView(View view){
        layoutList.removeView(view);
    }
}