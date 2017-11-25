package kns.bubbledroid;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Bitmap;
import android.content.Context;


/**
 * @author David and Calvin
 */

public class Bubble {

    private int color;
    private Point position;
    private float xvel;
    private float yvel;

    private Bitmap bitmap;

    public Bubble(Point position, float xvel, float yvel, int color, Context context){
        this.position = position;
        this.color = color;
        this.xvel = xvel;
        this.yvel = yvel;

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bubble);
    }

    @Override
    public String toString(){
        return "Bubble|position: "+position+"velocity: "+xvel+","+yvel+"color: "+color;
    }

    @Override
    public int hashCode(){
        return toString().hashCode();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Point getPosition() {
        return position;
    }

    public int getX() {
        return position.x;
    }

    public int getY() {
        return position.y;
    }
}
