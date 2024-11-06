import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

public class Face {
    private int v1i, v2i, v3i;

    public Face(int v1i, int v2i, int v3i) {
        this.v1i = v1i;
        this.v2i = v2i;
        this.v3i = v3i;
    }

    public void draw(Graphics g, ArrayList<Vector3> vertices, boolean fill) {
        Vector3 v1 = vertices.get(v1i);
        Vector3 v2 = vertices.get(v2i);
        Vector3 v3 = vertices.get(v3i);
        if (fill) {
            g.fillPolygon(new Polygon(new int[]{v1.getScreenX(), v2.getScreenX(), v3.getScreenX()}, 
                                     new int[]{v1.getScreenY(), v2.getScreenY(), v3.getScreenY()}, 
                                     3));
        }
        else {
            g.drawPolygon(new Polygon(new int[]{v1.getScreenX(), v2.getScreenX(), v3.getScreenX()}, 
                                     new int[]{v1.getScreenY(), v2.getScreenY(), v3.getScreenY()}, 
                                     3));
        }
    }
}
