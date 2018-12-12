package android.shiny.view.main_activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import android.shiny.R;

import android.shiny.data.Pokemon;
import android.shiny.data.PokemonViewModel;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PokemonViewModel pokemonViewModel;

    public RecyclerView rv_pokemon;
    public PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_pokemon = findViewById(R.id.rv_pokemon);
        rv_pokemon.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_pokemon.setHasFixedSize(true); // a enlever je pense

        adapter = new PokemonAdapter();
        rv_pokemon.setAdapter(adapter);

        pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);
        pokemonViewModel.getAllPokemons().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(@Nullable List<Pokemon> pokemonEntities) {
                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
                adapter.setPokemons(pokemonEntities);
            }
        });

        /*adapter = new PokemonAdapter();



        PokemonService pokemonService = new Retrofit.Builder()
                .baseUrl(PokemonService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PokemonService.class);

        pokemonService.listPokemons().enqueue(new Callback<getPokemonList>() {
            @Override
            public void onResponse(Call<getPokemonList> call, Response<getPokemonList> response) {
                adapter.setPokemons(response.body().getResults());

                Context context = rv_pokemon.getContext();
                LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down);
                rv_pokemon.setAdapter(adapter);

                rv_pokemon.setLayoutAnimation(controller);

                rv_pokemon.getAdapter().notifyDataSetChanged();
                rv_pokemon.scheduleLayoutAnimation();
            }

            @Override
            public void onFailure(Call<getPokemonList> call, Throwable t) {
                Log.e("Retrofit", "onFailure: " + t.getMessage());
                Log.e("Retrofit", "onFailure: " + t.getCause());
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String string) {
                //adapter.getFilter().filter(string);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
