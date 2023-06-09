/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dna.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Windows 10
 */
@Entity
@Table(name = "likecomment")
public class LikeComment {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JoinColumn(name ="user_id")
    private User user;
     @ManyToOne()
    @JoinColumn(name ="comment_id")
    private Comments comment;
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comments getComment() {
        return comment;
    }

    public void setComment(Comments comment) {
        this.comment = comment;
    }
}
