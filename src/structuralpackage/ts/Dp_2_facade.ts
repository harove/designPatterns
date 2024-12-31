// ------------------------------------------------------------
// Facade Design Pattern Example
// ------------------------------------------------------------
// The Facade Pattern:
// - Provides a simplified and unified interface to a complex subsystem.
// - Reduces the complexity of the client’s interaction with multiple classes.
// - Encourages loose coupling between the client and subsystem components.

// ------------------------------------------------------------
// Key Steps for Applying the Facade Pattern (GoF)
// ------------------------------------------------------------
// 1. Identify the subsystems that need to be simplified for the client.
// 2. Create a unified interface (the Facade) that abstracts interactions 
//    with the subsystems.
// 3. Ensure the client interacts only with the Facade, while the subsystems 
//    remain hidden and encapsulated.

// ------------------------------------------------------------
// SOLID Principles Applied
// ------------------------------------------------------------
// - Single Responsibility Principle (SRP): Each subsystem class focuses on
//   a specific responsibility, ensuring clear and concise functionality.
// - Interface Segregation Principle (ISP): The Facade exposes only the necessary
//   methods required by the client, hiding the complexity of the subsystems.
// - Dependency Inversion Principle (DIP): The client depends on the Facade abstraction,
//   not the concrete implementation of subsystems.

// ------------------------------------------------------------
// Step 1: Subsystems
// ------------------------------------------------------------
// These are individual components of the Home Theater system, each handling
// a specific functionality. They adhere to SRP by being responsible for a single task.

class Amplifier {
    turnOn(): void {
        console.log("Amplifier is ON.");
    }
    turnOff(): void {
        console.log("Amplifier is OFF.");
    }
    setVolume(level: number): void {
        console.log(`Amplifier volume set to ${level}.`);
    }
}

class DVDPlayer {
    insertDVD(dvdName: string): void {
        console.log(`DVD '${dvdName}' inserted.`);
    }
    play(): void {
        console.log("DVD is playing.");
    }
    stop(): void {
        console.log("DVD stopped.");
    }
    eject(): void {
        console.log("DVD ejected.");
    }
}

class Projector {
    turnOn(): void {
        console.log("Projector is ON.");
    }
    turnOff(): void {
        console.log("Projector is OFF.");
    }
    setInputSource(source: string): void {
        console.log(`Projector input set to ${source}.`);
    }
}

// ------------------------------------------------------------
// Step 2: Facade
// ------------------------------------------------------------
// The Facade acts as a gateway for the client to interact with the subsystems.
// It simplifies operations by combining and sequencing calls to the subsystems.
// This ensures the client doesn't need to deal with subsystem complexity.

class HomeTheaterFacade {
    private amplifier: Amplifier;
    private dvdPlayer: DVDPlayer;
    private projector: Projector;

    constructor(amplifier: Amplifier, dvdPlayer: DVDPlayer, projector: Projector) {
        this.amplifier = amplifier;
        this.dvdPlayer = dvdPlayer;
        this.projector = projector;
    }

    // Simplifies starting the Home Theater system
    watchMovie(movieName: string): void {
        console.log("Starting the Home Theater...");
        this.amplifier.turnOn();
        this.amplifier.setVolume(10); // Set a standard volume level
        this.projector.turnOn();
        this.projector.setInputSource("DVD"); // Configure the projector's input
        this.dvdPlayer.insertDVD(movieName); // Insert the desired movie
        this.dvdPlayer.play(); // Start playing the movie
        console.log(`Enjoy your movie: ${movieName}!`);
    }

    // Simplifies shutting down the Home Theater system
    endMovie(): void {
        console.log("Shutting down the Home Theater...");
        this.dvdPlayer.stop(); // Stop playback
        this.dvdPlayer.eject(); // Eject the DVD
        this.projector.turnOff(); // Turn off the projector
        this.amplifier.turnOff(); // Turn off the amplifier
        console.log("Goodbye!");
    }
}

// ------------------------------------------------------------
// Step 3: Client
// ------------------------------------------------------------
// The Client interacts only with the Facade.
// The client is unaware of the subsystem classes, promoting loose coupling.
// This reduces the learning curve and makes client code easier to maintain.

const main = () => {
    // Create subsystem instances
    const amplifier = new Amplifier();
    const dvdPlayer = new DVDPlayer();
    const projector = new Projector();

    // Create the Facade with subsystem dependencies
    const homeTheater = new HomeTheaterFacade(amplifier, dvdPlayer, projector);

    // Use the Facade to perform complex operations in a simplified way
    homeTheater.watchMovie("Inception"); // Start the Home Theater system
    console.log();
    homeTheater.endMovie(); // Shut down the Home Theater system
};

// Run the client code
main();
