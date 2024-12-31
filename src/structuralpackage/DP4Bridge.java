// 1. Define the "Abstraction" interface (independent of implementation details)
interface Shape {
    void draw(); // High-level operation
}

// 2. Define the "Implementor" interface (bridge for different implementations)
interface Color {
    void fillColor(); // Low-level operation
}

// 3. Provide concrete implementations of the Implementor
class RedColor implements Color {
    @Override
    public void fillColor() {
        System.out.println("Filling with red color.");
    }
}

class BlueColor implements Color {
    @Override
    public void fillColor() {
        System.out.println("Filling with blue color.");
    }
}

// 4. Extend the Abstraction by using the Implementor
abstract class AbstractShape implements Shape {
    protected Color color; // Bridge to the Implementor

    // Constructor to link the Implementor
    public AbstractShape(Color color) {
        this.color = color;
    }
}

// 5. Concrete Abstractions that extend the high-level abstraction
class Circle extends AbstractShape {
    public Circle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.print("Drawing a Circle - ");
        color.fillColor(); // Delegate the color operation
    }
}

class Square extends AbstractShape {
    public Square(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.print("Drawing a Square - ");
        color.fillColor(); // Delegate the color operation
    }
}

// 6. Client code to demonstrate the Bridge pattern
public class DP4Bridge {
    public static void main(String[] args) {
        // Create different color implementations
        Color red = new RedColor();
        Color blue = new BlueColor();

        // Create shapes with different colors
        Shape redCircle = new Circle(red);
        Shape blueSquare = new Square(blue);

        // Draw shapes
        redCircle.draw();
        blueSquare.draw();
    }
}
