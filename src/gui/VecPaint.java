package gui;

import graphicsManage.Elipses;
import graphicsManage.Line;
import graphicsManage.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VecPaint extends JFrame {

    /**
     *
     */
    public static final int WIDTH = 800;
    public static final int HEIGHT = 500;

    // Drawing Canvas Instance
    private VecCanvas canvas;

    // Stores whether fill is selected
    private boolean fill;

    private Color fillColour;
    private Color penColour;

    private JButton rectangle;
    private JButton elipses;
    private JButton line;

    private JMenuItem loadFile;
    private JMenuItem saveFile;

    public VecPaint() {
        super("Panel Demonstration");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel biggerPanel = new JPanel();
        biggerPanel.setLayout(new GridBagLayout());

        canvas = VecCanvas.getCanvas();
        add(canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setLayout(new FlowLayout());

        rectangle = new JButton("Rectangle");
        rectangle.setBackground(Color.RED);
        rectangle.addActionListener(new ButtonCommandListener());
        buttonPanel.add(rectangle);

        elipses = new JButton("Blue");
        elipses.setBackground(Color.BLUE);
        elipses.addActionListener(new ButtonCommandListener());
        buttonPanel.add(elipses);

        line = new JButton("line");
        line.setBackground(Color.GREEN);
        line.addActionListener(new ButtonCommandListener());
        buttonPanel.add(line);

        add(buttonPanel, BorderLayout.SOUTH);
        JMenu colourMenu = new JMenu("Add Colours");

        loadFile = new JMenuItem("load");
        //redChoice.addActionListener(new ColourListener());
        colourMenu.add(loadFile);

        saveFile = new JMenuItem("save");
        //blueChoice.addActionListener(new ColourListener());
        colourMenu.add(saveFile);


        JMenuBar bar = new JMenuBar();
        bar.add(colourMenu);
        setJMenuBar(bar);
    }

    public static void main(String[] args) {
        VecPaint gui = new VecPaint();
        gui.setVisible(true);
    }

    private class ButtonCommandListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Component source = (Component) e.getSource();
            System.out.println(source);

            if (source == rectangle){
                canvas.updateCurrentInstruction(new Rectangle(0,0,0,0,true,Color.BLACK));
            }
            if (source == elipses){
                canvas.updateCurrentInstruction(new Elipses(0,0,0,0,true,Color.BLACK));
            }
            if (source == line){
                canvas.updateCurrentInstruction(new Line(0,0,0,0,Color.BLACK));
            }
        }
    }
}