package com.example.catalogueserver.factory;

import com.example.catalogueserver.entity.Auction;

public class AuctionFactory {
    public static AuctionI createAuction(Auction auction) {
        String type = auction.getType();

        if (type.equals("FORWARD")) {
            return new ForwardAuction(auction.getName(), auction.getPrice(), auction.getEndTime(), auction.getDescription(), auction.getExpeditedShipping());
        }
        else if (type.equals("DUTCH")) {
            return new DutchAuction(auction.getName(), auction.getPrice(), auction.getDescription(), auction.getExpeditedShipping());
        }

        throw new IllegalArgumentException("Invalid auction type: " + type);
    }
}
