package roulette.overwatchroulette;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by Harjit on 5/14/2016.
 */
public class MapInformation {

    public static HashMap<String, Integer> drawables = new HashMap<String, Integer>();

    public enum MAP_STATE{
        MAP_SELECTION,
        TEAM_SELECTION,
        STRAT_SELECTION;
    }

    public static void init(Context context){
        drawables.put("Hanamura", R.drawable.hanamura);
        drawables.put("Temple of Anubis", R.drawable.anubis);
        drawables.put("Volskaya Industries", R.drawable.volskaya);
        drawables.put("Dorado", R.drawable.dorado);
        drawables.put("Route 66", R.drawable.route66);
        drawables.put("Gibraltar", R.drawable.gibraltar);
        drawables.put("Hollywood", R.drawable.hollywood);
        drawables.put("King's Row", R.drawable.kings);
        drawables.put("Numbani", R.drawable.numbani);
        drawables.put("Ilios", R.drawable.ilios);
        drawables.put("Lijiang Tower", R.drawable.lijiang);
        drawables.put("Nepal", R.drawable.nepal);
    }

    public static int getDrawable(String target){
        return drawables.get(target);
    }
}
