package graphicsManage;

import gui.VecCanvas;

import java.awt.*;

public class Line extends FixedPointVector {
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

    @Override
    public void setCoordinates(int x1, int y1, int x2, int y2) {
        this.canvas= VecCanvas.getCanvas();
        double width = (double) canvas.getWidth();
        double height = (double) canvas.getHeight();

        this.x1 = (double) x1/width;
        this.x2 = (double) x2/width;
        this.y1 = (double) y1/height;
        this.y2 = (double) y2/height;
        this.color=color;
    }

    @Override
    public void setCoordinates(double x1, double y1, double x2, double y2) {
        this.canvas= VecCanvas.getCanvas();
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        this.color=color;
    }

    @Override
    public ShapeCommand getCommand() {
        return ShapeCommand.LINE;
    }
}
