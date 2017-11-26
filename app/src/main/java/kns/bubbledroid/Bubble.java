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

    private int maxRadius = 145;

    public Bubble(float x, float y, float xvel, float yvel, int color){
        this.x = x;
        this.y = y;
        this.color = color;
        this.xvel = xvel;
        this.yvel = yvel;
        radius = 2;
        maxRadius += (int)(Math.random() * 30) - 15;
    }

    @Override
    public String toString(){
        return "Bubble|position: "+x + ", " + y +"velocity: "+xvel+","+yvel+"color: "+color;
    }

    public void update(float dt, float speedFactor) {
        if(radius <= maxRadius)
            radius += (maxRadius + 10 - radius) * dt ;
        x += xvel * dt * speedFactor;
        y += yvel * dt * speedFactor;
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

    public int getMaxRadius() {
        return maxRadius;
    }

    public static int getAbsMaxRadius() {
        return 160;
    }

    public float getXVel() {
        return xvel;
    }
    public void setXVel(float xvel) {
        this.xvel = xvel;
    }

    public float getYVel() {
        return yvel;
    }
    public void setYVel(float yvel) {
        this.yvel = yvel;
    }

    public void translate(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }

    public float getXFloat() {
        return x;
    }
    public float getYFloat() {
        return y;
    }

    public Vector2D getVelVector() {
        return new Vector2D(xvel, yvel);
    }

    public void setVel(Vector2D vel) {
        xvel = (float)vel.x;
        yvel = (float)vel.y;
    }

    public void setPos(Vector2D pos) {
        x = (float)pos.x;
        y = (float)pos.y;
    }

    public Vector2D getPosVector() {
        return new Vector2D(x,y);
    }
}
