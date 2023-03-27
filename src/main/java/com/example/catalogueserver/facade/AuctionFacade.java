package com.example.catalogueserver.facade;

import com.example.catalogueserver.entity.Auction;
import com.example.catalogueserver.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuctionFacade {

    @Autowired
    private AuctionService auctionService;

    public Auction createAuction(Auction auction) {
        return auctionService.saveAuction(auction);
    }

    public Iterable<Auction> getAllAuctions() {
        return auctionService.getAllAuctions();
    }

    public Optional<Auction> getAuctionById(Long id) {
        return auctionService.getAuctionById(id);
    }

    public Auction updateAuction(Long id, Auction auction) {
        return auctionService.updateAuction(id, auction);
    }

    public void deleteAuction(Long id) {
        auctionService.deleteAuction(id);
    }

    public Optional<Auction> getAuctionByUUID(String UUID) {
        return auctionService.getAuctionByUUID(UUID);
    }
}
