import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Mesh {
    private ArrayList<Vector3> originalVertices;
    private ArrayList<Vector3> translatedVertices;
    private ArrayList<Face> faces;

    public Mesh(ArrayList<Vector3> vertices, ArrayList<Face> faces) {
        this.originalVertices = new ArrayList<Vector3>();
        this.translatedVertices = new ArrayList<Vector3>();
        for (Vector3 v: vertices) {
            this.originalVertices.add(v);
            this.translatedVertices.add(v);
        }
        this.faces = new ArrayList<Face>();
        for (Face f: faces) {
            this.faces.add(f);
        }
    }

    public Mesh(String fileName) {
        originalVertices = new ArrayList<Vector3>();
        translatedVertices = new ArrayList<Vector3>();
        faces = new ArrayList<Face>();
        try {
            File file = new File(fileName);
            Scanner fileInput = new Scanner(file);
            while (fileInput.hasNext()) {
                String line = fileInput.next();
                if (line.equals("#")) {
                    fileInput.nextLine();
                    continue;
                }

                if (line.equals("v")) {
                    double x = fileInput.nextDouble();
                    double y = fileInput.nextDouble();
                    double z = fileInput.nextDouble();
                    originalVertices.add(new Vector3(x, y, z));
                    translatedVertices.add(new Vector3(x, y, z));
                }

                if (line.equals("f")) {
                    int f1 = fileInput.nextInt();
                    int f2 = fileInput.nextInt();
                    int f3 = fileInput.nextInt();
                    faces.add(new Face(f1-1, f2-1, f3-1));
                }
                
                
                // System.out.println(line);
                
            }
            fileInput.close();
        }
        catch (FileNotFoundException e) {
            
        }
    }

    public void resize(double scaleFactor) {
        for (int i = 0; i < originalVertices.size(); i++) {
            Vector3 o = originalVertices.get(i);
            originalVertices.set(i, new Vector3(scaleFactor*o.getX(), scaleFactor*o.getY(), scaleFactor*o.getZ()));
        }
        // return new Vector3(scaleFactor*o.getX(), scaleFactor*o.getY(), scaleFactor*o.getZ());
    }

    public void scale(double scaleFactor) {
        for (int i = 0; i < translatedVertices.size(); i++) {
            Vector3 o = translatedVertices.get(i);
            translatedVertices.set(i, new Vector3(scaleFactor*o.getX(), scaleFactor*o.getY(), scaleFactor*o.getZ()));
        }
        // return new Vector3(scaleFactor*o.getX(), scaleFactor*o.getY(), scaleFactor*o.getZ());
    }

    public void move(double dx, double dy, double dz) {
        for (int i = 0; i < originalVertices.size(); i++) {
            Vector3 o = originalVertices.get(i);
            // [1  0  0  dx]   [x]
            // |0  1  0  dy| * |y|
            // |0  0  1  dz|   |z|
            // [0  0  0  1 ]   [w]
            double xn = o.getX()*1 + o.getW()*dx;
            double yn = o.getY()*1 + o.getW()*dy;
            double zn = o.getZ()*1 + o.getW()*dz;
            double wn = o.getY()*1;
            originalVertices.set(i, new Vector3(xn, yn, zn, wn));
        }
    }

    public void transform(Matrix m) {
        for (int i = 0; i < translatedVertices.size(); i++) {
            Vector3 o = translatedVertices.get(i);
            
            translatedVertices.set(i, o.mult(m));
        }
    }


    public void rotateZ(double degrees) {
        double r = Math.toRadians(degrees);
        for (int i = 0; i < translatedVertices.size(); i++) {
            Vector3 o = translatedVertices.get(i);
            
            
            // [cos(r) -sin(r)   0]   [x]   [xn]
            // |sin(r)  cos(r)   0| * |y| = |yn|
            // [  0       0      1]   [z]   [zn]

            //   [cos(r)]     [-sin(r)]     [0]   [xn]
            // x*|sin(r)| + y*| cos(r)| + z*|0| = |yn|
            //   [  0   ]     [   0   ]     [1]   [zn]

            // [x*cos(r)]   [y*-sin(r)]   [z*0]   [xn]
            // |x*sin(r)| + |y* cos(r)| + |z*0| = |yn|
            // [x*  0   ]   [y*   0   ]   [z*1]   [zn]

            double xn = o.getX()*Math.cos(r) + o.getY()*-Math.sin(r);
            double yn = o.getX()*Math.sin(r) + o.getY()*Math.cos(r);
            double zn = o.getZ();
            translatedVertices.set(i, new Vector3(xn, yn, zn));
        }
    }

    public void rotateY(double degrees) {
        double r = Math.toRadians(degrees);
        for (int i = 0; i < translatedVertices.size(); i++) {
            Vector3 o = translatedVertices.get(i);
            // [ cos(r)   0   sin(r)]   [x]   [xn]
            // |   0      1     0   | * |y| = |yn|
            // [-sin(r)   0   cos(r)]   [z]   [zn]

            //   [ cos(r)]     [0]     [ sin(r)]   [xn]
            // x*|   0   | + y*|1| + z*|   0   | = |yn|
            //   [-sin(r)]     [0]     [ cos(r)]   [zn]

            // [x* cos(r)]   [y*0]   [z*sin(r)]   [xn]
            // |x*   0   | + |y*1| + |z*  0   | = |yn|
            // [x*-sin(r)]   [y*0]   [z*cos(r)]   [zn]

            double xn = o.getX()*Math.cos(r) + o.getZ()*Math.sin(r);
            double yn = o.getY();
            double zn = o.getX()*-Math.sin(r) + o.getZ()*Math.cos(r);
            translatedVertices.set(i, new Vector3(xn, yn, zn));
        }
    }

    public void rotateX(double degrees) {
        double r = Math.toRadians(degrees);
        for (int i = 0; i < translatedVertices.size(); i++) {
            Vector3 o = translatedVertices.get(i);
            //     [   1             0             0   ]   [x]   [xn]
            //     |   0           cos(r)       -sin(r)| * |y| = |yn|
            //     [   0           sin(r)        cos(r)]   [z]   [zn]

            //     [   1   ]     [   0   ]     [   0   ]   [xn]
            //   x*|   0   | + y*| cos(r)| + z*|-sin(r)| = |yn|
            //     [   0   ]     [ sin(r)]     [ cos(r)]   [zn]

            //   [x*   1   ]   [y*   0   ]   [z*   0   ]   [xn]
            //   |x*   0   | + |y* cos(r)| + |z*-sin(r)| = |yn|
            //   [x*   0   ]   [y* sin(r)]   [z* cos(r)]   [zn]

            double xn = o.getX();
            double yn = o.getY()*Math.cos(r) + o.getZ()*-Math.sin(r);
            double zn = o.getY()*Math.sin(r) + o.getZ()* Math.cos(r);
            translatedVertices.set(i, new Vector3(xn, yn, zn));
        }
    }


    public void translate(double dx, double dy, double dz) {
        for (int i = 0; i < translatedVertices.size(); i++) {
            Vector3 o = translatedVertices.get(i);
            // [1  0  0  dx]   [x]
            // |0  1  0  dy| * |y|
            // |0  0  1  dz|   |z|
            // [0  0  0  1 ]   [w]
            double xn = o.getX()*1 + o.getW()*dx;
            double yn = o.getY()*1 + o.getW()*dy;
            double zn = o.getZ()*1 + o.getW()*dz;
            double wn = o.getY()*1;
            translatedVertices.set(i, new Vector3(xn, yn, zn, wn));
        }
    }


    public void draw(Graphics g) {
        for (Face face: faces) {
            face.draw(g, translatedVertices, false);
        }
        for (int i = 0; i < originalVertices.size(); i++) {
            translatedVertices.set(i, originalVertices.get(i));
        }
    }
}
