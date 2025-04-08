package com.example.webapp.servlet;

import com.example.webapp.dao.EventDAO;
import com.example.webapp.model.Event;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@MultipartConfig
public class EventServlet extends HttpServlet {

    private EventDAO eventDAO = new EventDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        String location = req.getParameter("location");
        String date = req.getParameter("date");

        List<Event> filteredEvents = eventDAO.getFilteredEvents(type, location, date);

        req.setAttribute("events", filteredEvents);
        req.getRequestDispatcher("eventList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        if (name != null && date != null && location != null && description != null) {
            Event event = new Event(0, name, date, location, description, type, attendees);
            eventDAO.addEvent(event);
        }

        resp.sendRedirect("events");
    }
}