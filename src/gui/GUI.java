package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * The primary class for the graphical user interface. Implements Runnable to run on even dispatch thread.
 */
public class GUI extends JFrame implements Runnable{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 500;

    // Central Panel which holds canvas.
    private JPanel canvasPanel;


    /**
     * Creates the GUI - called inside 'run' function.
     */
    public void createGUI(){
        setTitle("Vec Painter");
        setDefaultLookAndFeelDecorated(true);
        setSize(WIDTH, HEIGHT);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        canvasPanel = createCanvasPanel();
        add(canvasPanel, BorderLayout.CENTER);
        VecDrawButtonPanel drawButtonPanel = new VecDrawButtonPanel();
        VecConfigButtonPanel configButtonPanel = new VecConfigButtonPanel(drawButtonPanel);

        add(drawButtonPanel, BorderLayout.SOUTH);
        add(configButtonPanel, BorderLayout.EAST);
        setJMenuBar(new VecPainterMenuBar());
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeCanvas();
            }
        });
        setVisible(true);
    }

    /**
     * Creates Panel which houses the canvas.
     * @return The panel containing the canvas.
     */
    private JPanel createCanvasPanel(){
        JPanel canvasPanel = new JPanel();
        canvasPanel.setBackground(Color.LIGHT_GRAY);
        GridBagLayout layout = new GridBagLayout();
        canvasPanel.setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;

        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridheight=100;
        constraints.gridwidth=100;
        canvasPanel.add(VecCanvas.getCanvas(), constraints);
        return canvasPanel;
    }

    /**
     * Resize the canvas while maintaining square aspect to match parent panel.
     */
    private void resizeCanvas(){
        VecCanvas canvas = VecCanvas.getCanvas();

        double canvasWidth = (double) canvasPanel.getWidth() * 0.95;
        double canvasHeight = (double) canvasPanel.getHeight() * 0.95;

        System.out.println(canvasPanel.getWidth());

        if (canvasWidth > canvasHeight){
            canvasWidth=canvasHeight;
        }
        else{
            canvasHeight=canvasWidth;
        }
        canvas.setPreferredSize(new Dimension((int)canvasWidth,(int)canvasHeight));
        canvasPanel.repaint();
        canvasPanel.add(VecCanvas.getCanvas());
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new GUI());
    }

    /**
     * run method implemented to satisfy Runnable Interface. Calls createGUI to run on event dispatch thread.
     */
    @Override
    public void run() {
        createGUI();
    }
}