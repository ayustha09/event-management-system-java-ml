package com.example.webapp.servlet;

import com.example.webapp.dao.EventDAO;
import com.example.webapp.model.Event;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/rsvp")
public class RSVPEventServlet extends HttpServlet {

    private EventDAO eventDAO = new EventDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Event event = eventDAO.getEventById(id);

        if (event != null) {
            int newCount = event.getAttendees() + 1;
            event.setAttendees(newCount);
            eventDAO.updateEvent(event);
        }

        resp.sendRedirect("events");
    }
}
