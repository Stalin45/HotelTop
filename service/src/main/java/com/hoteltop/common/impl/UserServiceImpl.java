package com.hoteltop.common.impl;

import com.hoteltop.common.UserService;
import com.hoteltop.dao.UserDAO;
import com.hoteltop.dao.impl.UserDAOImpl;
import com.hoteltop.model.User;
import com.hoteltop.util.exceptions.HotelTopTechnicalException;

import java.util.List;

/**
 * Created by Vlastelin on 08.04.2016.
 */
public class UserServiceImpl implements UserService {

    private final static int POINTS_DISCOUNT_COEFFICIENT = 10;

    private final static byte MAX_DISCOUNT_PER_PURCHASE = 5;

    private final static byte MAX_DISCONT_TOTAL = 30;

    private static final UserDAO userDAO = new UserDAOImpl();

    public void createUser(User user) {
        //auth?
        userDAO.create(user);
    }

    /**
     * Deletes existing user
     *
     * @param user the user
     */
    public void deleteUser(User user) {
        userDAO.merge(user);
        userDAO.delete(user);
    }

    /**
     * Edits user
     *
     * @param user the user
     */
    public void editUser(User user) {
        userDAO.merge(user);
        userDAO.update(user);
    }

    /**
     * Gets user with detailed info
     *
     * @param id the id of user
     * @return user object
     */
    public User getUserInfo(Long id) {
        return userDAO.findById(id);
    }

    /**
     * Changes discount for user
     *
     * @param user the user
     */
    public void changeDiscount(User user) {
        long currentBonusPoints = user.getBonusPoints();
        byte currentDiscount = user.getDiscountPerc();
        long increaseDiscount = currentBonusPoints / POINTS_DISCOUNT_COEFFICIENT;

        if (increaseDiscount > MAX_DISCOUNT_PER_PURCHASE) {
            increaseDiscount = MAX_DISCOUNT_PER_PURCHASE;
        }

        if (currentDiscount + increaseDiscount > MAX_DISCONT_TOTAL) {
            user.setDiscountPerc(MAX_DISCONT_TOTAL);
        } else {
            user.setDiscountPerc((byte) increaseDiscount);
        }

        user.setBonusPoints(currentBonusPoints - increaseDiscount * POINTS_DISCOUNT_COEFFICIENT);
        editUser(user);
    }

    /**
     * Increase amount of bonus points for the user
     *
     * @param user the user
     * @param bonusPoints count of points to add
     */
    public void increaseBonuses(User user, Long bonusPoints) {
        user.setBonusPoints(bonusPoints);
        editUser(user);
    }

    /**
     * Shows list of users for one page
     *
     * @param page page number
     * @return list of users
     */
    public List<User> showListUsers(int page) {
        return userDAO.getList(page);
    }
}
