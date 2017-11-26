package kns.bubbledroid;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Bitmap;
import android.content.Context;


/**
 * @author David and Calvin
 * This class describes one of the bubbles that moves around on the screen and is popped
 * by the player
 */

public class Bubble {
    
    private int color;
    private float x, y;
    private float xvel;
    private float yvel;
    private float radius;

    private int maxRadius = 145;

    /**
     * This is the only constructor for the bubble, which initialized a position, velocity, and tint color
     * @param x the x position of the bubble
     * @param y the y position of the bubble
     * @param xvel the x velocity of the bubble
     * @param yvel the y velocity of the bubble
     * @param color the tint color of the bubble
     */
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

    /**
     * This method is where the bubble updates itself. This involves growing, as well as moving based 
     * on its velocity
     * @param dt delta time, or the amount of time passed sense this method was last called
     * @param speedFactor a multiplier for the speed of the bubble. This exists so the bubble 
     *                    manager can change their speed. It does not effect growth rate.
     */
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

    /**
     * 
     * @return  the integer representation of the bubble's radius
     */
    public int getRadius() {
        return (int)radius;
    }

    /**
     * 
     * @return  the integer representation of the bubble's x position
     */
    public int getX() {
        return (int)x;
    }

    /**
     *
     * @return  the integer representation of the bubble's y position
     */
    public int getY() {
        return (int)y;
    }

    /**
     * 
     * @return  the tint color of the bubble
     */
    public int getColor() {
        return color;
    }

    /**
     * 
     * @return  the maximum radius of this particular bubble
     */
    public int getMaxRadius() {
        return maxRadius;
    }

    /**
     * 
     * @return  the highest possible radius of any bubble
     */
    public static int getAbsMaxRadius() {
        return 160;
    }

    /**
     * 
     * @return  this bubble's x velocity
     */
    public float getXVel() {
        return xvel;
    }

    /**
     * Used to set this bubble's x velocity
     * @param xvel the new x velocity for the bubble
     */
    public void setXVel(float xvel) {
        this.xvel = xvel;
    }

    /**
     *
     * @return  this bubble's y velocity
     */
    public float getYVel() {
        return yvel;
    }
    /**
     * Used to set this bubble's y velocity
     * @param yvel the new y velocity for the bubble
     */
    public void setYVel(float yvel) {
        this.yvel = yvel;
    }

    /**
     * Used to translate the bubble in the 2D plane
     * @param dx the change in x position
     * @param dy the change in y position
     */
    public void translate(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * 
     * @return  the float representation of this bubble's x position
     */
    public float getXFloat() {
        return x;
    }
    /**
     *
     * @return the float representation of this bubble's y position
     */
    public float getYFloat() {
        return y;
    }

    /**
     * 
     * @return the vector representation of this bubble's velocity
     */
    public Vector2D getVelVector() {
        return new Vector2D(xvel, yvel);
    }

    /**
     * Used to set the bubble's velocity using a vector
     * @param vel the vector representation of the bubble's new velocity
     */
    public void setVel(Vector2D vel) {
        xvel = (float)vel.x;
        yvel = (float)vel.y;
    }

    /**
     * Used to set the bubble's potition using a position vector
     * @param pos the vector representation of the bubble's new positiion
     */
    public void setPos(Vector2D pos) {
        x = (float)pos.x;
        y = (float)pos.y;
    }

    /**
     *
     * @return a vector representation of this bubble's position
     */
    public Vector2D getPosVector() {
        return new Vector2D(x,y);
    }
}
