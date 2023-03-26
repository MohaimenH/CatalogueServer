package com.example.catalogueserver.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Auction {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic
    private Long id;
    private String name;
    private Double price;
    private String type;
    private String endTime;
    private String description;
    private String highestBidder;
    private Boolean isSold = false;
    private Double expeditedShipping;
    private String uuid = String.valueOf(UUID.randomUUID());

    public Auction() {}

    public Auction(String name, Double price, String type, String endTime, String description, Double expeditedShipping) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.endTime = endTime;
        this.description = description;
        this.isSold = false;
        this.expeditedShipping = expeditedShipping;
    }

    public Auction(Auction auction) {
        this.name = auction.getName();
        this.price = auction.getPrice();
        this.type = auction.getType();
        this.endTime = auction.getEndTime();
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
