package kns.bubbledroid;

/** @author David
 *  defines characteristics for all of the game state objects
 *  contains init() and a refrence to the game state manager */
public abstract class GameState {

    private GameManager manager;

    public abstract void init();

}
