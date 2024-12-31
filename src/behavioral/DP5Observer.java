
// ------------------------------------------------------------
// Observer Design Pattern Example
// ------------------------------------------------------------
// The Observer Pattern:
// - Defines a one-to-many dependency between objects.
// - When one object (subject) changes state, all its dependents (observers) are notified and updated automatically.
// Benefits:
// - Promotes loose coupling between the subject and observers.
// - Makes the system more extensible by adding or removing observers dynamically.

import java.util.ArrayList;
import java.util.List;

// ------------------------------------------------------------
// Step 1: Define the Observer Interface
// ------------------------------------------------------------
// The Observer interface declares the method that all concrete observers must implement to receive updates.
// SOLID Principle: Interface Segregation Principle (ISP)
// - The interface is focused and defines only the essential method for notification.
interface Observer {
    void update(String message); // Called when the subject's state changes
}

// ------------------------------------------------------------
// Step 2: Define the Subject Interface
// ------------------------------------------------------------
// The Subject interface declares methods for attaching, detaching, and notifying observers.
// SOLID Principle: Dependency Inversion Principle (DIP)
// - The subject depends on the abstraction (Observer), not on specific implementations.
interface Subject {
    void attach(Observer observer);   // Adds an observer
    void detach(Observer observer);   // Removes an observer
    void notifyObservers();           // Notifies all observers
}

// ------------------------------------------------------------
// Step 3: Concrete Subject Implementation
// ------------------------------------------------------------
// Implements the Subject interface and maintains a list of observers.
// Notifies observers whenever its state changes.
// SOLID Principle: Single Responsibility Principle (SRP)
// - The class focuses on managing observers and notifying them when changes occur.


class NewsAgency implements Subject {
    private List<Observer> observers = new ArrayList<>(); // List of observers
    private String news; // State of the subject

    public void setNews(String news) {
        this.news = news; // Update state
        notifyObservers(); // Notify all observers about the change
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(news); // Notify each observer
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
    private String name;

    public NewsChannel(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received news update: " + message);
    }
}

// ------------------------------------------------------------
// Step 5: Client
// ------------------------------------------------------------
// The client configures the subject and attaches observers dynamically.
// SOLID Principle: Open-Closed Principle (OCP)
// - The client can add or remove observers without modifying the subject or other observers.
public class DP5Observer {
    public static void main(String[] args) {
        // Create the subject
        NewsAgency newsAgency = new NewsAgency();

        // Create and attach observers
        Observer channel1 = new NewsChannel("Channel 1");
        Observer channel2 = new NewsChannel("Channel 2");
        newsAgency.attach(channel1);
        newsAgency.attach(channel2);

        // Change the state of the subject
        System.out.println("Breaking News 1:");
        newsAgency.setNews("Big storm approaching!");

        // Detach an observer and change the state again
        newsAgency.detach(channel1);
        System.out.println("\nBreaking News 2:");
        newsAgency.setNews("Election results are out!");
    }
}