package android.shiny.view.pokemon_activity;

import android.os.Bundle;
import android.shiny.R;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class PokemonActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    int pokemon_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);

        tabLayout = (TabLayout) findViewById(R.id.mytabs);
        tabLayout.setupWithViewPager(viewPager);

        pokemon_id = getIntent().getIntExtra("id", 0);
    }

    private void setViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), getApplicationContext());
        adapter.addFragment(new PokemonFragment(), "1");
        adapter.addFragment(new VoidFragment(), "2");
        adapter.addFragment(new VoidFragment(), "3");
        viewPager.setAdapter(adapter);
    }
}
