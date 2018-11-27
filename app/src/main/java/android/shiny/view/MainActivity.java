package android.shiny.view;

import android.shiny.R;

import android.shiny.data.PokemonService;
import android.shiny.model.PokemonSpecies;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


import javax.security.auth.login.LoginException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public RecyclerView rv_pokemon;

    List<PokemonSpecies> list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_pokemon = findViewById(R.id.rv_pokemon);
        rv_pokemon.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        final PokemonAdapter adapter = new PokemonAdapter();

        PokemonService pokemonService = new Retrofit.Builder()
                .baseUrl(PokemonService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PokemonService.class);

        pokemonService.listPokemons().enqueue(new Callback<PokemonSpecies>() {
            @Override
            public void onResponse(Call<PokemonSpecies> call, Response<PokemonSpecies> response) {
                adapter.setPokemons(response.body().getResults());
                rv_pokemon.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PokemonSpecies> call, Throwable t) {
                Log.e("Retrofit", "onFailure: " + t.getMessage());
                Log.e("Retrofit", "onFailure: " + t.getCause());
            }
        });


    }
}
