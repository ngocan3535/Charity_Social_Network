/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dna.repository;

import com.dna.pojos.Comments;
import com.dna.pojos.LikeComment;
import com.dna.pojos.Post;
import com.dna.pojos.User;
import java.util.List;

/**
 *
 * @author Windows 10
 */
public interface LikeCommentRepository {
    List<LikeComment> checkLikeComment(User userCurrentLoggedIn ,Comments commentLike);
    List<LikeComment> getLikeCommentsFromComment(Comments comment);
    boolean deleteListLikeCommentInComment(List<LikeComment> listLikeComments);
}
