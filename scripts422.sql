CREATE TABLE person (
Id INTEGER PRIMARY KEY,
Name VARCHAR ,
Age INTEGER,
DriverLicence BOOLEAN);

CREATE TABLE car (
Id SERIAL PRIMARY KEY,
Brand VARCHAR,
Model VARCHAR,
Price INTEGER);

CREATE TABLE carOwner(
Id_CarOwner serial,
Person_id INTEGER REFERENCES Person(id),
Car_id INTEGER REFERENCES Car(id));