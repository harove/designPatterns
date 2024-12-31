// ------------------------------------------------------------
// Mediator Design Pattern Example
// ------------------------------------------------------------
// The Mediator Pattern:
// - Defines an object that encapsulates how a set of objects interact.
// - Promotes loose coupling by preventing direct interactions between objects.
// - Mediators manage communication between colleagues, reducing dependencies.

// ------------------------------------------------------------
// Step 1: Define the Mediator Interface
// ------------------------------------------------------------
// The mediator interface defines the contract for communication between colleagues.
// SOLID Principle: Interface Segregation Principle (ISP)
// - The interface is specific and focused on mediating interactions between colleagues.
interface ChatMediator {
    sendMessage(message: string, user: User): void; // Mediates message sending
    addUser(user: User): void; // Adds a user to the chat
}

// ------------------------------------------------------------
// Step 2: Create the Concrete Mediator
// ------------------------------------------------------------
// The concrete mediator implements the communication logic between colleagues.
// SOLID Principle: Single Responsibility Principle (SRP)
// - The mediator is responsible only for managing interactions between users.
// SOLID Principle: Open-Closed Principle (OCP)
// - New colleague types can be added without modifying the mediator.
class ChatRoom implements ChatMediator {
    private users: User[] = []; // List of users in the chat

    sendMessage(message: string, user: User): void {
        // Notify all users except the sender
        for (const u of this.users) {
            if (u !== user) {
                u.receive(message);
            }
        }
    }

    addUser(user: User): void {
        this.users.push(user);
    }
}

// ------------------------------------------------------------
// Step 3: Define the Colleague Abstract Class
// ------------------------------------------------------------
// The colleague class represents participants in the chat, working through the mediator.
// SOLID Principle: Dependency Inversion Principle (DIP)
// - Colleagues depend on the abstract mediator (ChatMediator) rather than a concrete implementation.
abstract class User {
    protected mediator: ChatMediator; // Reference to the mediator
    protected name: string;

    constructor(mediator: ChatMediator, name: string) {
        this.mediator = mediator;
        this.name = name;
    }

    abstract send(message: string): void; // Sends a message via the mediator
    abstract receive(message: string): void; // Receives a message
}

// ------------------------------------------------------------
// Step 4: Create Concrete Colleagues
// ------------------------------------------------------------
// Concrete colleagues implement specific behavior and interact via the mediator.
// SOLID Principle: Liskov Substitution Principle (LSP)
// - Concrete users can replace the abstract User class without affecting functionality.
class ChatUser extends User {
    constructor(mediator: ChatMediator, name: string) {
        super(mediator, name);
    }

    send(message: string): void {
        console.log(`${this.name}: Sending Message = ${message}`);
        this.mediator.sendMessage(message, this); // Use the mediator to send the message
    }

    receive(message: string): void {
        console.log(`${this.name}: Received Message = ${message}`);
    }
}

// ------------------------------------------------------------
// Step 5: Client
// ------------------------------------------------------------
// The client creates users and the mediator, adding users to the mediator.
// SOLID Principle: Open-Closed Principle (OCP)
// - The client can easily add more users or mediator logic without modifying existing code.
const main = (): void => {
    // Create a chat mediator
    const chatRoom: ChatMediator = new ChatRoom();

    // Create users
    const user1: User = new ChatUser(chatRoom, "Alice");
    const user2: User = new ChatUser(chatRoom, "Bob");
    const user3: User = new ChatUser(chatRoom, "Charlie");
    const user4: User = new ChatUser(chatRoom, "Diana");

    // Add users to the chat room
    chatRoom.addUser(user1);
    chatRoom.addUser(user2);
    chatRoom.addUser(user3);
    chatRoom.addUser(user4);

    // Users send messages via the mediator
    user1.send("Hello, everyone!");
    user3.send("Hi Alice!");
};

main();
