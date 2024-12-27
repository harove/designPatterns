public class Logger {
    // Single instance, created eagerly when the class is loaded
    private static final Logger INSTANCE = new Logger();

    // Private constructor to prevent instantiation from other classes
    private Logger() {}

    // Public method to provide global access to the instance
    public static Logger getInstance() {
        return INSTANCE;
    }

    // Log method to display messages
    public void log(String message) {
        System.out.println("[LOG]: " + message);
    }

    // Test main method
    public static void main(String[] args) {
        // Get the logger instance
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // Log messages using the logger instance
        logger1.log("This is the first log message.");
        logger2.log("This is the second log message.");

        // Verify that both references point to the same instance
        if (logger1 == logger2) {
            System.out.println("logger1 and logger2 are the same instance.");
        } else {
            System.out.println("logger1 and logger2 are different instances.");
        }
    }
}
