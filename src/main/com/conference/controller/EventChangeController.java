package com.conference.controller;

import com.conference.config.ConnectionConfig;
import com.conference.model.Event;
import com.conference.service.EventService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.Provider;
import java.sql.*;
@WebServlet("/changeEvent")
public class EventChangeController extends HttpServlet {
    EventService eventService = new EventService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       Event event = new Event();
       event.setName(req.getParameter("name"));
       event.setDescribe( req.getParameter("describe"));
//       event.setDate(Timestamp.valueOf((String) req.getAttribute("date")));
       event.setPlace(req.getParameter("place"));
       eventService.updateEvent(event);

        req.getRequestDispatcher("/welcomePage.jsp").forward(req,resp);

    }
}
