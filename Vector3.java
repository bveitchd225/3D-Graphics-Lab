import java.awt.Graphics;

public class Vector3 {

    private double x, y, z, w;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 1;
    }

    public Vector3(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }
    public double getW() {
        return w;
    }

    public Vector3 copy() {
        return new Vector3(x, y, z);
    }

    public int getScreenX() {
        return (int) (this.x + 800/2);
    }

    public int getScreenY() {
        return (int) (-this.y + 600/2);
    }

    public void draw(Graphics g) {
        // Need (0,0) to be in middle of screen
        g.fillOval(getScreenX()-4, getScreenY()-4, 8, 8);
    }
}
