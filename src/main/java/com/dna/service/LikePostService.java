/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dna.service;

import com.dna.pojos.LikePost;
import com.dna.pojos.Post;
import com.dna.pojos.User;

/**
 *
 * @author Windows 10
 */
public interface LikePostService {
    boolean checkPostUserLike(String userNameLoggedIn , String postIdLike);
}
