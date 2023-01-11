package org.stackroute;

public class Products {

    private String productName;
    private int quantity;
    private float pricePerPiece;

    public Products(String productName, int quantity, float pricePerPiece) {
        this.productName = productName;
        this.quantity = quantity;
        this.pricePerPiece = pricePerPiece;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPricePerPiece() {
        return pricePerPiece;
    }

    public void setPricePerPiece(float pricePerPiece) {
        this.pricePerPiece = pricePerPiece;
    }

}
