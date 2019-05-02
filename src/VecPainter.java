import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;


public class GraphicManager {

    private class Line extends JComponent implements drawing {
        private int x1, x2, y1, y2;
        private Line(int x1, int x2, int y1, int y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }
        public void paint(Graphics2D g) {
            g.drawLine(x1, y1, x2, y2);
        }
    }



    public drawing ()
}

public class VecPainter {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setBounds(100, 100, 300, 300);
        window.setVisible(true);
        window.getContentPane().;
    }

    public static JFrame setupDrawingWindow(){

    }


}
