package com.example.catalogueserver.controller;

import com.example.catalogueserver.entity.Auction;
import com.example.catalogueserver.facade.AuctionFacade;
import com.example.catalogueserver.factory.AuctionFactory;
import com.example.catalogueserver.factory.AuctionI;
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

    @GetMapping("/test")
    public ResponseEntity<String> getAuctionTest() {
        logger.info("Received Test Request for Auction");
        return new ResponseEntity<>("Hello Auction Test", HttpStatus.OK);
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

    @PutMapping("/{id}")
    public ResponseEntity<Auction> updateAuction(@PathVariable Long id, @RequestBody Auction auction) {
        Auction updatedAuction = auctionFacade.updateAuction(id, auction);
        return new ResponseEntity<>(updatedAuction, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuction(@PathVariable Long id) {
        auctionFacade.deleteAuction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
