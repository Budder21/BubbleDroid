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
     * A multiplier for the double bubbles. Higher numbers increase the curvage of the probability distribution. 1 is linear
     */
    public static final int DOUBLE_BUBBLE_CHANCE = 1;

    /**
     *The maximum number of bubbles that could appear
     */
    public static final int MAX_NUMBER_OF_BUBBLES = 8;
    /**
     * The maximum chance there can be of a second bubble apearing. This is the change when there are 0 bubbles
     */
    public static final float MAX_DOUBLE_BUBBLE_CHANCE = 0.2f;

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
    public static final int FONT_COLOR = Color.rgb(255, 255, 100);

}
