package roulette.overwatchroulette;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harjit on 5/14/2016.
 */
public class MapsListAdapter extends ArrayAdapter<String> {

    public MapsListAdapter(Context context){
        super(context,R.layout.single_row, context.getResources().getStringArray(R.array.map_names));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater theInflater = LayoutInflater.from(getContext());

        View theView = theInflater.inflate(R.layout.single_row, parent, false);

        String results = getItem(position);
        /*RelativeLayout layout = (RelativeLayout) theView.findViewById(R.id.background);
        layout.setBackgroundResource(MapDrawables.getDrawable(results));*/
        ImageView img = (ImageView)  theView.findViewById(R.id.imageView);
        img.setBackgroundResource(MapDrawables.getDrawable(results));

        TextView theTextView = (TextView) theView.findViewById(R.id.name);
        theTextView.setText(results);




        return theView;
    }

}
