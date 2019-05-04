package gui;

import graphicsManage.DrawableVector;
import graphicsManage.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class MyCanvas extends JComponent implements ActionListener {
    /**
     * The GUI component for the drawing Canvas. Singleton design pattern for a shared instance across application.
     */

    private static MyCanvas instance;

    // indicates whether a user command is occuring.
    private boolean mouse_select_location;


    private DrawableVector currentInstruction;

    ArrayList<DrawableVector> instructions = new ArrayList<>();

    // stores the initial click location
    private int click_x = -1;
    private int click_y = -1;

    private MyCanvas(){

        super.setVisible(true);

        MouseAdapter mouse = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouse_select_location = true;
                click_x = e.getX();
                click_y = e.getY();

                currentInstruction = new Rectangle(click_x,click_y,click_x,click_y,false,Color.BLACK);
                instance.repaint();
                instance.addComponent(currentInstruction);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                currentInstruction.setCordinates(click_x,click_y,e.getX(),e.getY());
                instance.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentInstruction=null;
            }
        };
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
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
    public void actionPerformed(ActionEvent e) {
        mouse_select_location=true;
    }
}
