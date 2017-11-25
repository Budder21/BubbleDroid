package kns.bubbledroid;

/** @author David
 *  defines characteristics for all of the game state objects
 *  contains init() and a refrence to the game state manager */
public interface GameState {

    public void init();

    void pause();
    void resume();

}
