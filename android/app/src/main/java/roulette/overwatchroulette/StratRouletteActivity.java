package roulette.overwatchroulette;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class StratRouletteActivity extends NavBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strat_roulette);

        String team = getIntent().getExtras().getString("team");
        String map = getIntent().getExtras().getString("map");

        TextView strat = (TextView)findViewById(R.id.strat);
        strat.setText("Random Strat for " + map + " for the " + team + " team");

        activateNavBar();
    }

}
