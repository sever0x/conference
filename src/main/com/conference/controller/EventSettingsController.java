package com.conference.controller;

import com.conference.model.Event;
import com.conference.model.Role;
import com.conference.service.EventService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/edit")
public class EventSettingsController extends HttpServlet {

    private EventService eventService = new EventService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession httpSession = req.getSession();
        Role role = Role.valueOf((String) req.getSession().getAttribute("role"));

        if (role.equals(Role.MODERATOR)) {
            List<Event> events = eventService.getAllEvent();
            req.setAttribute("events", events);
            req.getRequestDispatcher("edit.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("userNotExist.jsp").forward(req, resp);
        }


    }
}
