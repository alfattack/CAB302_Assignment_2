package gui;

import graphicsManage.DrawableVector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VecConfigButtonPanel extends JPanel implements ActionListener {
    private JButton color;
    private JButton fillColor;
    private VecDrawButtonPanel drawButtonPanel;


    private JPanel fillIndicator;
    private JPanel brushIndicator;

    VecCanvas canvas;


    public VecConfigButtonPanel(VecDrawButtonPanel drawButtonPanel){

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



    private JButton createButton(ImageIcon icon){
        JButton var = new JButton(icon);
        var.setBackground(Color.WHITE);
        var.addActionListener(this);
        return var;
    }


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
