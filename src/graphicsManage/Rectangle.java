package graphicsManage;

import java.awt.*;

/**
 * Rectangle Command.
 */
public class Rectangle extends FixedPointVector {

    private Color fillColor = null;
    private boolean fill = false;

    /**
     * Constructs a rectangle with all coordinates defined.
     * @param x1 first x
     * @param y1 first y
     * @param x2 second x
     * @param y2 second y
     * @param fill whether to fill when drawn
     * @param color pen colour
     * @param fillColor fill colour
     */
    public Rectangle(double x1, double y1, double x2, double y2, boolean fill, Color color, Color fillColor){
        super(x1,y1,x2,y2,color);
        this.fill=fill;
        this.fillColor =fillColor;
    }

    /**
     * Constructs a rectangle with no coordinates defined.
     * @param color pen colour
     * @param fillColour fill colour
     * @param fill whether to fill when drawn
     */
    public Rectangle(Color color, Color fillColour, boolean fill) {
        super.color = color;
        this.fillColor = fillColour;
        this.fill = fill;
    }

    /**
     * Draws the rectangle based on the current dimensions of the canvas.
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

        if (fill) {
            g.setColor(fillColor);
            g.fillRect(x1_pixel, y1_pixel, x2_pixel - x1_pixel, y2_pixel - y1_pixel);
        }

        g.setColor(color);
        g.drawRect(x1_pixel,y1_pixel,x2_pixel-x1_pixel,y2_pixel-y1_pixel);
    }

    /**
     * Gets VectorCommand relating to Rectangle class.
     * @return command
     */
    @Override
    public VectorCommand getCommand() {
        return VectorCommand.RECTANGLE;
    }

    /**
     * Returns copy of the class without defined coordinates.
     * @return copy
     */
    @Override
    public DrawableVector returnCopy() {
        return new Rectangle(super.color, fillColor, fill);
    }

    /**
     * Indicates whether the rectangle is filled.
     * @return filled
     */
    @Override
    public boolean isFilled() {
        return fill;
    }

    /**
     * Gets the colour used by fill.
     * @return colour of fill.
     */
    @Override
    public Color getFillColor() {
        return fillColor;
    }


    /**
     * Sets the colour to fill rectangle with.
     * @param fillColor colour to set fill.
     */
    @Override
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * String representation of object. Command and coordinates.
     * @return string representation of object.
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
