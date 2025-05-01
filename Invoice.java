import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.IntStream;

public class Invoice {
    private final Merchant merchant;
    private final Customer customer;
    private final List<CartItem> cartItems;
    private final LocalDate invoiceDate;
    private final LocalDate dueDate;

    private double subTotal;
    private double totalGst;
    private double grandTotal;

    public Invoice(Merchant merchant, Customer customer, List<CartItem> cartItems) {
        this.merchant = merchant;
        this.customer = customer;
        this.cartItems = cartItems;
        this.invoiceDate = LocalDate.now();
        this.dueDate = invoiceDate.plusDays(15);
        calculateTotals();
    }

    private void calculateTotals() {
        subTotal = cartItems.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();

        totalGst = cartItems.stream()
                .mapToDouble(item -> {
                    double base = item.getTotalPrice();
                    double gstRate = item.getProduct().getGstPercentage();
                    return (base * gstRate) / 100;
                })
                .sum();

        grandTotal = subTotal + totalGst;
    }

    public void printInvoice() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.println("\n==================== " + merchant.getMerchantName() + " ====================");
        System.out.println("Invoice Date: " + invoiceDate.format(formatter));
        System.out.println("Due Date   : " + dueDate.format(formatter));
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-5s %-12s %-6s %-10s\n", "S.No", "Product", "Qty", "Unit Price", "GST%", "Total");
        System.out.println("----------------------------------------------------------------------");

        IntStream.range(0, cartItems.size()).forEach(i -> {
            CartItem item = cartItems.get(i);
            Product product = item.getProduct();
            int qty = item.getQuantity();
            double unitPrice = product.getPricePerUnit();
            double gstRate = product.getGstPercentage();
            double itemTotalWithGst = item.getTotalPrice() * (1 + gstRate / 100);

            System.out.printf("%-5d %-20s %-5d ₹%-11.2f %-6.1f ₹%-10.2f\n",
                    i + 1,
                    product.getDescription(),
                    qty,
                    unitPrice,
                    gstRate,
                    itemTotalWithGst);
        });

        System.out.println("----------------------------------------------------------------------");
        System.out.printf("%-45s ₹%-10.2f\n", "Subtotal:", subTotal);
        System.out.printf("%-45s ₹%-10.2f\n", "Total GST:", totalGst);
        System.out.printf("%-45s ₹%-10.2f\n", "Grand Total:", grandTotal);
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Customer Name : " + customer.getCustomerName());
        System.out.println("Contact       : " + customer.getContactDetails());
        System.out.println("======================================================================\n");
    }
}
