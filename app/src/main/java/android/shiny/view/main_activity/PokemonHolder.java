package android.shiny.view.main_activity;

import android.app.Activity;
import android.content.Intent;
import android.shiny.R;
import android.shiny.view.pokemon_activity.PokemonActivity;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PokemonHolder extends RecyclerView.ViewHolder {
    public CardView card;
    public TextView name;
    public ImageView sprite;

    public PokemonHolder(final View itemView) {
        super(itemView);
        card = itemView.findViewById(R.id.cardview);
        name = itemView.findViewById(R.id.tv_pokemon);
        sprite = itemView.findViewById(R.id.iv_pokemon);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), PokemonActivity.class);
                intent.putExtra("id", getAdapterPosition()+1);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) itemView.getContext(), sprite, ViewCompat.getTransitionName(sprite));
                itemView.getContext().startActivity(intent, options.toBundle());

            }
        });
    }
}
