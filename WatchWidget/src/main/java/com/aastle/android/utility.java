package com.aastle.android;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.aastle.watchwidget.R;

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

    public static Bitmap getRandomBackroundBitmap(String backgroundName, Context context, String rPackage ){
        return  BitmapFactory.decodeResource(context.getResources(),getRandomBackgroundId(backgroundName,context,rPackage));

    }
    public static Drawable getRandomBackroundDrawable(String backgroundName, Context context, String rPackage ){
        return context.getResources()
                .getDrawable(context.getResources().getIdentifier(backgroundName, "drawable", rPackage));

    }

    public static int getRandomBackgroundId(String backgroundName,Context context,String rPackage){

       return context.getResources().getIdentifier(backgroundName,"drawable",rPackage);
    }
    public static void showTextMessage(Context context, String message){
        LayoutInflater inflater =  (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.toast_layout,null);
        // (ViewGroup) findViewById(R.id.toast_layout_root)

        TextView text = (TextView) layout.findViewById(R.id.toast_text);
        text.setText(message);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

}
