CREATE TABLE University (
    stuId char(5) primary key,
    lastName varchar(64),
    firstName varchar(64),
    major varchar(64),
    credits int
);
INSERT INTO University (stuId, lastName, firstName, major, credits) VALUES ('S1001', 'Smith', 'Tom', 'History', 90);
INSERT INTO University (stuId, lastName, firstName, major, credits) VALUES ('S1002', 'Chin', 'Ann', 'Math', 36);
INSERT INTO University (stuId, lastName, firstName, major, credits) VALUES ('S1005', 'Lee', 'Perry', 'History', 3);
INSERT INTO University (stuId, lastName, firstName, major, credits) VALUES ('S1010', 'Burns', 'Edward', 'Art', 63);
INSERT INTO University (stuId, lastName, firstName, major, credits) VALUES ('S1013', 'McCarthy', 'Owen', 'Math', 0);
INSERT INTO University (stuId, lastName, firstName, major, credits) VALUES ('S1015', 'Jones', 'Mary', 'Math', 42);
INSERT INTO University (stuId, lastName, firstName, major, credits) VALUES ('S1020', 'Rivera', 'Jane', 'CSC', 15);
CREATE TABLE Faculty (
    facId char(4) primary key,
    name varchar(64),
    department varchar(64),
    rank varchar(64)
);
INSERT INTO Faculty (facId, name, department, rank) VALUES ('F101', 'Adams', 'Art', 'Professor');
INSERT INTO Faculty (facId, name, department, rank) VALUES ('F105', 'Tanaka', 'CSC', 'Instructor');
INSERT INTO Faculty (facId, name, department, rank) VALUES ('F110', 'Byrne', 'Math', 'Assistant');
INSERT INTO Faculty (facId, name, department, rank) VALUES ('F115', 'Smith', 'History', 'Associate');
INSERT INTO Faculty (facId, name, department, rank) VALUES ('F221', 'Smith', 'CSC', 'Professor');
CREATE TABLE Class (
    classNumber char(7) primary key,
    facId char(4),
    schedule varchar(32),
    room varchar(32)
);
INSERT INTO Class (classNumber, facId, schedule, room) VALUES ('ART103A', 'F101', 'MWF9', 'H221');
INSERT INTO Class (classNumber, facId, schedule, room) VALUES ('CSC201A', 'F105', 'TuThF10', 'M110');
INSERT INTO Class (classNumber, facId, schedule, room) VALUES ('CSC203A', 'F105', 'MThF12', 'M110');
INSERT INTO Class (classNumber, facId, schedule, room) VALUES ('HST205A', 'F115', 'MWF11', 'H221');
INSERT INTO Class (classNumber, facId, schedule, room) VALUES ('MTH101B', 'F110', 'MTuTh9', 'H225');
INSERT INTO Class (classNumber, facId, schedule, room) VALUES ('MTH103C', 'F110', 'MWF11', 'H225');
CREATE TABLE Enroll (
    stuId char(5),
    classNumber char(7),
    grade char(1),
    primary key (stuId, classNumber)
);
INSERT INTO Enroll (stuId, classNumber, grade) VALUES ('S1001', 'ART103A', 'A');
INSERT INTO Enroll (stuId, classNumber, grade) VALUES ('S1001', 'HST205A', 'C');
INSERT INTO Enroll (stuId, classNumber, grade) VALUES ('S1002', 'ART103A', 'D');
INSERT INTO Enroll (stuId, classNumber, grade) VALUES ('S1002', 'CSC201A', 'F');
INSERT INTO Enroll (stuId, classNumber, grade) VALUES ('S1002', 'MTH103C', 'B');
INSERT INTO Enroll (stuId, classNumber) VALUES ('S1010', 'ART103A');
INSERT INTO Enroll (stuId, classNumber) VALUES ('S1010', 'MTH103C');
INSERT INTO Enroll (stuId, classNumber, grade) VALUES ('S1020', 'CSC201A', 'B');
INSERT INTO Enroll (stuId, classNumber, grade) VALUES ('S1020', 'MTH101B', 'A');
