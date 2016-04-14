package com.hoteltop.common.impl;

import com.hoteltop.common.RoomStatusCalendarService;
import com.hoteltop.common.UserService;
import com.hoteltop.dao.UserDAO;
import com.hoteltop.model.RoomStatusCalendar;
import com.hoteltop.model.User;

/**
 * Created by Vlastelin on 08.04.2016.
 */
public class RoomStatusCalendarServiceImpl implements RoomStatusCalendarService {

    UserDAO userDAO = null;

    public void createUser(User user) {
        //auth?
        userDAO.create(user);
    }

    public void deleteUser(User user) {
        userDAO.delete(user);
    }

    public void editUser(User user) {
        userDAO.merge(user);
        userDAO.update(user);
    }

    public User getUserInfo(Long id) {
        return userDAO.findById(id);
    }

    public void changeDiscount() {
    //   Billing     ???
    }

    public void increaseBonuses(User user) {

    }
}
