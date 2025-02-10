package com.example.Receipt.Processor.model;

import java.util.List;

public class Receipt {
    private String retailer;
    private String purchaseDate;
    private String purchaseTime;
    private List<Item> items;
    private String total;

    //Retailer get set methods
    public String getRetailer(){
        return retailer;
    }
    public void setRetailer(String retailer){
        this.retailer = retailer;
    }

    //purchase date get and set methods
    public String getPurchaseDate(){
        return purchaseDate;
    }
    public void setPurchaseDate(String purchaseDate){
        this.purchaseDate = purchaseDate;
    }

    //purchase time get and set methods
    public String getPurchaseTime(){
        return purchaseTime;
    }
    public void setPurchaseTime(String purchaseTime){
        this.purchaseTime = purchaseTime;
    }

    //Items get and set methods
    public List<Item> getItems(){
        return items;
    }
    public void setItems(List<Item> items){
        this.items = items;
    }

    //Total get and set methods
    public String getTotal(){
        return total;
    }
    public void setTotal(String total){
        this.total = total;
    }
}