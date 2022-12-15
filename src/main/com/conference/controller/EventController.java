package com.conference.controller;

import com.conference.model.Event;
import com.conference.model.Topic;
import com.conference.service.EventService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        String[] topicsName = req.getParameterValues("topic");

        Event event = new Event();
        event.setName(eventName);
        event.setDescribe(eventDescribe);
        event.setPlace("Kiev");

        List<Topic> topics = new ArrayList<>();

        for (String topicName : topicsName) {
            Topic topic = new Topic(topicName);
            topics.add(topic);
        }

        event.setTopics(topics);

        eventService.addEvent(event);

        resp.sendRedirect(req.getContextPath() + "/welcome");
    }
}