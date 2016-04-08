package com.hoteltop.common;

import com.hoteltop.model.User;

/**
 * Created by Vlastelin on 08.04.2016.
 */
public interface UserService {

    public void createUser(User user);

    public void deleteUser(User user);

    public void editUser(User user);

    public User getUserInfo(Long id);

//    public void increaseBonuses();
}
