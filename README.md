**Event Management System**

Overview

This repository contains the implementation of a Spring Boot application for managing events, tickets, speakers, and schedules. The project demonstrates the use of RESTful APIs, transaction management, exception handling, and database interactions with MySQL. It also incorporates advanced entity relationships and mappings like One-to-One, One-to-Many, and Many-to-Many.



**Entity Relationships:**

**One-to-One Mapping:**

Event ↔ EventScheduleDetail

Ticket ↔ Person

**One-to-Many Mapping:**

Event ↔ Tickets

**Many-to-Many Mapping:**

Event ↔ Speaker




**Features**

**1)Event Management**

Retrieve event details by ID (GET /event/{id})

Retrieve all events (GET /event/all)

Save a new event (POST /event/save)

Delete an event by ID (DELETE /event/delete/{id})

Update an event (PUT /event/update)

Fetch event tickets filtered by price (GET /event/tickets/PriceGreaterThan/{price})

Fetch event tickets by location (case-insensitive) (GET /event/location/{location})

Fetch schedule details for an event (GET /event/eventScheduleDetail/{id})


**2)Ticket Management**

Retrieve ticket details by ID (GET /ticket/{id})

Retrieve all tickets (GET /ticket/all)

Fetch tickets filtered by the age of the ticket holder (GET /ticket/allByAge/{age})

**3)Person Management**

Retrieve person details by ID (GET /person/{id})

Retrieve all persons (GET /person/all)

**4)Schedule Management**

Retrieve schedule details by ID (GET /eventScheduleDetail/{id})

Retrieve all schedules (GET /eventScheduleDetail/all)

Save a new schedule (POST /eventScheduleDetail/save)

Delete an event schedule detail by ID (DELETE /event/delete/eventScheduleDetail/{id})

**5)Speaker Management**

Retrieve speaker details by ID (GET /speaker/{id})

Retrieve all speakers (GET /speaker/all)

Fetch speakers filtered by event count and experience (GET /speaker/eventCount/{eventCount}/experience/{experience})

Save a new speaker (POST /speaker/save)

Link a speaker to an event (POST /speaker/id/{speakerId}/eventId/{eventId})
