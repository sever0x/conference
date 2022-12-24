package com.conference.controller;

import com.conference.model.Topic;
import com.conference.model.User;
import com.conference.service.TopicService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/showSpeakerTopic")
public class SpeakerTopicController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
       User user= (User) httpSession.getAttribute("login");
        TopicService topicService = new TopicService();
       List<Topic> topics= topicService.getAllTopicsByUser(user);
        for (Topic topic : topics) {
            System.out.println(topic);
        }

    }


}
