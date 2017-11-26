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
    private float x, y;
    private float xvel;
    private float yvel;
    private float radius;

    private static int maxRadius = 150;

    public Bubble(float x, float y, float xvel, float yvel, int color){
        this.x = x;
        this.y = y;
        this.color = color;
        this.xvel = xvel;
        this.yvel = yvel;
        radius = 2;
    }

    @Override
    public String toString(){
        return "Bubble|position: "+x + ", " + y +"velocity: "+xvel+","+yvel+"color: "+color;
    }

    public void update(float dt) {
        if(radius < maxRadius)
            radius += (maxRadius + 10 - radius) * dt * 0.4;
        else {/*TODO:remove bubble */}
        x += xvel * dt;
        y += xvel * dt;
        System.out.println(radius);
    }

    @Override
    public int hashCode(){
        return toString().hashCode();
    }

    public int getRadius() {
        return (int)radius;
    }

    public int getX() {
        return (int)x;
    }

    public int getY() {
        return (int)y;
    }

    public int getColor() {
        return color;
    }

    public static int getMaxRadius() {
        return maxRadius;
    }
}
