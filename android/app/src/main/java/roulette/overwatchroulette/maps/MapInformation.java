package roulette.overwatchroulette.maps;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import java.util.HashMap;

import roulette.overwatchroulette.R;

/**
 * Created by Harjit on 5/14/2016.
 */
public class MapInformation {

    public static HashMap<String, Integer> drawables = new HashMap<String, Integer>();
    public static HashMap<String, String> gameModes = new HashMap<String, String>();

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

        String assault = "Assault";
        String escort = "Escort";
        String hybrid = "Hybrid";
        String control = "Control";

        gameModes.put("Hanamura", assault);
        gameModes.put("Temple of Anubis", assault);
        gameModes.put("Volskaya Industries", assault);
        gameModes.put("Dorado", escort);
        gameModes.put("Route 66", escort);
        gameModes.put("Gibraltar", escort);
        gameModes.put("Hollywood", hybrid);
        gameModes.put("King's Row", hybrid);
        gameModes.put("Numbani", hybrid);
        gameModes.put("Ilios", control);
        gameModes.put("Lijiang Tower", control);
        gameModes.put("Nepal", control);

    }

    public static int getDrawable(String target){
        return drawables.get(target);
    }
}
