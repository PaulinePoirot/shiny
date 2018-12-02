package android.shiny.view.pokemon_activity;

import android.os.Bundle;
import android.shiny.R;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class PokemonFragment extends Fragment {
    ImageView artwork;
    ImageView sprite;
    ImageView shiny;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_infos, container, false);

        artwork = (ImageView) view.findViewById(R.id.artwork);
        sprite = (ImageView) view.findViewById(R.id.sprite);
        shiny = (ImageView) view.findViewById(R.id.sprite_shiny);


        Glide.with(getActivity()).load("https://www.pokebip.com/pokedex-images/artworks/197.png").into(artwork);
        Glide.with(getActivity()).load("https://www.pokebip.com/pokedex-images/sprites-7G/197.png").into(sprite);
        Glide.with(getActivity()).load("https://www.pokebip.com/pokedex-images/sprites-shinies-7G/197.png").into(shiny);



        //sprite = (ImageView) view.findViewById(R.id.sprite);
        //sprite_shiny = (ImageView) view.findViewById(R.id.sprite_shiny);

//            Glide.with(getActivity()).load("https://www.pokebip.com/pokedex-images/sprites-7G/197.png").into(sprite);
//            Glide.with(getActivity()).load("https://www.pokebip.com/pokedex-images/artworks/197.png").into(sprite);
//            Glide.with(getActivity()).load("https://www.pokebip.com/pokedex-images/sprites-shinies-7G/197.png").into(sprite_shiny);

        return view;
    }



}
