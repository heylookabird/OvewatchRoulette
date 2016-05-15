package roulette.overwatchroulette;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

public class MapsActivity extends NavBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapDrawables.init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new MapsListAdapter(this));
        activateNavBar();
    }

}
