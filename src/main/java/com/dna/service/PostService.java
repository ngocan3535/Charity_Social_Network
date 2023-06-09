/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dna.service;

import com.dna.pojos.LikePost;
import com.dna.pojos.Post;
import com.dna.pojos.User;
import java.util.List;

/**
 *
 * @author Windows 10
 */
public interface PostService {
    List<Object[]> getPostsUserProfile(String kw , String username);
    List<Object[]> getPostNewFeed(String kw , int page,String cateID);
    long countPost();
    long countPostByCategory(int id);
    boolean addNewPost(Post post);
    boolean updatePost(Post post);
    boolean deletePost(Post post);
    Post getPostId(String id);
    boolean likePost(String username , String postId ,LikePost likePost);
    boolean unLikePost(String username,String postId);
    Object[] getPostDetail(int id);
    public List<Object[]> getPostInteractMost();
    public  List<Object[]> getPostMostAuctionsRate();
    boolean deleteCpRelatedToPost(Post post);
}
