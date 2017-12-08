package kns.graphics;

//TODO: use Glide library to efficiently manage recources

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;

import kns.bubbledroid.R;

/**
 * This class is used to manage the graphical recources of the image effeciently
 * @author Calvin
 */

public class Recources {

    public static Bitmap bubbleTexture;
    public static Activity currentActivity;

    public static void init(Context context) {
        bubbleTexture = BitmapFactory.decodeResource(context.getResources(), R.drawable.bubblesmall);
    }

}