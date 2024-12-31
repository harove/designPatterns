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
    // The static `INSTANCE` is initialized eagerly during class definition.
    // TypeScript does not support `final`, but we use `readonly` to indicate immutability.
    private static readonly INSTANCE: Logger = new Logger();

    // ------------------------------------------------------------
    // Step 2: Make the constructor private.
    // ------------------------------------------------------------
    // The private constructor ensures that no other class can instantiate
    // the Logger directly, guaranteeing a single instance.
    private constructor() {}

    // ------------------------------------------------------------
    // Step 3: Provide a public method to access the instance.
    // ------------------------------------------------------------
    // The `getInstance()` method acts as the global access point for the
    // Singleton instance.
    public static getInstance(): Logger {
        return Logger.INSTANCE;
    }

    // ------------------------------------------------------------
    // Step 4: Add business logic (log functionality).
    // ------------------------------------------------------------
    // This method simulates logging functionality, showing how the singleton
    // instance is used across the application.
    public log(message: string): void {
        console.log(`[LOG]: ${message}`);
    }
}

// ------------------------------------------------------------
// Main Function (Client Code)
// ------------------------------------------------------------
// Demonstrates the Singleton Pattern in action. The client retrieves the 
// singleton instance and ensures that all references point to the same object.
(() => {
    // Retrieve the singleton instance
    const logger1 = Logger.getInstance();
    const logger2 = Logger.getInstance();

    // Use the logger to log messages
    logger1.log("This is the first log message.");
    logger2.log("This is the second log message.");

    // Verify that both references point to the same instance
    if (logger1 === logger2) {
        console.log("logger1 and logger2 are the same instance.");
    } else {
        console.log("logger1 and logger2 are different instances.");
    }
})();
