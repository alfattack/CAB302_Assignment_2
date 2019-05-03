package graphicsManage;

import java.awt.*;

public class Line extends DrawableVector {

    private int x1,x2,y1,y2;

    public Line(double x1, double y1, double x2, double y2, Color color){
        super(x1,y1,x2,y2,color);
    }

    @Override
    public void draw(Graphics g) {
        int width = super.canvas.getWidth();
        int height = super.canvas.getHeight();

        int x1_pixel = (int) Math.round(super.x1*width);
        int x2_pixel = (int) Math.round(super.x2*width);
        int y1_pixel = (int) Math.round(super.y1*height);
        int y2_pixel = (int) Math.round(super.y2*height);

        g.setColor(super.color);
        g.drawLine(x1_pixel,y1_pixel,x2_pixel,y2_pixel);
    }
}
