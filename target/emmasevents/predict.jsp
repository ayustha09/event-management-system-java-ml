<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Predict Event Type</title>
    <link rel="stylesheet" type="text/css" href="assets/styles.css">
</head>
<body>
    <h1>Predict Event Type</h1>

    <nav>
        <a href="index.jsp">Home</a>
        <a href="events">View All Events</a>
    </nav>

    <form action="predict" method="post">
        <div style="margin-bottom: 10px;">
            <label for="name">Event Name:</label><br>
            <input type="text" name="name" required />
        </div>
        <div style="margin-bottom: 10px;">
            <label for="date">Date (yyyy-mm-dd):</label><br>
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

        <input type="submit" value="Predict Type" />
    </form>

    <%
        String prediction = (String) request.getAttribute("prediction");
        if (prediction != null) {
    %>
        <h2 style="margin-top: 30px;">Predicted Event Type: <%= prediction %></h2>
    <%
        }
    %>
</body>
</html>
