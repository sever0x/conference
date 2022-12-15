package com.conference.controller;

import com.conference.model.Event;
import com.conference.service.EventService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/edit/*")
public class EventEditController extends HttpServlet {

    private EventService eventService = new EventService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int eventId = Integer.parseInt(req.getPathInfo().substring(1));
        Event event = eventService.getEvent(eventId);

        req.setAttribute("event", event);
        req.getRequestDispatcher("/editEvent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Event event = new Event();
        event.setId(Integer.parseInt(req.getPathInfo().substring(1)));
        event.setName(req.getParameter("name"));
        event.setDescribe(req.getParameter("describe"));
//       event.setDate(Timestamp.valueOf((String) req.getAttribute("date")));
        event.setPlace(req.getParameter("place"));
        eventService.updateEvent(event);

        resp.sendRedirect(req.getContextPath() + "/edit");
    }
}
