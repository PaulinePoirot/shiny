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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PokemonFragment extends Fragment {
    int pokeId;

    ImageView artwork;
    ImageView sprite;
    ImageView shiny;

    TextView type1;
    TextView type2;

    TextView hp;
    TextView attack;
    TextView defense;
    TextView attackSp;
    TextView defenseSp;
    TextView speed;



    ProgressBar pg_hp;
    ProgressBar pg_attack;
    ProgressBar pg_defense;
    ProgressBar pg_attackSp;
    ProgressBar pg_defenseSp;
    ProgressBar pg_speed;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_infos, container, false);
        pokeId = this.getArguments().getInt("id");
        Pokemon myPokemon = ViewModelProviders.of(this).get(PokemonViewModel.class).getPokemon(pokeId);

        artwork = (ImageView) view.findViewById(R.id.artwork);
        sprite = (ImageView) view.findViewById(R.id.sprite);
        shiny = (ImageView) view.findViewById(R.id.sprite_shiny);
        type1 = view.findViewById(R.id.type1);
        type2 = view.findViewById(R.id.type2);
        hp = view.findViewById(R.id.hp);
        attack = view.findViewById(R.id.attack);
        defense = view.findViewById(R.id.defence);
        attackSp = view.findViewById(R.id.sp_attack);
        defenseSp = view.findViewById(R.id.sp_defence);
        speed = view.findViewById(R.id.speed);

        pg_hp = view.findViewById(R.id.pg_hp);
        pg_attack= view.findViewById(R.id.pg_attack);
        pg_attackSp = view.findViewById(R.id.pg_sp_attack);
        pg_defense = view.findViewById(R.id.pg_defence);
        pg_defenseSp = view.findViewById(R.id.pg_sp_defence);
        pg_speed = view.findViewById(R.id.pg_speed);

        getActivity().setTitle(myPokemon.getName_fr()+" - "+myPokemon.getName_en());

        Glide.with(getActivity()).load(myPokemon.getUrl_artwork()).into(artwork);
        Glide.with(getActivity()).load(myPokemon.getUrl_sprite()).into(sprite);
        Glide.with(getActivity()).load(myPokemon.getUrl_shiny()).into(shiny);

        type1.setText(myPokemon.getType1());
        type2.setText(myPokemon.getType2());

        hp.setText("Vie : "+myPokemon.getHealth());
        pg_hp.setIndeterminate(false);
        pg_hp.setMin(0);
        pg_hp.setMax(255);
        pg_hp.setProgress(myPokemon.getSpeed(), true);

        attack.setText("Attaque : "+myPokemon.getAttack());
        pg_attack.setIndeterminate(false);
        pg_attack.setMin(0);
        pg_attack.setMax(255);
        pg_attack.setProgress(myPokemon.getAttack(), true);

        defense.setText("Défense : "+myPokemon.getDefense());
        pg_defense.setIndeterminate(false);
        pg_defense.setMin(0);
        pg_defense.setMax(255);
        pg_defense.setProgress(myPokemon.getDefense(), true);

        attackSp.setText("AttaqueSpé : "+myPokemon.getAttack_sp());
        pg_attackSp.setIndeterminate(false);
        pg_attackSp.setMin(0);
        pg_attackSp.setMax(255);
        pg_attackSp.setProgress(myPokemon.getAttack_sp(), true);

        defenseSp.setText("DéfenseSpé : "+myPokemon.getDefense_sp());
        pg_defenseSp.setIndeterminate(false);
        pg_defenseSp.setMin(0);
        pg_defenseSp.setMax(255);
        pg_defenseSp.setProgress(myPokemon.getDefense_sp(), true);

        speed.setText("Vitesse : "+myPokemon.getSpeed());
        pg_speed.setIndeterminate(false);
        pg_speed.setMin(0);
        pg_speed.setMax(255);
        pg_speed.setProgress(myPokemon.getSpeed(), true);

        return view;
    }
}
