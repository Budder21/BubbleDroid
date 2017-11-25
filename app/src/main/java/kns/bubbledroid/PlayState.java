package kns.bubbledroid;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.content.Context;
import android.graphics.Color;
import java.util.HashSet;


/**@author David
 * concrete implementation of gamestate
 * implements Runnable for threading
 * describes the state of active gameplay */

public class PlayState extends SurfaceView implements Runnable {


    //PlayState continues while running == True
    private boolean running;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private Paint paint;

    private BubbleManager bubbleManager;

    public PlayState(Context context) {
        super(context);

        setBackgroundResource(R.drawable.background);

        surfaceHolder = getHolder();
        paint = new Paint();

        bubbleManager = new BubbleManager(new HashSet<Bubble>());
        bubbleManager.registerBubble(new Bubble(new Point(50,50),0,0, Color.WHITE, context));

        running = true;
        System.out.println("Did we get here?");
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        float currTime = System.currentTimeMillis();
        while(running){
            float delta = (System.currentTimeMillis() - currTime) / 1000f;
            update(delta);
            draw();
        }
    }

    private void update(float dt) {
        bubbleManager.update(dt);
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            //canvas.drawColor(Color.BLACK);

            bubbleManager.draw(canvas, paint, surfaceHolder);

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause() {

    }

    public void resume() {

    }

}
