package com.conference.controller.speaker;

import com.conference.model.Event;
import com.conference.model.Topic;
import com.conference.service.EventService;
import com.conference.service.TopicService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Заменяет какой-то там контроллер, потом напишу название, если найду
 */
@WebServlet("/speaker/*")
public class JoinAsSpeakerController extends HttpServlet {

    private TopicService topicService = new TopicService();

    /**
     * По ивенту получаем все топики и отправляем на страницу
     * На странице будут отображены все топики и, в зависимости от того, есть ли у топика спикер,
     * будет доступна функция присоединиться к топику
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Topic> topics = topicService.getAllTopicsWithSpeakers(Integer.parseInt(req.getPathInfo().substring(1)));

        req.setAttribute("topics", topics);
        req.setAttribute("eventId", req.getPathInfo().substring(1));
        req.getRequestDispatcher("/topicList.jsp").forward(req, resp);
    }

    /**
     * Присоединение к топику (одобряет администратор)
     * Отсылается запрос модератору на присоединение к топику (заносится в отдельную таблицу)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int eventId = topicService.getEventIdByTopicId(Integer.parseInt(req.getParameter("topicId")));
        int topicId = Integer.parseInt(req.getParameter("topicId"));
        int userId = (int) req.getSession().getAttribute("id");

        topicService.makeRequestAsTopicSpeaker(userId, topicId);

        resp.sendRedirect(req.getContextPath() + "/speaker/" + eventId);
    }
}
