// 1. Subsystems
// These are the individual subsystems with their own specific responsibilities.

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

// 2. Facade
// The Facade simplifies interaction with the complex subsystems and provides a unified interface.
class HomeTheaterFacade(
    private val amplifier: Amplifier,
    private val dvdPlayer: DVDPlayer,
    private val projector: Projector
) {
    // Simplifies starting the home theater
    fun watchMovie(movieName: String) {
        println("Starting the Home Theater...")
        amplifier.turnOn()
        amplifier.setVolume(10)
        projector.turnOn()
        projector.setInputSource("DVD")
        dvdPlayer.insertDVD(movieName)
        dvdPlayer.play()
        println("Enjoy your movie: $movieName!")
    }

    // Simplifies shutting down the home theater
    fun endMovie() {
        println("Shutting down the Home Theater...")
        dvdPlayer.stop()
        dvdPlayer.eject()
        projector.turnOff()
        amplifier.turnOff()
        println("Goodbye!")
    }
}

// 3. Client
// The client interacts with the Facade, unaware of the complexity of the subsystems.
fun main() {
    // Create the subsystems
    val amplifier = Amplifier()
    val dvdPlayer = DVDPlayer()
    val projector = Projector()

    // Create the Facade
    val homeTheater = HomeTheaterFacade(amplifier, dvdPlayer, projector)

    // Use the Facade to watch and end a movie
    homeTheater.watchMovie("Inception")
    println()
    homeTheater.endMovie()
}
