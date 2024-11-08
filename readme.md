# Lab XXA
In this lab we will be implementing a very basic "3D Engine". This will take quite a bit of work but the end goal will be rendering an OBJ file and being able to move around with the keyboard and look around with the mouse.

## Table of Contents
### Chapter 1: Foundations
#### Basic Window
- Create a `Main.java` as entry point to program
- Create a `JFrame` window
- Implement our own `JFrame` and add it to `JFrame`
  - Override `paintComponent`
  - Call `drawLine` and `fillOval`
#### Vector3 class
- Create a Vector3 class
    - 3 double instance variables `x`, `y`, `z`
    - 1 constructor `Vector3(double x, double y, double z)`
    - 1 method to draw circle at location `void draw(Graphics g)`
- Create an `ArrayList` of `Vector3`s
- Create 4 `Vector3`s that form a square centered at (0,0) with z value of 0
- Draw `Vector3` points to the screen from `ArrayList`
#### Face class
- Create a `Face` class
    - 3 int instance variables `p1`, `p2`, and `p3`.
        - These will be the indices of the vertices in the `ArrayList`
        - This will be very important later, do not store `Vector3`s
    - 1 constructor `Face(int p1, int p2, int p3)`
    - 1 method `draw(Graphics g, ArrayList<Vector3>)`
        - Draws lines connecting the `Vector3`
- Create an `ArrayList` of `Face`s connecting the `Vector3`s
- Draw `Face`s from `ArrayList`.

### Chapter 2: Adding the 3rd dimension
#### Updating the Screen
- Create an infinite loop to constantly update the screen
- Implement it to lock to a consistent 60 frames per second.

#### Transforming Vertices
- Add 4 new `Vector3`s  to create a cube centered at (0,0,0)
- Create Matrix class
    - stores values in a row major 2D array of doubles
- Scaling Matrix
- Rotation Matrix

#### Adding a 4th dimension?
- Translation Matrix
    - Add a hidden 4th variable to Vector3, `w`
- Add the ability to render with perspective using `w`

### Chapter 3: Adding `.obj` support and User Input
### Rendering OBJ Files
- Creating `.obj` Files
- Create `Mesh3D` class
- Importing OBJ Files to create `Mesh3D` Objects
### Inputs
- Create `InputListener`
    - extends `KeyListener`
    - extends `MouseInputListener`
- Use `w`, `a`, `s`, `d` to move around in 3D space
- Use the `MouseInputListener` to move "camera"

### Chapter 4: Optimization and Lighting
- Calculate the normal of a face
- Use the dot product to calculate 
- Lighting
- Optimization