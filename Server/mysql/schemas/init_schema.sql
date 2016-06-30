DROP DATABASE IF EXISTS StratRoulette;

CREATE DATABASE StratRoulette;

CREATE TABLE StratRoulette.Maps(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(32) NOT NULL,
	map_type VARCHAR(16) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE(name)
);

-- List of tradable goods and resources
CREATE TABLE StratRoulette.Strats (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(32) NOT NULL,
	map_id INT NOT NULL,
	description VARCHAR(2056),
	team INT,

	UNIQUE(name),
	PRIMARY KEY (id),
	FOREIGN KEY(map_id)
		REFERENCES StratRoulette.Maps(id)
		ON UPDATE CASCADE
		ON DELETE CASCADE
	
);





