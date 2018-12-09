package android.shiny.view.main_activity;

import android.shiny.R;
import android.shiny.model.PokemonSpecies;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonHolder> {
    private List<PokemonSpecies.PokemonNames> pokemons;

    public PokemonAdapter() {
        super();
    }

    public void setPokemons(List<PokemonSpecies.PokemonNames> pokemons) {
        this.pokemons = pokemons;
    }

    @NonNull
    @Override
    public PokemonHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_pokemon, viewGroup, false);
        return new PokemonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonHolder pokemonHolder, int position) {
        String actualPokemon = pokemons.get(position).getName();
        pokemonHolder.name.setText("#"+String.format("%03d",position+1)+" "+actualPokemon);

        String URL = "https://www.pokebip.com/pokedex-images/artworks/"+(position+1)+".png";
        Glide.with(pokemonHolder.itemView.getContext()).load(URL).into(pokemonHolder.sprite);
    }

    @Override
    public int getItemCount() {
        if ( pokemons == null ) {
            return 0;
        }
        return pokemons.size();
    }
}
