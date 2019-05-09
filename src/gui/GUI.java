package gui;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

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
    private JMenuItem clear;

    public GUI() {
        super("Vec Painter");
        setDefaultLookAndFeelDecorated(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel canvasPanel = new JPanel();
        canvasPanel.setLayout(new GridBagLayout());

        add(VecCanvas.getCanvas(), BorderLayout.CENTER);

        JPanel buttonPanel = new VecButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
        setJMenuBar(new VecPainterMenuBar());
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setVisible(true);
    }
}