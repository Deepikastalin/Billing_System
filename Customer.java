public class Customer {
    private String customerId;
    private String customerName;
    private String contactDetails;

    public Customer(String customerId, String customerName, String contactDetails) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.contactDetails = contactDetails;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getContactDetails() {
        return contactDetails;
    }
}
