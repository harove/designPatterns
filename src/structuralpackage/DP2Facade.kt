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
    fun turnOn() = println("Amplifier is ON.")
    fun turnOff() = println("Amplifier is OFF.")
    fun setVolume(level: Int) = println("Amplifier volume set to $level.")
}

class DVDPlayer {
    fun insertDVD(dvdName: String) = println("DVD '$dvdName' inserted.")
    fun play() = println("DVD is playing.")
    fun stop() = println("DVD stopped.")
    fun eject() = println("DVD ejected.")
}

class Projector {
    fun turnOn() = println("Projector is ON.")
    fun turnOff() = println("Projector is OFF.")
    fun setInputSource(source: String) = println("Projector input set to $source.")
}

// ------------------------------------------------------------
// Step 2: Facade
// ------------------------------------------------------------
// The Facade acts as a gateway for the client to interact with the subsystems.
// It simplifies operations by combining and sequencing calls to the subsystems.
// This ensures the client doesn't need to deal with subsystem complexity.

class HomeTheaterFacade(
    private val amplifier: Amplifier,
    private val dvdPlayer: DVDPlayer,
    private val projector: Projector
) {
    // Simplifies starting the Home Theater system
    fun watchMovie(movieName: String) {
        println("Starting the Home Theater...")
        amplifier.turnOn()
        amplifier.setVolume(10) // Set a standard volume level
        projector.turnOn()
        projector.setInputSource("DVD") // Configure the projector's input
        dvdPlayer.insertDVD(movieName) // Insert the desired movie
        dvdPlayer.play() // Start playing the movie
        println("Enjoy your movie: $movieName!")
    }

    // Simplifies shutting down the Home Theater system
    fun endMovie() {
        println("Shutting down the Home Theater...")
        dvdPlayer.stop() // Stop playback
        dvdPlayer.eject() // Eject the DVD
        projector.turnOff() // Turn off the projector
        amplifier.turnOff() // Turn off the amplifier
        println("Goodbye!")
    }
}

// ------------------------------------------------------------
// Step 3: Client
// ------------------------------------------------------------
// The Client interacts only with the Facade.
// The client is unaware of the subsystem classes, promoting loose coupling.
// This reduces the learning curve and makes client code easier to maintain.

fun main() {
    // Create subsystem instances
    val amplifier = Amplifier()
    val dvdPlayer = DVDPlayer()
    val projector = Projector()

    // Create the Facade with subsystem dependencies
    val homeTheater = HomeTheaterFacade(amplifier, dvdPlayer, projector)

    // Use the Facade to perform complex operations in a simplified way
    homeTheater.watchMovie("Inception") // Start the Home Theater system
    println()
    homeTheater.endMovie() // Shut down the Home Theater system
}
