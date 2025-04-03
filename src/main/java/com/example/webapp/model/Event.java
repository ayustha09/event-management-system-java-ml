package com.example.webapp.model;

public class Event {
    private int id;
    private String name;
    private String date;
    private String location;
    private String description;
    private String type;
    private int attendees;

    public Event(int id, String name, String date, String location, String description, String type, int attendees) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.description = description;
        this.type = type;
        this.attendees = attendees;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDate() { return date; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public String getType() { return type; }
    public int getAttendees() { return attendees; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDate(String date) { this.date = date; }
    public void setLocation(String location) { this.location = location; }
    public void setDescription(String description) { this.description = description; }
    public void setType(String type) { this.type = type; }
    public void setAttendees(int attendees) { this.attendees = attendees; }
}
