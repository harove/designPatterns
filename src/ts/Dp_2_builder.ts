// Builder Design Pattern in TypeScript

// 1. Product (The complex object to be built)
class Car {
  engine: string = ""; // Engine type of the car
  wheels: number = 0; // Number of wheels
  body: string = ""; // Type of car body

  // Method to provide a string representation of the car
  toString(): string {
    return `Car(engine='${this.engine}', wheels=${this.wheels}, body='${this.body}')`;
  }
}

// 2. Builder Interface (Defines the building steps)
interface CarBuilder {
  buildEngine(): CarBuilder; // Step to build the engine
  buildWheels(): CarBuilder; // Step to build the wheels
  buildBody(): CarBuilder; // Step to build the body
  getResult(): Car; // Returns the final product
}

// 3. Concrete Builders (Implement the building steps)

// Builder for sports cars
class SportsCarBuilder implements CarBuilder {
  private readonly car: Car = new Car(); // Instance of the car being built

  buildEngine(): CarBuilder {
    this.car.engine = "V8 Engine"; // Sports cars have a V8 engine
    return this; // Return this for method chaining
  }

  buildWheels(): CarBuilder {
    this.car.wheels = 4; // Sports cars have 4 wheels
    return this; // Return this for method chaining
  }

  buildBody(): CarBuilder {
    this.car.body = "Sports body"; // Sports cars have a sleek body
    return this; // Return this for method chaining
  }

  getResult(): Car {
    return this.car; // Return the final product
  }
}

// Builder for SUVs
class SUVCarBuilder implements CarBuilder {
  private car: Car = new Car(); // Instance of the car being built

  buildEngine(): CarBuilder {
    this.car.engine = "V6 Engine"; // SUVs have a V6 engine
    return this; // Return this for method chaining
  }

  buildWheels(): CarBuilder {
    this.car.wheels = 4; // SUVs also have 4 wheels
    return this; // Return this for method chaining
  }

  buildBody(): CarBuilder {
    this.car.body = "SUV body"; // SUVs have a robust body
    return this; // Return this for method chaining
  }

  getResult(): Car {
    return this.car; // Return the final product
  }
}

// 4. Director (Constructs the product using the builder)
class CarDirector {
  private builder: CarBuilder; // Builder instance

  constructor(builder: CarBuilder) {
    this.builder = builder; // Assign builder to director
  }

  // Construct the car step by step
  construct(): Car {
    return this.builder.buildEngine().buildWheels().buildBody().getResult();
  }
}

// 5. Main Function (Demonstrating the Builder Pattern)

// Create a director and builder for a sports car
const sportsCarBuilder = new SportsCarBuilder(); // Sports car builder instance
const sportsCarDirector = new CarDirector(sportsCarBuilder); // Director with the sports car builder
const sportsCar = sportsCarDirector.construct(); // Construct the sports car
console.log(sportsCar.toString()); // Output: Car(engine='V8 Engine', wheels=4, body='Sports body')

// Create a director and builder for an SUV
const suvCarBuilder = new SUVCarBuilder(); // SUV builder instance
const suvCarDirector = new CarDirector(suvCarBuilder); // Director with the SUV builder
const suvCar = suvCarDirector.construct(); // Construct the SUV
console.log(suvCar.toString()); // Output: Car(engine='V6 Engine', wheels=4, body='SUV body')
