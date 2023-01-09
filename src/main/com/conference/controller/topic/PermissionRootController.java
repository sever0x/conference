package com.conference.controller.topic;

import com.conference.model.Event;
import com.conference.model.Topic;
import com.conference.model.User;
import com.conference.service.EventService;
import com.conference.service.TopicService;
import com.conference.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/permission/*")
public class PermissionRootController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        String login = (String) req.getSession().getAttribute("login");
        UserService userService = new UserService();
        User user = userService.getUserByLogin(login);

        userService.addUserPermission(user);

        EventService eventService = new EventService();
        int eventId = Integer.parseInt(req.getPathInfo().substring(1));

        Event event = eventService.getEvent(eventId);
        httpSession.setAttribute("id", event.getId());

        req.setAttribute("event", event);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/topicListOld.jsp").forward(req, resp);


//        req.getRequestDispatcher("topicList").forward(req,resp);
    }
}
