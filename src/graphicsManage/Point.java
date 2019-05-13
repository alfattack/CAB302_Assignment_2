package graphicsManage;

import java.awt.*;

public class Point extends FixedPointVector {

    /**
     *
     * @param color
     */

    public Point(Color color){
        super.color=color;
    }

    public Point(double x, double y, Color color){
        super(x,y,x,y,color);
    }


    @Override
    public void draw(Graphics g, int width, int height) {
        int x1_pixel = (int) Math.round(super.x1*width);
        int y1_pixel = (int) Math.round(super.y1*height);
        g.setColor(super.color);
        g.drawLine(x1_pixel,y1_pixel,x1_pixel,y1_pixel);
    }

    @Override
    public VectorCommand getCommand() {
        return null;
    }

    @Override
    public DrawableVector returnCopy() {
        return new Point(-1,-1,super.color);
    }
}
