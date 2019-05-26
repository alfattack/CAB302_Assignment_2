package graphicsManage;
import java.awt.*;

/**
 * Abstract class for all shapes which have a fixed number of points (everything except polygon). Implements shared methods across these classes.
 */
public abstract class FixedPointVector implements DrawableVector {
    protected Color color;
    protected  double x1, x2, y1, y2;

    /**
     * Default constructor - takes no arguments.
     */
    public FixedPointVector(){
        //pass
    }
    /**
     * Fixed Point Vector constructor. Creates a drawable component with relative coordinates, with 0.0 - 1.0 being the visible space on the X and Y axis.
     * @param x1 first x
     * @param y1 first y
     * @param x2 second x
     * @param y2 second y
     * @param color pen colour
     */
    public FixedPointVector(double x1, double y1, double x2, double y2, Color color){
        if (x1 > x2){
            double temp=x1;
            x1=x2;
            x2=temp;
        }

        if (y1 > y2){
            double temp=y1;
            y1=y2;
            y2=temp;
        }

        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        this.color=color;
    }

    /**
     * Constructs a drawable vector based on absolute pixel locations.
     * These are converted to relative locations.
     * @param x1 first x
     * @param y1 first y
     * @param x2 second x
     * @param y2 second y
     * @param color pen color
     */
    public FixedPointVector(int x1, int y1, int x2, int y2, Color color, int width, int height){
        if (x1 > x2){
            int temp=x1;
            x1=x2;
            x2=temp;
        }

        if (y1 > y2){
            int temp=y1;
            y1=y2;
            y2=temp;
        }

        this.x1 = (double) x1/width;
        this.x2 = (double) x2/width;
        this.y1 = (double) y1/height;
        this.y2 = (double) y2/height;
        this.color=color;
    }

    /**
     * Sets instruction based on relative position on the canvas. (0.0 - 1.0 visible space on x and y axis).
     * @param x1 first x
     * @param y1 first y
     * @param x2 second x
     * @param y2 second y
     */
    public void setCoordinates(double x1, double y1, double x2, double y2){
        if (x1 > x2){
            double temp=x1;
            x1=x2;
            x2=temp;
        }

        if (y1 > y2){
            double temp=y1;
            y1=y2;
            y2=temp;
        }

        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
    }

    /**
     * Sets instruction coordinates based on absolute pixel locations. These are converted to relative locations.
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void setCoordinates(int x1, int y1, int x2, int y2, int width, int height){
        if (x1 > x2){
            int temp=x1;
            x1=x2;
            x2=temp;
        }

        if (y1 > y2){
            int temp=y1;
            y1=y2;
            y2=temp;
        }

        this.x1 = (double) x1/width;
        this.x2 = (double) x2/width;
        this.y1 = (double) y1/height;
        this.y2 = (double) y2/height;
    }

    /**
     * Gets pen colour of object.
     * @return pen colour
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Sets pen colour of object.
     * @param color Color to set pen
     */
    @Override
    public void setColor(Color color) {
        this.color=color;
    }

    /**
     * Sets fill colour of object.
     * @param color Color for fill.
     */
    @Override
    public void setFillColor(Color color) {
        return;
    }

    /**
     * base String representation of some fixed point shapes (only coordinates as instructions change).
     * @return string representation of coordinates
     */
    @Override
    public String toString() {
        return String.format("%.6f %.6f %.6f %.6f",x1,y1,x2,y2);
    };

    /**
     * VectorCommand relating to object.
     * @return command.
     */
    public abstract VectorCommand getCommand();
}

