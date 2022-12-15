package com.conference.controller;

import com.conference.model.Event;
import com.conference.service.EventService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

@WebServlet("/edit/*" )
public class EventEditController extends HttpServlet {
    EventService eventService = new EventService();
    Event event = new Event();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       event = eventService.getEvent(1);
        req.setAttribute("event",event);
        req.getRequestDispatcher("/editEvent.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
