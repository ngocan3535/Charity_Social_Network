 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dna.repository;

import com.dna.pojos.User;
import java.util.List;

/**
 *
 * @author Windows 10
 */
public interface UserRepository {
    List<User> getUserProfile(String userName);
    boolean addUser(User user);
    boolean updateProfile(User user);
    List<User> getUsers(String username);
    List<User> checkUserName(String username);
    List<User> getUserIdLoggedIn(String UserId);
    List<User> getCurrentLoggedInUser(String username);
    User getUserID(String username);
    List<User> getUserAdminRole();
    List<Object[]> getUserLikeMost(String username);
    List<Object[]> getUserCommentMost(String username);
}
