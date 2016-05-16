package roulette.overwatchroulette.suggestions;

import android.content.Intent;
import android.os.Bundle;

import roulette.overwatchroulette.R;
import roulette.overwatchroulette.maps.MapsActivity;
import roulette.overwatchroulette.navigation.NavBaseActivity;

public class SuggestionsActivity extends NavBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);


        activateNavBar();
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(i);
        finish();
    }

}
