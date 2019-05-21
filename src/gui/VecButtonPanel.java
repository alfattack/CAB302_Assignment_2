package gui;

import graphicsManage.Elipses;
import graphicsManage.Line;
import graphicsManage.Point;
import graphicsManage.Polygon;
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
    private JButton point;

    private Color fillColour = Color.BLACK;
    private Color color = Color.BLACK;

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
        point=createButton(new ImageIcon("src/gui/icons/point.png"));


        add(rectangle);
        add(rectangleFill);
        add(elipses);
        add(elipsesFill);
        add(polygon);
        add(polygonFill);
        add(line);
        add(point);
    }

    public JButton createButton(ImageIcon icon){
        JButton var = new JButton(icon);
        var.setBackground(Color.WHITE);
        var.addActionListener(new ButtonCommandListener());
        return var;
    }

    public void setPenColour(Color color){
        this.fillColour=color;
    }

    public void setFillColour(Color color){
        this.color= color;
    }


    private class ButtonCommandListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Component source = (Component) e.getSource();

            if (source == rectangle){
                canvas.updateCurrentInstruction(new Rectangle(color, fillColour, false));
            }
            if (source == rectangleFill) {
                canvas.updateCurrentInstruction(new Rectangle(color, fillColour, true));
            }
            if (source == elipses){
                canvas.updateCurrentInstruction(new Elipses(color, fillColour,false));
            }
            if (source == elipsesFill){
                canvas.updateCurrentInstruction(new Elipses(color, fillColour,true));
            }
            if (source == line){
                canvas.updateCurrentInstruction(new Line(color));
            }
            if (source == point){
                canvas.updateCurrentInstruction(new Point(color));
            }
            if (source == polygon){
                canvas.updateCurrentInstruction(new Polygon(color, fillColour,false));
            }
            if (source == polygonFill){
                canvas.updateCurrentInstruction(new Polygon(color, fillColour,true));
            }
        }
    }
}
