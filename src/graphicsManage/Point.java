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
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(super.color);

        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(x1_pixel,y1_pixel,x1_pixel,y1_pixel);
    }

    @Override
    public VectorCommand getCommand() {
        return VectorCommand.POINT;
    }

    @Override
    public DrawableVector returnCopy() {
        return new Point(-1,-1,super.color);
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
        sb.append(x1+" "+y1);
        return sb.toString();
    }
}
