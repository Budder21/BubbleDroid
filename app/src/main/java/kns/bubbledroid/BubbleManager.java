package kns.bubbledroid;

import java.util.Iterator;
import java.util.Set;

/**@author David
 * This class contains all the bubbles that appear on the screen
 * and manages their actions and wearabouts */
public class BubbleManager {

    private Set<Bubble> bubbles;

    public BubbleManager(Set<Bubble> bubbles){
        this.bubbles=bubbles;
    }

    public boolean update(float delta){
        Iterator<Bubble> i = bubbles.iterator();
        while(i.hasNext()){
            //TODO actually do things here, bubbles need to move and pop and whatnot
            continue;
        }
        return !bubbles.isEmpty();
    }
}
