package graphicsManage;

import java.awt.*;

public class Elipses extends DrawableVector {

    private boolean fill;

    public Elipses(double x1, double y1, double x2, double y2, boolean fill, Color color){
        super(x1,y1,x2,y2,color);
        this.fill=fill;
    }

    public Elipses(int x1, int y1, int x2, int y2, boolean fill, Color color){
        super(x1,y1,x2,y2,color);
        this.fill=fill;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        Graphics2D g2 = (Graphics2D) g;
    }
}
