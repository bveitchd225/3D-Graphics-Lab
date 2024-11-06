import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.event.MouseInputListener;

// Input Setup
        // InputListener input = new InputListener();
        // this.addKeyListener(input);
        // this.addMouseListener(input);
        // this.addMouseMotionListener(input);

public class InputListener implements MouseInputListener, KeyListener  {

    public HashMap<String, Boolean> inputMap = new HashMap<>();
    public InputListener() {
        inputMap.put("forward", false);
        inputMap.put("left", false);
        inputMap.put("backward", false);
        inputMap.put("right", false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            inputMap.put("forward", true);
        }
        else if (e.getKeyChar() == 'a') {
            inputMap.put("left", true);
        }
        else if (e.getKeyChar() == 's') {
            inputMap.put("backward", true);
        }
        else if (e.getKeyChar() == 'd') {
            inputMap.put("right", true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            inputMap.put("forward", false);
        }
        else if (e.getKeyChar() == 'a') {
            inputMap.put("left", false);
        }
        else if (e.getKeyChar() == 's') {
            inputMap.put("backward", false);
        }
        else if (e.getKeyChar() == 'd') {
            inputMap.put("right", false);
        }
    }
    
}
