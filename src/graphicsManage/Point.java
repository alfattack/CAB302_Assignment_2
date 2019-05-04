package graphicsManage;

import java.awt.*;

public class Point extends FixedPointVector {
    /**
     *
     * @param x
     * @param y
     * @param color
     */

    public Point(int x, int y, Color color){
        super(x,y,x,y,color);
    }

    public Point(double x, double y, Color color){
        super(x,y,x,y,color);
    }


    @Override
    public void draw(Graphics g) {
        int width = super.canvas.getWidth();
        int height = super.canvas.getHeight();
        int x1_pixel = (int) Math.round(super.x1*width);
        int y1_pixel = (int) Math.round(super.y1*height);
        g.setColor(super.color);
        g.drawLine(x1_pixel,y1_pixel,x1_pixel,y1_pixel);
    }

    @Override
    public ShapeCommand getCommand() {
        return null;
    }
}
