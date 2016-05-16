package roulette.overwatchroulette.roulette;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import roulette.overwatchroulette.R;
import roulette.overwatchroulette.navigation.NavBaseActivity;

public class StratRouletteActivity extends NavBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strat_roulette);
        String description = null;
        String team = getIntent().getExtras().getString("team");
        String map = getIntent().getExtras().getString("map");
        if(getIntent().getExtras().containsKey("description")){
            description = getIntent().getExtras().getString("description");
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
    }

}
