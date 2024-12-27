"use strict";
// 2. Concrete Products
class Car {
    drive() {
        console.log("Driving a car");
    }
}
class Bike {
    drive() {
        console.log("Riding a bike");
    }
}
// 3. Creator (Abstract Factory)
class VehicleFactory {
}
// 4. Concrete Creators
class CarFactory extends VehicleFactory {
    createVehicle() {
        return new Car();
    }
}
class BikeFactory extends VehicleFactory {
    createVehicle() {
        return new Bike();
    }
}
// Main function to demonstrate the Factory Pattern
function main() {
    // Using the factory to create different types of vehicles
    const carFactory = new CarFactory();
    const car = carFactory.createVehicle();
    car.drive(); // Output: Driving a car
    const bikeFactory = new BikeFactory();
    const bike = bikeFactory.createVehicle();
    bike.drive(); // Output: Riding a bike
}
main();
