// 1. Define the Handler (abstract class with a reference to the next handler)
abstract class SupportHandler {
    protected SupportHandler nextHandler; // Reference to the next handler in the chain

    // Set the next handler in the chain
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    // Method to process the request, to be implemented by concrete handlers
    public abstract void handleRequest(String issue);
}

// 2. Define Concrete Handlers (specific support levels)

// Tier 1 Support (first level of handling)
class Tier1Support extends SupportHandler {
    @Override
    public void handleRequest(String issue) {
        if (issue.equals("basic")) {
            System.out.println("Tier 1 Support: Handling basic issue.");
        } else if (nextHandler != null) {
            System.out.println("Tier 1 Support: Cannot handle, passing to Tier 2.");
            nextHandler.handleRequest(issue);
        } else {
            System.out.println("Tier 1 Support: Cannot handle, and no further support available.");
        }
    }
}

// Tier 2 Support (second level of handling)
class Tier2Support extends SupportHandler {
    @Override
    public void handleRequest(String issue) {
        if (issue.equals("intermediate")) {
            System.out.println("Tier 2 Support: Handling intermediate issue.");
        } else if (nextHandler != null) {
            System.out.println("Tier 2 Support: Cannot handle, passing to Tier 3.");
            nextHandler.handleRequest(issue);
        } else {
            System.out.println("Tier 2 Support: Cannot handle, and no further support available.");
        }
    }
}

// Tier 3 Support (final level of handling)
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
