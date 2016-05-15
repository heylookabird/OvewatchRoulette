package roulette.overwatchroulette;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Harjit on 5/14/2016.
 */
public class TeamAdapterView extends ArrayAdapter<String> {

    int resource = 0;
    public TeamAdapterView(Context context, int imageResource){
        super(context,R.layout.team_row, context.getResources().getStringArray(R.array.team_names));
        resource = imageResource;
    }

    public String getTeam(int position){
        return getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater theInflater = LayoutInflater.from(getContext());

        View theView = theInflater.inflate(R.layout.team_row, parent, false);

        String results = getItem(position);
        /*RelativeLayout layout = (RelativeLayout) theView.findViewById(R.id.background);
        layout.setBackgroundResource(MapDrawables.getDrawable(results));*/
        ImageView img = (ImageView)  theView.findViewById(R.id.imageView);
        img.setBackgroundResource(resource);

        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Linux_Libertine.ttf");
        TextView theTextView = (TextView) theView.findViewById(R.id.name);
        theTextView.setTypeface(font);
        theTextView.setText(results);




        return theView;
    }

}
