// Interface (common to both RealBank and ProxyBank)
interface Bank {
    void withdrawMoney(String accountName);
}

// Real Object (expensive to create or protected)
class RealBank implements Bank {
    public RealBank() {
        System.out.println("RealBank instance created (expensive operation)");
    }

    @Override
    public void withdrawMoney(String accountName) {
        System.out.println("Processing withdrawal for: " + accountName);
    }
}

// Proxy (placeholder for RealBank)
class ProxyBank implements Bank {
    private RealBank realBank; // Placeholder for the real object

    @Override
    public void withdrawMoney(String accountName) {
        if (realBank == null) {
            // Create real object only when needed
            realBank = new RealBank();
        }
        System.out.println("Proxy: Delegating request to RealBank");
        realBank.withdrawMoney(accountName);
    }
}

// Client
public class DP3Proxy {
    public static void main(String[] args) {
        Bank proxyBank = new ProxyBank(); // Placeholder object
        proxyBank.withdrawMoney("Alice"); // RealBank created and called here
    }
}

