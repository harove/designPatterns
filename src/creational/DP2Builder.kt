// 1. Product (The complex object to be built)
package builder

class Car {
    var engine: String = ""
    var wheels: Int = 0
    var body: String = ""

    override fun toString(): String {
        return "Car(engine='$engine', wheels=$wheels, body='$body')"
    }
}

// 2. Builder (Interface that defines the building steps)
interface CarBuilder {
    fun buildEngine(): CarBuilder
    fun buildWheels(): CarBuilder
    fun buildBody(): CarBuilder
    fun getResult(): Car
}

// 3. ConcreteBuilder (A concrete builder that implements the building steps)
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

// 4. Director (A class that constructs the product using the builder)
class CarDirector(private val builder: CarBuilder) {
    fun construct(): Car {
        return builder.buildEngine()
            .buildWheels()
            .buildBody()
            .getResult()
    }
}

// Main function to demonstrate the Builder Pattern
fun main() {
    // Create a director and builder for a sports car
    val sportsCarBuilder = SportsCarBuilder()
    val director = CarDirector(sportsCarBuilder)
    val sportsCar = director.construct()
    println(sportsCar)  // Output: Car(engine='V8 Engine', wheels=4, body='Sports body')

    // Create a director and builder for an SUV
    val suvCarBuilder = SUVCarBuilder()
    val directorForSUV = CarDirector(suvCarBuilder)
    val suvCar = directorForSUV.construct()
    println(suvCar)  // Output: Car(engine='V6 Engine', wheels=4, body='SUV body')
}
