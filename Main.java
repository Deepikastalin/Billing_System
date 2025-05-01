import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static int customerIdCounter = 103;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Merchant merchant = new Merchant("ABC Traders");

        // Pre-existing customers
        Map<String, Customer> customerDatabase = new HashMap<>();
        customerDatabase.put("C101", new Customer("C101", "Alice", "9998887777"));
        customerDatabase.put("C102", new Customer("C102", "Bob", "9990001111"));

        // Pre-existing products
        Map<Integer, Product> productDatabase = Map.of(
                1, new Product(1, "Laptop", 50000, 18),
                2, new Product(2, "Mouse", 500, 12),
                3, new Product(3, "Keyboard", 1500, 12),
                4, new Product(4, "Monitor", 12000, 18)
        );

        // --- Get and Validate Phone Number ---
        String inputPhoneNumber;
        do {
            System.out.print("Enter Customer Phone Number (10 digits): ");
            inputPhoneNumber = sc.nextLine();
            if (!inputPhoneNumber.matches("\\d{10}")) {
                System.out.println("Invalid phone number! Please enter a 10-digit number.");
            }
        } while (!inputPhoneNumber.matches("\\d{10}"));

        final String phoneNumber = inputPhoneNumber;

        // --- Search or Register Customer ---
        Optional<Customer> optionalCustomer = customerDatabase.values().stream()
                .filter(c -> c.getContactDetails().equals(phoneNumber))
                .findFirst();

        Customer customer;
        if (optionalCustomer.isPresent()) {
            customer = optionalCustomer.get();
            System.out.println("Existing Customer Found: " + customer.getCustomerName() + ", " + customer.getContactDetails());
        } else {
            // Register New Customer
            System.out.println("Customer not found! Please register:");

            // Generate new customer ID
            String newCustomerId = "C" + customerIdCounter++;

            // Get new customer name and register
            System.out.print("Enter Customer Name: ");
            String name = sc.nextLine();
            customer = new Customer(newCustomerId, name, phoneNumber);

            // Add the new customer to the customer database
            customerDatabase.put(newCustomerId, customer);
            System.out.println("Customer registered successfully! Assigned ID: " + newCustomerId + "\n");
        }

        // --- Add Products to Cart ---
        List<CartItem> cartItems = new ArrayList<>();
        while (true) {
            System.out.println("\nAvailable Products:");
            productDatabase.values().forEach(p ->
                    System.out.printf("ID: %d | %s | ₹%.2f | GST: %.1f%%\n",
                            p.getProductId(), p.getDescription(), p.getPricePerUnit(), p.getGstPercentage()));

            System.out.print("Enter Product ID to add to cart (or 0 to finish): ");
            int prodId = sc.nextInt();
            if (prodId == 0) break;

            Product selectedProduct = productDatabase.get(prodId);
            if (selectedProduct == null) {
                System.out.println("Invalid Product ID! Try again.");
                continue;
            }

            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();
            cartItems.add(new CartItem(selectedProduct, qty));
        }

        // Check if the cart is empty
        if (cartItems.isEmpty()) {
            System.out.println("\nNo products selected. Billing cancelled.");
            return;
        }

        // Generate Invoice
        Invoice invoice = new Invoice(merchant, customer, cartItems);
        invoice.printInvoice();
    }
}
