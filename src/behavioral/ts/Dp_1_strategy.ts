// ------------------------------------------------------------
// Strategy Design Pattern Example
// ------------------------------------------------------------
// The Strategy Pattern:
// - Defines a family of algorithms (strategies).
// - Encapsulates each algorithm in a separate class.
// - Makes the algorithms interchangeable at runtime without altering the context class.
// Benefits:
// - Promotes flexibility by decoupling the algorithm from the context.
// - Allows runtime changes of behavior (strategy).

// ------------------------------------------------------------
// Step 1: Define the Strategy Interface
// ------------------------------------------------------------
// Common interface for all payment strategies.
// This interface defines the "contract" that all concrete strategies must implement.
// SOLID Principle: Interface Segregation Principle (ISP)
// - The interface is specific and focused, ensuring that it defines only what is required for a payment algorithm.

interface PaymentStrategy {
    pay(amount: number): void; // Algorithm to process payment
}

// ------------------------------------------------------------
// Step 2: Implement Concrete Strategies
// ------------------------------------------------------------
// Each concrete strategy implements the common interface,
// encapsulating its specific algorithm for payment processing.
// SOLID Principle: Open-Closed Principle (OCP)
// - New payment methods can be added by creating new classes without modifying existing ones.

// Payment using a Credit Card
class CreditCardPayment implements PaymentStrategy {
    private cardNumber: string;

    constructor(cardNumber: string) {
        this.cardNumber = cardNumber;
    }

    pay(amount: number): void {
        console.log(`Paid $${amount} using Credit Card: ${this.cardNumber}`);
    }
}

// Payment using PayPal
class PayPalPayment implements PaymentStrategy {
    private email: string;

    constructor(email: string) {
        this.email = email;
    }

    pay(amount: number): void {
        console.log(`Paid $${amount} using PayPal account: ${this.email}`);
    }
}

// Payment using Cash
class CashPayment implements PaymentStrategy {
    pay(amount: number): void {
        console.log(`Paid $${amount} in cash.`);
    }
}

// ------------------------------------------------------------
// Step 3: Context Class
// ------------------------------------------------------------
// The context class delegates payment operations to a PaymentStrategy.
// It does not implement any specific algorithm, promoting decoupling.
// SOLID Principle: Single Responsibility Principle (SRP)
// - The context class is only responsible for using the strategy, not defining it.
// SOLID Principle: Dependency Inversion Principle (DIP)
// - The context depends on the PaymentStrategy abstraction, not on concrete implementations.

class Payment {
    private strategy: PaymentStrategy | null = null; // Reference to a strategy

    // Dynamically sets the strategy at runtime
    setPaymentStrategy(strategy: PaymentStrategy | null): void {
        this.strategy = strategy;
    }

    // Processes the payment using the currently set strategy
    processPayment(amount: number): void {
        if (!this.strategy) {
            console.log("No payment method selected!");
            return;
        }
        this.strategy.pay(amount); // Delegates payment to the current strategy
    }
}

// ------------------------------------------------------------
// Step 4: Client
// ------------------------------------------------------------
// The client dynamically configures the context with different strategies at runtime.
// SOLID Principle: Open-Closed Principle (OCP)
// - The client can easily switch strategies without modifying the context class or strategy implementations.

const main = (): void => {
    // Create the context (Payment object)
    const payment = new Payment();

    // Dynamically set the Credit Card strategy
    payment.setPaymentStrategy(new CreditCardPayment("1234-5678-9876-5432"));
    payment.processPayment(100); // Output: Paid $100 using Credit Card: 1234-5678-9876-5432

    // Dynamically set the PayPal strategy
    payment.setPaymentStrategy(new PayPalPayment("user@example.com"));
    payment.processPayment(200); // Output: Paid $200 using PayPal account: user@example.com

    // Dynamically set the Cash strategy
    payment.setPaymentStrategy(new CashPayment());
    payment.processPayment(50); // Output: Paid $50 in cash.

    // Attempt to process payment without setting a strategy
    payment.setPaymentStrategy(null);
    payment.processPayment(30); // Output: No payment method selected!
};

// Run the client code
main();
