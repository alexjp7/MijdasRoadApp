
CREATE TABLE USER
(
	username	VARCHAR(20)	 NOT NULL,
	password	VARCHAR(64)	 NOT NULL,
	fName 		VARCHAR (15) NOT NULL,
	lName 		VARCHAR (15) NOT NULL,
    email	    VARCHAR (40) NOT NULL,

  	CONSTRAINT pk1_user PRIMARY KEY(username)
);

CREATE TABLE MOTORIST
(
	username 		VARCHAR (20) NOT NULL,
	hasMemberShip 	BOOLEAN NOT NULL,
  	paymentOption 	VARCHAR(10),
	license			INT(8),

	CONSTRAINT fk1_motorists FOREIGN KEY (username) REFERENCES USER(username) ON UPDATE CASCADE,
	CONSTRAINT pk1_motorists PRIMARY KEY (username),
    CONSTRAINT OPTION_TYPE CHECK (paymentOption IN ('NONE','CREDIT-CARD','PAYPAL','CASH'))
);

CREATE TABLE MECHANIC
(
	username 		VARCHAR (20),
	qualityInStars  INT(1),
	license			INT(8),

	CONSTRAINT pk1_mechanics PRIMARY KEY (username),
	CONSTRAINT fk1_mechanics FOREIGN KEY (username) REFERENCES USER(username) ON UPDATE CASCADE
);


CREATE TABLE SERVICE_REQUEST
(
	requestNum 			INT NOT NULL AUTO_INCREMENT,
	motoristUsername 	VARCHAR (20) NOT NULL,
	nearestAddress		VARCHAR(50) NOT NULL,
	details				VARCHAR(140) NOT NULL,  /*Tweet length description too small?*/
	isComplete			BOOLEAN NOT NULL,

	CONSTRAINT pk1_service_requests PRIMARY KEY (requestNum),
	CONSTRAINT fk1_service_requests FOREIGN KEY (motoristUsername) REFERENCES MOTORIST(username)  ON UPDATE CASCADE
);
