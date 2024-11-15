CREATE TABLE Locations (
id INTEGER PRIMARY KEY,
name TEXT NOT NULL,
city TEXT NOT NULL,
type TEXT,
address TEXT
);
CREATE TABLE Schedules (
id INTEGER PRIMARY KEY,
location_id INTEGER,
day_of_week TEXT,
start_time TIME,
end_time TIME,
availability BOOLEAN,
FOREIGN KEY (location_id) REFERENCES Locations(id)
);
CREATE TABLE Instructors (
id INTEGER PRIMARY KEY,
name TEXT NOT NULL,
phone_number TEXT,
specialization TEXT,
cities_available TEXT
);
CREATE TABLE Offerings (
id INTEGER PRIMARY KEY,
location_id INTEGER,
lesson_type TEXT,
instructor_id INTEGER,
schedule_id INTEGER,
is_group BOOLEAN,
FOREIGN KEY (location_id) REFERENCES Locations(id),
FOREIGN KEY (instructor_id) REFERENCES Instructors(id),
FOREIGN KEY (schedule_id) REFERENCES Schedules(id)
);
CREATE TABLE Clients (
id INTEGER PRIMARY KEY,
name TEXT NOT NULL,
age INTEGER,
guardian_id INTEGER,
phone_number TEXT,
FOREIGN KEY (guardian_id) REFERENCES Clients(id)
);
CREATE TABLE Bookings (
id INTEGER PRIMARY KEY,
client_id INTEGER,
offering_id INTEGER,
booking_date DATE,
status TEXT,
FOREIGN KEY (client_id) REFERENCES Clients(id),
FOREIGN KEY (offering_id) REFERENCES Offerings(id)
);
CREATE TABLE Admins (
id INTEGER PRIMARY KEY,
name TEXT NOT NULL,
email TEXT
);
