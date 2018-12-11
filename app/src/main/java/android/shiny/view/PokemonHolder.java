package android.shiny.view;

import android.shiny.R;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
