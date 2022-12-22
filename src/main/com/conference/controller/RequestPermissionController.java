package com.conference.controller;

import com.conference.model.Role;
import com.conference.model.User;
import com.conference.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/request")
public class RequestPermissionController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        List<User> list2 = userService.getAllUsers();
        for (User user : list2) {
//            int permission = user.getPermission();
//            System.out.println(permission);
//            String role = String.valueOf(user.getRole());
//            System.out.println(role);
//            if (permission == 1 && role== "USER") {
                System.out.println(user);
//            }
        }
    }
}