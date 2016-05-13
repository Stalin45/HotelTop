package com.hoteltop.controller.impl;

import com.hoteltop.common.RoomService;
import com.hoteltop.common.UserService;
import com.hoteltop.model.Room;
import com.hoteltop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vlastelin on 02.05.2016.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/show/{page}", method = RequestMethod.GET)
    public ModelAndView showListUsers(@PathVariable int page) {
        Map<String, Object> modelAttrs = new HashMap<>();
        modelAttrs.put("users", userService.showListUsers(page));
        modelAttrs.put("page", page);
        modelAttrs.put("entity", "user");
        modelAttrs.put("pageCount", userService.getPageCount());
        return new ModelAndView("user/showUsers", modelAttrs);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createRoom(Model model) {
        model.addAttribute("user", new User());
        return "user/createUser";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String completeCreation(@RequestParam String name, @RequestParam String surname) {
        User user = new User(name, surname);
        userService.createUser(user);
        return "redirect:/user/create";
    }

    @RequestMapping(value = "/edit/{userId}", method = RequestMethod.GET)
    public ModelAndView editRoom(@PathVariable long userId) {
        return new ModelAndView("user/editUser", "user", userService.getUserInfo(userId));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String completeEdition(@RequestParam Long userId, @RequestParam String name,
                                  @RequestParam String surname) {
        User user = userService.getUserInfo(userId);
        user.setName(name);
        user.setSurname(surname);
        userService.editUser(user);
        return "redirect:/user/show/1";
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
    public String deleteRoom(@PathVariable long userId) {
        User user = new User();
        user.setUserId(userId);
        userService.deleteUser(user);
        return "redirect:/user/show/1";
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
