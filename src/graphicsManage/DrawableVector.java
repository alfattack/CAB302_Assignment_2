package graphicsManage;

import java.awt.*;

public interface DrawableVector {

    void draw(Graphics g, int width, int height);

    VectorCommand getCommand();

    DrawableVector returnCopy();

    boolean isFilled();

    Color getColor();

    Color getFillColor();

    void setColor(Color color);

    void setFillColor(Color color);
}
