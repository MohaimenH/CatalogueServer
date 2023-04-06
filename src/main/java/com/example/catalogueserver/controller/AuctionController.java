package com.example.catalogueserver.controller;

import com.example.catalogueserver.entity.Auction;
import com.example.catalogueserver.facade.AuctionFacade;
import com.example.catalogueserver.factory.AuctionFactory;
import com.example.catalogueserver.factory.AuctionI;
import com.example.catalogueserver.type.Bid;
import com.example.catalogueserver.type.BuyNow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.util.Optional;

@RestController
@CrossOrigin
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

    @PostMapping("/bid")
    public ResponseEntity<Auction> bid(@RequestBody Bid bid) throws ParseException {
        logger.info("Got my information!");
        Optional<Auction> auction = auctionFacade.getAuctionByUUID(bid.getUuid());

        if (auction.isPresent()) {
            // Create a formatter for the input date string
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

            // Parse the date string into a LocalDateTime object
            LocalDateTime dateTime = LocalDateTime.parse(auction.get().getEndTime(), formatter);

            if (dateTime.compareTo(LocalDateTime.now()) < 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            auction.get().setPrice(bid.getAmount());
            auction.get().setHighestBidder(bid.getBidder());
            Auction updatedAuction = auctionFacade.updateAuction(auction.get().getId(), auction.get());

            return new ResponseEntity<>(updatedAuction, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/buy")
    public ResponseEntity<Auction> buy(@RequestBody BuyNow buyNow) {

        Optional<Auction> auction = auctionFacade.getAuctionByUUID(buyNow.getUuid());

        if (auction.isPresent()) {
            auction.get().setIsSold(true);
            auction.get().setHighestBidder(buyNow.getUsername());
            Auction updatedAuction = auctionFacade.updateAuction(auction.get().getId(), auction.get());
            return new ResponseEntity<>(updatedAuction, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{uuid}")
    public ResponseEntity<Auction> getAuctionById(@PathVariable String uuid) {
        Optional<Auction> fetchedAuction = auctionFacade.getAuctionByUUID(uuid);

        if (fetchedAuction.isPresent()) {
            return new ResponseEntity<>(fetchedAuction.get(), HttpStatus.OK);
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
