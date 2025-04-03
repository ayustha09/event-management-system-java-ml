<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Event :  Emma’s Event Management</title>
    <link rel="stylesheet" type="text/css" href="assets/styles.css">
</head>
<body>
    <h1>Add New Event</h1>

    <nav>
        <a href="events">View All Events</a>
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
            <label for="attendees">Attendees:</label><br>
            <input type="number" name="attendees" min="0" value="0" required />
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
