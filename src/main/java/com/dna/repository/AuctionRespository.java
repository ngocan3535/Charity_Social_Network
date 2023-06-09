/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dna.repository;

import com.dna.pojos.Auctions;
import com.dna.pojos.Post;
import com.dna.pojos.User;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Windows 10
 */
public interface AuctionRespository {
    List<Object[]> getBiddingInfo(int postId);
    boolean addBiddingPrice(Auctions auctions);
    List<Auctions> getListOfBiddingFromPost(Post post);
    BigDecimal getMaxAuctionPrice(Post post);
    boolean chooseWiner(Auctions auctions);
    Auctions findAuctionMethod(User user , Post post , BigDecimal priceBidding , Timestamp timestamp);
   List<Auctions> findAuctionAlreadyWinner(Post post);
   List<Auctions> checkHaveAuction(Post postHaveCurrentAuction);
}
