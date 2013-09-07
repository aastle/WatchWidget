package com.aastle.android;
import android.graphics.Color;

/**
 * Created by prometheus on 8/20/13.
 */
public  class utility {

    public static Integer randomNumber(int min, int max){
        Integer rand = 0;
        rand = min + (int)(Math.random() * ((max - min) + 1));
        return rand;
    }

    public static int randomColor(){
        int randomNumber = -1;
        int color = 0;
        int[] colors = {Color.RED,Color.DKGRAY,Color.GRAY,Color.MAGENTA,Color.GREEN};
        randomNumber = utility.randomNumber(0, colors.length - 1);
        color = colors[randomNumber];
        return color;
    }
}
