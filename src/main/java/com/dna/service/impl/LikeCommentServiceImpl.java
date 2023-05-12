/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dna.service.impl;

import com.dna.pojos.Comments;
import com.dna.pojos.LikeComment;
import com.dna.pojos.Post;
import com.dna.pojos.User;
import com.dna.repository.CommentsRepository;
import com.dna.repository.LikeCommentRepository;
import com.dna.repository.UserRepository;
import com.dna.service.LikeCommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Windows 10
 */
@Service
@Transactional
public class LikeCommentServiceImpl implements LikeCommentService{
    @Autowired
    private CommentsRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LikeCommentRepository likeCommentRepository;
    @Override
    public boolean checkCommentUserLike(String userNameLoggedIn, String commentIdLike) {
        User userLoggedIn = this.userRepository.getCurrentLoggedInUser(userNameLoggedIn).get(0);
        Comments commentUserLike = this.commentRepository.getCommentsById(commentIdLike);
        List<LikeComment>  likeComments = this.likeCommentRepository.checkLikeComment(userLoggedIn, commentUserLike);
        if(!(likeComments.isEmpty()))
            return true;
        else
            return false;
    }
    
}
