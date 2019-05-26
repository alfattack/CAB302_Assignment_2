package graphicsManage;

import java.awt.*;

/**
 * Elipses shape.
 */
public class Elipses extends FixedPointVector {

    private Color fillColor = null;
    private boolean fill = false;

    /**
     * Constructor containing coordinates to be set.
     * @param x1 first x
     * @param y1 first y
     * @param x2 second x
     * @param y2 second y
     * @param fill whether to fill when drawn
     * @param color pen colour
     * @param fillColor fill colour
     */
    public Elipses(double x1, double y1, double x2, double y2, boolean fill, Color color, Color fillColor){
        super(x1,y1,x2,y2,color);
        this.fill=fill;
        this.fillColor=fillColor;
    }

    /**
     * Constructor without coordinates. These can be set later.
     * @param color pen colour
     * @param fillColor fill colour
     * @param fill whether to fill when drawn.
     */
    public Elipses(Color color, Color fillColor, boolean fill) {
        super.color = color;
        this.fillColor = fillColor;
        this.fill = fill;
    }

    /**
     * Draws Elipses with absolute coordinates based on current size of canvas.
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

        g.setColor(this.color);

        if (fill){
            g.setColor(fillColor);
            g.fillOval(x1_pixel,y1_pixel,x2_pixel-x1_pixel,y2_pixel-y1_pixel);
        }
        g.setColor(this.color);
        g.drawOval(x1_pixel,y1_pixel,x2_pixel-x1_pixel,y2_pixel-y1_pixel);
    }

    /**
     * VectorCommand relating to object.
     * @return command.
     */
    @Override
    public VectorCommand getCommand() {
        return VectorCommand.ELIPSES;
    }

    /**
     * Returns shallow copy of object without set coordinates.
     * @return copy of object.
     */
    @Override
    public DrawableVector returnCopy() {
        return new Elipses(super.color, fillColor, this.fill);
    }

    /**
     * Indicates whether the shape is to filled.
     * @return fill status.
     */
    @Override
    public boolean isFilled() {
        return fill;
    }

    /**
     * Sets the fill colour.
     * @param fillColor colour for fill.
     */
    @Override
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * Gets the fill colour
     * @return colour of fill.
     */
    @Override
    public Color getFillColor() {
        return fillColor;
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
