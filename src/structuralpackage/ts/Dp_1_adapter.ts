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
    play(audioType: string, fileName: string): void;
}

// ------------------------------------------------------------
// Step 2: Adaptee
// ------------------------------------------------------------
// An existing class with incompatible functionality.
// The Adaptee contains specialized methods that the Client cannot directly use.
class AdvancedMediaPlayer {
    playMp3(fileName: string): void {
        console.log(`Playing mp3 file: ${fileName}`);
    }

    playMp4(fileName: string): void {
        console.log(`Playing mp4 file: ${fileName}`);
    }
}

// ------------------------------------------------------------
// Step 3: Adapter
// ------------------------------------------------------------
// The Adapter class translates requests from the Target interface (MediaPlayer) 
// to the Adaptee (AdvancedMediaPlayer). It acts as a bridge between the two.
class MediaAdapter implements MediaPlayer {
    constructor(private advancedMediaPlayer: AdvancedMediaPlayer) {}

    play(audioType: string, fileName: string): void {
        switch (audioType) {
            case "mp3":
                this.advancedMediaPlayer.playMp3(fileName); // Delegates mp3 requests
                break;
            case "mp4":
                this.advancedMediaPlayer.playMp4(fileName); // Delegates mp4 requests
                break;
            default:
                console.log(`Unsupported audio type: ${audioType}`);
        }
    }
}

// ------------------------------------------------------------
// Step 4: Client
// ------------------------------------------------------------
// The Client interacts with the Target interface (MediaPlayer).
// The Client is decoupled from the Adaptee, relying instead on the Adapter.
class AudioPlayer implements MediaPlayer {
    private advancedMediaPlayer = new AdvancedMediaPlayer(); // Adaptee instance
    private adapter = new MediaAdapter(this.advancedMediaPlayer); // Adapter instance

    play(audioType: string, fileName: string): void {
        if (audioType === "mp3" || audioType === "mp4") {
            this.adapter.play(audioType, fileName); // Uses the Adapter for playback
        } else {
            console.log(`Unsupported audio type: ${audioType}`);
        }
    }
}

// ------------------------------------------------------------
// Main Function (Client Code)
// ------------------------------------------------------------
// Demonstrates the use of the Adapter Pattern to enable the Client
// to work with an incompatible Adaptee via the Adapter.
(() => {
    const audioPlayer = new AudioPlayer();

    // Playing an mp3 file
    audioPlayer.play("mp3", "song.mp3"); // Output: Playing mp3 file: song.mp3

    // Playing an mp4 file
    audioPlayer.play("mp4", "video.mp4"); // Output: Playing mp4 file: video.mp4

    // Trying to play an unsupported file type
    audioPlayer.play("wav", "sound.wav"); // Output: Unsupported audio type: wav
})();
