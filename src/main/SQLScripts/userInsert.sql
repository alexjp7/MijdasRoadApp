INSERT INTO USER VALUES("alex","1234","Alexander","Perceval","alnerdo@hotmail.com");
INSERT INTO USER VALUES("stevejp","1234","Steven","Paramount","sp@gmail.com");
INSERT INTO USER VALUES("testuser","1234","Test","Johnson","tjo@hotmail.com");
INSERT INTO USER VALUES("isThisTaken","1234","Test 1 ","alnerdo@hotmail.com");
INSERT INTO USER VALUES("john","1234","John","Barlow","Jb@hotmail.com");
INSERT INTO MECHANIC(username, qualityInStars, license) VALUES("alex",5,12325);
INSERT INTO MOTORIST(username,hasMembership,license) VALUES ("stevejp",false,5225);

SELECT * FROM USER JOIN MECHANIC WHERE USER.USERNAME= "alex";

SELECT * FROM USER JOIN MECHANIC WHERE USER.USERNAME='alex';

SELECT * FROM USER JOIN MOTORIST WHERE USER.USERNAME='stevejp';

