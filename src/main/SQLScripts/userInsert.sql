INSERT INTO USER VALUES("alex","1234","Alexander","Perceval","alnerdo@hotmail.com");
INSERT INTO USER VALUES("stevejp","1234","Steven","Paramount","sp@gmail.com");
INSERT INTO USER VALUES("testuser","1234","Test","Johnson","tjo@hotmail.com");
INSERT INTO USER VALUES("testmechanic","1234","Mike","Mechanic","testmek@hotmail.com");
INSERT INTO USER VALUES("isThisTaken","1234","Test1FName", "Test1LName","alnerdo@hotmail.com");
INSERT INTO USER VALUES("john","1234","John","Barlow","Jb@hotmail.com");

insert into USER (username, password, fName, lName, email) values ('doliverasf', 'ukdBKLNOQLr6', 'Dickie', 'Oliveras', 'doliverasf@uiuc.edu');
INSERT INTO MECHANIC(username, qualityInStars, license) VALUES("alex",5,12325);
INSERT INTO MOTORIST(username,hasMembership,lNum) VALUES ("stevejp",false,5225);
INSERT INTO MECHANIC(username, qualityInStars, license) VALUES("testuser",1,11235);
INSERT INTO MOTORIST(username,hasMembership,lNum) VALUES ("doliverasf",false,1432);
INSERT INTO MOTORIST(username,hasMembership,lNum) VALUES ("testmechanic",false,1433);

INSERT INTO VEHICLE VALUES(5225, "ST3VEJ", "Subaru", "2002 WRX Impreza", "Blue", true);
INSERT INTO VEHICLE VALUES(3297, "ST3VES", "Toyota", "1994 Supra", "Orange", false);
INSERT INTO VEHICLE VALUES(1433, "TESTMK", "Nissan", "1994 R32 Skyline", "Black", false);

SELECT * FROM USER JOIN MECHANIC WHERE USER.USERNAME= "alex";

SELECT * FROM USER JOIN MECHANIC WHERE USER.USERNAME='alex';

SELECT * FROM USER JOIN MOTORIST WHERE USER.USERNAME='stevejp';

INSERT INTO SERVICE_REQUEST VALUES("1","stevejp","123 fake st","cars broke",false, NULL, false);
INSERT INTO SERVICE_REQUEST VALUES("2","stevejp","123 fake st","cars still broke",false, NULL, false);
INSERT INTO SERVICE_REQUEST VALUES("3","stevejp","123 fake st","please send someone",false, NULL, false);
INSERT INTO SERVICE_REQUEST VALUES("4","stevejp","123 fake st","cars all fixed now lol",true, NULL, false);

INSERT INTO SERVICE_REQUEST VALUES("5","testmechanic","456 meme rd","pls help",false, NULL, false);
INSERT INTO SERVICE_REQUEST VALUES("6","testmechanic","456 meme rd","engine smoking",false, NULL, false);
INSERT INTO SERVICE_REQUEST VALUES("7","testmechanic","456 meme rd","all fixed",true, NULL, false);

SELECT * FROM SERVICE_REQUEST;
