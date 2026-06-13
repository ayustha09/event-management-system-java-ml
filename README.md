Emma's Event Management System


Overview

A full-stack web application for managing events, built with Java EE technologies. The system allows users to create, view, edit, delete, and RSVP for events. It also includes a built-in machine learning model that predicts the type of an event based on its description, date, and location — powered by the WEKA library.


Features


Add new events (name, date, location, description, event type, attendees)
View all events in a table with search and filter by date, location, or event type
Edit and delete events
RSVP for events with live attendee count updates
ML-based event type prediction using a trained J48 Decision Tree classifier



Tech Stack

TechnologyPurposeJava EE (JSP + Servlets)Backend logic and dynamic UIJDBCDatabase connectivitySQLiteLightweight local databaseWEKAMachine learning (event type prediction)HTML / CSSFrontend stylingApache TomcatWeb server and deploymentMavenProject build management


Machine Learning

The app predicts event types (e.g., Conference, Wedding, Party, Workshop) based on event details using a classifier trained with WEKA.

Models Compared

ModelAccuracyJ48 Decision Tree ✅ (Used in app)93%Random Forest93% (slightly higher error rate)

J48 was chosen for the final application due to better overall performance and lower error rate.

Training Details


Dataset: 100 events with balanced class distribution (.arff format)
Feature extraction: StringToWordVector filter for text descriptions
Validation: 10-fold cross-validation
Metrics: Accuracy, Precision, Recall, Confusion Matrix
Saved model: event_model_j48.model (stored in WEB-INF/)



Database Structure

Two entities with a one-to-many relationship:


Events — stores event details (name, date, location, description, type, attendees)
RSVPs — linked to events via event_id foreign key



Project Structure

emmasevents/
├── src/main/
│   ├── java/com/example/webapp/
│   │   ├── dao/          # Database access (EventDAO.java)
│   │   ├── model/        # Data models (Event.java)
│   │   ├── servlet/      # Request handlers
│   │   └── weka/         # ML trainer (EventTrainer.java)
│   └── webapp/
│       ├── WEB-INF/      # Config, trained model, dataset
│       ├── *.jsp         # UI pages
│       └── assets/       # CSS styles
