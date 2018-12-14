package android.shiny.view.pokemon_activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.shiny.R;
import android.shiny.data.Pokemon;
import android.shiny.data.PokemonViewModel;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class PokemonFragment extends Fragment {
    int pokeId;

    ImageView artwork;
    ImageView sprite;
    ImageView shiny;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_infos, container, false);
        pokeId = this.getArguments().getInt("id");
        Pokemon myPokemon = ViewModelProviders.of(this).get(PokemonViewModel.class).getPokemon(pokeId);

        artwork = (ImageView) view.findViewById(R.id.artwork);
        sprite = (ImageView) view.findViewById(R.id.sprite);
        shiny = (ImageView) view.findViewById(R.id.sprite_shiny);

        getActivity().setTitle(myPokemon.getName_fr()+" - "+myPokemon.getName_en());

        Glide.with(getActivity()).load(myPokemon.getUrl_artwork()).into(artwork);
        Glide.with(getActivity()).load(myPokemon.getUrl_sprite()).into(sprite);
        Glide.with(getActivity()).load(myPokemon.getUrl_shiny()).into(shiny);

        return view;
    }
}
