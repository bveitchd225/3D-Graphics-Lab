import javax.swing.JFrame;

public class Main {

    public static int SCREEN_WIDTH = 800;
    public static int SCREEN_HEIGHT = 600;
    public static int FRAME_RATE = 60;

    public static void main(String[] args) {
        JFrame f = new JFrame();
        EnginePanel p = new EnginePanel();
        p.setFocusable(true);
        p.requestFocusInWindow();
        f.setSize(SCREEN_WIDTH, SCREEN_HEIGHT + 28);
        f.add(p);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(3);
        f.setVisible(true);

        long frameTime = 1000000000/FRAME_RATE;
        while (true) {
            long startTime = System.nanoTime();

            p.repaint(); // Here's our calculation/draw call

            long drawTime = (System.nanoTime() - startTime);
            System.out.print("\r" + drawTime/1000000.0 + "ms");
            if (drawTime < frameTime) {
                try {
                    Thread.sleep((frameTime - drawTime)/1000000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}