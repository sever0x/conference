package com.conference.controller.user;

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

        int id = (int) httpSession.getAttribute("id");
        User user = userService.getUserById(id);

        req.setAttribute("user", user);
        req.getRequestDispatcher("settings.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // FIXME Мы перепутали getParameter с getAttribute
        String newLogin = req.getParameter("login");
        String newEmail = req.getParameter("email");
        String newPassword = req.getParameter("password");
        String role = req.getParameter("role");
        String firstName= req.getParameter("first_name");
        String secondName = req.getParameter("second_name");


        HttpSession httpSession = req.getSession();

        httpSession.setAttribute("login", newLogin);
        httpSession.setAttribute("role", role);

        User user = userService.getUserById((int) httpSession.getAttribute("id"));
        user.setLogin(newLogin);
        user.setEmail(newEmail);
        user.setFirstName(firstName);
        user.setSecondName(secondName);

        userService.updateUser(user);

        resp.sendRedirect(req.getContextPath() + "/");

    }
}
