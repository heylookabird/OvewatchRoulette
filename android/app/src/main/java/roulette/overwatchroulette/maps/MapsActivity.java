package roulette.overwatchroulette.maps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import roulette.overwatchroulette.navigation.NavBaseActivity;
import roulette.overwatchroulette.R;
import roulette.overwatchroulette.roulette.StratRouletteActivity;

public class MapsActivity extends NavBaseActivity {
    ArrayAdapter<String> adapter;
    MapInformation.MAP_STATE state;
    String mapSelected;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        listView = (ListView) findViewById(R.id.listView);
        goToMapSelection();

        if(getIntent().getExtras() != null){
            mapSelected = getIntent().getExtras().getString("map");
            goToTeamSelection();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (state == MapInformation.MAP_STATE.MAP_SELECTION) {
                    mapSelected = adapter.getItem(position);
                    goToTeamSelection();
                } else if (state == MapInformation.MAP_STATE.TEAM_SELECTION) {
                    if (position < adapter.getCount()-1) {
                        String teamSelection = adapter.getItem(position);
                        Bundle bundle = new Bundle();
                        bundle.putString("map", mapSelected);
                        bundle.putString("team", teamSelection);
                        Intent i = new Intent(getApplicationContext(), StratRouletteActivity.class);
                        i.putExtras(bundle);
                        startActivity(i);
                        //finish();
                    } else {
                        goToMapSelection();
                    }
                }
            }
        });
        activateNavBar();
        if(state== MapInformation.MAP_STATE.MAP_SELECTION)
            setTitle("Select A Map");
        else
            setTitle("Select A Team");
    }

    void goToMapSelection(){
        adapter = new MapsListAdapter(getApplicationContext());
        listView.setAdapter(adapter);
        setTitle("Select A Map");
        state = MapInformation.MAP_STATE.MAP_SELECTION;
    }

    void goToTeamSelection(){
        adapter = new TeamAdapterView(getApplicationContext(), mapSelected);
        listView.setAdapter(adapter);
        setTitle("Select A Team");
        state = MapInformation.MAP_STATE.TEAM_SELECTION;
    }
    @Override
    public void onBackPressed(){
        if(state == MapInformation.MAP_STATE.TEAM_SELECTION){
            goToMapSelection();
        }
    }

}
