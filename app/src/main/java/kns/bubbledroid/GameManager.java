package kns.bubbledroid;

/**@author David
 *  manages the game states
 *  contains the setState()*/
public class GameManager {

    private GameState state;

    public enum States{
        PlayState,MenuState,OptionState,HelpState
    }

    public boolean setState(GameState state){
        this.state = state;
        return true;
    }

}
