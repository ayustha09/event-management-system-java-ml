<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.webapp.model.Event" %>

<!DOCTYPE html>
<html>
<head>
    <title>List of Events : Emma’s Event Management</title>

    <link rel="stylesheet" type="text/css" href="assets/styles.css">
</head>
<body>
    <h1>List of Events</h1>

    <nav>
        <a href="index.jsp">Home</a>
        <a href="addEvent.jsp">Add New Event</a>
    </nav>

    <%
        List<Event> events = (List<Event>) request.getAttribute("events");
        if (events != null && !events.isEmpty()) {
    %>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Date</th>
            <th>Location</th>
            <th>Description</th>
            <th>Type</th>
            <th>Attendees</th>
            <th>Actions</th>
        </tr>
        <%
            for (Event event : events) {
        %>
        <tr>
            <td><%= event.getId() %></td>
            <td><%= event.getName() %></td>
            <td><%= event.getDate() %></td>
            <td><%= event.getLocation() %></td>
            <td><%= event.getDescription() %></td>
            <td><%= event.getType() %></td>
            <td><%= event.getAttendees() %></td>
            <td>
                <a href="edit?id=<%= event.getId() %>">Edit</a> |
                <a href="delete?id=<%= event.getId() %>" onclick="return confirm('Are you sure you want to delete ?')">Delete</a>
                <a href="rsvp?id=<%= event.getId() %>">RSVP</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <%
        } else {
    %>
        <p>No events found.</p>
    <%
        }
    %>
</body>
</html>
