package com.hoteltop.common;

import com.hoteltop.model.User;
import com.hoteltop.util.exceptions.HotelTopTechnicalException;

import java.util.List;

/**
 * Created by Vlastelin on 08.04.2016.
 */
public interface UserService {

    public void createUser(User user);

    public void deleteUser(User user);

    public void editUser(User user);

    public User getUserInfo(Long id);

    public void increaseBonuses(User user, Long bonusPoints);

    public void changeDiscount(User user);

    public List<User> showListUsers(int page);

    public List<User> getAllUsers();

    public long getPageCount();
}
