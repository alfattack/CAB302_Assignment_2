package gui;

import javax.swing.*;
import java.awt.*;

import graphicsManage.DrawableVector;
import graphicsManage.Line;
import graphicsManage.Rectangle;

public class VecPainter {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setSize(500,500);
        window.setLayout(new GridLayout(1,3));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setMinimumSize(new Dimension(100, 100));

        Rectangle rect = new Rectangle(0,0,0.5,0.5, false, Color.MAGENTA);
        DrawableVector line = new Line(0.2,0.3,0.7,0.5, Color.GREEN);

        MyCanvas myCanvas = MyCanvas.getCanvas();
        myCanvas.addComponent(rect);
        myCanvas.addComponent(line);
        window.add(myCanvas);
        window.setVisible(true);
    }
}



