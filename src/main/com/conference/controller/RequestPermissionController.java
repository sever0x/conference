package com.conference.controller;

import com.conference.model.Event;
import com.conference.model.Role;
import com.conference.model.User;
import com.conference.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/request")
public class RequestPermissionController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService = new UserService();
        List<User> list2 = userService.getAllUsers();
        req.setAttribute("list2", list2);
        req.getRequestDispatcher("permission.jsp").forward(req, resp);
    }
}