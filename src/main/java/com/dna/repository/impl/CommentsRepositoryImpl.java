/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dna.repository.impl;

import com.dna.pojos.Comments;
import com.dna.pojos.LikeComment;
import com.dna.pojos.Post;
import com.dna.pojos.User;
import com.dna.repository.CommentsRepository;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Windows 10
 */
@Repository
@Transactional
public class CommentsRepositoryImpl implements CommentsRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Override
    public boolean addComment(Comments comments) {
        Session session = sessionFactory.getObject().getCurrentSession();
         try{
       
        session.save(comments);
        return true;
        }catch(HibernateException ex){
            System.err.println("== Add Comments To Post Have Error " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }
    
     @Override
    public List<Object[]> getCommentFromPost(int postId) {
      Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Post> pRoot = query.from(Post.class);
        Root<User> uRoot = query.from(User.class);  
        Root<Comments> cRoot = query.from(Comments.class);
        Predicate p1 = builder.equal(pRoot.get("id"),postId);
        Predicate p2 = builder.equal(uRoot.get("id"),cRoot.get("user"));
        Predicate p3 = builder.equal(pRoot.get("id"),cRoot.get("post"));
        query.where(builder.and(p1,p2,p3));
        query.multiselect(uRoot.get("name").as(String.class) ,
                          cRoot.get("postAt"),
                          cRoot.get("comment").as(String.class) , 
                          uRoot.get("avatar").as(String.class) ,
                          cRoot.get("likes") , 
                          cRoot.get("id")  , 
                          uRoot.get("username").as(String.class) , 
                          cRoot.get("checkReported").as(Boolean.class) , 
                          pRoot.get("id"));
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public Long countCommentList(int postId) {
      Session session = sessionFactory.getObject().getCurrentSession();
      CriteriaBuilder builder = session.getCriteriaBuilder();
      CriteriaQuery<Long> query = builder.createQuery(Long.class);
      Root<Comments> cRoot = query.from(Comments.class);
      Predicate p1 = builder.equal(cRoot.get("post"),postId);
      query.where(p1);
      query.multiselect(builder.count(cRoot.get("id")));
      Query q = session.createQuery(query);
      return  Long.parseLong(q.getSingleResult().toString());
    }

  

    @Override
    public boolean likeComment(Comments commentsLike , LikeComment likeComment ){
        Session session = sessionFactory.getObject().getCurrentSession();
        try{
        session.save(likeComment);
        session.update(commentsLike);
         return true;
        }catch(HibernateException ex)
        {
            System.err.println("Add Like To Comment Having Error" + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean unLikeComment(Comments commentsLike , LikeComment likeComment) {
       Session session = sessionFactory.getObject().getCurrentSession();
        try{
        session.delete(likeComment);
        session.update(commentsLike);
         return true;
        }catch(HibernateException ex)
        {
            System.err.println("Delete Like To Comment Having Error" + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Comments getCommentsById(String CommentId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        int id = Integer.parseInt(CommentId);
        return session.get(Comments.class,id);
    }

    @Override
    public Comments apiAddComments(Comments cmnts) {
        Session session = sessionFactory.getObject().getCurrentSession();
         try{
        session.save(cmnts);
        return cmnts;
        }catch(HibernateException ex){
            System.err.println("== Add Comments To Post Have Error " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Comments> getListCommentsFromPost(Post post) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Comments> query = builder.createQuery(Comments.class);
        Root<Comments> root = query.from(Comments.class);
        Predicate p = builder.equal(root.get("post").as(Post.class),post);
        query.where(p);
        query.select(root);
        Query q = session.createQuery(query);
        return q.getResultList();
    }
    
}
