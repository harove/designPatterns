import java.util.ArrayList; // Import for ArrayList
import java.util.List;      // Import for List

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
    void sendMessage(String message, User user); // Mediates message sending
    void addUser(User user); // Adds a user to the chat
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
    private List<User> users = new ArrayList<>(); // List of users in the chat

    @Override
    public void sendMessage(String message, User user) {
        // Notify all users except the sender
        for (User u : users) {
            if (u != user) {
                u.receive(message);
            }
        }
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }
}

// ------------------------------------------------------------
// Step 3: Define the Colleague Abstract Class
// ------------------------------------------------------------
// The colleague class represents participants in the chat, working through the mediator.
// SOLID Principle: Dependency Inversion Principle (DIP)
// - Colleagues depend on the abstract mediator (ChatMediator) rather than a concrete implementation.
abstract class User {
    protected ChatMediator mediator; // Reference to the mediator
    protected String name;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void send(String message); // Sends a message via the mediator
    public abstract void receive(String message); // Receives a message
}

// ------------------------------------------------------------
// Step 4: Create Concrete Colleagues
// ------------------------------------------------------------
// Concrete colleagues implement specific behavior and interact via the mediator.
// SOLID Principle: Liskov Substitution Principle (LSP)
// - Concrete users can replace the abstract User class without affecting functionality.
class ChatUser extends User {
    public ChatUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println(this.name + ": Sending Message = " + message);
        mediator.sendMessage(message, this); // Use the mediator to send the message
    }

    @Override
    public void receive(String message) {
        System.out.println(this.name + ": Received Message = " + message);
    }
}

// ------------------------------------------------------------
// Step 5: Client
// ------------------------------------------------------------
// The client creates users and the mediator, adding users to the mediator.
// SOLID Principle: Open-Closed Principle (OCP)
// - The client can easily add more users or mediator logic without modifying existing code.
public class DP4Mediator {
    public static void main(String[] args) {
        // Create a chat mediator
        ChatMediator chatRoom = new ChatRoom();

        // Create users
        User user1 = new ChatUser(chatRoom, "Alice");
        User user2 = new ChatUser(chatRoom, "Bob");
        User user3 = new ChatUser(chatRoom, "Charlie");
        User user4 = new ChatUser(chatRoom, "Diana");

        // Add users to the chat room
        chatRoom.addUser(user1);
        chatRoom.addUser(user2);
        chatRoom.addUser(user3);
        chatRoom.addUser(user4);

        // Users send messages via the mediator
        user1.send("Hello, everyone!");
        user3.send("Hi Alice!");
    }
}
