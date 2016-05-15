package roulette.overwatchroulette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MapsActivity extends NavBaseActivity {
    ArrayAdapter<String> adapter;
    MapInformation.MAP_STATE state;
    String mapSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        final ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new MapsListAdapter(this);
        listView.setAdapter(adapter);
        state = MapInformation.MAP_STATE.MAP_SELECTION;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (state == MapInformation.MAP_STATE.MAP_SELECTION) {
                    mapSelected = adapter.getItem(position);
                    adapter = new TeamAdapterView(view.getContext(), MapInformation.getDrawable(mapSelected));
                    listView.setAdapter(adapter);
                    state = MapInformation.MAP_STATE.TEAM_SELECTION;
                }else if(state == MapInformation.MAP_STATE.TEAM_SELECTION){
                    if(position < 3) {
                        String teamSelection = adapter.getItem(position);
                        Bundle bundle = new Bundle();
                        bundle.putString("map", mapSelected);
                        bundle.putString("team", teamSelection);
                        Intent i = new Intent(getApplicationContext(), StratRouletteActivity.class);
                        i.putExtras(bundle);
                        startActivity(i);
                    }else{
                        adapter = new MapsListAdapter(getApplicationContext());
                        listView.setAdapter(adapter);
                        state = MapInformation.MAP_STATE.MAP_SELECTION;
                    }
                }
            }
        });
        activateNavBar();
    }

}
