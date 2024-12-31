// ------------------------------------------------------------
// Singleton Design Pattern Example
// ------------------------------------------------------------
// The Singleton Pattern:
// - Ensures that a class has only one instance.
// - Provides a global access point to that instance.
// - The Logger class demonstrates a thread-safe Singleton implementation using eager initialization.

// ------------------------------------------------------------
// Key Steps for Applying the Singleton Pattern (GoF)
// ------------------------------------------------------------
// 1. Create a static instance of the class, initialized eagerly or lazily.
// 2. Make the constructor private to prevent external instantiation.
// 3. Provide a public static method to return the single instance.
// 4. Add business logic or operations to utilize the singleton instance.

// ------------------------------------------------------------
// SOLID Principles Applied
// ------------------------------------------------------------
// - Single Responsibility Principle (SRP): The Logger class is solely responsible 
//   for managing its unique instance and providing logging functionality.
// - Open/Closed Principle (OCP): Although not directly evident in Singleton, 
//   it allows extensions without altering its core behavior by encapsulating 
//   its instance creation logic.
// - Liskov Substitution Principle (LSP): As a utility class, this principle is 
//   not directly applied, but the Logger ensures consistent behavior as its usage
//   is uniform throughout the application.

class Logger {
    // ------------------------------------------------------------
    // Step 1: Create a single, static instance of the class.
    // ------------------------------------------------------------
    // The static `INSTANCE` is initialized eagerly during class loading.
    // This ensures thread safety without additional synchronization overhead.
    private static final Logger INSTANCE = new Logger();

    // ------------------------------------------------------------
    // Step 2: Make the constructor private.
    // ------------------------------------------------------------
    // The private constructor ensures that no other class can instantiate
    // the Logger directly, guaranteeing a single instance.
    private Logger() {}

    // ------------------------------------------------------------
    // Step 3: Provide a public method to access the instance.
    // ------------------------------------------------------------
    // The `getInstance()` method acts as the global access point for the
    // Singleton instance.
    public static Logger getInstance() {
        return INSTANCE;
    }

    // ------------------------------------------------------------
    // Step 4: Add business logic (log functionality).
    // ------------------------------------------------------------
    // This method simulates logging functionality, showing how the singleton
    // instance is used across the application.
    public void log(String message) {
        System.out.println("[LOG]: " + message);
    }
}

// ------------------------------------------------------------
// Main Function (Client Code)
// ------------------------------------------------------------
// Demonstrates the Singleton Pattern in action. The client retrieves the 
// singleton instance and ensures that all references point to the same object.
public class DP3Singleton {
    public static void main(String[] args) {
        // Retrieve the singleton instance
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // Use the logger to log messages
        logger1.log("This is the first log message.");
        logger2.log("This is the second log message.");

        // Verify that both references point to the same instance
        if (logger1 == logger2) {
            System.out.println("logger1 and logger2 are the same instance.");
        } else {
            System.out.println("logger1 and logger2 are different instances.");
        }
    }
}
