package roulette.overwatchroulette.roulette;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import roulette.overwatchroulette.R;
import roulette.overwatchroulette.favorites.FavoritesActivity;
import roulette.overwatchroulette.maps.MapsActivity;
import roulette.overwatchroulette.navigation.NavBaseActivity;

public class StratRouletteActivity extends NavBaseActivity {
    boolean fromFavorites =false;
    String team, map, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strat_roulette);
        description = null;
        team = getIntent().getExtras().getString("team");
        map = getIntent().getExtras().getString("map");
        if(getIntent().getExtras().containsKey("description")){
            description = getIntent().getExtras().getString("description");
            fromFavorites = true;
        }

        TextView strat = (TextView)findViewById(R.id.strat);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Linux_Libertine.ttf");
        strat.setTypeface(font);
        if(description == null) {
            strat.setText("Random Strat for " + map + " for the " + team + " team");
        }
        else {
            strat.setText("Favorite Strat for " + map + " for the " + team + " team \n" +
                    description);

        }


        activateNavBar();
        setTitle("The Strat!");
    }

    @Override
    public void onBackPressed(){
        Intent i;
        Bundle bundle = new Bundle();
        if(fromFavorites){
            i = new Intent(getApplicationContext(), FavoritesActivity.class);
        }else
            i = new Intent(getApplicationContext(), MapsActivity.class);

        bundle.putString("team", team);
        bundle.putString("map", map);
        i.putExtras(bundle);
        startActivity(i);
        finish();


    }


}
