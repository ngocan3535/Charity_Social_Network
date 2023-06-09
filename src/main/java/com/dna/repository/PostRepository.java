/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dna.repository;

import com.dna.pojos.Comments;
import com.dna.pojos.LikeComment;
import com.dna.pojos.LikePost;
import com.dna.pojos.Post;
import com.dna.pojos.ReportComment;
import com.dna.pojos.ReportPost;
import com.dna.pojos.User;
import java.util.List;

/**
 *
 * @author Windows 10
 */
public interface PostRepository {

    List<Object[]> getPostFromUser(String kw, String username);
    List<Object[]> getNewFeedPost(String kw, int page, String cateID);
    long countPost();
    long countPostByCategory(int id);
    boolean addNewPost(Post post);
    boolean updatePost(Post post);
    boolean deletePost(Post post);
    Post getPostId(String id);
    Post getPostID2(int id);
    boolean likePost(Post postLike, LikePost likePost);
    boolean unLikePost(Post postLike ,LikePost likePost);
    Object[] getPostDetail(int id);
    List<Object[]> getPostInteractMost();
    List<Object[]> getPostMostAuctionsRate();
    boolean deleteCpRelatedToPost( List<LikePost> likePosts, 
            List<Comments> commentses,  List<ReportPost> reportPosts );
}
