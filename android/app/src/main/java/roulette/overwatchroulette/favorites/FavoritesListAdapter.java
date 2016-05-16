package roulette.overwatchroulette.favorites;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import roulette.overwatchroulette.R;
import roulette.overwatchroulette.maps.MapInformation;

/**
 * Created by Harjit on 5/15/2016.
 */
public class FavoritesListAdapter extends ArrayAdapter<String>{

    public FavoritesListAdapter(Context context, List<String> list){
        super(context, R.layout.map_row, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater theInflater = LayoutInflater.from(getContext());

        View theView = theInflater.inflate(R.layout.map_row, parent, false);

        String results = getItem(position);
        ImageView img = (ImageView)  theView.findViewById(R.id.imageView);
        if(position%2==0)
            img.setBackgroundColor(getContext().getResources().getColor(R.color.blizzardBlue));
        else
            img.setBackgroundColor(getContext().getResources().getColor(R.color.blizzardOrange));
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Linux_Libertine.ttf");
        TextView theTextView = (TextView) theView.findViewById(R.id.name);
        theTextView.setTextSize(40);
        theTextView.setTypeface(font);
        theTextView.setText(results);




        return theView;
    }
}
