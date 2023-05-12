/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dna.service.impl;

import com.dna.pojos.Comments;
import com.dna.pojos.LikeComment;
import com.dna.repository.CommentsRepository;
import com.dna.repository.PostRepository;
import com.dna.repository.UserRepository;
import com.dna.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dna.pojos.Post;
import com.dna.pojos.User;
import com.dna.repository.LikeCommentRepository;
import freemarker.core.Comment;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Windows 10
 */
@Service
@Transactional
public class CommentsServiceImpl implements CommentsService{
     @Autowired
     private PostRepository postRepository;
     @Autowired
     private UserRepository userRepository;
     @Autowired
     private CommentsRepository commentsRepository;
     @Autowired
     private LikeCommentRepository likeCommentRepository;
    @Override
    public boolean addComments(String Username, String PostId, String commentText ,  Comments comments) {
        User user = this.userRepository.getCurrentLoggedInUser(Username).get(0);
        Post postUserLike = this.postRepository.getPostId(PostId);
        comments.setComment(commentText);
        comments.setUser(user);
        comments.setPost(postUserLike);
        comments.setCheckReported(false);
       Calendar cal = Calendar.getInstance();
        comments.setPostAt(new Timestamp(cal.getTimeInMillis()));
        return this.commentsRepository.addComment(comments);
    }

    @Override
    public List<Object[]> getCommentFromPost(int postId) {
        return this.commentsRepository.getCommentFromPost(postId);
    }

    @Override
    public Long countCommentList(int postid) {
       return this.commentsRepository.countCommentList(postid);
    }

    @Override
    public boolean likeComment(String username , String commentId ,LikeComment likeComment ) {
       User userLikeComment = this.userRepository.getCurrentLoggedInUser(username).get(0);
       Comments commentsUserLike = this.commentsRepository.getCommentsById(commentId);
       int currentCommentLikes = commentsUserLike.getLikes() + 1;
       likeComment.setUser(userLikeComment);
       likeComment.setComment(commentsUserLike);
       commentsUserLike.setLikes(currentCommentLikes);
       return this.commentsRepository.likeComment(commentsUserLike, likeComment);
    }

    @Override
    public boolean unLikeComment(String username , String CommentId) {
        User userLikeComment = this.userRepository.getCurrentLoggedInUser(username).get(0);
       Comments commentsUserLike = this.commentsRepository.getCommentsById(CommentId);
       int currentCommentLikes = commentsUserLike.getLikes() - 1;
       commentsUserLike.setLikes(currentCommentLikes);
       LikeComment likeComment = this.likeCommentRepository.checkLikeComment(userLikeComment, commentsUserLike).get(0);
       return this.commentsRepository.unLikeComment(commentsUserLike, likeComment);
    }

    @Override
    public Comments getCommentsID(String commentID) {
       return this.commentsRepository.getCommentsById(commentID);
    }

    @Override
    public Comments apiAddComments(String contentComment , String Username , int PostID) {
        User user = this.userRepository.getCurrentLoggedInUser(Username).get(0);
        Post postUserLike = this.postRepository.getPostID2(PostID);
        Calendar cal = Calendar.getInstance();
        Comments c = new Comments();
        c.setComment(contentComment);
        c.setUser(user);
        c.setPost(postUserLike);
        c.setCheckReported(false);
        c.setPostAt(new Timestamp(cal.getTimeInMillis()));
        return this.commentsRepository.apiAddComments(c);
    }
    
    
    
}
