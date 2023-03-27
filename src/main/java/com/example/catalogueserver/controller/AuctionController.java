package com.example.catalogueserver.controller;

import com.example.catalogueserver.entity.Auction;
import com.example.catalogueserver.facade.AuctionFacade;
import com.example.catalogueserver.factory.AuctionFactory;
import com.example.catalogueserver.factory.AuctionI;
import com.example.catalogueserver.type.Bid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auctions")
public class AuctionController {
    private final Logger logger = LoggerFactory.getLogger(AuctionController.class);
    @Autowired
    private AuctionFacade auctionFacade;

    @PostMapping("/")
    public ResponseEntity<Auction> createAuction(@RequestBody Auction auction) {
        logger.info("Received Post Request for Auction");
        AuctionI auctionFactory = AuctionFactory.createAuction(auction);
        Auction auctionFromFactory = new Auction(auctionFactory.getName(), auctionFactory.getPrice(), auctionFactory.getType(), auctionFactory.getEndTime(), auctionFactory.getDescription(), auctionFactory.getExpeditedShipping());
        Auction createdAuction = auctionFacade.createAuction(auctionFromFactory);
        return new ResponseEntity<>(createdAuction, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<Auction>> getAllAuctions() {
        Iterable<Auction> auctions = auctionFacade.getAllAuctions();
        return new ResponseEntity<>(auctions, HttpStatus.OK);
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<Auction> getAuctionByUUID(@PathVariable String uuid) {
        Optional<Auction> auctionOptional = auctionFacade.getAuctionByUUID(uuid);

        if (auctionOptional.isPresent()) {
            return new ResponseEntity<>(auctionOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/bid/")
    public ResponseEntity<Auction> bid(@RequestBody Bid bid) {
        Optional<Auction> auction = auctionFacade.getAuctionByUUID(bid.getUuid());

        if (auction.isPresent()) {
            auction.get().setPrice(bid.getAmount());
            auction.get().setHighestBidder(bid.getBidder());
            Auction updatedAuction = auctionFacade.updateAuction(auction.get().getId(), auction.get());
            return new ResponseEntity<>(updatedAuction, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{uuid}/buyNow")
    public ResponseEntity<Auction> bid(@PathVariable String uuid) {

        Optional<Auction> auction = auctionFacade.getAuctionByUUID(uuid);

        if (auction.isPresent()) {
            auction.get().setIsSold(true);
            Auction updatedAuction = auctionFacade.updateAuction(auction.get().getId(), auction.get());
            return new ResponseEntity<>(updatedAuction, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Auction> getAuctionById(@PathVariable Long id) {
        Optional<Auction> auctionOptional = auctionFacade.getAuctionById(id);

        if (auctionOptional.isPresent()) {
            return new ResponseEntity<>(auctionOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Auction> updateAuction(@PathVariable String uuid, @RequestBody Auction auction) {
        Optional<Auction> fetchedAuction = auctionFacade.getAuctionByUUID(uuid);

        if (fetchedAuction.isPresent()) {
            Auction updatedAuction = auctionFacade.updateAuction(fetchedAuction.get().getId(), auction);
            return new ResponseEntity<>(updatedAuction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuction(@PathVariable Long id) {
        auctionFacade.deleteAuction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
