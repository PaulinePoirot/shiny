package android.shiny.view;

import android.content.Intent;
import android.shiny.R;
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
    }
}
