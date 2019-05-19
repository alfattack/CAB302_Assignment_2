package graphicsManage;

import java.awt.*;

public class Elipses extends FixedPointVector {

    private Color fillColour = null;
    private boolean fill = false;

    public Elipses(double x1, double y1, double x2, double y2, boolean fill, Color color, Color fillColour){
        super(x1,y1,x2,y2,color);
        this.fill=fill;
    }

    public Elipses(Color color, Color fillColour, boolean fill) {
        super.color = color;
        this.fillColour = fillColour;
        this.fill = fill;
    }

    @Override
    public void draw(Graphics g, int width, int height) {
        int x1_pixel = (int) Math.round(super.x1*width);
        int x2_pixel = (int) Math.round(super.x2*width);
        int y1_pixel = (int) Math.round(super.y1*height);
        int y2_pixel = (int) Math.round(super.y2*height);

        g.setColor(this.color);

        if (fill){
            g.setColor(fillColour);
            g.fillOval(x1_pixel,y1_pixel,x2_pixel-x1_pixel,y2_pixel-y1_pixel);
        }
        g.setColor(this.color);
        g.drawOval(x1_pixel,y1_pixel,x2_pixel-x1_pixel,y2_pixel-y1_pixel);
    }

    @Override
    public VectorCommand getCommand() {
        return VectorCommand.ELIPSES;
    }

    @Override
    public DrawableVector returnCopy() {
        return new Elipses(super.color, fillColour, this.fill);
    }
}
