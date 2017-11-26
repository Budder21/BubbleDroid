package kns.utils;

import android.graphics.Color;

/**
 * This class contains static final references to many of the constants that control the game's
 * physics. This includes the speeda that bubbles grow, their max size, and the chance of spawning
 * a second
 * @author Calvin
 */

public class Constants {

    /**
     * The proportion of popped bubbles that will spawn two more instead of one more
     */
    public static final float DOUBLE_BUBBLE_CHANCE = 0.2f;

    /**
     * The base maximum radius of a bubble.
     */
    public static final int MAX_BUBBLE_RADIUS = 145;
    /**
     * The variance of all max bubble radii: the actuall radius is:
     *              [MAX_RADIUS - VARIANCE / 2, MAX_RADIUS + VAIRANCE / 2]
     */
    public static final int BUBBLE_RADIUS_VARIANCE = 30;
    /**
     * A multiplier to control bubble growth speed
     */
    public static final float BUBBLE_GROWTH_SPEED = 1.0f;


    /**
     * The color of the font in the game istelf and on the GameOVer actvity
     */
    public static final int FONT_COLOR = Color.rgb(214, 51, 255);

}
