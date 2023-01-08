package com.conference.controller.moderator;

import com.conference.model.Role;
import com.conference.model.User;
import com.conference.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/changeRoleToUser/*")
public class ChangePermissionToUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        User user = userService.getUserById(Integer.parseInt(req.getPathInfo().substring(1)));
        user.setRole(Role.USER);
        userService.changeUserPermission(user);
        userService.updateUser(user);
        resp.sendRedirect(req.getContextPath() + "/request");

    }



}
