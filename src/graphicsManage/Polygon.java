package graphicsManage;

import java.awt.*;

public class Polygon implements DrawableVector {
//    ArrayList<double> x_points;
//    ArrayList<double> y_points;

    @Override
    public void draw(Graphics g, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;

        //g2d.fillPolygon(x_points, y_points, x_points.size());
    }

    @Override
    public VectorCommand getCommand() {
        return VectorCommand.POLYGON;
    }

    @Override
    public DrawableVector returnCopy() {
        return null;
    }

    public void addCoordinates(int x, int y, int width, int height){

    }

    public void addCoordinates(double x, double y){

    }

    public void finishShape(){

    }
}
