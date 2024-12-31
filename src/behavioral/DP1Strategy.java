// 1. Define a family of algorithms (strategies)
// This is the common interface for all payment strategies.
interface PaymentStrategy {
    void pay(int amount);
}

// 2. Create concrete strategies that implement the algorithm
// Each concrete class represents a specific payment method.

class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid $" + amount + " using Credit Card: " + cardNumber);
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid $" + amount + " using PayPal account: " + email);
    }
}

class CashPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid $" + amount + " in cash.");
    }
}

// 3. Context class: Accepts a strategy and uses it to perform the operation
// This class uses a PaymentStrategy to process payments.
class Payment {
    private PaymentStrategy strategy;

    // Set the strategy dynamically
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void processPayment(int amount) {
        if (strategy == null) {
            System.out.println("No payment method selected!");
            return;
        }
        strategy.pay(amount); // Delegate the payment operation to the strategy
    }
}

// 4. Client: Configure the context with different strategies
// This demonstrates how to use the Strategy Pattern in action.
public class DP1Strategy {
    public static void main(String[] args) {
        Payment payment = new Payment();

        // Use Credit Card strategy
        payment.setPaymentStrategy(new CreditCardPayment("1234-5678-9876-5432"));
        payment.processPayment(100);

        // Use PayPal strategy
        payment.setPaymentStrategy(new PayPalPayment("user@example.com"));
        payment.processPayment(200);

        // Use Cash strategy
        payment.setPaymentStrategy(new CashPayment());
        payment.processPayment(50);
    }
}


