package gui;

import graphicsManage.DrawableVector;
import graphicsManage.FixedPointVector;
import graphicsManage.Polygon;
import graphicsManage.ShapeCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static graphicsManage.ShapeCommand.*;


public class VecCanvas extends JComponent {
    /**
     * The GUI component for the drawing Canvas. Singleton design pattern for a shared instance across application.
     */

    // Single canvas instance.
    private static VecCanvas instance;

    // Instruction that is currently being manipulated through GUI.
    private DrawableVector currentInstruction;

    ArrayList<DrawableVector> instructions = new ArrayList<>();

    // stores the initial click location
    private int click_x = -1;
    private int click_y = -1;

    /**
     * VecCanvas constructor. Creates a new VecCanvas object. This is a private constructor as only one instance is created.
     */
    private VecCanvas(){

        super.setVisible(true);
        super.setOpaque(false);

        MouseAdapter mouse = new MouseAdapter() {
            boolean polygon_initialised = false;
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Test");
                if (currentInstruction == null) return;

                // right click and current instruction is polygon.
                if ((e.getButton() == MouseEvent.BUTTON3) && (currentInstruction.getCommand()==POLYGON)){
                    ((Polygon)currentInstruction).finishShape();
                }

                click_x = e.getX();
                click_y = e.getY();

                switch(currentInstruction.getCommand()){
                    case POINT:
                        instance.addComponent(currentInstruction);
                        ((FixedPointVector) currentInstruction).setCoordinates(click_x,click_y,click_x,click_y);
                        break;
                    case POLYGON:
                        if (!polygon_initialised){
                            instructions.add(currentInstruction);
                        }
                        ((Polygon)currentInstruction).addCoordinates(e.getX(),e.getY());
                        break;

                    default:
                        ((FixedPointVector)currentInstruction).setCoordinates(click_x,click_y,e.getX(),e.getY());
                        instructions.add(currentInstruction);
                }
                instance.repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (currentInstruction == null) return;

                ShapeCommand cmd = currentInstruction.getCommand();

                if ((cmd==RECTANGLE)||(cmd==ELIPSES)||(cmd==LINE)){
                    ((FixedPointVector)currentInstruction).setCoordinates(click_x,click_y,e.getX(),e.getY());
                    instance.repaint();
                }
            }


            @Override
            public void mouseReleased(MouseEvent e) {
                if (currentInstruction == null) return;

                if (currentInstruction.getCommand() != POLYGON)
                    currentInstruction=null;
            }
        };
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
    }

    /**
     *
     * @return VecCanvas single instance.
     */
    public static VecCanvas getCanvas(){
        if (instance == null)
            instance = new VecCanvas();
        return instance;
    }

    /**
     *
     * @param draw
     */
    public void updateCurrentInstruction(FixedPointVector draw){
        currentInstruction=draw;
    }

    /**
     *
     */
    public void cancelCurrentInstruction(){
        currentInstruction=null;
    }

    public void undo(){
        int index_of_last_operation = instructions.size() - 1;
        if (index_of_last_operation >= 0){
            instructions.remove(index_of_last_operation);
        }
    }

    /**
     *
     * @param draw
     */
    public void addComponent(DrawableVector draw) {
        instructions.add(draw);
    }

    /**
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,super.getWidth(),super.getHeight());

        for (DrawableVector instruction : instructions){
            instruction.draw(g);
        }
    }
}