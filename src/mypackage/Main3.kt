// 1. Product (Abstract Interface)
interface Vehicle {
    fun drive()
}

// 2. Concrete Products
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

// 3. Creator (Factory)
abstract class VehicleFactory {
    abstract fun createVehicle(): Vehicle
}

// 4. Concrete Creator
class CarFactory : VehicleFactory() {
    override fun createVehicle(): Vehicle {
        return Car()
    }
}

class BikeFactory : VehicleFactory() {
    override fun createVehicle(): Vehicle {
        return Bike()
    }
}

// Main function to demonstrate the Factory Pattern
fun main() {
    // Using the factory to create different types of vehicles
    val carFactory: VehicleFactory = CarFactory()
    val car: Vehicle = carFactory.createVehicle()
    car.drive()  // Output: Driving a car

    val bikeFactory: VehicleFactory = BikeFactory()
    val bike: Vehicle = bikeFactory.createVehicle()
    bike.drive()  // Output: Riding a bike
}
