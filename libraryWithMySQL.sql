CREATE TABLE Member
(
SSN Integer,
DriverLicState VARCHAR(50),
DriverLicNum VARCHAR(50),
FName VARCHAR(50),
LName VARCHAR(50),
Street VARCHAR(255),
City VARCHAR(50),
State VARCHAR(50),
PCode VARCHAR(50),
Phone VARCHAR(50),
Email VARCHAR(50),
Passwd VARCHAR(50),
CONSTRAINT MemberSSN PRIMARY KEY (SSN)
);

CREATE TABLE Librarian
(
SSN Integer,
FName VARCHAR(50),
LName VARCHAR(50),
Street VARCHAR(255),
City VARCHAR(50),
State VARCHAR(50),
PCode VARCHAR(50),
Phone VARCHAR(50),
Email VARCHAR(50),
Passwd VARCHAR(50),
CONSTRAINT LibrarianSSN PRIMARY KEY (SSN)
);

CREATE TABLE BookTitle
(
CallNumber VARCHAR(50),
Name VARCHAR(255),
Author VARCHAR(50),
Edition VARCHAR(50),
ISBN VARCHAR(50),
PublishedYear Integer,
Publisher VARCHAR(50),
CONSTRAINT CallNumber PRIMARY KEY (CallNumber)
);

CREATE TABLE Book
(
BookID Integer,
CallNumber VARCHAR(50),
BorrowerSSN Integer,
DueDate VARCHAR(50),
LibrarianSSN Integer,
CONSTRAINT BookID PRIMARY KEY (BookID),
CONSTRAINT MemberBook FOREIGN KEY (BorrowerSSN) REFERENCES Member(SSN),
CONSTRAINT LibrarianBook FOREIGN KEY (LibrarianSSN) REFERENCES Librarian(SSN),
CONSTRAINT TitleBook FOREIGN KEY (CallNumber) REFERENCES BookTitle(CallNumber)
);

CREATE TABLE Hold
(
SSN Integer,
CallNumber VARCHAR(50),
HoldDate Date,
CONSTRAINT HoldMember FOREIGN KEY (SSN) REFERENCES Member(SSN),
CONSTRAINT HoldTitle FOREIGN KEY (CallNumber) REFERENCES BookTitle(CallNumber)
);

INSERT INTO Member VALUES (123123123,'PA','P123-12-123456789','John','Doe','123 Main St','Pittsburgh','PA','15244','412-555-5262','johndoe@hotmail.com','123');
INSERT INTO Member VALUES (111222333,'PA','P321-14-123456','Jane','Doe','456 Elm St','Pittsburgh','PA','15215','412-555-4635','janedoe@hotmail.com','111');
INSERT INTO Member VALUES (123456789,'PA','P111-22-654321','Stan','Dardeviation','1 Sigma Way','Pittsburgh','PA','15213','412-555-4335','stan@hotmail.com','411');
INSERT INTO Member VALUES (111111111,'PA','P334-23-654246','George','Bush','1600 Pennsylvania Avenue','Washington','PA','22213','203-555-4335','president@whitehouse.gov','111');
INSERT INTO Member VALUES (222222222,'PA','P331-25-644321','William','Clinton','4532 W 125th Street','New York','NY','05213','201-555-4335','bill@clinton.com','222');

INSERT INTO Librarian VALUES (321321321,'Joe','Bloggs','421 Some Place','Pittsburgh','PA','15206','4125551212','montet@manyatta.com','321');

INSERT INTO BookTitle VALUES ('TK5105.888.J37 2018','Web Design and Development Black Book','Jarol, Scott','','',2018,'Coriolis Group Books. Albany, NY');
INSERT INTO BookTitle VALUES ('QA76.625.M33 2015','Visual Basic Programmer''s Guide to Web Development','Martiner, William','1st','0471193828',2015,'Wiley Computer Pub. New York');
INSERT INTO BookTitle VALUES ('QA76.73.P224A85 2018','Core PHP Programming : Using PHP to Build Dynamic Web Sites','Atkinson, Leon','','',2018,'Prentice Hall PTR. Upper Saddle River, NJ');
INSERT INTO BookTitle VALUES ('QA76.73.J38K66 2018','Java Programming for Dummies','Koosis, Donald J','3rd','',2018,'IDG Books Worldwide. Foster City, CA');
INSERT INTO BookTitle VALUES ('QA76.9.D3E57 2020','Fundamentals of Database Systems','Elmasri, Ramez; Navathe, Shamkant B.','3rd','0805317554',2020,'Addison-Wesley Longman');
INSERT INTO BookTitle VALUES ('QA76.73.J38J47 2015','Java Database Programming','Jepson, Brian','','',2015,'Wiley Computer Pub. New York');
INSERT INTO BookTitle VALUES ('QA76.L2967 2018','Information Systems and the Internet : A Problem-solving Approach','Laudon, Kenneth C.; Laudon, Jane Price','4th','0030225779',2018,'Dryden Press. Fort Worth, TX');
INSERT INTO BookTitle VALUES ('QA76.9.A43H67 2018','Computer Algorithms','Horowitz, Ellis','','',2018,'Computer Science Press. New York');
INSERT INTO BookTitle VALUES ('HD38.D68','The Effective Executive','Drucker, Peter Ferdinand','1st','0887306128',1967,'Harper & Row. New York');
INSERT INTO BookTitle VALUES ('HF5548.2.S7728 2018','Business Data Communications','Stallings, William','3rd','013594581X',2018,'Prentice Hall. Upper Saddle River, N.J.');
INSERT INTO BookTitle VALUES ('HD58.82.D38 2018','Working Knowledge : How Organizations Manage What They Know','Davenport, Thomas H.','','0875846556',2018,'Harvard Business School Press. Boston, Mass');
INSERT INTO BookTitle VALUES ('HD30.25.A53 2023','An Introduction to Management Science : Quantitative Approaches to Decision Making','Anderson, David Ray','9th','0324003242',2023,'Southwestern Pub Co');
INSERT INTO BookTitle VALUES ('HD9696.63.U62C585 2023','The New New Thing: A Silicon Valley Story','Lewis, Michael','','0393048136',2023,'W.W. Norton & Company. New York');
INSERT INTO BookTitle VALUES ('HD2336.3.N55 2018','Managing Telework: Strategies for Managing the Virtual Workforce','Nilles, Jack M.','','0471293164',2018,'John Wiley & Sons. New York');
INSERT INTO BookTitle VALUES ('HD62.7.S524 2023','Smart Guide to Starting a Small Business','Shaw, Lisa Angowski','','047131885X',2023,'Cader Books. New York');
INSERT INTO BookTitle VALUES ('HD69.T54S5 2023','Smart Guide to Managing Your Time','Shaw, Lisa Angowski','','0471318868',2023,'Cader Books. New York');

INSERT INTO Hold VALUES (123123123,'QA76.9.A43H67 2018', '2023-05-12');
INSERT INTO Hold VALUES (123123123,'QA76.73.P224A85 2018', '2023-05-12');

INSERT INTO Book (BookID, CallNumber) VALUES (1, 'HD2336.3.N55 2018');
INSERT INTO Book (BookID, CallNumber) VALUES (2, 'HD30.25.A53 2023');
INSERT INTO Book (BookID, CallNumber) VALUES (3, 'HD38.D68');
INSERT INTO Book (BookID, CallNumber) VALUES (4, 'HD58.82.D38 2018');
INSERT INTO Book (BookID, CallNumber) VALUES (5, 'HD62.7.S524 2023');
INSERT INTO Book (BookID, CallNumber) VALUES (6, 'HD69.T54S5 2023');
INSERT INTO Book (BookID, CallNumber) VALUES (7, 'HD9696.63.U62C585 2023');
INSERT INTO Book (BookID, CallNumber) VALUES (8, 'HF5548.2.S7728 2018');
INSERT INTO Book (BookID, CallNumber) VALUES (9, 'HF5548.2.S7728 2018');
INSERT INTO Book (BookID, CallNumber) VALUES (10, 'QA76.625.M33 2015');
INSERT INTO Book (BookID, CallNumber) VALUES (11, 'QA76.73.J38J47 2015');
INSERT INTO Book (BookID, CallNumber) VALUES (12, 'QA76.73.J38K66 2018');
INSERT INTO Book (BookID, CallNumber) VALUES (13, 'QA76.73.P224A85 2018');
INSERT INTO Book (BookID, CallNumber) VALUES (14, 'QA76.9.A43H67 2018');
INSERT INTO Book (BookID, CallNumber, BorrowerSSN, DueDate, LibrarianSSN) VALUES (15, 'QA76.9.D3E57 2020',123456789,'05/14/22',321321321);
INSERT INTO Book (BookID, CallNumber) VALUES (16, 'QA76.L2967 2018');
INSERT INTO Book (BookID, CallNumber) VALUES (17, 'TK5105.888.J37 2018');
INSERT INTO Book (BookID, CallNumber, BorrowerSSN, DueDate, LibrarianSSN) VALUES (18, 'TK5105.888.J37 2018',123123123,'05/9/22',321321321);
INSERT INTO Book (BookID, CallNumber, BorrowerSSN, DueDate, LibrarianSSN) VALUES (19, 'QA76.9.D3E57 2020',111222333,'05/14/22',321321321);
INSERT INTO Book (BookID, CallNumber) VALUES (20, 'HF5548.2.S7728 2018');
INSERT INTO Book (BookID, CallNumber) VALUES (21, 'QA76.73.J38K66 2018');