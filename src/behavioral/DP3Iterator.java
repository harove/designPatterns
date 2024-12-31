// ------------------------------------------------------------
// Iterator Design Pattern Example
// ------------------------------------------------------------
// The Iterator Pattern:
// - Provides a way to access elements of a collection sequentially without exposing its underlying representation.
// - Decouples the client from the details of traversing the collection.
// Benefits:
// - Promotes single responsibility by separating iteration logic from the collection itself.
// - Allows different types of iteration (e.g., forward, backward) by implementing different iterators.

// ------------------------------------------------------------
// Step 1: Define the Iterator Interface
// ------------------------------------------------------------
// Common interface for iterators.
// Defines the methods needed to traverse a collection.
// SOLID Principle: Interface Segregation Principle (ISP)
// - The interface is focused, defining only the essential operations for iteration.
interface Iterator<T> {
    boolean hasNext(); // Checks if there are more elements
    T next(); // Retrieves the next element
}

// ------------------------------------------------------------
// Step 2: Define the Collection Interface
// ------------------------------------------------------------
// The collection interface declares a method to create an iterator.
// This ensures that the collection can provide its iterator without exposing its internal structure.
// SOLID Principle: Dependency Inversion Principle (DIP)
// - The collection depends on the abstraction (Iterator), not on a specific implementation.
interface Collection<T> {
    Iterator<T> createIterator(); // Factory method for the iterator
}

// ------------------------------------------------------------
// Step 3: Concrete Collection Implementation
// ------------------------------------------------------------
// Implements the collection interface and stores the actual data.
// Encapsulates data storage and provides an iterator to access it.
// SOLID Principle: Single Responsibility Principle (SRP)
// - The class focuses on managing its elements and delegating iteration to the iterator.

class NameCollection implements Collection<String> {
    private String[] names = {"Alice", "Bob", "Charlie", "Diana"};

    @Override
    public Iterator<String> createIterator() {
        return new NameIterator(); // Provides an iterator for this collection
    }

    // Inner class to implement the iterator for NameCollection
    private class NameIterator implements Iterator<String> {
        private int index = 0; // Tracks the current position

        @Override
        public boolean hasNext() {
            return index < names.length; // Checks if more elements are available
        }

        @Override
        public String next() {
            return hasNext() ? names[index++] : null; // Retrieves the next element
        }
    }
}

// ------------------------------------------------------------
// Step 4: Client
// ------------------------------------------------------------
// The client uses the iterator to access elements of the collection without knowing its internal structure.
// SOLID Principle: Open-Closed Principle (OCP)
// - The client can work with any collection or iterator implementation that adheres to the interfaces.

public class DP3Iterator {
    public static void main(String[] args) {
        // Create a concrete collection
        Collection<String> nameCollection = new NameCollection();

        // Get the iterator for the collection
        Iterator<String> iterator = nameCollection.createIterator();

        // Use the iterator to traverse the collection
        System.out.println("Iterating over names:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next()); // Output: Alice, Bob, Charlie, Diana
        }
    }
}
