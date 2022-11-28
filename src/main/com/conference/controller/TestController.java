package com.conference.controller;

import com.conference.dao.UserDAO;
import com.conference.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/hello")
public class TestController extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userDAO.index();

        /*
        * Просто через PrintWriter выводит инфу о юзерах */
//        for (User user : users) {
//            resp.getWriter().println("Login: " + user.getLogin() + " | Password: " + user.getPassword());
//        }

        req.setAttribute("users", users);
        req.getRequestDispatcher("test.jsp").forward(req, resp);
    }
}
