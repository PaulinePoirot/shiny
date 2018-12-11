package android.shiny.view;

import android.content.Context;

import android.shiny.R;

// import android.shiny.data.PokemonService;
//import android.shiny.model.PokemonSpecies;
import android.shiny.api.PokemonService;
import android.shiny.api.PokemonSpecies;


import android.shiny.data.Data;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public RecyclerView rv_pokemon;

    private List<PokemonSpecies.PokemonNames> list;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_pokemon = findViewById(R.id.rv_pokemon);
        rv_pokemon.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


    }

    @Override
    protected void onStart() {
        super.onStart();

        PokemonService pokemonService = new Retrofit.Builder()
                .baseUrl(PokemonService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PokemonService.class);

        pokemonService.listPokemons().enqueue(new Callback<PokemonSpecies>() {
            @Override
            public void onResponse(Call<PokemonSpecies> call, Response<PokemonSpecies> response) {

                try {
                    list = response.body().getResults();
                } catch (NullPointerException e) {
                    System.out.print("Connection error.");
                }
                data = new Data();
                data.updatePokemonsList(list);
            }

            @Override
            public void onFailure(Call<PokemonSpecies> call, Throwable t) {
                Log.e("Retrofit", "onFailure: " + t.getMessage());
                Log.e("Retrofit", "onFailure: " + t.getCause());
            }
        });

    }
}
