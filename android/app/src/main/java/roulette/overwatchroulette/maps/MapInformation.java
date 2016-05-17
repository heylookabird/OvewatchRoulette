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

        drawables.put("Hanamura_Attacking", R.drawable.hanamura_attacking);
        drawables.put("Hanamura_Defending", R.drawable.hanamura_defense);
        drawables.put("Temple of Anubis_Attacking", R.drawable.anubis_attacking);
        drawables.put("Temple of Anubis_Defending", R.drawable.anubis_defense);
        drawables.put("Temple of Anubis_Both", R.drawable.anubis);
        drawables.put("Volskaya Industries_Attacking", R.drawable.volskaya_attacking);
        drawables.put("Volskaya Industries_Defending", R.drawable.volskaya_defense);
        drawables.put("Volskaya Industries_Both", R.drawable.volskaya);
        drawables.put("Dorado_Attacking", R.drawable.dorado_attacking);
        drawables.put("Dorado_Defending", R.drawable.dorado_defense);
        drawables.put("Route 66_Attacking", R.drawable.route66_attacking);
        drawables.put("Route 66_Defending", R.drawable.route66_defense);
        drawables.put("Gibraltar_Attacking", R.drawable.gibraltar_attacking);
        drawables.put("Gibraltar_Defending", R.drawable.gibraltar_defense);
        drawables.put("Hollywood_Attacking", R.drawable.hollywood_attacking);
        drawables.put("Hollywood_Defending", R.drawable.hollywood_defense);
        drawables.put("King's Row_Attacking", R.drawable.kings_attacking);
        drawables.put("King's Row_Defending", R.drawable.kings_defense);
        drawables.put("Numbani_Attacking", R.drawable.numbani_attacking);
        drawables.put("Numbani_Defending", R.drawable.numbani_defense);
        drawables.put("Ilios_Attacking", R.drawable.ilios_attacking);
        drawables.put("Ilios_Defending", R.drawable.ilios_defense);
        drawables.put("Lijiang Tower_Attacking", R.drawable.lijiang_attacking);
        drawables.put("Lijiang Tower_Defending", R.drawable.lijiang_defense);
        drawables.put("Nepal_Attacking",R.drawable.nepal_attacking);
        drawables.put("Nepal_Defending", R.drawable.nepal_defense);

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
