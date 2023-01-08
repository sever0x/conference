package com.conference.controller.event;

import com.conference.model.Event;
import com.conference.model.Topic;
import com.conference.service.EventService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
        String[] topics = req.getParameterValues("topic");

        Event event = eventService.getEvent(Integer.parseInt(req.getPathInfo().substring(1)));
        event.setName(req.getParameter("name"));
        event.setDescribe(req.getParameter("describe"));
        event.setDate(req.getParameter("date"));

        List<Topic> topicList = event.getTopics();

        for (int i = 0; i < topics.length; i++) {
            Topic topic = topicList.get(i);
            topic.setName(topics[i]);
            topicList.set(i, topic);
        }
        event.setTopics(topicList);
        event.setPlace(req.getParameter("place"));
        eventService.updateEvent(event);

        resp.sendRedirect(req.getContextPath() + "/edit");
    }
}
