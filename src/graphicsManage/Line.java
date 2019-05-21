package graphicsManage;

import java.awt.*;

public class Line extends FixedPointVector {

    public Line(double x1, double y1, double x2, double y2, Color color){
        super.x1=x1;
        super.x2=x2;
        super.y1=y1;
        super.y2=y2;
        super.color=color;
    }

    public Line(Color color){
        this.color=color;
    }

    @Override
    public void draw(Graphics g, int width, int height) {
        int x1_pixel = (int) Math.round(super.x1*width);
        int x2_pixel = (int) Math.round(super.x2*width);
        int y1_pixel = (int) Math.round(super.y1*height);
        int y2_pixel = (int) Math.round(super.y2*height);

        g.setColor(super.color);
        g.drawLine(x1_pixel,y1_pixel,x2_pixel,y2_pixel);
    }

    @Override
    public void setCoordinates(int x1, int y1, int x2, int y2, int canvasWidth, int canvasHeight) {
        this.x1 = (double) x1/canvasWidth;
        this.x2 = (double) x2/canvasWidth;
        this.y1 = (double) y1/canvasHeight;
        this.y2 = (double) y2/canvasHeight;
    }

    @Override
    public void setCoordinates(double x1, double y1, double x2, double y2) {
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
    }

    @Override
    public VectorCommand getCommand() {
        return VectorCommand.LINE;
    }

    @Override
    public DrawableVector returnCopy() {
        return new Line(this.x1,this.y1,this.x2,this.y2,color);
    }

    @Override
    public boolean isFilled() {
        return false;
    }

    @Override
    public Color getFillColor() {
        return null;
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
