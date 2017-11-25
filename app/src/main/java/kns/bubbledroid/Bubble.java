package kns.bubbledroid;

import android.graphics.Color;
import android.graphics.Point;

/**
 * @author David
 */

public class Bubble {

    private Color color;
    private Point position;
    private float xvel;
    private float yvel;

    public Bubble(Point position, float xvel, float yvel, Color color){
        this.position = position;
        this.color = color;
        this.xvel = xvel;
        this.yvel = yvel;
    }

    @Override
    public String toString(){
        return "Bubble|position: "+position+"velocity: "+xvel+","+yvel+"color: "+color;
    }

    @Override
    public int hashCode(){
        return toString().hashCode();
    }
}
