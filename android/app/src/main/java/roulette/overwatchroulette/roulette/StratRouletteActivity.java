package roulette.overwatchroulette.roulette;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import roulette.overwatchroulette.DBManager;
import roulette.overwatchroulette.R;
import roulette.overwatchroulette.favorites.FavoritesActivity;
import roulette.overwatchroulette.maps.MapsActivity;
import roulette.overwatchroulette.navigation.NavBaseActivity;

public class StratRouletteActivity extends NavBaseActivity {
    boolean fromFavorites =false;
    String team, map, title, description, storageMap, storageTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strat_roulette);
        title = null;
        team = getIntent().getExtras().getString("team");
        map = getIntent().getExtras().getString("map");
        if(getIntent().getExtras().containsKey("description")){
            title = getIntent().getExtras().getString("description");
            fromFavorites = true;
        }

        TextView strat = (TextView)findViewById(R.id.strat);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Linux_Libertine.ttf");
        strat.setTypeface(font);
        if(title == null) {
            rollStrat();
        }
        else {
            description = DBManager.getFavoriteStratDescription(title);
        }

        activateNavBar();
        setTitle("The Strat!");
    }

    public void reRoll(View v){
        rollStrat();
    }

    void rollStrat(){
        Cursor c = DBManager.getRandomStrat(map, team);
        title = c.getString(c.getColumnIndex(DBManager.KEY_TITLE));
        description = c.getString(c.getColumnIndex(DBManager.KEY_DESCRIPTION));
        storageMap = c.getString(c.getColumnIndex(DBManager.KEY_MAP));
        storageTeam = c.getString(c.getColumnIndex(DBManager.KEY_TEAM));
        TextView strat = (TextView)findViewById(R.id.strat);

        strat.setText(description);
        final Button favorite = (Button)findViewById(R.id.favorite);

        final String removeLine = "Remove From Favorites";
        final String addLine = "Add To Favorites";
        if(fromFavorites)
            favorite.setText("Remove From Favorites");

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (favorite.getText().toString().equalsIgnoreCase(removeLine)) {
                    DBManager.removeFromFavorites(title);
                    favorite.setText(addLine);
                }else{
                    int size = DBManager.getAllFavoritesRows().getCount();
                    DBManager.insertFavoriteRow(size, storageMap, storageTeam, title, description);
                    favorite.setText(removeLine);
                }
            }
        });
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
