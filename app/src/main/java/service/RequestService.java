package service;

import android.content.Context;
import android.widget.Toast;
import com.example.whatcanicook.R;
import helper.Utils;
import listeners.RandomRecipeResponseListener;
import listeners.RecipeDetailsListener;
import listeners.RecipeResponseListener;
import models.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
        String [] ingredients = {"apple","rice","banana"};
        Call<RandomRecipeResponse> call = callRandomRecipes.callRandomRecipe("10",context.getString(R.string.api_key));
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

    public void getRecipes (RecipeResponseListener listener){
        CallRecipes callRecipes = retrofit.create(CallRecipes.class);
        String [] ingredients = {"apple"};
        Call<RecipeResponse> call = callRecipes.callRecipe(ingredients,"10",context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {

                if(!response.isSuccessful()){
                    listener.error(response.message());
                    return;
                }

                listener.fetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
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
        Call <RecipeResponse> callRecipe(
                //ingredients
                @Query("ingredients") String[] ingredients,
                //number
                @Query("number") String number,
                //ranking
                //ignorePantry
                //apiKey
                @Query("apiKey") String apiKey
        );
    }


/*
    private static void getBitmapIcons(RecipeDTO recipe) {
        List<RecipeDTO> recipe = recipe.get;
        for (WeatherConditionDTO day : weather) {
            if (day.getIcon() == null) {
                Bitmap icon = HttpOperation.getIcon(day.getIconUrl());
                city.setWeatherConditionIcon(day.getIconUrl(), icon);
            }
        }
    }

    private static CityDTO _getCityWeatherConditions(String urlStr) {
        CityDTO city = null;
        try {
            String jsonString = HttpOperation.get(urlStr);
            city = XmlHandler.deSerializeXml2CityDTO(jsonString);
            if (city != null) {
                getBitmapIcons(city);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }

    public static City getCityWeatherConditions(String urlStr) {
        CityDTO cityDTO = _getCityWeatherConditions(urlStr);
        City city = Mapper.cityDTO2city(cityDTO);
        return city;

    }
    */

}