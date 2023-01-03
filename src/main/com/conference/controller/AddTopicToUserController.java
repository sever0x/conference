package com.conference.controller;

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

@WebServlet("/joinToTopic/*")

public class AddTopicToUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        TopicService topicService = new TopicService();
        HttpSession httpSession = req.getSession();
        String login = String.valueOf(httpSession.getAttribute("login"));
        User user = userService.getUserByLogin(login);
        Topic topic = topicService.getTopicById(Integer.parseInt(req.getPathInfo().substring(1,2)));
        int eventId = topicService.getEventById(Integer.parseInt(req.getPathInfo().substring(1,2)));
        int status = topicService.getEventById(Integer.parseInt(req.getPathInfo().substring(2)));
        if (status == 0) {
            user.setTopicStatus(0);
        } else {
            user.setTopicStatus(1);
        }


        userService.updateUserTopic(user, topic);
        resp.sendRedirect(req.getContextPath() + " /permission/" + eventId);

    }
}
