// ------------------------------------------------------------
// Observer Design Pattern Example
// ------------------------------------------------------------
// The Observer Pattern:
// - Defines a one-to-many dependency between objects.
// - When one object (subject) changes state, all its dependents (observers) are notified and updated automatically.
// Benefits:
// - Promotes loose coupling between the subject and observers.
// - Makes the system more extensible by adding or removing observers dynamically.

// ------------------------------------------------------------
// Step 1: Define the Observer Interface
// ------------------------------------------------------------
// The Observer interface declares the method that all concrete observers must implement to receive updates.
// SOLID Principle: Interface Segregation Principle (ISP)
// - The interface is focused and defines only the essential method for notification.
interface Observer {
    update(message: string): void; // Called when the subject's state changes
}

// ------------------------------------------------------------
// Step 2: Define the Subject Interface
// ------------------------------------------------------------
// The Subject interface declares methods for attaching, detaching, and notifying observers.
// SOLID Principle: Dependency Inversion Principle (DIP)
// - The subject depends on the abstraction (Observer), not on specific implementations.
interface Subject {
    attach(observer: Observer): void;   // Adds an observer
    detach(observer: Observer): void;   // Removes an observer
    notifyObservers(): void;            // Notifies all observers
}

// ------------------------------------------------------------
// Step 3: Concrete Subject Implementation
// ------------------------------------------------------------
// Implements the Subject interface and maintains a list of observers.
// Notifies observers whenever its state changes.
// SOLID Principle: Single Responsibility Principle (SRP)
// - The class focuses on managing observers and notifying them when changes occur.
class NewsAgency implements Subject {
    private observers: Observer[] = []; // List of observers
    private news: string = ""; // State of the subject

    setNews(news: string): void {
        this.news = news; // Update state
        this.notifyObservers(); // Notify all observers about the change
    }

    attach(observer: Observer): void {
        this.observers.push(observer);
    }

    detach(observer: Observer): void {
        this.observers = this.observers.filter(o => o !== observer);
    }

    notifyObservers(): void {
        for (const observer of this.observers) {
            observer.update(this.news); // Notify each observer
        }
    }
}

// ------------------------------------------------------------
// Step 4: Concrete Observer Implementation
// ------------------------------------------------------------
// Implements the Observer interface and updates itself based on the Subject's state.
// SOLID Principle: Open-Closed Principle (OCP)
// - New observers can be added without modifying existing code.
class NewsChannel implements Observer {
    private name: string;

    constructor(name: string) {
        this.name = name;
    }

    update(message: string): void {
        console.log(`${this.name} received news update: ${message}`);
    }
}

// ------------------------------------------------------------
// Step 5: Client
// ------------------------------------------------------------
// The client configures the subject and attaches observers dynamically.
// SOLID Principle: Open-Closed Principle (OCP)
// - The client can add or remove observers without modifying the subject or other observers.
const main = (): void => {
    // Create the subject
    const newsAgency = new NewsAgency();

    // Create and attach observers
    const channel1: Observer = new NewsChannel("Channel 1");
    const channel2: Observer = new NewsChannel("Channel 2");
    newsAgency.attach(channel1);
    newsAgency.attach(channel2);

    // Change the state of the subject
    console.log("Breaking News 1:");
    newsAgency.setNews("Big storm approaching!");

    // Detach an observer and change the state again
    newsAgency.detach(channel1);
    console.log("\nBreaking News 2:");
    newsAgency.setNews("Election results are out!");
};

// Run the client code
main();
