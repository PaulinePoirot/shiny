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
        rv_pokemon.setHasFixedSize(true);

        adapter = new PokemonAdapter();
        rv_pokemon.setAdapter(adapter);

        pokemonViewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);
        pokemonViewModel.getAllPokemons().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(@Nullable List<Pokemon> pokemonEntities) {
                adapter.setPokemons(pokemonEntities);
            }
        });
    }
}
    /*@Override
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
                adapter.getFilter().filter(string);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }*/
