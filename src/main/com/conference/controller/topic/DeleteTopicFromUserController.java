package com.conference.controller.topic;

import com.conference.model.Topic;
import com.conference.model.User;
import com.conference.service.TopicService;
import com.conference.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/deleteTopicFromUser/*")
public class DeleteTopicFromUserController extends HttpServlet {

    private UserService userService = new UserService();

    private TopicService topicService = new TopicService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        String login = String.valueOf(httpSession.getAttribute("login"));

        User user = userService.getUserByLogin(login);

        int topicId = Integer.parseInt(req.getPathInfo().substring(1,2));
        Topic topic = topicService.getTopicById(topicId);
        int eventId = topicService.getEventIdByTopicId(topicId);

        userService.deleteTopicFromUser(user, topic);

        resp.sendRedirect(req.getContextPath() + " /permission/" + eventId);
    }
}

