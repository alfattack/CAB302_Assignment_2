package gui;

import graphicsManage.Elipses;
import graphicsManage.Line;
import graphicsManage.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class VecButtonPanel extends JPanel {

    private VecCanvas canvas;
    private JButton rectangle;
    private JButton rectangleFill;
    private JButton elipses;
    private JButton elipsesFill;
    private JButton line;
    private JButton polygon;
    private JButton polygonFill;

    VecButtonPanel(){
        setLayout(new FlowLayout());
        canvas = VecCanvas.getCanvas();

        rectangle = createButton(new ImageIcon("src/gui/icons/rectangleBlank.png"));
        rectangleFill = createButton(new ImageIcon("src/gui/icons/rectangleFill.png"));
        elipses = createButton(new ImageIcon("src/gui/icons/elipsesBlank.png"));
        elipsesFill = createButton(new ImageIcon("src/gui/icons/elipsesFill.png"));
        line = createButton(new ImageIcon("src/gui/icons/line.png"));
        polygon=createButton(new ImageIcon("src/gui/icons/polygon.png"));
        polygonFill=createButton(new ImageIcon("src/gui/icons/polygonFill.png"));


        add(rectangle);
        add(rectangleFill);
        add(elipses);
        add(elipsesFill);
        add(polygon);
        add(polygonFill);
        add(line);
    }

    public JButton createButton(ImageIcon icon){
        JButton var = new JButton(icon);
        var.setBackground(Color.WHITE);
        var.addActionListener(new ButtonCommandListener());
        return var;
    }


    private class ButtonCommandListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Component source = (Component) e.getSource();

            if (source == rectangle){
                canvas.updateCurrentInstruction(new Rectangle(Color.BLACK,Color.GREEN,true));
            }
            if (source == elipses){
                canvas.updateCurrentInstruction(new Elipses(Color.BLACK,Color.RED,true));
            }
            if (source == line){
                canvas.updateCurrentInstruction(new Line(Color.BLACK));
            }

            if (source == elipsesFill){
                Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.RED);
            }
        }
    }
}
