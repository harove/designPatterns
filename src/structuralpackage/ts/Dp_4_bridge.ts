// ------------------------------------------------------------
// Bridge Design Pattern Example
// ------------------------------------------------------------
// The Bridge Pattern:
// - Decouples an abstraction (e.g., Shape) from its implementation (e.g., Color).
// - Both the abstraction and implementation can evolve independently.
// - Promotes flexibility and scalability by favoring composition over inheritance.

// ------------------------------------------------------------
// Step 1: Define the Abstraction (Shape)
// ------------------------------------------------------------
// The "Shape" interface defines high-level operations that depend on low-level details (Color).
// SOLID Principle: Dependency Inversion Principle (DIP)
// - Shape depends on the abstraction (Color) instead of concrete implementations.

interface Shape {
    draw(): void; // High-level operation to draw a shape
}

// ------------------------------------------------------------
// Step 2: Define the Implementor (Color)
// ------------------------------------------------------------
// The "Color" interface defines the low-level operations (filling with color)
// that can vary independently from the Shape abstraction.
// SOLID Principle: Interface Segregation Principle (ISP)
// - The interface is specific and focused, providing only what is required for colors.

interface Color {
    fillColor(): void; // Low-level operation to fill a shape with color
}

// ------------------------------------------------------------
// Step 3: Implement Concrete Implementors (Color)
// ------------------------------------------------------------
// Concrete implementations of the "Color" interface provide specific behaviors for filling shapes with colors.
// SOLID Principle: Open-Closed Principle (OCP)
// - New colors can be added without modifying existing classes.

class RedColor implements Color {
    fillColor(): void {
        console.log("Filling with red color.");
    }
}

class BlueColor implements Color {
    fillColor(): void {
        console.log("Filling with blue color.");
    }
}

// ------------------------------------------------------------
// Step 4: Extend the Abstraction (AbstractShape)
// ------------------------------------------------------------
// The "AbstractShape" class acts as a bridge between the Shape abstraction and the Color implementation.
// It links high-level Shape behavior with low-level Color details through composition.
// SOLID Principle: Single Responsibility Principle (SRP)
// - The AbstractShape class only manages the bridge between Shape and Color.

abstract class AbstractShape implements Shape {
    protected color: Color; // Bridge to the implementor

    constructor(color: Color) {
        this.color = color;
    }

    abstract draw(): void; // High-level behavior, to be defined by concrete shapes
}

// ------------------------------------------------------------
// Step 5: Implement Concrete Abstractions (Specific Shapes)
// ------------------------------------------------------------
// Concrete shapes like Circle and Rectangle extend the AbstractShape
// and implement the high-level behavior for drawing.
// SOLID Principle: Open-Closed Principle (OCP)
// - New shapes can be added without modifying existing classes.

class Circle extends AbstractShape {
    draw(): void {
        console.log("Drawing a Circle.");
        this.color.fillColor(); // Delegate the color-related behavior
    }
}

class Rectangle extends AbstractShape {
    draw(): void {
        console.log("Drawing a Rectangle.");
        this.color.fillColor(); // Delegate the color-related behavior
    }
}

// ------------------------------------------------------------
// Step 6: Client Code
// ------------------------------------------------------------
// The client interacts only with the Shape abstraction and uses composition
// to link specific shapes with specific colors. This promotes loose coupling.

const main = (): void => {
    // Create colors
    const red = new RedColor();
    const blue = new BlueColor();

    // Create shapes with different colors
    const redCircle = new Circle(red);
    const blueRectangle = new Rectangle(blue);

    // Draw shapes
    redCircle.draw();
    blueRectangle.draw();
};

// Run the client code
main();
