package com.conference.controller;

import com.conference.model.Event;
import com.conference.service.EventService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/eventRegistration")
public class EventController extends HttpServlet {

    private EventService eventService = new EventService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("eventRegistration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eventName = req.getParameter("name");
        String eventDescribe = req.getParameter("descr");
        Event event = new Event();
        event.setName(eventName);
        event.setDescribe(eventDescribe);
        eventService.addEvent(event);

        resp.sendRedirect(req.getContextPath() + "/welcome");
    }
}