package android.shiny.view;

import android.shiny.R;
import android.shiny.model.PokemonSpecies;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonHolder> implements Filterable {
    private List<PokemonSpecies.PokemonNames> pokemons;
    private List<PokemonSpecies.PokemonNames> pokemonsFull;

    public PokemonAdapter() {
        super();
    }

    public void setPokemons(List<PokemonSpecies.PokemonNames> pokemons) {
        this.pokemons = pokemons;
        this.pokemonsFull = new ArrayList<>(pokemons);
    }

    @NonNull
    @Override
    public PokemonHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_pokemon, viewGroup, false);
        return new PokemonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonHolder pokemonHolder, int position) {

        PokemonSpecies.PokemonNames actualPokemon = pokemons.get(position);
        String actualPokemonName = actualPokemon.getName();

        int id = pokemonsFull.indexOf(actualPokemon);

        pokemonHolder.name.setText("#" + String.format("%03d", id + 1) + " " + actualPokemonName);

        String URL = "https://www.pokebip.com/pokedex-images/artworks/" + (id + 1) + ".png";
        Glide.with(pokemonHolder.itemView.getContext()).load(URL).into(pokemonHolder.sprite);
    }

    @Override
    public int getItemCount() {
        if (pokemons == null) {
            return 0;
        }
        return pokemons.size();
    }

    @Override
    public Filter getFilter() {
        Filter pokemonFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<PokemonSpecies.PokemonNames> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(pokemonsFull);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (PokemonSpecies.PokemonNames p : pokemonsFull) {
                        if (p.getName().toLowerCase().contains(filterPattern)){
                            filteredList.add(p);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                pokemons.clear();
                pokemons.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };

        return pokemonFilter;
    }
}
