/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dna.repository.impl;

import com.dna.pojos.Comments;
import com.dna.pojos.Post;
import com.dna.pojos.ReportComment;
import com.dna.pojos.ReportPost;
import com.dna.repository.ReportRepository;
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
public class ReportRepositoryImpl implements ReportRepository{

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public boolean addReportPost(ReportPost reportPost) {
        Session session = sessionFactory.getObject().getCurrentSession();
         try{
       
        session.save(reportPost);
        return true;
        }catch(HibernateException ex){
            System.err.println("== Add Report Post Error " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addReportComment(ReportComment reportComment) {
        Session session = sessionFactory.getObject().getCurrentSession();
         try{
       
        session.save(reportComment);
        return true;
        }catch(HibernateException ex){
            System.err.println("== Add Report Comment Error " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ReportPost> listReporPostt() {
       Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ReportPost> query = builder.createQuery(ReportPost.class);
        Root<ReportPost> rRoot = query.from(ReportPost.class);
        query.select(rRoot);
        query.orderBy(builder.desc(rRoot.get("id")));
        Query q = session.createQuery(query);
        return q.getResultList();
    }
    
    @Override
    public List<ReportComment> listReportComments() {
       Session session = sessionFactory.getObject().getCurrentSession();
       CriteriaBuilder builder = session.getCriteriaBuilder();
       CriteriaQuery<ReportComment> query = builder.createQuery(ReportComment.class);
       Root<ReportComment> rRoot = query.from(ReportComment.class);
       query.select(rRoot);
       query.orderBy(builder.desc(rRoot.get("id")));
       Query q = session.createQuery(query);
       return q.getResultList();
    }

    @Override
    public ReportPost getReportPostID(int reportID) {
       Session session = sessionFactory.getObject().getCurrentSession();
       return session.get(ReportPost.class,reportID);
    }
    
      @Override
    public ReportComment getReportCommentID(int i) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return session.get(ReportComment.class, i);
    }


    @Override
    public boolean removePost(ReportPost reportedPost , Post postSelected) {
       Session session = sessionFactory.getObject().getCurrentSession();
         try{
        session.update(postSelected);
        session.update(reportedPost);
        return true;
        }catch(HibernateException ex){
            System.err.println("== Update Post and Report have Error " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }
    
      @Override
    public boolean removeComment(ReportComment repotedComment , Comments commentSelected) {
         Session session = sessionFactory.getObject().getCurrentSession();
         try{
        session.update(commentSelected);
        session.update(repotedComment);
        return true;
        }catch(HibernateException ex){
            System.err.println("== Update Comment and Report have Error " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deniedRemovePost(ReportPost reportedPost) {
        Session session = sessionFactory.getObject().getCurrentSession();
         try{
        session.update(reportedPost);
        return true;
        }catch(HibernateException ex){
            System.err.println("== Update  Report have Error " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

  

    @Override
    public boolean deniedRemoveComment(ReportComment reportedComment) {
         Session session = sessionFactory.getObject().getCurrentSession();
         try{
        session.update(reportedComment);
        return true;
        }catch(HibernateException ex){
            System.err.println("== Update  Report have Error " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ReportPost> getListReportPostFromPost(Post post) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ReportPost> query = builder.createQuery(ReportPost.class);
        Root<ReportPost> root = query.from(ReportPost.class);
        Predicate p = builder.equal(root.get("postReport").as(Post.class),post);
        query.where(p);
        query.select(root);
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<ReportComment> getListReportCommentFromComment(Comments cmnts) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ReportComment> query = builder.createQuery(ReportComment.class);
        Root<ReportComment> root = query.from(ReportComment.class);
        Predicate p = builder.equal(root.get("commentsReport"),cmnts);
        query.where(p);
        query.select(root);
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public boolean  deleteListOfReportComment(List<ReportComment> reportComments) {
        Session session = sessionFactory.getObject().getCurrentSession();
            try{
                
             for(ReportComment item: reportComments)
                session.delete(item);
          
            return true;
            }catch(HibernateException ex){
                System.err.println("== Delete ReportComments having  Error " + ex.getMessage());
                ex.printStackTrace();
            }
            return false;
    }

  
   
    
}
