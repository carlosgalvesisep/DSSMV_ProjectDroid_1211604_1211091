package service;

import android.content.Context;
import android.widget.Toast;
import com.example.whatcanicook.R;
import helper.Utils;
import listeners.RandomRecipeResponseListener;
import listeners.RecipeDetailsListener;
import listeners.RecipeResponseListener;
import listeners.ShoppingListListener;
import models.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

import java.util.List;
import java.util.Random;

public class RequestService {

    Context context;
    private static RequestService instance = null;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Utils.URL_SERVICE_PREFIX)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestService(Context context) {
        this.context = context;
    }





    //https://www.section.io/engineering-education/making-api-requests-using-retrofit-android/
    public void getRandomRecipes (RandomRecipeResponseListener listener){
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipeResponse> call = callRandomRecipes.callRandomRecipe("1",context.getString(R.string.api_key));
        call.enqueue(new Callback<RandomRecipeResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeResponse> call, Response<RandomRecipeResponse> response) {
                //RandomRecipeResponse randomRecipeResponse = new RandomRecipeResponse();
                //randomRecipeResponse = response.body();

                if(!response.isSuccessful()){
                    listener.error(response.message());
                    return;
                }

                listener.fetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeResponse> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getRecipes (RecipeResponseListener listener, String[] ingredients){
        CallRecipes callRecipes = retrofit.create(CallRecipes.class);
        //ingredients = new String[]{"rice", "chicken"};
        Call<List<RecipeResponse>> call = callRecipes.callRecipe(ingredients,"2",1,context.getString(R.string.api_key));
        call.enqueue(new Callback<List<RecipeResponse>>() {
            @Override
            public void onResponse(Call<List<RecipeResponse>> call, Response<List<RecipeResponse>> response) {

                if(!response.isSuccessful()){
                    listener.error(response.message());
                    return;
                }

                listener.fetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<RecipeResponse>> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getRecipeDetails(RecipeDetailsListener listener, int id){
        CallRecipeDetails callRecipeDetails = retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetails> call = callRecipeDetails.callRecipeDetails(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetails>() {
            @Override
            public void onResponse(Call<RecipeDetails> call, Response<RecipeDetails> response) {
                if (!response.isSuccessful()){
                    listener.error(response.message());
                    return;
                }
                listener.fetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetails> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });
    }

    public void computeShoppingList (ShoppingListListener listener, int id){
        ComputeShoppingList computeShoppingList = retrofit.create(ComputeShoppingList.class);
        String[] items = {"tomatoes"};
        Call<ShoppingListResponse> call = computeShoppingList.computeShoppingList(context.getString(R.string.api_key), items);

        call.enqueue(new Callback<ShoppingListResponse>() {
            @Override
            public void onResponse(Call<ShoppingListResponse> call, Response<ShoppingListResponse> response) {
                if(!response.isSuccessful()){
                    listener.error(response.message());
                    return;
                }
                listener.fetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<ShoppingListResponse> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });
    }

    private interface CallRandomRecipes {
        @GET("recipes/random")
        Call <RandomRecipeResponse> callRandomRecipe(
                //limitlicense
                //tags
                //ingredients
                //@Query("ingredients") String[] ingredients,
                //number
                @Query("number") String number,
                //apiKey
                @Query("apiKey") String apiKey
        );
    }

    private interface CallRecipeDetails{
        @GET("recipes/{id}/information")
        Call<RecipeDetails> callRecipeDetails(
                //id
                @Path("id") int id,
                //apiKey
                @Query("apiKey") String apiKey
        );
    }

    private interface CallRecipes {
        @GET("recipes/findByIngredients")
        Call <List<RecipeResponse>> callRecipe(
                //ingredients
                @Query("ingredients") String[] ingredients,
                //number
                @Query("number") String number,
                //ranking
                @Query("ranking")  int ranking,
                //ignorePantry
                //apiKey
                @Query("apiKey") String apiKey
        );
    }

    private interface ComputeShoppingList {
        @POST("mealplanner/shopping-list/compute")
        Call <ShoppingListResponse> computeShoppingList(
                //apiKey
                @Query("apiKey") String apiKey,
                @Body String[] items
        );
    }


}