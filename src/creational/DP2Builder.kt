package builder

// ------------------------------------------------------------
// Builder Design Pattern Example
// ------------------------------------------------------------
// The Builder Pattern:
// - Separates the construction of a complex object from its representation.
// - Allows step-by-step construction of objects and provides flexibility for 
//   different representations of the same type of object.

// ------------------------------------------------------------
// Step 1: Define the Product (Complex Object)
// ------------------------------------------------------------
// The `Car` class represents the complex object to be built. It has multiple 
// attributes (engine, wheels, body) that will be incrementally set by the builder.
// - Adheres to the Single Responsibility Principle (SRP): The class focuses solely
//   on representing the Car entity.

class Car {
    var engine: String = ""
    var wheels: Int = 0
    var body: String = ""

    override fun toString(): String {
        return "Car(engine='$engine', wheels=$wheels, body='$body')"
    }
}

// ------------------------------------------------------------
// Step 2: Define the Builder Interface
// ------------------------------------------------------------
// The `CarBuilder` interface declares abstract methods for building each part of
// the product. This ensures that all concrete builders follow a consistent protocol.
// - Adheres to the Interface Segregation Principle (ISP): The interface is narrowly
//   focused on construction-related methods.

interface CarBuilder {
    fun buildEngine(): CarBuilder
    fun buildWheels(): CarBuilder
    fun buildBody(): CarBuilder
    fun getResult(): Car
}

// ------------------------------------------------------------
// Step 3: Implement Concrete Builders
// ------------------------------------------------------------
// Concrete implementations of the `CarBuilder` interface provide specific
// instructions for constructing different types of cars.
// - Adheres to the Single Responsibility Principle (SRP): Each builder focuses
//   on constructing a particular representation of the product.
// - Follows the Open/Closed Principle (OCP): Adding new builders for different
//   car types does not require modifying the existing code.

class SportsCarBuilder : CarBuilder {
    private val car = Car()

    override fun buildEngine(): CarBuilder {
        car.engine = "V8 Engine"
        return this
    }

    override fun buildWheels(): CarBuilder {
        car.wheels = 4
        return this
    }

    override fun buildBody(): CarBuilder {
        car.body = "Sports body"
        return this
    }

    override fun getResult(): Car {
        return car
    }
}

class SUVCarBuilder : CarBuilder {
    private val car = Car()

    override fun buildEngine(): CarBuilder {
        car.engine = "V6 Engine"
        return this
    }

    override fun buildWheels(): CarBuilder {
        car.wheels = 4
        return this
    }

    override fun buildBody(): CarBuilder {
        car.body = "SUV body"
        return this
    }

    override fun getResult(): Car {
        return car
    }
}

// ------------------------------------------------------------
// Step 4: Define the Director
// ------------------------------------------------------------
// The `CarDirector` class encapsulates the construction process.
// It delegates the step-by-step construction to the builder.
// - Adheres to the Dependency Inversion Principle (DIP): The director depends
//   on the abstraction (`CarBuilder`), not concrete implementations.
// - Provides better maintainability and flexibility for constructing products.

class CarDirector(private val builder: CarBuilder) {
    fun construct(): Car {
        return builder.buildEngine()
            .buildWheels()
            .buildBody()
            .getResult()
    }
}

// ------------------------------------------------------------
// Main Function (Client Code)
// ------------------------------------------------------------
// The client code demonstrates how to use the builder to construct complex objects.
// - Adheres to the Open/Closed Principle (OCP): The client can use new builders
//   without changing its implementation.
// - Promotes separation of concerns: The client does not need to know the details
//   of the construction process.

fun main() {
    // Constructing a Sports Car using the builder
    val sportsCarBuilder = SportsCarBuilder()
    val director = CarDirector(sportsCarBuilder)
    val sportsCar = director.construct()
    println(sportsCar)  // Output: Car(engine='V8 Engine', wheels=4, body='Sports body')

    // Constructing an SUV using the builder
    val suvCarBuilder = SUVCarBuilder()
    val directorForSUV = CarDirector(suvCarBuilder)
    val suvCar = directorForSUV.construct()
    println(suvCar)  // Output: Car(engine='V6 Engine', wheels=4, body='SUV body')
}
