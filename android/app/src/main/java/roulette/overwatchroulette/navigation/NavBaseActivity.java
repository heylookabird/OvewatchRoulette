package roulette.overwatchroulette.navigation;

import java.util.ArrayList;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import roulette.overwatchroulette.favorites.FavoritesActivity;
import roulette.overwatchroulette.R;
import roulette.overwatchroulette.suggestions.SuggestionsActivity;
import roulette.overwatchroulette.maps.MapsActivity;


/**
 *  Create by: MG
 *  For every activity that wants to use nav drawer, class must extend NavBaseActivity
 *  Then do the following:
 *
 *      1) create two variables:
 *              private String[] navMenuTitles;
 *              private TypedArray navMenuIcons;
 *
 *      2) go on onCreate and copy paste at the bottom:
 *              navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
 *              navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
 *		        set(navMenuTitles, navMenuIcons);
 *
 *      3) go to your class xml layout:
 *		        look at activity_main.xml for an example.  IMPORTANT:  the main content
 *		        that you want to display on the screen has to be placed INSIDE the FrameLayout.
 *		        Don't forget to include the ListView nav drawer.  Just copy and paste it
 *
 */

public class NavBaseActivity extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private ArrayList<NavDrawer> navDrawers;
    private NavDrawerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);
    }

    /**
     * Method that handles menu name and icon and sets
     * them to the nav drawer
     */
    public void activateNavBar() {
        String[] navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        navDrawers = new ArrayList<NavDrawer>();

        // adding nav drawer item
            for (int i = 0; i < navMenuTitles.length; i++) {
                navDrawers.add(new NavDrawer(navMenuTitles[i]));
            }

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer adapter
        adapter = new NavDrawerAdapter(getApplicationContext(),
                navDrawers);
        mDrawerList.setAdapter(adapter);

        // turning on app icon in action bar
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_icon);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.menu_icon, // nav menu toggle icon
                R.string.drawer_open, // required string paramater
                R.string.drawer_close // require string parameter
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu();
            }
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                supportInvalidateOptionsMenu();
            }
        };
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mDrawerToggle.syncState();
    }

    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* TODO: SHOULD LOOK LIKE THIS BUT GOING TO HARD CODE IT FOR TESTING SAKE
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        }*/

        if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            mDrawerLayout.openDrawer(mDrawerList);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * Displays the menu text and icon on nav drawer
     * and open that activity
     * @param position Position of menu title to access
     */
    private void displayView(int position) {
        switch (position) {
            case 0:
                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                finish();
                break;
            case 1:
                Intent intent1 = new Intent(this, FavoritesActivity.class);
                startActivity(intent1);
                finish();
                break;
            case 2:
                Intent intent2 = new Intent(this, SuggestionsActivity.class);
                startActivity(intent2);
                finish();
                break;
            default:
                break;
        }

        // update selected menu title and close nav drawer
        mDrawerList.setItemChecked(position, true);
        mDrawerList.setSelection(position);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}