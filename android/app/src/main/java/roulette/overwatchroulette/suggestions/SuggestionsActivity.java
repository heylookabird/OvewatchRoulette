package roulette.overwatchroulette.suggestions;

import android.os.Bundle;

import roulette.overwatchroulette.R;
import roulette.overwatchroulette.navigation.NavBaseActivity;

public class SuggestionsActivity extends NavBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);


        activateNavBar();
    }

}
