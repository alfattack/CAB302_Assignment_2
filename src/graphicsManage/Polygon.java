package graphicsManage;

import java.awt.*;
import java.util.ArrayList;

public class Polygon implements DrawableVector {

    ArrayList<Double> x_points = new ArrayList<>();
    ArrayList<Double> y_points = new ArrayList<>();

    private Color color, fillColour;
    private boolean fill;
    private boolean finished;


    public Polygon(ArrayList<Double> x_points, ArrayList<Double> y_points, boolean fill, Color color, Color fillColour){
        this.x_points = x_points;
        this.y_points = y_points;
        this.fill = fill;
        this.color=color;
        this.fillColour=fillColour;
        this.finished=true;
    }

    /**
     *
     * @param color
     * @param fillColour
     * @param fill
     */
    public Polygon(Color color, Color fillColour, boolean fill){
        this.color=color;
        this.fillColour = fillColour;
        this.fill=fill;
        this.finished=false;
    }

    /**
     *
     * @param g
     * @param width
     * @param height
     */
    @Override
    public void draw(Graphics g, int width, int height) {

        int xpoints[] = getAbsoluteCoordinates(x_points,width,height);
        int ypoints[] = getAbsoluteCoordinates(y_points,width,height);
        int npoints = x_points.size();

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(this.color);

        if (finished){
            g2d.drawPolygon(xpoints,ypoints,npoints);
            if (fill){
                g2d.setColor(fillColour);
                g2d.fillPolygon(xpoints,ypoints,npoints);
            }
        }
        else{
            for(int i = 0; i < xpoints.length-1; i++){
                g2d.drawLine(xpoints[i],ypoints[i],xpoints[i+1],ypoints[i+1]);
            }
        }
    }

    /**
     *
     * @param relativePoints
     * @return
     */
    private int[] getAbsoluteCoordinates(ArrayList<Double> relativePoints, int width, int height){
        int[] abs_points = new int[relativePoints.size()];
        for (int i = 0; i < relativePoints.size(); i++){
            abs_points[i] = (int)Math.round(relativePoints.get(i)*width);
        }
        return abs_points;
    }

    /**
     *
     * @return
     */
    @Override
    public VectorCommand getCommand() {
        return VectorCommand.POLYGON;
    }

    public boolean isValid(){
        return ((x_points.size() > 1) && (y_points.size() > 1));
    }

    /**
     *
     * @return
     */
    @Override
    public DrawableVector returnCopy() {
        return null;
    }

    @Override
    public boolean isFilled() {
        return fill;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Color getFillColor() {
        return fillColour;
    }

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void addCoordinates(int x, int y, int width, int height){
        x_points.add((double)x/(double)width);
        y_points.add((double)y/(double)height);
    }

    /**
     *
     * @param x
     * @param y
     */
    public void addCoordinates(double x, double y){
        x_points.add(x);
        y_points.add(y);
    }

    /**
     * Finish drawing the polygon.
     */
    public void finishShape(){

        if (!x_points.isEmpty()){
            x_points.add(x_points.get(0));
            y_points.add(y_points.get(0));
            finished=true;
            System.out.println("test finish shape");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getCommand().toString());
        sb.append(" ");
        for (int i=0;i<x_points.size();i++){
            sb.append(String.format("%f %f ",x_points.get(i),y_points.get(i)));
        }
        return sb.toString();
    }
}
