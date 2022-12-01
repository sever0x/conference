package com.conference.controller;

import com.mysql.cj.exceptions.DataReadException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("webapp/welcomePage.jsp");
        PrintWriter out = resp.getWriter();
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/epam",
                    "root","pass");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user where login = '"+login+
                    "'and password='"+password+"'");
            if(resultSet.next()){
                resp.sendRedirect("welcomePage.jsp");
            }else{
                resp.sendRedirect("userNotExist.jsp");
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
