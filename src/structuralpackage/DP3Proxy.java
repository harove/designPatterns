// ------------------------------------------------------------
// Proxy Design Pattern Example
// ------------------------------------------------------------
// The Proxy Pattern:
// - Provides a placeholder or surrogate to control access to the real object.
// - Adds functionality such as lazy initialization, access control, logging, etc.
// - Ensures the client interacts with the proxy in the same way it would with the real object.

// ------------------------------------------------------------
// Key Steps for Applying the Proxy Pattern (GoF)
// ------------------------------------------------------------
// 1. Define a common interface to ensure both the real object and proxy can be used interchangeably.
// 2. Implement the real object that contains the core business logic or expensive operations.
// 3. Create a proxy class that adds functionality like access control or lazy initialization 
//    while delegating requests to the real object.
// 4. Ensure the client interacts only with the common interface, maintaining loose coupling.

// ------------------------------------------------------------
// SOLID Principles Applied
// ------------------------------------------------------------
// - Single Responsibility Principle (SRP): The RealBank class handles core banking operations,
//   while the ProxyBank class manages additional functionality such as lazy initialization.
// - Open/Closed Principle (OCP): The ProxyBank class can be extended to add new behavior
//   (e.g., logging, access control) without modifying existing code.
// - Dependency Inversion Principle (DIP): Both the proxy and the real object depend on
//   the abstraction (Bank interface), decoupling the client from implementation details.


// ------------------------------------------------------------
// Step 1: Interface
// ------------------------------------------------------------
// The Bank interface defines the common behavior for both the RealBank and ProxyBank.
// This abstraction ensures the client interacts seamlessly with either implementation.

interface Bank {
    void withdrawMoney(String accountName);
}

// ------------------------------------------------------------
// Step 2: Real Object
// ------------------------------------------------------------
// The RealBank class represents the actual business logic or resource-intensive object.
// It is expensive to create or needs restricted access, justifying the use of a proxy.

class RealBank implements Bank {
    public RealBank() {
        System.out.println("RealBank instance created (expensive operation)");
    }

    @Override
    public void withdrawMoney(String accountName) {
        System.out.println("Processing withdrawal for: " + accountName);
    }
}

// ------------------------------------------------------------
// Step 3: Proxy
// ------------------------------------------------------------
// The ProxyBank class acts as a placeholder for RealBank, adding functionality
// like lazy initialization and delegating requests to the real object.
// It adheres to the Dependency Inversion Principle by implementing the Bank interface.

class ProxyBank implements Bank {
    private RealBank realBank; // Lazy initialization for RealBank

    @Override
    public void withdrawMoney(String accountName) {
        if (realBank == null) {
            // Create the real object only when it is first needed
            realBank = new RealBank();
        }
        System.out.println("Proxy: Delegating request to RealBank");
        realBank.withdrawMoney(accountName);
    }
}

// ------------------------------------------------------------
// Step 4: Client
// ------------------------------------------------------------
// The client interacts with the Bank interface, unaware of whether it is using
// a proxy or the real object. This promotes loose coupling and simplifies client code.

public class DP3Proxy {
    public static void main(String[] args) {
        // Create the proxy object
        Bank proxyBank = new ProxyBank(); // Proxy acts as a placeholder

        // Use the proxy to withdraw money
        proxyBank.withdrawMoney("Alice"); // RealBank is created and used here

        // Subsequent calls reuse the existing RealBank instance
        proxyBank.withdrawMoney("Bob");
    }
}
