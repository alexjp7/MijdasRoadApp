
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
    CONSTRAINT fk1_motorists FOREIGN KEY (username) REFERENCES USER(username),
	CONSTRAINT pk1_motorists PRIMARY KEY (username),
    CONSTRAINT OPTION_TYPE CHECK (STATUS IN ('NONE','CREDIT-CARD','PAYPAL','CASH'))

);

CREATE TABLE MECHANIC
(
	username 		VARCHAR (20),
	qualityInStars  INT(1),
	license			INT(8),	
    CONSTRAINT pk1_mechanics PRIMARY KEY (username),
	CONSTRAINT fk1_motorists FOREIGN KEY (username) REFERENCES USER(username)
);

