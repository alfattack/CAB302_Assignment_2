package gui;

import graphicsManage.*;
import graphicsManage.Polygon;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStreamImpl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static graphicsManage.VectorCommand.*;

/**
 * The GUI component for the drawing Canvas. Singleton design pattern for a shared instance across application.
 */
public class VecCanvas extends JPanel {

    // Single canvas instance.
    private static VecCanvas instance;

    // Instruction that is currently being manipulated through GUI.
    private DrawableVector currentInstruction;
    private ArrayList<DrawableVector> instructions = new ArrayList<>();

    // stores the initial click location
    private int click_x = -1;
    private int click_y = -1;

    /**
     * VecCanvas constructor. Creates a new VecCanvas object. This is a private constructor as only one instance is created.
     */
    private VecCanvas(){
        MouseAdapter mouse = new CanvasMouse();
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
        setupKeyBindings();
    }

    /**
     * Set up keyboard shortcuts for controlling canvas.
     */
    private void setupKeyBindings(){
        // CTRL Z
        KeyStroke ctrl_z = KeyStroke.getKeyStroke("control Z");
        getActionMap().put("ctrl_z", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undo();
                repaint();
            }
        });
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ctrl_z,"ctrl_z");
    }

    /**
     * Returns the one and only instance of the canvas. Follows singleton design pattern.
     * @return VecCanvas single instance.
     */
    public static VecCanvas getCanvas(){
        if (instance == null)
            instance = new VecCanvas();
        return instance;
    }

    /**
     * Sets current instruction (instruction not yet completed - i.e. being manipulated via GUI.)
     * @param draw DrawableVector of current instruction.
     */
    public void updateCurrentInstruction(DrawableVector draw){
        currentInstruction=draw;
    }

    /**
     * Undo last committed instruction.
     */
    public synchronized void undo(){
        int index_of_last_operation = instructions.size() - 1;
        if (index_of_last_operation >= 0){
            instructions.remove(index_of_last_operation);
        }
    }

    /**
     * clears ArrayList of instructions.
     */
    public void clear(){
        instructions.clear();
        super.repaint();
    }

    /**
     * Adds a drawable vector to the list of instructions.
     * @param draw DrawableVector to add
     */
    public synchronized void addComponent(DrawableVector draw) {
        instructions.add(draw);
    }

    /**
     * Sets instructions to predefined list of DrawableVectors.
     * @param instructions list of predefined instructions.
     */
    public void setInstructions(ArrayList<DrawableVector> instructions){
        this.instructions=instructions;
    }

    /**
     * Returns all the instructions.
     * @return ArrayList of all the instructions.
     */
    public ArrayList<DrawableVector> getInstructions(){
        return instructions;
    }

    /**
     * returns current instruction.
     * @return DrawableVector of current instruction.
     */
    public DrawableVector getCurrentInstruction() {
        return currentInstruction;
    }

    /**
     * Paints the canvas using the draw method for all the DrawableVectors in contained instructions.
     * @param g passed Graphics object.
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,super.getWidth(),super.getHeight());

        for (DrawableVector instruction : instructions){
            instruction.draw(g, getWidth(), getHeight());
        }
        if (currentInstruction != null){
            currentInstruction.draw(g, getWidth(), getHeight());
        }
    }

    /**
     * Paints the canvas using the draw method for all the DrawableVectors in contained instructions with enforced dimensions. (Used for exporting)
     * @param g passed Graphics object
     * @param width width of component to draw to
     * @param height height of component to draw to
     */
    public void paintComponent(Graphics g, int width, int height) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,width,height);

        for (DrawableVector instruction : instructions){
            instruction.draw(g, width, height);
        }
        if (currentInstruction != null){
            currentInstruction.draw(g, width, height);
        }
    }


    /**
     * Exports canvas as png to specified path
     * @param path path of png.
     * @throws IOException something went wrong with writing to file.
     */
    public void saveCanvasToPng(String path) throws IOException {
        int WIDTH = 500;
        int HEIGHT = 500;

        BufferedImage image = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        paintComponent(g, WIDTH, HEIGHT);
        ImageIO.write(image,"png",new File(path));
    }

    /**
     * Mouse that interacts with the canvas. (I'll admit this is a bit spaghetti)
     */
    private class CanvasMouse extends MouseAdapter{

        /**
         * mouse pressed event.
         * @param e event object.
         */
        @Override
        public void mousePressed(MouseEvent e) {

            if (currentInstruction == null) return;

            click_x = e.getX();
            click_y = e.getY();

            // right click and current instruction is polygon.
            if ((e.getButton() == MouseEvent.BUTTON3) && (currentInstruction.getCommand()==POLYGON)){
                if (((Polygon)currentInstruction).isValid())
                {
                    ((Polygon)currentInstruction).finishShape();
                    instructions.add(currentInstruction);
                    currentInstruction=null;
                }
            }
            else{
                switch(currentInstruction.getCommand()){
                    case PLOT:
                        ((FixedPointVector) currentInstruction).setCoordinates(click_x,click_y,click_x,click_y,getWidth(),getHeight());
                        instance.addComponent(currentInstruction);
                        break;
                    case POLYGON:
                        ((Polygon)currentInstruction).addCoordinates(e.getX(),e.getY(),getWidth(),getHeight());
                        break;
                    default:
                        ((FixedPointVector)currentInstruction).setCoordinates(click_x,click_y,e.getX(),e.getY(),getWidth(),getHeight());
                        break;
                }
            }
            instance.repaint();
        }

        /**
         * mouse dragged event.
         * @param e event object.
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            if (currentInstruction == null) return;

            VectorCommand cmd = currentInstruction.getCommand();

            if ((cmd==RECTANGLE)||(cmd==ELIPSES)||(cmd==LINE)){
                ((FixedPointVector)currentInstruction).setCoordinates(click_x,click_y,e.getX(),e.getY(),getWidth(),getHeight());
                instance.repaint();
            }
        }

        /**
         * mouse released event.
         * @param e event object.
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            if (currentInstruction == null) return;

            if (currentInstruction.getCommand() != POLYGON){
                instructions.add(currentInstruction);
                currentInstruction=currentInstruction.returnCopy();
            }
        }
    }
}
