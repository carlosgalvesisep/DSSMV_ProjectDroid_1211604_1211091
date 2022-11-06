package com.example.whatcanicook;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import java.util.ArrayList;
import java.util.List;

public class your_fridge extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layoutList;
    Button buttonAdd;
    List<String> listQuantity = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_fridge);

        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.button_add);

        buttonAdd.setOnClickListener(this);

        listQuantity.add("Quantity");
        listQuantity.add("One");
        listQuantity.add("Two");
        listQuantity.add("Three");
        listQuantity.add("Four");




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.button_add:

                addView();

                break;

            /*case R.id.button_submit_list:

                if(checkIfValidAndRead()){

                    Intent intent = new Intent(MainActivity.this,ActivityCricketers.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list",cricketersList);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }

                break;*/

        }


    }


    private void addView() {
        final View ingredienteView = getLayoutInflater().inflate(R.layout.row_add_ingredient,null, false);
        EditText editText = ingredienteView.findViewById(R.id.edit_ingredient_name);
        AppCompatSpinner spinnerQuantidade = ingredienteView.findViewById(R.id.spinner_quantidade);
        ImageView imageClose = ingredienteView.findViewById(R.id.image_remove);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listQuantity);
        spinnerQuantidade.setAdapter(arrayAdapter);

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