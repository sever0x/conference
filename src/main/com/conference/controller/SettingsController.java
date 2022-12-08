package com.conference.controller;

import com.conference.dao.UserDAO;
import com.conference.model.User;
import com.conference.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/settings")
public class SettingsController extends HttpServlet {
    private UserDAO userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
//        String login = (String) httpSession.getAttribute("login");
//        User user = userService.getUserByLogin(login);

        int id = (int) httpSession.getAttribute("id");
        User user = userService.getUserById(id);

        req.setAttribute("user",user);
        req.getRequestDispatcher("settings.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String newLogin = (String)req.getAttribute("login");
       String newEmail = (String)req.getAttribute("email");
       String newPassword = (String)req.getAttribute("password");

        HttpSession httpSession = req.getSession();
        String oldLogin = (String) httpSession.getAttribute("login");
        if(newLogin!=oldLogin){
            httpSession.setAttribute("login",newLogin);

        }








    }
}
