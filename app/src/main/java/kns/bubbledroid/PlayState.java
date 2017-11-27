package kns.bubbledroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.text.DecimalFormat;

import kns.utils.Constants;
import kns.utils.Vector2D;


/**@author David and Calvin
 * concrete implementation of gamestate
 * implements Runnable for threading
 * describes the state of active gameplay */

public class PlayState extends SurfaceView implements Runnable {


    //TODO: handle running variable to support pausing and do something when the game loop ends
    /**
     * While this is true, the game is running. When it becomes false, the game loop
     * ends. It does not move to the game over screen.
     */
    private boolean running;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private Paint paint;
    private Bitmap backgroundImage;
    private BubbleManager bubbleManager;
    /**
     * The timer shown in the top right: counts down from 30 right now
     */
    private float time;
    /**
     * Number of bubbles popped
     */
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

    /**
     * Has the main loop of the game, which calculate deltatime, updates the world, and renders everything
     */
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

    /**
     * Updates the bubble manager and, if time has run out, goes to the GameOVer activity
     * @param dt delta time, or the time that has passed sense this method was last called
     */
    private void update(float dt) {
        bubbleManager.update(dt);
        time -= dt;
        if(time<=0) {
            time=0;
            running = false;
            Intent intent = new Intent(getContext(), GameOver.class);
            intent.putExtra("score", score);
            super.getContext().startActivity(intent);

        }
    }

    public boolean isRunning() {
        return running;
    }

    /**
     * Used to render all the elements of the world. Right now, this is just the bubbles, the score,
     * and the timer
     */
    private void draw() {

        canvas = surfaceHolder.lockCanvas();{

            canvas.drawBitmap(backgroundImage, 0, 0, paint);
            bubbleManager.draw(canvas, paint);

            paint.setTextSize(110);
            paint.setColor(Constants.FONT_COLOR);
            DecimalFormat f = new DecimalFormat(("0"));
            canvas.drawText(f.format(time), 10, 100, paint);
            int xdel = score < 10 ? 70 : score < 100 ? 125 : 185;
            canvas.drawText(f.format(score), this.getDisplay().getWidth() - xdel, 100, paint);

        }surfaceHolder.unlockCanvasAndPost(canvas);

    }

    //TODO: handle pausing
    public void pause() {

    }

    public void resume() {

    }

    /**
     * Called when the screen is touched. It sees if the touch pops a bubble.
     * @param e holds information about the touch motion
     */
    public void onTouch(MotionEvent e) {
        if(bubbleManager.poppedBubble(new Vector2D(e.getX(), e.getY())))
            score++;
    }

}
