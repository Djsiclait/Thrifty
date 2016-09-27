/**
 * Created by Djidjelly Siclait on 9/23/2016.
 */
package Class;

import java.util.UUID;

public class Item {
    // Attributes
    private String productKey;
    private String name;
    private String imageUrl;
    private float price;
    private Integer inStock;
    private boolean visible;

    // Constructors
    public Item(){

    }

    public Item(String name, float price, Integer inStock, boolean visible){
        this.setProductKey(UUID.randomUUID().toString().split("-")[0]);
        this.setName(name);
        this.setPrice(price);
        this.setInStock(inStock);
        this.setVisible(visible);
    }

    // Getters and Setters
    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
