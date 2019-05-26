package graphicsManage;

import java.awt.*;

/**
 * Point Command.
 */
public class Point extends FixedPointVector {

    /**
     * Constructs a point object with pen colour.
     * @param color pen colour.
     */
    public Point(Color color){
        super.color=color;
    }

    /**
     * Constructs point object with coordinates and pen colour.
     * @param x x location
     * @param y y location
     * @param color pen colour.
     */
    public Point(double x, double y, Color color){
        super(x,y,x,y,color);
    }


    /**
     * Draws the point relating to current dimensions of canvas.
     * @param g passed Graphics object.
     * @param width width of canvas.
     * @param height height of canvas.
     */
    @Override
    public void draw(Graphics g, int width, int height) {
        int x1_pixel = (int) Math.round(super.x1*width);
        int y1_pixel = (int) Math.round(super.y1*height);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(super.color);

        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(x1_pixel,y1_pixel,x1_pixel,y1_pixel);
    }

    /**
     * Gets VectorCommand relating to the object.
     * @return command.
     */
    @Override
    public VectorCommand getCommand() {
        return VectorCommand.PLOT;
    }

    /**
     * Returns copy of the object.
     * @return copy of object.
     */
    @Override
    public DrawableVector returnCopy() {
        return new Point(-1,-1,super.color);
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
        sb.append(String.format("%.6f %.6f", x1, y1));
        return sb.toString();
    }
}
