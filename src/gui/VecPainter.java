package gui;

import graphicsManage.Rectangle;

import javax.swing.*;
import java.awt.*;

public class VecPainter {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setSize(500,500);
        window.setLayout(new GridLayout(1,3));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setMinimumSize(new Dimension(100, 100));

        Rectangle rect = new Rectangle(0,0,0.5,0.5, false, Color.MAGENTA);

        MyCanvas myCanvas = MyCanvas.getCanvas();
        myCanvas.addComponent(rect);
        window.add(myCanvas);
        window.setVisible(true);
    }
}



