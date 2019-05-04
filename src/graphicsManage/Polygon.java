package graphicsManage;

import java.awt.*;
import java.util.ArrayList;

public class Polygon implements DrawableVector {
//    ArrayList<double> x_points;
//    ArrayList<double> y_points;

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        //g2d.fillPolygon(x_points, y_points, x_points.size());
    }

    @Override
    public ShapeCommand getCommand() {
        return ShapeCommand.POLYGON;
    }

    public void addCoordinates(int x, int y){

    }

    public void addCoordinates(double x, double y){

    }

    public void finishShape(){

    }
}
