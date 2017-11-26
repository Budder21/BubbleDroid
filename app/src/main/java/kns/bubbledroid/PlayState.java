package kns.bubbledroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.annotation.Dimension;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.content.Context;
import android.graphics.Color;

import java.text.DecimalFormat;
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
        time = 30;
        score = 0;

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

        long currTime;
        float delta = 0;
        while(running){
            currTime = System.currentTimeMillis();
            update(delta);
            if (surfaceHolder.getSurface().isValid())
                draw();
            delta = (System.currentTimeMillis() - currTime) / 1000f;
        }
    }

    private void update(float dt) {
        bubbleManager.update(dt);
        time -= dt;
        if(time<=0) {
            running = false;
            Intent intent = new Intent(getContext(), GameOver.class);
            intent.putExtra("score", score);
            super.getContext().startActivity(intent);

        }
    }

    public boolean isRunning() {
        return running;
    }

    private void draw() {

        canvas = surfaceHolder.lockCanvas();{

            canvas.drawBitmap(backgroundImage, 0, 0, paint);
            bubbleManager.draw(canvas, paint);

            paint.setTextSize(110);
            paint.setColor(Color.argb(200,214, 51, 255));
            DecimalFormat f = new DecimalFormat(("0"));
            canvas.drawText(f.format(time), 10, 100, paint);
            int xdel = score < 10 ? 70 : score < 100 ? 125 : 185;
            canvas.drawText(f.format(score), this.getDisplay().getWidth() - xdel, 100, paint);

        }surfaceHolder.unlockCanvasAndPost(canvas);

    }

    public void pause() {

    }

    public void resume() {

    }

    public void onTouch(MotionEvent e) {
        if(bubbleManager.poppedBubble(new Vector2D(e.getX(), e.getY())))
            score++;
    }

}
