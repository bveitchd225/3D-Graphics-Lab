import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class EnginePanel extends JPanel {

    InputListener inputListener;
    ArrayList<Vector3> vertices;
    ArrayList<Face> faces;
    Mesh cube;
    Mesh cube1;
    int secondsLoop = 6;
    int frameNum;

    double cameraX, cameraY, cameraZ, cameraSpeed;

    public EnginePanel() {
        super();

        inputListener = new InputListener();
        this.addKeyListener(inputListener);

        frameNum = 0;
        cameraX = 0.0;
        cameraY = 0.0;
        cameraZ = 0.0;
        cameraSpeed = 2.0;

        cube = new Mesh("objects/cow.obj");
        cube.resize(40);
        // cube.move(0,0,-200);

    }

    public void createCube() {
        ArrayList<Vector3> vertices = new ArrayList<Vector3>();
        vertices.add(new Vector3( 50,  50, 50)); // v 0  right top
        vertices.add(new Vector3( 50, -50, 50)); // v 1  right bottom
        vertices.add(new Vector3(-50, -50, 50)); // v 2  left bottom
        vertices.add(new Vector3(-50,  50, 50)); // v 3  left top

        vertices.add(new Vector3( 50,  50, -50)); // v 0  right top
        vertices.add(new Vector3( 50, -50, -50)); // v 1  right bottom
        vertices.add(new Vector3(-50, -50, -50)); // v 2  left bottom
        vertices.add(new Vector3(-50,  50, -50)); // v 3  left top

        ArrayList<Face> faces = new ArrayList<Face>();

        // Front
        faces.add(new Face(3, 2, 1));
        faces.add(new Face(1, 0, 3));

        // Right
        faces.add(new Face(0,1,5));
        faces.add(new Face(5,4,0));

        // Back
        faces.add(new Face(4, 5, 6));
        faces.add(new Face(6, 7, 4));

        // Left
        faces.add(new Face(7, 6, 2));
        faces.add(new Face(2, 3, 7));

        // Top
        faces.add(new Face(7, 3, 0));
        faces.add(new Face(0, 4, 7));

        // Bottom
        faces.add(new Face(2, 6, 5));
        faces.add(new Face(5, 1, 2));    

        cube = new Mesh(vertices, faces);
    }

    public void updateCamera() {
        HashMap<String, Boolean> inputMap = inputListener.inputMap;
        if (inputMap.get("forward")) {
            cameraZ += cameraSpeed;
        }
        if (inputMap.get("left")) {
            cameraX -= cameraSpeed;
        }
        if (inputMap.get("backward")) {
            cameraZ -= cameraSpeed;
        }
        if (inputMap.get("right")) {
            cameraX += cameraSpeed;
        }
        if (inputMap.get("up")) {
            cameraY -= cameraSpeed;
        }
        if (inputMap.get("down")) {
            cameraY += cameraSpeed;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // updateCamera();
        
        Matrix combo = Matrix.getIdentityMatrix(4);
        combo = combo.mult(Matrix.getXRotationMatrix(1*frameNum));
        combo = combo.mult(Matrix.getYRotationMatrix(2*frameNum));
        combo = combo.mult(Matrix.getZRotationMatrix(3*frameNum));
        // cube.rotateX(1*frameNum);
        // cube.rotateY(2*frameNum);
        // cube.rotateZ(3*frameNum);

        cube.transform(combo);

        // Move to it's world space coordinate
        // cube.translate(-cameraX, cameraY, cameraZ);
        // cube.translate(100, 0,0);

        
        cube.draw(g);

        // g.drawString("Camera Pos: (" + cameraX+", "+cameraY+ ")",10,10);
        
        // g.drawString(""+frameNum, 750, 30);
        frameNum++;
        if (frameNum >= 60*secondsLoop) {
            frameNum = 0;
        }
    }
}
