
DELIMITER $$
CREATE FUNCTION checkMotorist(uName VARCHAR(20))returns VARCHAR(20)
BEGIN
   DECLARE userFound VARCHAR(20) DEFAULT NULL;

	SELECT USER.username
  INTO userFound
	FROM USER
	JOIN MOTORIST
	WHERE MOTORIST.username = USER.username AND MOTORIST.username = uName;

	return userFound;
END$$

DELIMITER $$
CREATE FUNCTION checkMechanic(uName VARCHAR(20))returns VARCHAR(20)
BEGIN
   DECLARE userFound VARCHAR(20) DEFAULT NULL;

	SELECT USER.username
  INTO userFound
	FROM USER
	JOIN MECHANIC
	WHERE  MECHANIC.username = USER.username AND MECHANIC.username = uName;

	return userFound;
END$$
