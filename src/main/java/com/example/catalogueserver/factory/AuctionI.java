package com.example.catalogueserver.factory;

public interface AuctionI {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    Double getPrice();

    void setPrice(Double price);

    String getType();

    void setType(String type);

    String getEndTime();

    void setEndTime(String endTime);

    String getDescription();

    void setDescription(String description);

    String getHighestBidder();

    void setHighestBidder(String highestBidder);

    Boolean getIsSold();

    void setIsSold(Boolean isSold);

    Double getExpeditedShipping();

    void setExpeditedShipping(Double expeditedShipping);
}
