/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dna.service;

import com.dna.pojos.Notifications;
import com.dna.pojos.User;
import java.util.List;

/**
 *
 * @author Windows 10
 */
public interface NotificationService {
    List<Object[]> getNotificationFromUser(String UserName);
    boolean addNotifications(String userCurrentLoggedInName , String postIdHaveUser , Notifications notifications);
    boolean addNotificationAdmin(User adminSendReport , User userReceiveReport , Notifications notifications);
}
