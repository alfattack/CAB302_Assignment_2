package graphicsManage;

import java.awt.*;
import java.util.ArrayList;

/**
 * Polygon shape which implements DrawableVector
 */
public class Polygon implements DrawableVector {

    ArrayList<Double> x_points = new ArrayList<>();
    ArrayList<Double> y_points = new ArrayList<>();

    private Color color, fillColour;
    private boolean fill;
    private boolean finished;

    /**
     * Constructor using predefined points. Creates finished polygon.
     * @param x_points ArrayList of x points
     * @param y_points ArrayList of y points
     * @param fill boolean indicating whether to fill.
     * @param color pen color.
     * @param fillColour fill color.
     */
    public Polygon(ArrayList<Double> x_points, ArrayList<Double> y_points, boolean fill, Color color, Color fillColour) throws VecFileException{

        if (x_points.size() != y_points.size()){
            throw new VecFileException("length of x points does not match length of y points");
        }

        this.x_points = x_points;
        this.y_points = y_points;

        this.fill = fill;
        this.color=color;
        this.fillColour=fillColour;
        this.finished=true;
    }

    /**
     * Constructor with no initial coordinates. Creates unfinished polygon.
     * @param color pen colour
     * @param fillColour fill colour
     * @param fill use fill
     */
    public Polygon(Color color, Color fillColour, boolean fill){
        this.color=color;
        this.fillColour = fillColour;
        this.fill=fill;
        this.finished=false;
    }

    /**
     * Draws the polygon at absolute coordinates based on width and height of canvas.
     * @param g passed Graphics object.
     * @param width width of canvas.
     * @param height height of canvas.
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
     * Gets array absolute coordinates (relativePoints) scaled to size of canvas.
     * @param relativePoints ArrayList of points in double precision.
     * @param width canvas width.
     * @param height canvas height.
     * @return array of ints
     */
    private int[] getAbsoluteCoordinates(ArrayList<Double> relativePoints, int width, int height){
        int[] abs_points = new int[relativePoints.size()];
        for (int i = 0; i < relativePoints.size(); i++){
            abs_points[i] = (int)Math.round(relativePoints.get(i)*width);
        }
        return abs_points;
    }

    /**
     * gets the VectorCommand relating to the object.
     * @return command
     */
    @Override
    public VectorCommand getCommand() {
        return VectorCommand.POLYGON;
    }

    /**
     * Returns whether the polygon is valid (i.e. at least one point contained for x and y, and of equal size)
     * @return
     */
    public boolean isValid(){
        return ((x_points.size() > 0) && (y_points.size() > 0) && (x_points.size()==y_points.size()));
    }

    /**
     * Implemented from DrawableVector interface. Returns null for polygon.
     * @return
     */
    @Override
    public DrawableVector returnCopy() {
        return null;
    }

    /**
     * returns boolean indicating whether instruction is to be filled in.
     * @return filled
     */
    @Override
    public boolean isFilled() {
        return fill;
    }

    /**
     * gets the pen color of the object.
     * @return pen color.
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * gets the fill color pertaining to object.
     * @return fill color
     */
    @Override
    public Color getFillColor() {
        return fillColour;
    }

    /**
     * Sets the pen color.
     * @param color color to set pen
     */
    @Override
    public void setColor(Color color) {
        this.color=color;
    }

    /**
     * Sets the fill color.
     * @param color color for fill
     */
    @Override
    public void setFillColor(Color color) {
        this.fillColour=color;
    }

    /**
     * Adds points to the polygon based on absolute canvas locations. These are scaled to relative
     * locations (0.0 - 1.0 visible space for x and y) based on canvas dimensions.
     *
     * @param x x point to add
     * @param y y point to add
     * @param width width of canvas
     * @param height height of canvas
     */
    public void addCoordinates(int x, int y, int width, int height){
        x_points.add((double)x/(double)width);
        y_points.add((double)y/(double)height);
    }

    /**
     * Finish drawing the polygon.
     */
    public void finishShape(){
        if (!x_points.isEmpty()){
            finished=true;
            System.out.println("test finish shape");
        }
    }

    /**
     * returns a string representation of the object.
     * @return String representation of object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getCommand().toString());
        sb.append(" ");
        for (int i=0;i<x_points.size();i++){
            sb.append(String.format("%.6f %.6f ",x_points.get(i),y_points.get(i)));
        }
        return sb.toString();
    }
}
