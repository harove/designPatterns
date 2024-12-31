package factory

// ------------------------------------------------------------
// Factory Design Pattern Example
// ------------------------------------------------------------
// The Factory Pattern:
// - Defines an interface for creating objects but allows subclasses to alter the type of objects created.
// - Promotes flexibility by delegating the instantiation logic to a factory class.
// - Simplifies object creation, making the code more maintainable and extensible.

// ------------------------------------------------------------
// Step 1: Define the Product (Abstract Interface)
// ------------------------------------------------------------
// The "Product" interface declares the contract for objects the factory will create.
// - Promotes the Dependency Inversion Principle (DIP): The client depends on this abstraction rather than concrete implementations.

interface Vehicle {
    fun drive() // Abstract operation that concrete products will implement
}

// ------------------------------------------------------------
// Step 2: Implement Concrete Products
// ------------------------------------------------------------
// Concrete implementations of the "Product" interface provide specific behaviors.
// - Adheres to the Single Responsibility Principle (SRP): Each class focuses only on its behavior.

class Car : Vehicle {
    override fun drive() {
        println("Driving a car")
    }
}

class Bike : Vehicle {
    override fun drive() {
        println("Riding a bike")
    }
}

// ------------------------------------------------------------
// Step 3: Create the Creator (Abstract Factory)
// ------------------------------------------------------------
// The "Creator" defines an interface or abstract class with a factory method for creating products.
// - Adheres to the Open/Closed Principle (OCP): New product types can be introduced without modifying the factory interface.

abstract class VehicleFactory {
    abstract fun createVehicle(): Vehicle // Factory method for creating products
}

// ------------------------------------------------------------
// Step 4: Implement Concrete Creators
// ------------------------------------------------------------
// Concrete factories override the factory method to create specific product instances.
// - Encapsulates the object creation logic, adhering to the Single Responsibility Principle (SRP).
// - Follows the Open/Closed Principle (OCP): Adding a new product only requires a new factory class.

class CarFactory : VehicleFactory() {
    override fun createVehicle(): Vehicle {
        return Car() // Creates a Car instance
    }
}

class BikeFactory : VehicleFactory() {
    override fun createVehicle(): Vehicle {
        return Bike() // Creates a Bike instance
    }
}

// ------------------------------------------------------------
// Main Function (Client Code)
// ------------------------------------------------------------
// The client code relies on the factory to instantiate objects, decoupling it from the specific implementations.
// - Follows the Dependency Inversion Principle (DIP): The client depends on abstractions (Vehicle and VehicleFactory) rather than concrete classes.
// - Enables flexibility and maintainability by avoiding hard-coded object creation.

fun main() {
    // Step 5: Use the Factory (Client Code)
    // - The client code interacts with the factory to obtain product instances.

    // Use the CarFactory to create a Car
    val carFactory: VehicleFactory = CarFactory()
    val car: Vehicle = carFactory.createVehicle()
    car.drive() // Output: Driving a car

    // Use the BikeFactory to create a Bike
    val bikeFactory: VehicleFactory = BikeFactory()
    val bike: Vehicle = bikeFactory.createVehicle()
    bike.drive() // Output: Riding a bike
}
