package graphicsManage;

import java.awt.*;

/**
 * Interface all drawing instructions used by canvas must implement.
 */
public interface DrawableVector {

    /**
     * Draws the instruction using current canvas dimensions.
     * @param g passed Graphics object.
     * @param width width of canvas.
     * @param height height of canvas.
     */
    void draw(Graphics g, int width, int height);

    /**
     * Ger the VectorCommand relating to the object.
     * @return command relating to object.
     */
    VectorCommand getCommand();

    /**
     * returns a new instance of DrawableVector.
     * @return copy of current object.
     */
    DrawableVector returnCopy();

    /**
     * returns fill status of object.
     * @return boolean indicating filled status.
     */
    boolean isFilled();

    /**
     * returns pen colour of the object.
     * @return Colour object relating to pen colour.
     */
    Color getColor();

    /**
     * returns fill color of the object.
     * @return Color object relating to fill colour.
     */
    Color getFillColor();

    /**
     * Sets the pen colour of the object.
     * @param color Color to set pen
     */
    void setColor(Color color);

    /**
     * Sets the fill colour of the object.
     * @param color Color for fill.
     */
    void setFillColor(Color color);
}
