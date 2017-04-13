package logoin.example.com.myapplication.Other;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by 450 G1 on 04/04/2017.
 */

public class Utiles {

    public static Integer getNoOfColumns(Context context) {
        System.out.println("contexxxxxxt "+ context.getApplicationContext());
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float width = displayMetrics.widthPixels / displayMetrics.density;
        System.out.println("widthhhhhhhhhhhhhhhh "+width);
        Integer numOfColumns = (int) (width / 100);
        return numOfColumns;
    }

}