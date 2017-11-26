package kns.bubbledroid;

import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Dimension;
import android.view.Display;
import android.view.SurfaceHolder;
import android.graphics.Canvas;

import java.util.Iterator;
import java.util.Set;

/**@author David
 * This class contains all the bubbles that appear on the screen
 * and manages their actions and wearabouts */
public class BubbleManager {

    private Set<Bubble> bubbles;
    private Display display;

    private float speedFactor;

    public BubbleManager(Set<Bubble> bubbles){
        this.bubbles=bubbles;
        speedFactor = 5;
    }

    public boolean update(float dt){
        Iterator<Bubble> i = bubbles.iterator();
        for(Bubble b: bubbles) {
            b.update(dt, speedFactor);
            if(b.getX() - b.getRadius() <= 0)
                b.setXVel(Math.abs(b.getXVel()));
            else if(b.getX() + b.getRadius() > display.getWidth())
                b.setXVel(-Math.abs(b.getXVel()));

            if(b.getY() - b.getRadius() <= 0)
                b.setYVel(Math.abs(b.getYVel()));
            else if(b.getY() + b.getRadius() > display.getHeight())
                b.setYVel(-Math.abs(b.getYVel()));

        }
        return !bubbles.isEmpty();
    }

    public void draw(Canvas canvas, Paint paint, SurfaceHolder surfaceHolder) {
        paint.setStrokeWidth(3);
            for(Bubble b: bubbles) {
                //canvas.drawBitmap(b.getBitmap(), b.getX(), b.getY(), paint);
                paint.setColorFilter(new LightingColorFilter(b.getColor(), b.getColor()));
                paint.setColor(Color.WHITE);
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawCircle(b.getX(),b.getY(),b.getRadius(),paint);

                paint.setColor(Color.argb(50,255,255,255));
                paint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(b.getX(),b.getY(),b.getRadius(),paint);
            }
            paint.setColorFilter(null);
            paint.setAlpha(255);
    }

    public boolean addNewBubble(Display d) {
        this.display = d;

        int x = Bubble.getMaxRadius() / 2 + (int)(Math.random() * (d.getWidth() - Bubble.getMaxRadius()));
        int y = Bubble.getMaxRadius() / 2 + (int)(Math.random() * (d.getHeight() - Bubble.getMaxRadius()));

        float xvel = (float)Math.random() * 50 - 25;
        if(Math.abs(xvel) < 18) xvel *= 3;
        float yvel = (float)Math.random() * 50 - 25;
        if(Math.abs(yvel) < 18) yvel *= 3;

        int color = Color.rgb( (int)(Math.random() * 255),(int)(Math.random() * 255),(int)(Math.random() * 255) );

        return bubbles.add(new Bubble(x,y, xvel, yvel, color));
    }

    public int numberOfBubbles() {
        return bubbles.size();
    }

    public float getSpeedFactor() {
        return speedFactor;
    }
    public void setSpeedFactor(float speedFactor) {
        this.speedFactor = speedFactor;
    }
}
