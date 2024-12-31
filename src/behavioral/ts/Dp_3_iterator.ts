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
    hasNext(): boolean; // Checks if there are more elements
    next(): IteratorResult<T, null>; // Retrieves the next element wrapped in an IteratorResult
}

// ------------------------------------------------------------
// Step 2: Define the Collection Interface
// ------------------------------------------------------------
// The collection interface declares a method to create an iterator.
// This ensures that the collection can provide its iterator without exposing its internal structure.
// SOLID Principle: Dependency Inversion Principle (DIP)
// - The collection depends on the abstraction (Iterator), not on a specific implementation.
interface Collection<T> {
    createIterator(): Iterator<T>; // Factory method for the iterator
}

// ------------------------------------------------------------
// Step 3: Concrete Collection Implementation
// ------------------------------------------------------------
// Implements the collection interface and stores the actual data.
// Encapsulates data storage and provides an iterator to access it.
// SOLID Principle: Single Responsibility Principle (SRP)
// - The class focuses on managing its elements and delegating iteration to the iterator.
class NameCollection implements Collection<string> {
    private names: string[] = ["Alice", "Bob", "Charlie", "Diana"];

    createIterator(): Iterator<string> {
        return new NameIterator(this.names); // Provides an iterator for this collection
    }
}

// ------------------------------------------------------------
// Step 4: Concrete Iterator Implementation
// ------------------------------------------------------------
// Implements the iterator interface for the NameCollection class.
// Responsible for maintaining the current state of iteration and traversing the collection.
// SOLID Principle: Single Responsibility Principle (SRP)
// - Focuses only on the logic for traversing the collection.
class NameIterator implements Iterator<string> {
    private index = 0; // Tracks the current position

    constructor(private names: string[]) {}

    hasNext(): boolean {
        return this.index < this.names.length; // Checks if more elements are available
    }

    next(): IteratorResult<string, null> {
        if (this.hasNext()) {
            return { done: false, value: this.names[this.index++] }; // Retrieves the next element
        }
        return { done: true, value: null }; // Indicates iteration is complete
    }
}

// ------------------------------------------------------------
// Step 5: Client
// ------------------------------------------------------------
// The client uses the iterator to access elements of the collection without knowing its internal structure.
// SOLID Principle: Open-Closed Principle (OCP)
// - The client can work with any collection or iterator implementation that adheres to the interfaces.
const main = (): void => {
    // Create a concrete collection
    const nameCollection: Collection<string> = new NameCollection();

    // Get the iterator for the collection
    const iterator: Iterator<string> = nameCollection.createIterator();

    // Use the iterator to traverse the collection
    console.log("Iterating over names:");
    while (iterator.hasNext()) {
        const result = iterator.next();
        if (!result.done) {
            console.log(result.value); // Output: Alice, Bob, Charlie, Diana
        }
    }
};

main();
