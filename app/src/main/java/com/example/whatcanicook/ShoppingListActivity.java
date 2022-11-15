package com.example.whatcanicook;

import Adapters.ShoppingListAdapter;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class ShoppingListActivity extends AppCompatActivity {
    static ListView listView;
    static ArrayList<String> ingredients;
    static ShoppingListAdapter adapter;

    EditText input;
    ImageView enter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        listView = findViewById(R.id.listview_shopping);
        input = findViewById(R.id.input);
        enter = findViewById(R.id.add_ingredient);

        ingredients = new ArrayList<>();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String name = ingredients.get(i);
                makeToast(name);
            }
        });
            loadContent();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                makeToast("Removed: " + ingredients.get(i));
                removeIngredient(i);
                return false;
            }
        });

        adapter = new ShoppingListAdapter(getApplicationContext(), ingredients);
        listView.setAdapter(adapter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = input.getText().toString();
                if(text == null || text.length() == 0){
                    makeToast("Enter an ingredient");
                }else{
                    addIngredient(text);
                    input.setText("");
                    makeToast("Added "+ text);
                }
            }
        });
    }

    public void loadContent(){
        File path = getApplicationContext().getFilesDir();
        File readFrom = new File(path, "list.txt");
        byte[] content = new byte[(int) readFrom.length()];

        FileInputStream stream = null;
        try {
            stream = new FileInputStream(readFrom);
            stream.read(content);

            String s = new String(content);
            //[kiwi, banana, apple]
            s = s.substring(1, s.length() - 1);
            String[] split = s.split(", ");
            ingredients = new ArrayList<>(Arrays.asList(split));
            adapter = new ShoppingListAdapter(this, ingredients);
            listView.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        File path = getApplicationContext().getFilesDir();
        try {
            FileOutputStream writer = new FileOutputStream(new File(path, "list.txt"));
            writer.write(ingredients.toString().getBytes());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();

    }

    public static void addIngredient(String ingredient){
        ingredients.add(ingredient);
        listView.setAdapter(adapter);
    }
    public static void removeIngredient(int remove ){
        ingredients.remove(remove);
        listView.setAdapter(adapter);
    }
    Toast t;

    private void makeToast(String s) {
        if (t != null) t.cancel();
        t = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        t.show();
    }
}