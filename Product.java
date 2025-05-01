public class Product {
    private int productId;
    private String description;
    private double pricePerUnit;
    private double gstPercentage;

    public Product(int productId, String description, double pricePerUnit, double gstPercentage) {
        this.productId = productId;
        this.description = description;
        this.pricePerUnit = pricePerUnit;
        this.gstPercentage = gstPercentage;
    }

    public int getProductId() {
        return productId;
    }

    public String getDescription() {
        return description;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public double getGstPercentage() {
        return gstPercentage;
    }
}
