package graphicsManage;

import java.awt.*;
import java.util.ArrayList;

public class Polygon implements DrawableVector {
    ArrayList<Double> x_points;
    ArrayList<Double> y_points;


    private boolean finished;

    @Override
    public void draw(Graphics g, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;

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
