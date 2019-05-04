package graphicsManage;

import gui.MyCanvas;

import java.awt.*;

public abstract class DrawableVector {
    protected Color color;
    protected MyCanvas canvas;
    protected  double x1, x2, y1, y2;

    /**
     * Drawable Vector constructor. Creates a drawable component with relative coordinates from 0.0 to 1.0.
     * On canvas is between 0.0 - 1.0 for X and Y.
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param color
     */
    public DrawableVector(double x1, double y1, double x2, double y2,Color color){
        if (x1 > x2){
            double temp=x1;
            x1=x2;
            x2=temp;
        }

        if (y1 > y2){
            double temp=y1;
            y1=y2;
            y2=temp;
        }


        this.canvas=MyCanvas.getCanvas();
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        this.color=color;
    }

    /**
     * Constructs a drawable vector based on absolute pixel locations.
     * These are converted to relative locations.
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param color
     */
    public DrawableVector(int x1, int y1, int x2, int y2,Color color){
        this.canvas=MyCanvas.getCanvas();
        double width = (double) canvas.getWidth();
        double height = (double) canvas.getHeight();

        if (x1 > x2){
            int temp=x1;
            x1=x2;
            x2=temp;
        }

        if (y1 > y2){
            int temp=y1;
            y1=y2;
            y2=temp;
        }

        this.x1 = (double) x1/width;
        this.x2 = (double) x2/width;
        this.y1 = (double) y1/height;
        this.y2 = (double) y2/height;
        this.color=color;
    }

    /**
     * Sets instruction based on relative position on the canvas.
     * On canvas is between 0.0 - 1.0 for X and Y.
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void setCordinates(double x1, double y1, double x2, double y2){
        if (x1 > x2){
            double temp=x1;
            x1=x2;
            x2=temp;
        }

        if (y1 > y2){
            double temp=y1;
            y1=y2;
            y2=temp;
        }

        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
    }

    /**
     * Sets instruction coordinates based on absolute pixel locations. These are converted to relative locations.
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void setCordinates(int x1, int y1, int x2, int y2){
        if (x1 > x2){
            int temp=x1;
            x1=x2;
            x2=temp;
        }

        if (y1 > y2){
            int temp=y1;
            y1=y2;
            y2=temp;
        }


        double width = (double) canvas.getWidth();
        double height = (double) canvas.getHeight();
        this.x1 = (double) x1/width;
        this.x2 = (double) x2/width;
        this.y1 = (double) y1/height;
        this.y2 = (double) y2/height;
    }

    public abstract void draw(Graphics g);

}

