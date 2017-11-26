package kns.utils;

/**
 * Created by calvi on 11/25/2017.
 */

public class Vector2D {

    public double x, y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2D subtractVector(Vector2D other) {
        return new Vector2D(x - other.x, y - other.y);
    }

    public double length() {
        return Math.sqrt(x*x + y*y);
    }

    public Vector2D getUnitVector() {
        double l = length();
        return new Vector2D(x / l, y / l);
    }

    public double dot(Vector2D other) {
        return x * other.x + y * other.y;
    }

    public Vector2D scale(double scale) {
        return new Vector2D(x*scale, y*scale);
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(x + other.x, y + other.y);
    }

    public Vector2D difference(Vector2D other) {
        return new Vector2D(x - other.x, y - other.y);
    }



}
