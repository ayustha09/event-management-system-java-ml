<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.webapp.model.Event" %>

<%
    Event event = (Event) request.getAttribute("event");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Event : Emma’s Event Management</title>
    <link rel="stylesheet" type="text/css" href="assets/styles.css">
</head>
<body>
    <h1>Edit Event</h1>

    <nav>
        <a href="events">Back to Event List</a>
    </nav>

    <form action="edit" method="post" style="margin-top: 20px;">
        <input type="hidden" name="id" value="<%= event.getId() %>" />

        <div style="margin-bottom: 10px;">
            <label for="name">Event Name:</label><br>
            <input type="text" name="name" value="<%= event.getName() %>" required />
        </div>

        <div style="margin-bottom: 10px;">
            <label for="date">Date:</label><br>
            <input type="date" name="date" value="<%= event.getDate() %>" required />
        </div>

        <div style="margin-bottom: 10px;">
            <label for="location">Location:</label><br>
            <input type="text" name="location" value="<%= event.getLocation() %>" required />
        </div>

        <div style="margin-bottom: 10px;">
            <label for="description">Description:</label><br>
            <input type="text" name="description" value="<%= event.getDescription() %>" required />
        </div>

        <div style="margin-bottom: 10px;">
            <label for="attendees">Attendees:</label><br>
            <input type="number" name="attendees" min="0" value="<%= event.getAttendees() %>" required />
        </div>

        <div style="margin-bottom: 10px;">
            <label for="type">Event Type:</label><br>
            <select name="type" id="eventType" onchange="toggleOtherTypeInput()">
                <option value="Conference" <%= "Conference".equals(event.getType()) ? "selected" : "" %>>Conference</option>
                <option value="Wedding" <%= "Wedding".equals(event.getType()) ? "selected" : "" %>>Wedding</option>
                <option value="Workshop" <%= "Workshop".equals(event.getType()) ? "selected" : "" %>>Workshop</option>
                <option value="Party" <%= "Party".equals(event.getType()) ? "selected" : "" %>>Party</option>
                <option value="Seminar" <%= "Seminar".equals(event.getType()) ? "selected" : "" %>>Seminar</option>
                <option value="Birthday" <%= "Birthday".equals(event.getType()) ? "selected" : "" %>>Birthday</option>
                <option value="Festival" <%= "Festival".equals(event.getType()) ? "selected" : "" %>>Festival</option>
                <option value="Other" <%= !event.getType().matches("Conference|Wedding|Workshop|Party|Seminar|Birthday|Festival") ? "selected" : "" %>>Other</option>
            </select>
        </div>

        <div id="otherTypeDiv" style="display: none; margin-bottom: 10px;">
            <label for="customType">Enter Custom Type:</label><br>
            <input type="text" name="customType" id="customType" value="<%= (!event.getType().matches("Conference|Wedding|Workshop|Party|Seminar|Birthday|Festival")) ? event.getType() : "" %>" />
        </div>

        <input type="submit" value="Update Event" />
    </form>

    <script>
        function toggleOtherTypeInput() {
            var eventTypeSelect = document.getElementById("eventType");
            var otherTypeDiv = document.getElementById("otherTypeDiv");
            var customTypeInput = document.getElementById("customType");

            if (eventTypeSelect.value === "Other") {
                otherTypeDiv.style.display = "block";
                customTypeInput.setAttribute("required", "required");
            } else {
                otherTypeDiv.style.display = "none";
                customTypeInput.removeAttribute("required");
            }
        }

        window.onload = function() {
            toggleOtherTypeInput();
        };
    </script>
</body>
</html>
