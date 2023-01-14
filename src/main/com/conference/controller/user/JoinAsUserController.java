package com.conference.controller.user;

import com.conference.service.EventService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/join")
public class JoinAsUserController extends HttpServlet {

    private EventService eventService = new EventService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("id");
        int eventId = Integer.parseInt(req.getParameter("eventId"));
        eventService.joinToEvent(userId, eventId);

        resp.sendRedirect(req.getContextPath() + "/welcome");
    }
}
