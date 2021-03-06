package android.shiny.view.main_activity;

import android.shiny.R;
import android.shiny.data.Pokemon;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonHolder> { //implements Filterable {
    //private List<Pokemon> pokemonsFull = new ArrayList<>();
    private List<Pokemon> pokemons = new ArrayList<>();

    public PokemonAdapter() {
        super();
    }

    @NonNull
    @Override
    public PokemonHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_pokemon, viewGroup, false);
        return new PokemonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonHolder pokemonHolder, int position) {
        Pokemon current = pokemons.get(position);
        pokemonHolder.name.setText("#" + String.format("%03d", current.getId()) + " " + current.getName_fr());
        Glide.with(pokemonHolder.itemView.getContext()).load(current.getUrl_artwork()).into(pokemonHolder.artwork);
    }

    @Override
    public int getItemCount() {
        if (pokemons == null) {
            return 0;
        }
        return pokemons.size();
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons.clear();
        this.pokemons.addAll(pokemons);
        notifyDataSetChanged();
    }


    /*@Override
    public Filter getFilter() {
        Filter pokemonFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Pokemon> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(pokemonsFull);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for ( Pokemon p : pokemonsFull) {
                        if (p.getName_fr().toLowerCase().contains(filterPattern)){
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
    }*/
}
