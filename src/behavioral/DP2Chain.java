// 1. Define the Handler (abstract class with a reference to the next handler)
/**
 * Abstract handler that defines the contract for processing a request 
 * and holding a reference to the next handler in the chain.
 * Follows the **Open-Closed Principle (OCP)**: 
 * This class is open for extension (new types of handlers can extend it) 
 * but closed for modification (the abstract logic remains unchanged).
 */
abstract class SupportHandler {
    protected SupportHandler nextHandler; // Reference to the next handler in the chain

    /**
     * Set the next handler in the chain.
     * Follows the **Single Responsibility Principle (SRP)**:
     * Each handler is responsible only for its specific behavior and linking to the next handler.
     */
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * Method to process the request, to be implemented by concrete handlers.
     */
    public abstract void handleRequest(String issue);
}

// 2. Define Concrete Handlers (specific support levels)
/**
 * Tier 1 Support: Handles basic issues. If unable, passes the request to the next handler.
 * Demonstrates the **Liskov Substitution Principle (LSP)**:
 * This class can replace the abstract SupportHandler without altering program behavior.
 */
class Tier1Support extends SupportHandler {
    @Override
    public void handleRequest(String issue) {
        if (issue.equals("basic")) {
            System.out.println("Tier 1 Support: Handling basic issue.");
        } else if (nextHandler != null) {
            System.out.println("Tier 1 Support: Cannot handle, passing to Tier 2.");
            nextHandler.handleRequest(issue); // Delegates responsibility to the next handler
        } else {
            System.out.println("Tier 1 Support: Cannot handle, and no further support available.");
        }
    }
}

/**
 * Tier 2 Support: Handles intermediate issues. Delegates to the next handler if necessary.
 * Applies **Dependency Inversion Principle (DIP)**:
 * High-level modules (client code) depend on abstractions (SupportHandler), not concrete implementations.
 */
class Tier2Support extends SupportHandler {
    @Override
    public void handleRequest(String issue) {
        if (issue.equals("intermediate")) {
            System.out.println("Tier 2 Support: Handling intermediate issue.");
        } else if (nextHandler != null) {
            System.out.println("Tier 2 Support: Cannot handle, passing to Tier 3.");
            nextHandler.handleRequest(issue); // Passes the responsibility further down the chain
        } else {
            System.out.println("Tier 2 Support: Cannot handle, and no further support available.");
        }
    }
}

/**
 * Tier 3 Support: Handles advanced issues and is the final handler in the chain.
 * Adheres to the **Interface Segregation Principle (ISP)**:
 * Each handler only implements the functionality it needs to process requests.
 */
class Tier3Support extends SupportHandler {
    @Override
    public void handleRequest(String issue) {
        if (issue.equals("advanced")) {
            System.out.println("Tier 3 Support: Handling advanced issue.");
        } else {
            System.out.println("Tier 3 Support: Cannot handle, and no further support available.");
        }
    }
}

// 3. Client code to demonstrate the Chain of Responsibility
/**
 * Client code demonstrating how to use the Chain of Responsibility pattern.
 * Steps for applying the Chain of Responsibility Design Pattern:
 * 
 * 1. **Define the Abstract Handler**: 
 *    Create an abstract class or interface with a method for handling requests 
 *    and a reference to the next handler.
 * 
 * 2. **Create Concrete Handlers**: 
 *    Implement the abstract handler in concrete classes that handle specific types of requests.
 * 
 * 3. **Link the Handlers**: 
 *    Set up the chain by linking handlers together using their references.
 * 
 * 4. **Make the Request**: 
 *    Start processing the request from the first handler in the chain. 
 *    Each handler decides whether to handle the request or pass it down the chain.
 * 
 * SOLID Principles in the Client:
 * - **SRP**: Each handler in the chain has a single responsibility.
 * - **OCP**: The chain can be extended with new handlers without modifying existing ones.
 * - **DIP**: The client depends on the abstraction (`SupportHandler`), not the concrete implementations.
 */
public class DP2Chain {
    public static void main(String[] args) {
        // Create handlers (tiers of support)
        SupportHandler tier1 = new Tier1Support();
        SupportHandler tier2 = new Tier2Support();
        SupportHandler tier3 = new Tier3Support();

        // Set up the chain (Tier 1 -> Tier 2 -> Tier 3)
        tier1.setNextHandler(tier2);
        tier2.setNextHandler(tier3);

        // Process different issues
        System.out.println("Request: Basic issue");
        tier1.handleRequest("basic");

        System.out.println("\nRequest: Intermediate issue");
        tier1.handleRequest("intermediate");

        System.out.println("\nRequest: Advanced issue");
        tier1.handleRequest("advanced");

        System.out.println("\nRequest: Unknown issue");
        tier1.handleRequest("unknown");
    }
}
