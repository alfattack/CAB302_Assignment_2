package graphicsManage;

import java.awt.*;

public interface DrawableVector {

    void draw(Graphics g);

    ShapeCommand getCommand();
}
