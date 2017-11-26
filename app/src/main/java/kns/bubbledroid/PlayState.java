package kns.bubbledroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Dimension;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.content.Context;
import android.graphics.Color;
import java.util.HashSet;
import android.view.WindowManager;


/**@author David and Calvin
 * concrete implementation of gamestate
 * implements Runnable for threading
 * describes the state of active gameplay */

public class PlayState extends SurfaceView implements Runnable {


    //PlayState continues while running == True
    private boolean running;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private Paint paint;
    private Bitmap backgroundImage;

    private BubbleManager bubbleManager;

    public PlayState(Context context) {
        super(context);

        surfaceHolder = getHolder();
        paint = new Paint();

        bubbleManager = new BubbleManager();

        running = true;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        backgroundImage = Bitmap.createScaledBitmap(backgroundImage, this.getDisplay().getWidth(), this.getDisplay().getHeight(), true);

        for(int i = 0; i < 3; i++)
            bubbleManager.addNewBubble(this.getDisplay());

        long currTime = System.currentTimeMillis();
        while(running){
            float delta = (System.currentTimeMillis() - currTime) / 1000f;
            currTime = System.currentTimeMillis();
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
            canvas.drawBitmap(backgroundImage,0,0,paint);

            bubbleManager.draw(canvas, paint, surfaceHolder);

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause() {

    }

    public void resume() {

    }

    public void onTouch(MotionEvent e) {
         if(bubbleManager.poppedBubble(new Vector2D(e.getX(), e.getY()))) {
            Thread t = new Thread(()-> {
                long startTime = System.currentTimeMillis();
                while(System.currentTimeMillis() - startTime < (int)(Math.random() * 1000) + 500);
                bubbleManager.addNewBubble(this.getDisplay());
            });
            t.start();
        }
    }

}
