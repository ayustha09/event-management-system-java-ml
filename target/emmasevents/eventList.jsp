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
    <h1>Event Management</h1>

    <nav>
        <a href="index.jsp">Home</a>
        <a href="addEvent.jsp">Add New Event</a>
    </nav>

    <h2>Search & Filter</h2>
    <form method="get" action="events" style="margin-bottom: 20px;">
        <div style="margin-right: 10px; display:inline-block;">
            <label for="type">Type:</label><br>
            <input type="text" name="type" placeholder="e.g. Conference, Wedding etc." />
        </div>

        <div style="margin-right: 10px; display:inline-block;">
            <label for="location">Location:</label><br>
            <input type="text" name="location" placeholder="Enter location" />
        </div>

        <div style="margin-right: 10px; display:inline-block;">
            <label for="date">Date:</label><br>
            <input type="date" name="date" />
        </div>

        <div style="display:inline-block;">
            <label>&nbsp;</label><br>
            <input type="submit" value="Search" />
        </div>
    </form>

    <%
        List<Event> events = (List<Event>) request.getAttribute("events");
        if (events != null && !events.isEmpty()) {
    %>
    <h1>List of Events</h1>
    <div style="margin-top: 30px;">
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
                    <a href="edit?id=<%= event.getId() %>" class="action-btn edit-btn">Edit</a>
<a href="delete?id=<%= event.getId() %>" class="action-btn delete-btn" onclick="return confirm('Are you sure?')">Delete</a>
                    <a href="rsvp?id=<%= event.getId() %>" class="action-btn rsvp-btn">RSVP</a>
                    
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
    <%
        } else {
    %>
    <p>No events found.</p>
    <%
        }
    %>
</body>
</html>
