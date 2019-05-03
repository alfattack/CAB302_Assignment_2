package gui;

import graphicsManage.DrawableVector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MyCanvas extends JComponent implements ActionListener {

    private static MyCanvas instance;

    // indicates whether a user command is occuring.
    private boolean mouse_select_location;

    ArrayList<DrawableVector> instructions = new ArrayList<>();

    // stores the click location
    private int screen_x = -1;
    private int screen_y = -1;

    // stores how many times the mouse has been pressed on screen. (2 presses indicates completed action).
    private int numberPresses = 0;

    private MyCanvas(){
        setSize(30,30);
        setBackground(new Color(255,255,255));
        setVisible(true);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                screen_x = e.getX();
                screen_y = e.getX();
                System.out.println("mouse location: "+screen_x);
                System.out.println("width: "+getWidth());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                screen_x=-1;
                screen_y=-1;
                System.out.println(screen_x);
                mouse_select_location=false;
            }
        });
    }

    public static MyCanvas getCanvas(){
        if (instance == null)
            instance = new MyCanvas();
        return instance;
    }

    public void addComponent(DrawableVector draw) {
        instructions.add(draw);
    }

    @Override
    public void paintComponent(Graphics g) {
        for (DrawableVector instruction : instructions){
            instruction.draw(g);
        }
    }

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mouse_select_location=true;
    }
}
