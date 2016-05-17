package roulette.overwatchroulette.favorites;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import roulette.overwatchroulette.DBManager;
import roulette.overwatchroulette.R;
import roulette.overwatchroulette.maps.MapInformation;
import roulette.overwatchroulette.maps.MapsActivity;
import roulette.overwatchroulette.maps.MapsListAdapter;
import roulette.overwatchroulette.maps.TeamAdapterView;
import roulette.overwatchroulette.navigation.NavBaseActivity;
import roulette.overwatchroulette.roulette.StratRouletteActivity;

public class FavoritesActivity extends NavBaseActivity {
    //public static FavoritesDB db;
    ArrayAdapter<String> adapter;
    MapInformation.MAP_STATE state;
    String mapSelected;
    String teamSelected;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if(FavoritesActivity.db == null){
            FavoritesActivity.db = new FavoritesDB(this);
            FavoritesActivity.db.open();

            fillWithDummys();
        }*/
        setContentView(R.layout.activity_maps);
        listView = (ListView) findViewById(R.id.listView);
        goToMapSelection();

        if(getIntent().getExtras() != null){
            mapSelected = getIntent().getExtras().getString("map");
            teamSelected = getIntent().getExtras().getString("team");
            goToStratSelection();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (state == MapInformation.MAP_STATE.MAP_SELECTION) {
                    mapSelected = adapter.getItem(position);
                    goToTeamSelection();
                } else if (state == MapInformation.MAP_STATE.TEAM_SELECTION) {
                    if (position < adapter.getCount() - 1) {
                        teamSelected = adapter.getItem(position);
                        goToStratSelection();
                    } else {
                        goToMapSelection();
                    }
                } else if (state == MapInformation.MAP_STATE.STRAT_SELECTION) {
                    if (position < adapter.getCount() - 1) {
                        Intent i = new Intent(getApplicationContext(), StratRouletteActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("map", mapSelected);
                        bundle.putString("team", teamSelected);
                        bundle.putString("description", adapter.getItem(position));
                        i.putExtras(bundle);
                        startActivity(i);
                        finish();
                    } else {
                        goToTeamSelection();
                    }
                }
            }
        });
        activateNavBar();
        setTitle("Favorites: Select A Map");

    }

    void goToMapSelection(){
        adapter = new MapsListAdapter(getApplicationContext());
        listView.setAdapter(adapter);
        setTitle("Favorites: Select A Map");
        state = MapInformation.MAP_STATE.MAP_SELECTION;
    }
    void goToTeamSelection(){
        adapter = new TeamAdapterView(getApplicationContext(), mapSelected);
        listView.setAdapter(adapter);
        setTitle("Favorites: Select A Team");
        state = MapInformation.MAP_STATE.TEAM_SELECTION;
    }
    void goToStratSelection(){
        Cursor c = DBManager.getFavoriteStrats(mapSelected, teamSelected);
        setUpList(c);
        setTitle("Your Favorite Strats");
        state = MapInformation.MAP_STATE.STRAT_SELECTION;
    }

    public void setUpList(Cursor c){
        ArrayList<String> list = new ArrayList<String>();
        while(!c.isAfterLast()){
            list.add(c.getString(c.getColumnIndex(DBManager.KEY_TITLE)));
            c.moveToNext();
        }
        list.add("Go Back");
        adapter = new FavoritesListAdapter(this, list);
        listView.setAdapter(adapter);
    }

    /*void fillWithDummys(){
        FavoritesActivity.db.insertRow(0,"All", "Both", "FLASH EVERYTHING", "6 Mcrees " +
                "\n Throw Flashes around all corners." +
                "\n Stagger the throws");
        FavoritesActivity.db.insertRow(1,"All","Both", "The Flying Snake", "1 Pharah, 5 Mercy's" +
                "\n Start Flying");
        FavoritesActivity.db.insertRow(2,"All","Defending", "FORM THE TURTLE","6 Reinhardts" +
                "\n Stack Shields into a shell");
        FavoritesActivity.db.insertRow(3, "All", "Both", "RobinHood", "6 Hanzos" +
                "\n Embrace your Inner Archer" +
                "\n Release the Dragons at the same time");
        FavoritesActivity.db.insertRow(4, "All", "Defending", "Great Wall of Mei", "6 Mei's" +
                "\n Attempt to prevent any attacker from leaving their base" +
                "\n 2 people per exit to their base, stagger ice walls to block them in");
        FavoritesActivity.db.insertRow(5, "All", "Both", "SURPESSING FIREEEE", "3 Bastions, 3 Torbjorns" +
                "\n More bullets, MORE BULLETS");
        FavoritesActivity.db.insertRow(6, "All", "Both", "CMIYC", "6 Tracers" +
                "\n Catch Me If You Can!");
        FavoritesActivity.db.insertRow(7, "All", "Both", "Where was that??", "Wear your headset backwards");
        FavoritesActivity.db.insertRow(8, "All", "Both", "MLG 420% Skillshots Only!", "Jumpshots ONLY!" +
                "\n Best with 6 Mcree's");
    }*/

    @Override
    public void onBackPressed(){
        if(state == MapInformation.MAP_STATE.TEAM_SELECTION){
            goToMapSelection();
        }else if(state == MapInformation.MAP_STATE.MAP_SELECTION){
            Intent i = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(i);
            finish();
        }else if(state == MapInformation.MAP_STATE.STRAT_SELECTION){
            goToTeamSelection();
        }
    }



}
