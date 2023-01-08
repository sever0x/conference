package com.conference.controller.moderator;

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
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> allUsers = userService.getAllUsers();
        req.setAttribute("allUsers", allUsers);
        req.getRequestDispatcher("permission.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getUserById(Integer.parseInt(req.getParameter("id")));
        user.setRole(Role.SPEAKER);
        userService.updateUser(user);
        resp.sendRedirect(req.getContextPath() + "/request");
    }
}