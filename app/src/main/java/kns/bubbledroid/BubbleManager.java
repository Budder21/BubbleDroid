package kns.bubbledroid;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.graphics.Canvas;

import java.util.Iterator;
import java.util.Set;

/**@author David
 * This class contains all the bubbles that appear on the screen
 * and manages their actions and wearabouts */
public class BubbleManager {

    private Set<Bubble> bubbles;

    public BubbleManager(Set<Bubble> bubbles){
        this.bubbles=bubbles;
    }

    public boolean update(float dt){
        Iterator<Bubble> i = bubbles.iterator();
        for(Bubble b: bubbles) {
            //TODO: something
        }
        return !bubbles.isEmpty();
    }

    public void draw(Canvas canvas, Paint paint, SurfaceHolder surfaceHolder) {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            for(Bubble b: bubbles) {
                canvas.drawBitmap(b.getBitmap(), b.getX(), b.getY(), paint);
             }

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public boolean registerBubble(Bubble newBubble) {
        return bubbles.add(newBubble);
    }
}
