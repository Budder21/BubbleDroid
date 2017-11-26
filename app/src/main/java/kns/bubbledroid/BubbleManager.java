package kns.bubbledroid;

import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.view.Display;
import android.graphics.Canvas;

import java.util.ArrayList;

import kns.utils.Constants;
import kns.utils.Vector2D;

/**@author David and Calvin
 * This class contains all the bubbles that appear on the screen
 * and manages their actions and wearabouts */
public class BubbleManager {

    /**
     * The list of bubbles contained in the manager
     */
    private ArrayList<Bubble> bubbles;
    /**
     * The display used by the current Action
     */
    private Display display;
    /**
     * The speed factor is a multiplier for the bubble's movement speed
     */
    private float speedFactor;

    /**
     * The inly and default constructor of the BubbleManager. It creates an empty list of bubbles and sets the
     * speed factor to 5
     */
    public BubbleManager(){
        this.bubbles= new ArrayList<Bubble> ();
        speedFactor = 5;
    }

    //TODO: actually implement collision detection and reaction
    /**
     * Used to update all the bubbles. It moves them, pops them if they get too big, and deals with
     * bubble collisions
     * @param dt delta time, or the change in time sense this method was last called
     * @return whether or not there are still bubbles in the list
     */
    public boolean update(float dt){
        synchronized (bubbles) {
            for (int i = 0; i < bubbles.size(); i++) {
                Bubble b = bubbles.get(i);
                b.update(dt, speedFactor);
                if (b.getX() - b.getRadius() <= 0)
                    b.setXVel(Math.abs(b.getXVel()));
                else if (b.getX() + b.getRadius() > display.getWidth())
                    b.setXVel(-Math.abs(b.getXVel()));

                if (b.getY() - b.getRadius() <= 0)
                    b.setYVel(Math.abs(b.getYVel()));
                else if (b.getY() + b.getRadius() > display.getHeight())
                    b.setYVel(-Math.abs(b.getYVel()));
                if(b.getRadius() >= b.getMaxRadius()) {
                    popBubble(b, false);
                    i--;
                }
            }


            for (Bubble b1 : bubbles) {
                for (Bubble b2 : bubbles) {
                    if (b1 != b2 && b1.getPosVector().difference(b2.getPosVector()).length() < b1.getRadius() + b2.getRadius())
                        fixBubbleCollision(b1, b2);
                }
            }
        }
        return !bubbles.isEmpty();
    }

    //TODO: blurring behind bubbles
    /**
     * Used to draw all the bubbles to the current canvas
     * @param canvas the canvas being drawn to
     * @param paint the paint object being used to paint with
     */
    public void draw(Canvas canvas, Paint paint) {
        paint.setStrokeWidth(3);
        synchronized (bubbles) {
            for (Bubble b : bubbles) {
                paint.setColorFilter(new LightingColorFilter(b.getColor(), b.getColor()));
                paint.setColor(Color.WHITE);
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawCircle(b.getX(), b.getY(), b.getRadius(), paint);

                paint.setColor(Color.argb(50, 255, 255, 255));
                paint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(b.getX(), b.getY(), b.getRadius(), paint);
            }
            paint.setColorFilter(null);
            paint.setAlpha(255);
        }
    }

    //TODO: ensure that new bubbles aren't placed in old bubbles
    /**
     * Used to add a new bubble to the 2D plane. It is a random position, and can be placed within another
     * bubble
     * @param d
     * @return
     */
    public boolean addNewBubble(Display d) {
        this.display = d;

        int x = Bubble.getAbsMaxRadius() / 2 + (int)(Math.random() * (d.getWidth() - Bubble.getAbsMaxRadius()));
        int y = Bubble.getAbsMaxRadius() / 2 + (int)(Math.random() * (d.getHeight() - Bubble.getAbsMaxRadius()));

        float xvel = (float)Math.random() * 50 - 25;
        if(Math.abs(xvel) < 18) xvel *= 3;
        float yvel = (float)Math.random() * 50 - 25;
        if(Math.abs(yvel) < 18) yvel *= 3;

        int color = Color.rgb( (int)(Math.random() * 255),(int)(Math.random() * 255),(int)(Math.random() * 255) );

        synchronized (bubbles) {
            return bubbles.add(new Bubble(x, y, xvel, yvel, color));
        }
    }

    /**
     * @return the number of bubbles the manager handles
     */
    public int numberOfBubbles() {
        return bubbles.size();
    }

    /**
     *
     * @return the speed factor of the manager
     */
    public float getSpeedFactor() {
        return speedFactor;
    }

    /**
     * Used to set the speed factor of the manager
     * @param speedFactor the new speed factor for the manager
     */
    public void setSpeedFactor(float speedFactor) {
        this.speedFactor = speedFactor;
    }

    /**
     * Used to see if a bubble, when the screen is touched at a point, is popped. If so,
     * the bubble is popped.
     * @param point the point being touched
     * @return whether or not a bubble was popped
     */
    public boolean poppedBubble(Vector2D point) {
        Bubble bub = null;
        synchronized (bubbles) {
            for (Bubble b : bubbles) {
                if (b.getPosVector().difference(point).length() < b.getRadius()) {
                    bub = b;
                }
            }
        }
        if(bub!= null) {
            popBubble(bub, true);
            return true;
        }
        return false;
    }

    //TODO: poping animation
    /**
     * Used to pop a bubble. Removes it, and plays any necesarry popping animations. It then
     * creates a thread that will spawn another bubble. If a bonus
     * bubble is requested, there is an 80 percent chance that the bubble is made as well.
     * @param b the bubble to be deleted
     * @param bonus whether or not the 80% change of the second buble being created is enabled
     */
    private void popBubble(Bubble b, boolean bonus) {
        synchronized (bubbles) {
            bubbles.remove(b);
        }
        Thread t = new Thread(()-> {
            long startTime = System.currentTimeMillis();
            while(System.currentTimeMillis() - startTime < (int)(Math.random() * 1000) + 500);
            addNewBubble(display);
            if(bonus && Math.random() < Constants.DOUBLE_BUBBLE_CHANCE)
                addNewBubble(display);
        });
        t.start();
    }

    /**
     * Supposed to do collision stuff...
     * @param b1
     * @param b2
     */
    private void fixBubbleCollision(Bubble b1, Bubble b2) {
       /* double backTimeRoot = 0.5 * Math.sqrt(4 * Math.pow(b1.getXFloat() * (b1.getXVel() - b2.getXVel()) +
                b2.getXFloat() * (-b1.getXVel() + b2.getXVel()) + (b1.getYFloat() - b2.getYFloat()) * (b1.getYVel() - b2.getYVel()), 2) -
                4 * (b1.getXFloat() * b1.getXFloat() + b1.getYFloat() * b1.getYFloat() - 2 * b1.getXFloat() * b2.getXFloat() + b2.getXFloat() * b2.getXFloat() - 2 * b1.getYFloat() * b2.getYFloat() + b2.getYFloat() * b2.getYFloat() -
                        b1.getRadius() * b1.getRadius() - 2 * b1.getRadius() * b2.getRadius() - b2.getRadius() * b2.getRadius()) * (b1.getXVel() * b1.getXVel() + b1.getYVel() * b1.getYVel() - 2 * b1.getXVel() * b2.getXVel() + b2.getXVel() * b2.getXVel() -
                        2 * b1.getYVel() * b2.getYVel() + b2.getYVel() * b2.getYVel()));
        float backTimeSummand = b1.getXFloat() * b1.getXVel() - b2.getXFloat() * b1.getXVel() + b1.getYFloat() * b1.getYVel() - b2.getYFloat() * b1.getYVel() - b1.getXFloat() * b2.getXVel() + b2.getXFloat() * b2.getXVel() - b1.getYFloat() * b2.getYVel() + b2.getYFloat() * b2.getYVel();
        float backTimeDivisor = b1.getXVel() * b1.getXVel() + b1.getYVel() * b1.getYVel() - 2 * b1.getXVel() * b2.getXVel() + b2.getXVel() * b2.getXVel() - 2 * b1.getYVel() * b2.getYVel() + b2.getYVel() * b2.getYVel();
        float backTime = (float)(backTimeSummand + backTimeRoot) / backTimeDivisor;
        backTime += 0.001; //compensate for floating point errors

        b1.translate(-b1.getXVel() * backTime, -b1.getYVel() * backTime);
        b2.translate(-b2.getXVel() * backTime, -b2.getYVel() * backTime);

        Vector2D collisionNormal = new Vector2D(b1.getXFloat() - b2.getXFloat(), b1.getYFloat() - b2.getYFloat() );
        collisionNormal = collisionNormal.getUnitVector();
        double v1dot = collisionNormal.dot(b1.getVelVector());
        Vector2D v1Collide = collisionNormal.scale(v1dot);
        Vector2D v1Remainder = b1.getVelVector().difference(v1Collide);

        double v2dot = collisionNormal.dot(b2.getVelVector());
        Vector2D v2Collide = collisionNormal.scale(v2dot);
        Vector2D v2Remainder = b2.getVelVector().difference(v2Collide);

        double v1Length = v1Collide.length() * Math.sin(v1dot);
        double v2Length = v2Collide.length() * Math.sqrt(v2dot);
        double commonVelocity = 2 * (b1.getRadius() * v1Length + b2.getRadius() * v2Length) / (b1.getRadius() + b2.getRadius());
        double v1LengthAfterCollision  = commonVelocity - v1Length;
        double v2LengthAfterCollision = commonVelocity - v2Length;
        v1Collide = v1Collide.scale(v1LengthAfterCollision/v1Length);
        v2Collide = v2Collide.scale(v2LengthAfterCollision / v2Length);

        b1.setVel(v1Collide.add(v1Remainder));
        b2.setVel(v2Collide.add(v2Remainder)); */

        //b1.setPos(b1.getVelVector().scale(backTime));
        //b2.setPos(b2.getVelVector().scale(backTime));

    }
}
