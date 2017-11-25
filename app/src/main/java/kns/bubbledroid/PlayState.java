package kns.bubbledroid;

import android.view.SurfaceView;
import android.content.Context;

/**@author David
 * concrete implementation of gamestate
 * implements Runnable for threading
 * describes the state of active gameplay */

public class PlayState extends SurfaceView implements Runnable {


    //PlayState continues while running == True
    private boolean running;

    public PlayState(Context context) {
        super(context);

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

    }

    private void draw() {

    }

    public void pause() {

    }

    public void resume() {

    }

}
