package graphicsManage;

import java.awt.*;
import gui.MyCanvas;

public abstract class DrawableVector {
    protected Color color;
    protected MyCanvas canvas;
    protected  double x1, x2, y1, y2;

    public DrawableVector(double x1, double y1, double x2, double y2,Color color){
        this.canvas=MyCanvas.getCanvas();
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        this.color=color;
    }

    public abstract void draw(Graphics g);

}

