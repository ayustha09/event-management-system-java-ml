<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.webapp.model.Event" %>

<!DOCTYPE html>
<html>
<head>
    <title>Emma’s Event Management</title>
    <link rel="stylesheet" type="text/css" href="assets/styles.css">
</head>
<body>
    <h1>Emma’s Event Management</h1>

    <nav>
        <a href="index.jsp">Home</a>
        <a href="events">Refresh Events</a>
    </nav>

    <form action="events" method="post" style="margin-bottom: 20px;">
        <div style="margin-bottom: 10px;">
            <label for="name">Event Name:</label><br>
            <input type="text" name="name" required />
        </div>

        <div style="margin-bottom: 10px;">
            <label for="date">Date:</label><br>
            <input type="date" name="date" required />
        </div>

        <div style="margin-bottom: 10px;">
            <label for="location">Location:</label><br>
            <input type="text" name="location" required />
        </div>

        <div style="margin-bottom: 10px;">
            <label for="description">Description:</label><br>
            <input type="text" name="description" required />
        </div>

        <div style="margin-bottom: 10px;">
            <label for="type">Event Type:</label><br>
            <select name="type" id="eventType" onchange="toggleOtherTypeInput()">
                <option value="Conference">Conference</option>
                <option value="Wedding">Wedding</option>
                <option value="Workshop">Workshop</option>
                <option value="Party">Party</option>
                <option value="Seminar">Seminar</option>
                <option value="Birthday">Birthday</option>
                <option value="Festival">Festival</option>
                <option value="Other">Other</option>
            </select>
        </div>
        
        <div id="otherTypeDiv" style="display:none; margin-bottom: 10px;">
            <label for="customType">Enter Custom Type:</label><br>
            <input type="text" name="customType" id="customType" />
        </div>
        

        <input type="submit" value="Add Event" />
    </form>

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
    <script>
        function toggleOtherTypeInput() {
            var eventTypeSelect = document.getElementById("eventType");
            var otherTypeDiv = document.getElementById("otherTypeDiv");
            if (eventTypeSelect.value === "Other") {
                otherTypeDiv.style.display = "block";
                document.getElementById("customType").setAttribute("required", "required");
            } else {
                otherTypeDiv.style.display = "none";
                document.getElementById("customType").removeAttribute("required");
            }
        }
        </script>
        
</body>
</html>
