// 1. Product (Abstract Interface)
interface Vehicle {
    drive(): void;
}

// 2. Concrete Products
class Car implements Vehicle {
    drive(): void {
        console.log("Driving a car");
    }
}

class Bike implements Vehicle {
    drive(): void {
        console.log("Riding a bike");
    }
}

// 3. Creator (Abstract Factory)
abstract class VehicleFactory {
    abstract createVehicle(): Vehicle;
}

// 4. Concrete Creators
class CarFactory extends VehicleFactory {
    createVehicle(): Vehicle {
        return new Car();
    }
}

class BikeFactory extends VehicleFactory {
    createVehicle(): Vehicle {
        return new Bike();
    }
}

// Main function to demonstrate the Factory Pattern
function main() {
    // Using the factory to create different types of vehicles
    const carFactory: VehicleFactory = new CarFactory();
    const car: Vehicle = carFactory.createVehicle();
    car.drive(); // Output: Driving a car

    const bikeFactory: VehicleFactory = new BikeFactory();
    const bike: Vehicle = bikeFactory.createVehicle();
    bike.drive(); // Output: Riding a bike
}

main();
