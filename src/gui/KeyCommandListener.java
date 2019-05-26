package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * Listens to key commands and manipulates canvas.
 */
public class KeyCommandListener extends KeyAdapter {
    private VecCanvas canvas = VecCanvas.getCanvas();

    /**
     * Event when a key is pressed.
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        System.out.println(e.isControlDown());

        if ((e.getKeyCode() == 90) && (e.isControlDown())){
            canvas.undo();
            canvas.repaint();
        }
    }
}
