// 1. Target Interface
// This is the interface the client expects to work with.
interface MediaPlayer {
    fun play(audioType: String, fileName: String)
}

// 2. Adaptee
// This is the existing class with incompatible functionality that we want to use.
class AdvancedMediaPlayer {
    fun playMp3(fileName: String) {
        println("Playing mp3 file: $fileName")
    }

    fun playMp4(fileName: String) {
        println("Playing mp4 file: $fileName")
    }
}

// 3. Adapter
// The Adapter bridges the gap between the Target interface (MediaPlayer) and the Adaptee (AdvancedMediaPlayer).
class MediaAdapter(private val advancedMediaPlayer: AdvancedMediaPlayer) : MediaPlayer {
    override fun play(audioType: String, fileName: String) {
        when (audioType) {
            "mp3" -> advancedMediaPlayer.playMp3(fileName) // Delegate to Adaptee
            "mp4" -> advancedMediaPlayer.playMp4(fileName) // Delegate to Adaptee
            else -> println("Unsupported audio type: $audioType")
        }
    }
}

// 4. Client
// The client interacts with the Target interface (MediaPlayer), unaware of the Adaptee.
class AudioPlayer : MediaPlayer {
    private val advancedMediaPlayer = AdvancedMediaPlayer()
    private val adapter = MediaAdapter(advancedMediaPlayer) // Use the adapter to bridge the gap

    override fun play(audioType: String, fileName: String) {
        if (audioType == "mp3" || audioType == "mp4") {
            adapter.play(audioType, fileName) // Use the adapter for playback
        } else {
            println("Unsupported audio type: $audioType")
        }
    }
}

// Main function to demonstrate the Adapter Pattern
fun main() {
    val audioPlayer = AudioPlayer()

    // Playing an mp3 file
    audioPlayer.play("mp3", "song.mp3")  // Output: Playing mp3 file: song.mp3

    // Playing an mp4 file
    audioPlayer.play("mp4", "video.mp4") // Output: Playing mp4 file: video.mp4

    // Trying to play an unsupported file type
    audioPlayer.play("wav", "sound.wav") // Output: Unsupported audio type: wav
}
