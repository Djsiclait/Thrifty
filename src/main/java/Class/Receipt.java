/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Djidjelly Siclait
 */
public class Receipt {
    // Attributes
    private String transactionId;
    private String buyer;
    private Date transactionDate;
    private Timestamp transactionTime;
    private List<Item> purchase;
    private float totalPrice;
    
    // Constructors
    public Receipt(){
        
    }
    
    public Receipt(String buyer, List<Item> purchase, float totalPrice){
        this.setTransactionId(UUID.randomUUID().toString().split("-")[0]);
        this.setBuyer(buyer);
        java.util.Date dateGenerator = new java.util.Date();
        this.setTransactionDate(new Date(dateGenerator.getTime()));
    }

    /**
     * @return the transactionId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId the transactionId to set
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * @return the buyer
     */
    public String getBuyer() {
        return buyer;
    }

    /**
     * @param buyer the buyer to set
     */
    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    /**
     * @return the transactionDate
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * @param transactionDate the transactionDate to set
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * @return the transactionTime
     */
    public Timestamp getTransactionTime() {
        return transactionTime;
    }

    /**
     * @param transactionTime the transactionTime to set
     */
    public void setTransactionTime(Timestamp transactionTime) {
        this.transactionTime = transactionTime;
    }

    /**
     * @return the purchase
     */
    public List<Item> getPurchase() {
        return purchase;
    }

    /**
     * @param purchase the purchase to set
     */
    public void setPurchase(List<Item> purchase) {
        this.purchase = purchase;
    }

    /**
     * @return the totalPrice
     */
    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    
}
