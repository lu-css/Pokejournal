package com.example.helpers;

import android.graphics.Bitmap;
import android.graphics.Color;

public class ImageHelper {

    public static Bitmap convertToBlack(Bitmap img){
        Bitmap blackandwhite = img.copy(img.getConfig(), true);
        int width = blackandwhite.getWidth();
        int height = blackandwhite.getHeight();

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                int pixel = blackandwhite.getPixel(x, y);
                int alpha = Color.alpha(pixel);

                if(alpha == 0){
                    continue;
                }

                int red = Color.red(pixel);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);

                int gray = (int) (red * 0.3 + green * 0.59 + blue * 0.11);

                int blackPixel = Color.rgb(0, 0, 0);
                blackandwhite.setPixel(x, y, blackPixel);
            }
        }

        return blackandwhite;
    }
}
