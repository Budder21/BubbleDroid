package kns.bubbledroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.annotation.Dimension;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.content.Context;
import android.graphics.Color;
import java.util.HashSet;
import android.view.WindowManager;
import android.widget.TextView;


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
    private float time;
    private int score;

    public PlayState(Context context) {
        super(context);
        float startTime = System.currentTimeMillis();
        time = (System.currentTimeMillis() - startTime)/1000f;
        score = 0;

        surfaceHolder = getHolder();
        paint = new Paint();

        bubbleManager = new BubbleManager(new HashSet<Bubble>());

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

        long currTime;
        while(running){
            currTime = System.currentTimeMillis();
            float delta = (System.currentTimeMillis() - currTime) / 1000f;
            update(delta);
            if (surfaceHolder.getSurface().isValid())
                draw();
        }
    }

    private void update(float dt) {
        bubbleManager.update(dt);
        time += dt;
        System.out.println(time);
    }

    private void draw() {

        canvas = surfaceHolder.lockCanvas();{

            canvas.drawBitmap(backgroundImage, 0, 0, paint);
            bubbleManager.draw(canvas, paint, surfaceHolder);
            canvas.drawText(String.valueOf(time), 0, 0, paint);

        }surfaceHolder.unlockCanvasAndPost(canvas);

    }

    public void pause() {

    }

    public void resume() {

    }

}
