package com.example.catalogueserver.factory;

import jakarta.persistence.*;

@Entity
@Table(name = "auction")
public class DutchAuction implements AuctionI {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic
    private Long id;
    private String name;
    private Double price;
    private String type = "DUTCH";
    private String endTime = null;
    private String description;
    private String highestBidder;
    private Boolean isSold = false;
    private Double expeditedShipping;

    public DutchAuction() {}

    public DutchAuction(String name, Double price, String description, Double expeditedShipping) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.isSold = false;
        this.expeditedShipping = expeditedShipping;
    }

    public DutchAuction(AuctionI auction) {
        this.name = auction.getName();
        this.price = auction.getPrice();
        this.type = "DUTCH";
        this.endTime = null;
        this.description = auction.getDescription();
        this.highestBidder = auction.getHighestBidder();
        this.isSold = auction.getIsSold();
        this.expeditedShipping = auction.getExpeditedShipping();
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHighestBidder() {
        return highestBidder;
    }

    public void setHighestBidder(String highestBidder) {
        this.highestBidder = highestBidder;
    }

    public Boolean getIsSold() {
        return isSold;
    }

    public void setIsSold(Boolean isSold) {
        this.isSold = isSold;
    }

    public Double getExpeditedShipping() {
        return expeditedShipping;
    }

    public void setExpeditedShipping(Double expeditedShipping) {
        this.expeditedShipping = expeditedShipping;
    }
}
