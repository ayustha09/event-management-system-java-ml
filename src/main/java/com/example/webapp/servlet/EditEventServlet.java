package com.example.webapp.servlet;

import com.example.webapp.dao.EventDAO;
import com.example.webapp.model.Event;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/edit")
public class EditEventServlet extends HttpServlet {

    private EventDAO eventDAO = new EventDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Event event = eventDAO.getEventById(id);
        req.setAttribute("event", event);
        req.getRequestDispatcher("editEvent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String date = req.getParameter("date");
        String location = req.getParameter("location");
        String description = req.getParameter("description");
        String type = req.getParameter("type");
        int attendees = Integer.parseInt(req.getParameter("attendees"));

        if ("Other".equals(type)) {
            String customType = req.getParameter("customType");
            if (customType != null && !customType.trim().isEmpty()) {
                type = customType.trim();
            }
        }

        Event updatedEvent = new Event(id, name, date, location, description, type, attendees);
        eventDAO.updateEvent(updatedEvent);
        resp.sendRedirect("events");
    }
}
