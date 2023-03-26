package com.example.catalogueserver.repository;

import com.example.catalogueserver.entity.Auction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuctionRepository extends CrudRepository<Auction, Long> {
    Optional<Auction> findByUuid(String uuid);
}
