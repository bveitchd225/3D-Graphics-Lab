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

    public Vector3 mult(Matrix m) {
        double[][] values = m.getValues();
        Vector3 newVector = new Vector3(
            x*values[0][0] + y*values[0][1] + z*values[0][2] + w*values[0][3], 
            x*values[1][0] + y*values[1][1] + z*values[1][2] + w*values[1][3], 
            x*values[2][0] + y*values[2][1] + z*values[2][2] + w*values[2][3],
            x*values[3][0] + y*values[3][1] + z*values[3][2] + w*values[3][3]
        );
        return newVector;
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
