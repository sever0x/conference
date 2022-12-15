package com.conference.controller;

import com.conference.config.ConnectionConfig;
import com.conference.model.Role;
import com.conference.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            Statement statement = ConnectionConfig.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user where login = '" + login +
                    "'and password='" + password + "'");

            if (resultSet.next()) {
                req.getSession().setAttribute("id", resultSet.getInt("id"));
                req.getSession().setAttribute("password", password);
                req.getSession().setAttribute("login", login);
                req.getSession().setAttribute("role", Role.USER);


//                req.getRequestDispatcher("welcomePage.jsp").forward(req, resp);
                resp.sendRedirect(req.getContextPath() + "/welcome");
            } else {
                req.getRequestDispatcher("userNotExist.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
