package com.example.catalogueserver.service;

import com.example.catalogueserver.entity.Auction;
import com.example.catalogueserver.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    public Auction saveAuction(Auction auction) {
        return auctionRepository.save(auction);
    }

    public Iterable<Auction> getAllAuctions() {
        return auctionRepository.findAll();
    }

    public void deleteAuction(Long id) {
        auctionRepository.deleteById(id);
    }

    public Optional<Auction> getAuctionById(Long id) {
        return auctionRepository.findById(id);
    }

    public Auction updateAuction(Long id, Auction auction) {
        Optional<Auction> existingAuctionOptional = auctionRepository.findById(id);

        if (existingAuctionOptional.isPresent()) {
            Auction existingAuction = existingAuctionOptional.get();

            existingAuction.setName(auction.getName());
            existingAuction.setPrice(auction.getPrice());
            existingAuction.setType(auction.getType());
            existingAuction.setEndTime(auction.getEndTime());
            existingAuction.setDescription(auction.getDescription());
            existingAuction.setHighestBidder(auction.getHighestBidder());
            existingAuction.setIsSold(auction.getIsSold());

            return auctionRepository.save(existingAuction);
        } else {
            return auctionRepository.save(auction);
        }
    }
}
