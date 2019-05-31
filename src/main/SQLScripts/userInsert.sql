INSERT INTO USER VALUES("alex","1234","Alexander","Perceval","alnerdo@hotmail.com");
INSERT INTO USER VALUES("stevejp","1234","Steven","Paramount","sp@gmail.com");
INSERT INTO USER VALUES("testuser","1234","Test","Johnson","tjo@hotmail.com");
INSERT INTO USER VALUES("isThisTaken","1234","Test1FName", "Test1LName","alnerdo@hotmail.com");
INSERT INTO USER VALUES("john","1234","John","Barlow","Jb@hotmail.com");
INSERT INTO MECHANIC(username, qualityInStars, license) VALUES("alex",5,12325);
INSERT INTO MOTORIST(username,hasMembership,license) VALUES ("stevejp",false,5225);
INSERT INTO MECHANIC(username, qualityInStars, license) VALUES("testuser",1,11235);

SELECT * FROM USER JOIN MECHANIC WHERE USER.USERNAME= "alex";

SELECT * FROM USER JOIN MECHANIC WHERE USER.USERNAME='alex';

SELECT * FROM USER JOIN MOTORIST WHERE USER.USERNAME='stevejp';

INSERT INTO SERVICE_REQUEST VALUES("1","stevejp","123 fake st","cars broke",false);
INSERT INTO SERVICE_REQUEST VALUES("2","stevejp","123 fake st","cars still broke",false);
INSERT INTO SERVICE_REQUEST VALUES("3","stevejp","123 fake st","please send someone",false);

SELECT * FROM SERVICE_REQUEST;
