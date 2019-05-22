package graphicsManage;

import java.awt.*;

public class Rectangle extends FixedPointVector {

    private Color fillColor = null;
    private boolean fill = false;

    public Rectangle(double x1, double y1, double x2, double y2, boolean fill, Color color, Color fillColor){
        super(x1,y1,x2,y2,color);
        this.fill=fill;
        this.fillColor =fillColor;
    }

    public Rectangle(Color color, Color fillColour, boolean fill) {
        super.color = color;
        this.fillColor = fillColour;
        this.fill = fill;
    }

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

    @Override
    public VectorCommand getCommand() {
        return VectorCommand.RECTANGLE;
    }

    @Override
    public DrawableVector returnCopy() {
        return new Rectangle(super.color, fillColor, fill);
    }

    @Override
    public boolean isFilled() {
        return fill;
    }

    @Override
    public Color getFillColor() {
        return fillColor;
    }


    @Override
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getCommand().toString());
        sb.append(" ");
        sb.append(super.toString());
        return sb.toString();
    }
}
