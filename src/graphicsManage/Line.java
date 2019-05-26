package graphicsManage;

import java.awt.*;

/**
 * Line implementation of FixedPointVector
 */
public class Line extends FixedPointVector {

    /**
     * Constructor containing coordinates to be set.
     * @param x1 first x
     * @param y1 first y
     * @param x2 second x
     * @param y2 second y
     * @param color colour of pen
     */
    public Line(double x1, double y1, double x2, double y2, Color color){
        super.x1=x1;
        super.x2=x2;
        super.y1=y1;
        super.y2=y2;
        super.color=color;
    }

    /**
     * Constructs line without coordinates. These can be set later.
     * @param color color of pen
     */
    public Line(Color color){
        this.color=color;
    }

    /**
     * Draws the line with absolute coordinates relating to current dimension of canvas.
     * @param g passed Graphics object.
     * @param width width of canvas.
     * @param height height of canvas.
     */
    @Override
    public void draw(Graphics g, int width, int height) {
        int x1_pixel = (int) Math.round(super.x1*width);
        int x2_pixel = (int) Math.round(super.x2*width);
        int y1_pixel = (int) Math.round(super.y1*height);
        int y2_pixel = (int) Math.round(super.y2*height);

        g.setColor(super.color);
        g.drawLine(x1_pixel,y1_pixel,x2_pixel,y2_pixel);
    }

    /**
     * Sets relative coordinates of line (0.0 - 1.0 visible space for x and y) based on absolute coordinates and canvas dimensions.
     * @param x1 first x
     * @param y1 first y
     * @param x2 second x
     * @param y2 second y
     * @param canvasWidth width of canvas
     * @param canvasHeight height of canvas
     */
    @Override
    public void setCoordinates(int x1, int y1, int x2, int y2, int canvasWidth, int canvasHeight) {
        this.x1 = (double) x1/canvasWidth;
        this.x2 = (double) x2/canvasWidth;
        this.y1 = (double) y1/canvasHeight;
        this.y2 = (double) y2/canvasHeight;
    }

    /**
     * Sets relative coordinates of line (0.0 - 1.0 visible space for x and y)
     * @param x1 first x
     * @param y1 first y
     * @param x2 second x
     * @param y2 second y
     */
    @Override
    public void setCoordinates(double x1, double y1, double x2, double y2) {
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
    }

    /**
     * Gets VectorCommand relating to Line object
     * @return command
     */
    @Override
    public VectorCommand getCommand() {
        return VectorCommand.LINE;
    }

    /**
     * Returns copy of line
     * @return Line object based on current instance.
     */
    @Override
    public DrawableVector returnCopy() {
        return new Line(this.x1,this.y1,this.x2,this.y2,color);
    }

    /**
     * Implemented from abstract method. Will always be false as cannot be filled.
     * @return false
     */
    @Override
    public boolean isFilled() {
        return false;
    }

    /**
     * Implemented from abstract method. Will always be null as cannot be filled.
     * @return null
     */
    @Override
    public Color getFillColor() {
        return null;
    }

    /**
     * String representation of object containing command and coordinates.
     * @return string representation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getCommand().toString());
        sb.append(" ");
        sb.append(super.toString());
        return sb.toString();
    }
}
