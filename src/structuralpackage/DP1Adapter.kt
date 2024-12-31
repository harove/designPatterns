// ------------------------------------------------------------
// Adapter Design Pattern Example
// ------------------------------------------------------------
// The Adapter Pattern:
// - Allows incompatible interfaces to work together.
// - Acts as a bridge between two incompatible interfaces (Target and Adaptee).
// - Promotes flexibility by decoupling the client from the Adaptee's implementation.

// ------------------------------------------------------------
// Key Steps for Applying the Adapter Pattern (GoF)
// ------------------------------------------------------------
// 1. Define a Target interface that the client expects to work with.
// 2. Identify an existing class (Adaptee) with incompatible behavior.
// 3. Create an Adapter class that implements the Target interface and 
//    translates requests from the Target to the Adaptee.
// 4. Ensure the Client uses only the Target interface, relying on the Adapter
//    to handle interactions with the Adaptee.

// ------------------------------------------------------------
// SOLID Principles Applied
// ------------------------------------------------------------
// - Single Responsibility Principle (SRP): The Adapter class focuses solely 
//   on bridging the gap between the Target and the Adaptee.
// - Open/Closed Principle (OCP): The Adapter allows new types of Adaptees to
//   be integrated without modifying the Client or existing Adapter logic.
// - Dependency Inversion Principle (DIP): The Client depends on abstractions 
//   (Target interface) rather than concrete implementations (Adaptee).

// ------------------------------------------------------------
// Step 1: Target Interface
// ------------------------------------------------------------
// Defines the expected interface for the Client.
// The Client interacts with this interface, ensuring loose coupling.
interface MediaPlayer {
    fun play(audioType: String, fileName: String)
}

// ------------------------------------------------------------
// Step 2: Adaptee
// ------------------------------------------------------------
// An existing class with incompatible functionality.
// The Adaptee contains specialized methods that the Client cannot directly use.
class AdvancedMediaPlayer {
    fun playMp3(fileName: String) {
        println("Playing mp3 file: $fileName")
    }

    fun playMp4(fileName: String) {
        println("Playing mp4 file: $fileName")
    }
}

// ------------------------------------------------------------
// Step 3: Adapter
// ------------------------------------------------------------
// The Adapter class translates requests from the Target interface (MediaPlayer) 
// to the Adaptee (AdvancedMediaPlayer). It acts as a bridge between the two.
class MediaAdapter(private val advancedMediaPlayer: AdvancedMediaPlayer) : MediaPlayer {
    override fun play(audioType: String, fileName: String) {
        when (audioType) {
            "mp3" -> advancedMediaPlayer.playMp3(fileName) // Delegates mp3 requests
            "mp4" -> advancedMediaPlayer.playMp4(fileName) // Delegates mp4 requests
            else -> println("Unsupported audio type: $audioType")
        }
    }
}

// ------------------------------------------------------------
// Step 4: Client
// ------------------------------------------------------------
// The Client interacts with the Target interface (MediaPlayer).
// The Client is decoupled from the Adaptee, relying instead on the Adapter.
class AudioPlayer : MediaPlayer {
    private val advancedMediaPlayer = AdvancedMediaPlayer() // Adaptee instance
    private val adapter = MediaAdapter(advancedMediaPlayer) // Adapter instance

    override fun play(audioType: String, fileName: String) {
        if (audioType == "mp3" || audioType == "mp4") {
            adapter.play(audioType, fileName) // Uses the Adapter for playback
        } else {
            println("Unsupported audio type: $audioType")
        }
    }
}

// ------------------------------------------------------------
// Main Function (Client Code)
// ------------------------------------------------------------
// Demonstrates the use of the Adapter Pattern to enable the Client
// to work with an incompatible Adaptee via the Adapter.
fun main() {
    val audioPlayer = AudioPlayer()

    // Playing an mp3 file
    audioPlayer.play("mp3", "song.mp3")  // Output: Playing mp3 file: song.mp3

    // Playing an mp4 file
    audioPlayer.play("mp4", "video.mp4") // Output: Playing mp4 file: video.mp4

    // Trying to play an unsupported file type
    audioPlayer.play("wav", "sound.wav") // Output: Unsupported audio type: wav
}
