/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dna.service;

import com.dna.pojos.Comments;
import com.dna.pojos.LikeComment;
import java.util.List;

/**
 *
 * @author Windows 10
 */
public interface CommentsService {
    boolean addComments(String Username , String PostId , String commentText  , Comments comments);
    List<Object[]> getCommentFromPost(int postId);
    Long countCommentList(int postId);
    boolean likeComment(String username , String commentId ,LikeComment likeComment );
    boolean unLikeComment(String username , String CommentId);
    Comments getCommentsID(String ID);
    Comments apiAddComments(String contentComment , String Username , int PostID);
}
