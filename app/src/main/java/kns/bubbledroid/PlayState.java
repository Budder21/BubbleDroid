package kns.bubbledroid;

/**@author David
 * concrete implementation of gamestate
 * implements Runnable for threading
 * describes the state of active gameplay */

public class PlayState extends GameState implements Runnable {


    //PlayState continues while running == True
    private boolean running;

    @Override
    public void run() {
        while(running){
            continue;
        }
    }

    @Override
    public void init() {
        Thread t = new Thread(this);
        t.start();
    }
}
