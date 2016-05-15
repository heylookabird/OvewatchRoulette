package roulette.overwatchroulette;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Typeface;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

/**
 * Created by Harjit on 5/14/2016.
 */
public class MapsListAdapter extends ArrayAdapter<String> {

    public MapsListAdapter(Context context){
        super(context,R.layout.map_row, context.getResources().getStringArray(R.array.map_names));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater theInflater = LayoutInflater.from(getContext());

        View theView = theInflater.inflate(R.layout.map_row, parent, false);

        String results = getItem(position);
        ImageView img = (ImageView)  theView.findViewById(R.id.imageView);
        img.setBackgroundResource(MapInformation.getDrawable(results));

        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Linux_Libertine.ttf");
        TextView theTextView = (TextView) theView.findViewById(R.id.name);
        theTextView.setTypeface(font);
        theTextView.setText(results);




        return theView;
    }

}
