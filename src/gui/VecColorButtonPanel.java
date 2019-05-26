package gui;

import graphicsManage.DrawableVector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Button Panel relating to color selection for fill and pen.
 */
public class VecColorButtonPanel extends JPanel implements ActionListener {
    private JButton color;
    private JButton fillColor;
    private VecCommandButtonPanel drawButtonPanel;


    private JPanel fillIndicator;
    private JPanel brushIndicator;

    VecCanvas canvas;


    /**
     * Constructor of panel. VecCommandButtonPanel passed to update colour selections.
     * @param drawButtonPanel GUI panel with command buttons.
     */
    public VecColorButtonPanel(VecCommandButtonPanel drawButtonPanel){

        this.drawButtonPanel = drawButtonPanel;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        canvas = VecCanvas.getCanvas();
        color = createButton(new ImageIcon("src/gui/icons/paintBrush.png"));
        fillColor = createButton(new ImageIcon("src/gui/icons/paintBucket.png"));

        fillIndicator = new JPanel();
        fillIndicator.setBackground(Color.BLACK);
        brushIndicator = new JPanel();
        brushIndicator.setBackground(Color.BLACK);

        this.add(brushIndicator);
        this.add( Box.createVerticalGlue() );
        this.add(color);
        this.add(fillColor);
        this.add( Box.createVerticalGlue() );
        this.add(fillIndicator);
    }


    /**
     * Creates a button using an ImageIcon
     * @param icon
     * @return
     */
    private JButton createButton(ImageIcon icon){
        JButton var = new JButton(icon);
        var.setBackground(Color.WHITE);
        var.addActionListener(this);
        return var;
    }


    /**
     * Button pressed event, updates colours for VecCommandButtonPanel.
     * @param e event object.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Component source = (Component) e.getSource();

        if (source == fillColor){
            Color newColor = JColorChooser.showDialog(null, "Choose Fill Color.",drawButtonPanel.getFillColor());
            fillIndicator.setBackground(newColor);
            drawButtonPanel.setFillColor(newColor);

            DrawableVector instruction = canvas.getCurrentInstruction();

            if (instruction != null){
                instruction.setFillColor(newColor);
            }
        }

        if (source == color){
            Color newColor = JColorChooser.showDialog(null, "Choose Pen Color.",drawButtonPanel.getPenColor());
            brushIndicator.setBackground(newColor);
            drawButtonPanel.setPenColor(newColor);

            DrawableVector instruction = canvas.getCurrentInstruction();

            if (instruction != null){
                instruction.setColor(newColor);
            }
        }


    }
}
