# Lab XXA
In this lab we will be implementing a very basic "3D Engine". This will take quite a bit of work but the end goal will be rendering an OBJ file and being able to move around with the keyboard and look around with the mouse.

## Table of Contents
### Foundations
- Create a Vector3 class
    - `x`, `y`, `z`
    - `Vector3(double x, double y, double z)`
    - `Vector3 mult(Vector3 other)`
    - `void draw(Graphics g)`
- Implement a simple Swing GUI
    - create `JFrame`
    - create `JPanel`
- Draw Vector3 points to the screen
- Create a Face class to connect Vector3 points

# Updating the Screen
- Create an infinite loop to constantly update the screen
- Implement it to lock to a consistent 60 frames per second.

### Transforming Vertices
- Scaling
- Rotation
- Translation
    - Add a hidden 4th variable to Vector3, `w`
- Add the ability to render with perspective rather than isomorphic

### Inputs
- Create `InputListener`
    - extends `KeyListener`
    - extends `MouseInputListener`
- Use `w`, `a`, `s`, `d` to move around in 3D space
- Use the `MouseInputListener` to move "camera"

### Rendering OBJ Files
- Creating `.obj` Files
- Create `Mesh3D` class
- Importing OBJ Files to create `Mesh3D` Objects

### and Beyond
- Lighting
- Optimization



# Preface
Starting from nothing is a *daunting* task but rewarding when everything starts coming together. Much of what we do has been implemented before, this will be *our* solution which we'll be able to tinker with.

We'll occassionally make "mistakes" where we'll have to go back and modify or add something. This is by design to show a lot of the "why"s behind some of the systems that are happening behind the scenes. If you ever choose to make a 3D engine in the future in another language or just for fun, hopefully you'll have the foresight to avoid some of the common mistakes programmers make when creating a 3D engine.

This will be a multi-part lab that will have plenty of checkpoints to check in with your teacher or reflect on what you've written so far. Since you'll most likely not be implementing this all at once, I'd recommend to add comments throughout your code so when you come back to your code that you haven't seen in a while, you can get back up to speed.

# JPanel and JFrame
## How to draw to the screen
Unfortunately we'll start off by saying we won't learn how to have our Operating System (OS) communicate with the screen to turn pixels on and off or set colors. This is a complicated task that is typically left to engineers designing the displays. 

It's also a difficult task to have our code tell the OS what to draw to the screen as well. Luckily Java includes a basic Graphics Library called Swing that has some useful classes such as `JFrame` and `JPanel`. We're using Swing as it's included in the standard Java Library meaning if you install Java, you have Swing. There are other options out there but it requires us to download libraries and proprely import them into our code.

So far in our class, we've been using some code written specifically for GBS students that makes it easy for us to draw to the screen. There's not too much setup to achieve this from nothing so that's what we're going to do now.

## Creating our entry point
When writing a Java program, there needs to be a `static` `void` method named specifically named `main` that takes in a `String` array as an explicit parameter in some `*.java` file. Start by creating a `Main.java` file and create a `Main` class with a `public static void main` method. In our `main` method, let's add a `System.out.println("Hello World!");` to make sure our program starts.

<details>
  <summary>Example Main File</summary>
  
```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
```
</details>

## Creating our JFrame
The first elemtn

# Foundations
## Create `Vector3`
We'll start off with creating a class for a 3D point in space that we'll call `Vector3`.  Our `Vector3` class will have three `double` instance variables `x`, `y`, and `z`. Start by creating a class with these instance variables and a constructor.

## Draw `Vector3`
Next we'll eventually want to draw these `Vector3`s as points in space. Create a `public void` method called `draw` that will take in one explicit parameter `Graphics g`. In the method, we're going to use the `Graphics` object `g` to draw a circle to the screen at the position `this.x` and `this.y`. There is no `fillCircle` method so we'll need to use the `fillOval` method with a matching `width` and `height` (I've set the width and height to 8, but feel free to adjust for preference). Since `this.x` and `this.y` are `double`s and `fillOval` needs `int`s for the location of the top-left of the "oval", we'll need to cast them to `int`s in the method call. Since we want our circle to be drawn centered on this point, we'll also want to subtract half the width and height from the x and y so it's centered.

## Creating our `JFrame`
Something that has been done for you most of the time is dealing with Java's built in GUI library `swing`. The `JFrame` acts like a "picture frame" that will hold any elements we put inside of it. This could be `JButton`s, `JTextField`s, or `JTextArea`. In our case, after we have our `JFrame`, we're going to create our own version of a `JPanel` which will allow us to `Override` the method `paintComponent`. This will give us the tools to draw lines, rectangles, and ovals.

First let's create a new file named `Main.java` which will be entry point of our program. Create a static main method and test it with a simple `System.out.println("Hello World!");` to make sure everything is typed in correctly.

Remove the print statement and add the following code to your `main` method to get a window on the screen. 
```java
JFrame f = new JFrame();
// includes bar on top, hence +28
f.setSize(800, 628); 
// opens JFrame in middle of screen
f.setLocationRelativeTo(null); 
// Let's us end program by closing JFrame
f.setDefaultCloseOperation(3); 
// Needed, stays hidden by default
f.setVisible(true); 
```

Your `Main.java` file should look something like this:
```java
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(800, 628);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(3);
        f.setVisible(true);
    }
}
```

## Creating `EnginePanel`
Now that we have our `JFrame` to hold elements, we're going to create our own version of a `JPanel` so we can control what happens when we paint to the screen.

Create a new file `EnginePanel.java` and create a new public class called `EnginePanel`. After the name `EnginePanel`, type `extends JPanel`. This is how we can "extend" the functionality of another class already made. Create a constructor and call `super()` on the first line of the constructor.

Create a new `public void` method called `paintComponent` that takes in one explicit parameter `Graphics g`. On the first line of this method, you should call `super.paintComponent(g)`.

To make sure we're drawing to the screen, I like to do a pseudo "Hello World!" by drawing a rectangle at x = 10, y = 20, w = 30, h = 40 using `g.fillRect`.

Your `EnginePanel.java` file should look something like this:
```java
import java.awt.Graphics;
import javax.swing.JPanel;

public class EnginePanel extends JPanel {

    public EnginePanel() {
        super();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(10,20,30,40);
    }
}
```

## Using `EnginePanel`
Go back to your `Main.java` and after creating a `JFrame`, let's make an instance of our new `EnginePanel`. On the next line, we need to add our `EnginePanel` to our `JFrame` object by calling the `add` method off of our `JFrame` object. If our `JFrame` object is called `f` and our `EnginePanel` object is called `p`, then we can add `p` to `f` by typing `f.add(p)`.

Run the program and you should see our Rectangle object on the screen!

# Drawing Vertices
It's been a while but it's finally time to use our `Vector3` class to draw points to the screen.

Our end goal is 3D but for now we're going to leave our `z` component as `0`. This means we'll effectively be working in 2D.

*Most of our work will be done in `EnginePanel.java` so feel free to close `Main.java` as we won't be using it.*

Let's create a new `Vector3` variable `p1` with the coordinate (50,50,0). Then, in your `paintComponent` method, call `p1.draw(g)` to draw the point to the screen. Hopefully you'll see your point in the top left of the screen.

```java
import java.awt.Graphics;
import javax.swing.JPanel;

public class EnginePanel extends JPanel {

    Vector3 p1;

    public EnginePanel() {
        super();
        p1 = new Vector3(50, 50, 0);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        p1.draw(g);
    }
}
```


## Screen space coordinates
When working in 2D, the origin (0,0) is at the top left of the screen, with the y axis upside down (+y is going down). In 3D, the origin is typically in the center of the screen with the y axis going up, the x axis going to the right, and the z axis going out of the screen (towards you). As a useful aide, the "right hand rule" helps us remember which way is which.

In order to set us up for success, we're going to apply a **transformation** to our points to go from their real coordinates, to what their coordinates are on the screen's wonky axis setup.

This can be done by creating two new `int` methods in our `Vector3` class, `getScreenX` and `getScreenY`. Since they're returning ints, we'll be able to handle the casting needed for the `drawOval` call in `draw`.

```java
public int getScreenX() {
    return (int) (this.x + 800/2);
}

public int getScreenY() {
    return (int) (-this.y + 600/2);
}
```

## Moar Points!
If you can imagine the x and y axis on the screen, hopefully the point's location (50,50,0) makes sense. Let's add three more points, `p2`, `p3`, and `p4` so the corners of a square are drawn.

```java
import java.awt.Graphics;
import javax.swing.JPanel;

public class EnginePanel extends JPanel {

    Vector3 p1, p2, p3, p4;

    public EnginePanel() {
        super();
        p1 = new Vector3(50, 50, 0);
        p2 = new Vector3(50, -50, 0);
        p3 = new Vector3(-50, 50, 0);
        p4 = new Vector3(-50, -50, 0);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        p1.draw(g);
        p2.draw(g);
        p3.draw(g);
        p4.draw(g);
    }
}
```

## Plan for the future
As you might be able to tell, the more points there are, the more tedious it becomes to declare all of those variables at the top, instantiate all of them in our constructor, and then draw each one individually. We won't fix all of our problems, but we can simplify some things and it will also set us up nicely for the next part.

When storing a lot of values like this, it can be helpful to store them in a data structure. There are many data structures in Java but an easy to use one will be the ArrayList. An ArrayList allows us to store a bunch of values in one variable. This lets us declare one variable and then create a for-each loop to go through all of the points and draw them.

```java
import java.awt.Graphics;
import javax.swing.JPanel;

public class EnginePanel extends JPanel {

    ArrayList<Vector3> vertices;

    public EnginePanel() {
        super();
        
        vertices = new ArrayList<Vector3>();
        vertices.add(new Vector3(50, 50, 0));  // right top
        vertices.add(new Vector3(50, -50, 0)); // right bot
        vertices.add(new Vector3(-50, 50, 0)); // left bot
        vertices.add(new Vector3(-50, -50, 0));// left top
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Vector3 v: vertices) {
            v.draw(g);
        }
    }
}
```

## `Face` class
A face is when we draw a triangle to connect three `Vector3`s together. So when we create a `Face`, we'll want to know which `Vector3`s to connect together. This may seem weird but it will be helpful later, we're going to have a Face store the indices of it's `Vector3`s instead of references to the actual `Vector3`s.

Start by creating a `Face` class with three `int` variables. Define a constructor to intialize the instance variables and create a `public void draw(Graphics g, ArrayList<Vector3> vertices)` method that will use the `drawLine` method three times to draw the three sides of the triangle. The `Face` class stores the indices, and we'll pass in the vertices that we'll use the indices on.

## Creating the `Face`s of our square
Back in `EnginePanel`, let's create 2 faces, one connecting the top left corner, to the bottom left corner, to the bottom right corner and the other connecting the bottom right corner, to the top right corner, to the top left corner. This order will be important later.

```java
import java.awt.Graphics;
import javax.swing.JPanel;

public class EnginePanel extends JPanel {

    ArrayList<Vector3> vertices;
    Face f1, f2;

    public EnginePanel() {
        super();
        
        vertices = new ArrayList<Vector3>();
        vertices.add(new Vector3(50, 50, 0));  // right top
        vertices.add(new Vector3(50, -50, 0)); // right bot
        vertices.add(new Vector3(-50, 50, 0)); // left bot
        vertices.add(new Vector3(-50, -50, 0));// left top

        f321 = new Face(3, 2, 1);
        f103 = new Face(1, 0, 3);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Vector3 v: vertices) {
            v.draw(g);
        }

        f321.draw(g, vertices);
        f103.draw(g, vertices);
    }
}
```

# Updating the screen
It's time for some motion! We called our `JPanel` an engine but right now our "engine" draws the scene once and then never again. Essentially our train engine hasn't left the station! It's just sitting there.

In order to see some motion, we'll need to make it so our paintComponent is drawn many times a second. Right now, it paints our vertices and faces to the screen once and then sits there doing nothing.

Most screens when this is written (2024), are 60hz, meaning they update what's on screen 60 times in one second. It's common in games for this to be much higher (120hz, 144hz, 240hz, all the way up to 540hz). 

The unit for framerate is `frames/seconds` but another useful measure is frametime which is the inverse `seconds/frames`. As they're the inverse of each other, they naturally have an inverse relationship :O. 

|Framerate (frames/s)|Frametime (ms/frame)|
|-|-|
|30|33.3ms|
|60|16.7ms|
|120|8.3ms|
|144|7ms|
|240|4.2ms|
|540|1.9ms|

*I know it can be difficult to get excited about a table of values, but think about the fact that a computer has to completely draw an entire 3D game scene in 1.9ms! Just to emphasize, there are 1000ms in 1 second. When you read this, about 9 seconds has passed which means at 540hz, the computer has drawn a scene 5000 times.*

All of this to say that if we wanted to double the framerate to 120hz, we'd have half the time to do it. Later one when rendering complex objects, we might struggle to hit the time budget of 8.3ms. So for now we'll settle on 16.7ms as our time budget and we'll explore later on how long it actually takes to draw everything!

## Actually updating the screen
History lesson over. Currently, we're barely doing anything in our code so our program would probably run at a very high framerate (again, less time, more frames!). To avoid this, we're going to lock our framerate at 60 frames per second (fps)

A common amateur solution is to just do everything we need to do (calculations, actually drawing to the buffer) and then just wait (1000/60) = 16.7ms until we do it all again. the only issue is the calculations and drawing actually took time, so one frame it took 1.3ms to calculate everything and then we waited 16.7ms so we actually draw another picture 18ms later which means instead of 60fps, we'd really be doing 1000/18 = 55.6fps which will make the program feel like it's dragging or slowing down.

So no matter how long it takes to calculate, we want to wait 16.7ms. If it takes 1ms to calculate and draw, we want to wait 15.7ms. If it takes 10ms to calculate and draw, we only want to wait 6.7ms. 

Before we start drawing we want to record when we start calculating and drawing. Once we're done calcualting and drawing, we'll record the time again and see the difference in time. Now all we have to do is wait a certain amount of time so the difference and our wait time add to 16.7ms.

This is *a lot* so I'm generously going to give you the code that will do this for us :). Add the following to the end of you `main` method in `Main.java`.

```java
long frameTime = 1000000000/FRAME_RATE;
while (true) {
    long startTime = System.nanoTime();

    p.repaint(); // Here's our calculation/draw call

    long drawTime = (System.nanoTime() - startTime);
    if (drawTime < frameTime) {
        try {
            Thread.sleep((frameTime - drawTime)/1000000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
```
Make sure to save `Main.java` and then close it as we won't use for implementing any more engine features.

Until we start adding some user input, to make motion easier to see, we're going to create a looping animation. To do this we need two new variables in our `EnginePanel` class, `frameNum` and `secondsLoop`. `secondsLoop` will determine how long our animation will loop in seconds. `frameNum` will tell us which frame we're in in that looping animation. 

We'll start with `secondsLoop` as `6` for 6 seconds meaning at 60 frames per second, our animation will be 360 frames.

We'll also use `drawString` to display the current frameNum when we're viewing the animation.

# Transformations
There are three main transformations that we can do, Scaling, Rotation, and Translations. The order may seem random but it's in order of difficulty to implement.

## Scaling
The math underneath the hood is linear algebra, a very useful subset of mathematics for 3D graphics. Without a fundamental knowledge of linear algebra, it may be difficult to fully understand where some of our formulas are coming from.

Right now, we have a bunch of vertices sitting in our `ArrayList` `vertices`. Our goal is to not change these vertices, but *transform* them before we draw them. 
