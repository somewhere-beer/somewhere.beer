-- create the database
DROP DATABASE IF EXISTS beer_data;
CREATE DATABASE beer_data;

-- use the new database
USE beer_data;

-- creating the brewery table
CREATE TABLE brewery
(
	brewery_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    brewery_name VARCHAR(60) NOT NULL UNIQUE
);

-- creating the style table
CREATE TABLE styles
(
	style_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	style_name VARCHAR(200) NOT NULL UNIQUE
);

-- creating the beer table
CREATE TABLE beers
(
    beer_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    brewery_id INT UNSIGNED,
    beer_name VARCHAR(200) NOT NULL,
    style_id INT UNSIGNED, 
    beer_abv DECIMAL(4,2) NOT NULL,
    is_featured BOOLEAN DEFAULT FALSE,
    beer_ibu INT UNSIGNED,
	light_dark ENUM('light', 'dark') DEFAULT NULL,
    CONSTRAINT beer_fk_brewery 
    	FOREIGN KEY (brewery_id) 
    	REFERENCES brewery (brewery_id),
CONSTRAINT beer_fk_styles
	FOREIGN KEY (style_id) 
	REFERENCES styles (style_id)		
);

-- creating the picture referance table
CREATE TABLE pictures
(
  beer_id INT UNSIGNED,
  local_disk_location VARCHAR(100) NOT NULL,
  CONSTRAINT pictures_fk_beers
    FOREIGN KEY (beer_id) REFERENCES beers (beer_id)
);

-- creating the description table
CREATE TABLE discriptions
(
	beer_id INT UNSIGNED,
    description TEXT NOT NULL,
    CONSTRAINT discrip_fk_beers
    	FOREIGN KEY (beer_id) 
    	REFERENCES beers (beer_id)
);

-- creating the staff reviews table
CREATE TABLE staff_reviews
(
	beer_id INT UNSIGNED,
    staff_member VARCHAR(50) NOT NULL,
    review TEXT NOT NULL,
    CONSTRAINT s_reviews_fk_beers
    	FOREIGN KEY (beer_id) 
    	REFERENCES beers (beer_id)
);

GRANT SELECT, INSERT, DELETE, UPDATE
ON beer_data.*
TO beerUser@localhost
IDENTIFIED BY 'drinkUP';
