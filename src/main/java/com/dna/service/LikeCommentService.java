/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dna.service;

/**
 *
 * @author Windows 10
 */
public interface LikeCommentService {
     boolean checkCommentUserLike(String userNameLoggedIn , String commentIdLike);
}
