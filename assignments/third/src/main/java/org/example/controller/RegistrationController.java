package org.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.example.dao.UserDaoImpl;
import org.example.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.example.model.User;

@Controller
@Validated
public class RegistrationController {
    @Autowired
    public UserDaoImpl userDao;
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("user", new User());
        return mav;
    }

    @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
                                @Valid @ModelAttribute("user") User user) {

        try {
            userDao.register(user);
        }catch (UserAlreadyExistException e){
            ModelAndView mav = new  ModelAndView("register");
            mav.addObject("message","user already exist");
            return mav;
        }


        return new ModelAndView("welcome", "username", user.getUsername());
    }
}