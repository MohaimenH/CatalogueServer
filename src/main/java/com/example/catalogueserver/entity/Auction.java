package com.example.catalogueserver.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

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

    public Auction() {}

    public Auction(String name, Double price, String type, String endTime, String description, String highestBidder) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.endTime = endTime;
        this.description = description;
        this.highestBidder = highestBidder;
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
}
