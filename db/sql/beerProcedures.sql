USE beer_data;

#Get one beer by it's id
DELIMITER //
CREATE PROCEDURE get_by_beer_id
	(
     	search_id INT UNSIGNED
    )

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE beers.beer_id = search_id;

END//

#fuzzy text matching search across all text attributes
DELIMITER //
CREATE PROCEDURE text_search
	(
     	search_target VARCHAR(200)
    )

BEGIN

DECLARE target_lc VARCHAR(200);
#setting the target to a lower case version
SELECT LOWER(search_target)
INTO target_lc;

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE
	LOWER(beers.beer_name) LIKE CONCAT('%',target_lc,'%') OR
    LOWER(brewery.brewery_name) LIKE CONCAT('%',target_lc,'%') OR
    LOWER(discriptions.description) LIKE CONCAT('%',target_lc,'%') OR
    LOWER(styles.style_name) LIKE CONCAT('%',target_lc,'%') OR
    beers.beer_id IN (SELECT staff_reviews.beer_id
                     FROM staff_reviews
                     WHERE
                     		LOWER(staff_reviews.staff_member) LIKE CONCAT('%',target_lc,'%') OR
                      		LOWER(staff_reviews.staff_member) LIKE CONCAT('%',target_lc,'%'))
ORDER BY beers.beer_id;

END//

#Get the featured beer
DELIMITER //

CREATE PROCEDURE get_featured()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE beers.is_featured = 1;

END//

#Get all the dark beers
DELIMITER //

CREATE PROCEDURE get_dark()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE beers.light_dark = 'dark';

END//

# all the light beers

DELIMITER //

CREATE PROCEDURE get_light()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE beers.light_dark = 'light';

END//

#Get any reviewed beers
DELIMITER //

CREATE PROCEDURE get_reviewed()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE beers.beer_id IN (SELECT staff_reviews.beer_id
                        FROM staff_reviews)  ;

END//

#Begining of the category procedures

DELIMITER //

CREATE PROCEDURE get_ipa()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(styles.style_name) LIKE '%ind%pale%ale%' OR LOWER(styles.style_name) LIKE('%ipa%');

END//

DELIMITER //

CREATE PROCEDURE get_double_ipa()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(styles.style_name) LIKE '%double%i%' ;

END//

DELIMITER //
CREATE PROCEDURE get_amer_pale_ale()
BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(styles.style_name) LIKE '%amer%ind%pale%ale%';

END//

DELIMITER //

CREATE PROCEDURE get_eng_pale_ale()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(styles.style_name) LIKE'%eng%ind%pale%ale%' ;

END//

DELIMITER //

CREATE PROCEDURE get_porter()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(styles.style_name) LIKE '%porter%' ;

END//

DELIMITER //

CREATE PROCEDURE get_sour()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(styles.style_name) LIKE '%sour%' ;

END//

DELIMITER //

CREATE PROCEDURE get_brown()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(styles.style_name) LIKE '%brown%a%' ;

END//

DELIMITER //

CREATE PROCEDURE get_wheat()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(styles.style_name) LIKE '%wheat%' ;

END//

DELIMITER //

CREATE PROCEDURE get_golden()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(styles.style_name) LIKE '%golden%' ;

END//

DELIMITER //

CREATE PROCEDURE get_lager()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(styles.style_name) LIKE '%pilse%' OR LOWER(styles.style_name) LIKE '%amer%lager%'  ;

END//


DELIMITER //

CREATE PROCEDURE get_pilsner()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(styles.style_name) LIKE '%pilse%'  ;

END//


DELIMITER //

CREATE PROCEDURE get_amer_pilsner()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(styles.style_name) LIKE '%amer%pilse%'  ;

END//


DELIMITER //

CREATE PROCEDURE get_amer_lager()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(styles.style_name) LIKE '%amer%lager%'  ;

END//


DELIMITER //

CREATE PROCEDURE get_amber()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(styles.style_name) LIKE '%amber%'  ;

END//

DELIMITER //

CREATE PROCEDURE get_amer_amber()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(styles.style_name) LIKE '%amer%amber%'  ;

END//


DELIMITER //

CREATE PROCEDURE get_cider()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(styles.style_name) LIKE '%cider%'  ;

END//


DELIMITER //

CREATE PROCEDURE get_bwine()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(styles.style_name) LIKE '%barle%wine%'  ;

END//
DELIMITER //

CREATE PROCEDURE get_amer_bwine()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(styles.style_name) LIKE '%amer%barle%wine%'  ;

END//

DELIMITER //

CREATE PROCEDURE get_brit_bwine()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(styles.style_name) LIKE '%bri%barle%wine%'  ;

END//

DELIMITER //

CREATE PROCEDURE get_pale_ale()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(styles.style_name)
                        LIKE('%ind%pale%ale%') OR LOWER(styles.style_name)
                        LIKE('%ipa%') OR LOWER(styles.style_name) LIKE ('%double%i%')
                         OR LOWER(styles.style_name) LIKE ('%amer%pale%ale%') OR
                        LOWER(styles.style_name) LIKE('%eng%pale%ale%');

END//

DELIMITER //

CREATE PROCEDURE get_ale()

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(styles.style_name) LIKE('%ind%pale%ale%') OR
      LOWER(styles.style_name) LIKE('%ipa%') OR
      LOWER(styles.style_name) LIKE ('%double%i%')OR
      LOWER(styles.style_name) LIKE ('%amer%pale%ale%') OR
      LOWER(styles.style_name) LIKE('%eng%pale%ale%') OR
      LOWER(styles.style_name) LIKE('%stout%');

END//

#End of category procedures

# Beginning of the attribute search procedures

#abv searching gives a matching range of target - 0.5 to target + 0.5
DELIMITER //

CREATE PROCEDURE get_by_abv
    (
    search_abv DECIMAL(4,2)
    )

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE beers.beer_abv BETWEEN (5.2 - 0.5) AND (5.2 + 0.5);

END//

DELIMITER //

CREATE PROCEDURE get_by_abv
    (
    search_abv DECIMAL(4,2)
    )

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE beers.beer_abv BETWEEN (search_abv - 0.5) AND (search_abv + 0.5);

END//


DELIMITER //

CREATE PROCEDURE get_by_ibu
    (
    search_ibu INT UNSIGNED
    )

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE beers.beer_ibu = search_ibu;

END//


DELIMITER //

CREATE PROCEDURE get_by_brewery
    (
    search_brewery VARCHAR(60)
    )

BEGIN

DECLARE brewery_lc VARCHAR(60);

SELECT LOWER(search_brewery)
    INTO brewery_lc;

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(brewery.brewery_name) LIKE CONCAT('%',brewery_lc,'%');

END//

DELIMITER //

CREATE PROCEDURE get_by_type
    (
    search_type VARCHAR(60)
    )

BEGIN

DECLARE type_lc VARCHAR(60);

SELECT LOWER(search_type)
    INTO type_lc;

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(styles.style_name) LIKE CONCAT('%',type_lc,'%');

END//

DELIMITER //

CREATE PROCEDURE get_by_abv_ibu
    (
    search_abv DECIMAL(4,2),
    search_ibu INT UNSIGNED
    )

BEGIN

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE (beers.beer_abv BETWEEN (search_abv - 0.5) AND (search_abv + 0.5))
    AND beers.beer_ibu = search_ibu;

END//

DELIMITER //

CREATE PROCEDURE get_by_abv_type
    (
    search_abv DECIMAL(4,2),
    search_type VARCHAR(60)
    )

BEGIN

DECLARE type_lc VARCHAR(60);

SELECT LOWER(search_type)
    INTO type_lc;

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE (beers.beer_abv BETWEEN (search_abv - 0.5) AND (search_abv + 0.5))
    AND LOWER(styles.style_name) LIKE CONCAT('%',type_lc,'%');

END//

DELIMITER //

CREATE PROCEDURE get_by_abv_brewery
    (
    search_abv DECIMAL(4,2),
    search_brewery VARCHAR(60)
    )

BEGIN

DECLARE brewery_lc VARCHAR(60);

SELECT LOWER(search_brewery)
    INTO brewery_lc;

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE (beers.beer_abv BETWEEN (search_abv - 0.5) AND (search_abv + 0.5))
    AND LOWER(brewery.brewery_name) LIKE CONCAT('%',brewery_lc,'%');

END//

DELIMITER //

CREATE PROCEDURE get_by_ibu_brewery
    (
    search_ibu INT UNSIGNED,
    search_brewery VARCHAR(60)
    )

BEGIN

DECLARE brewery_lc VARCHAR(60);

SELECT LOWER(search_brewery)
    INTO brewery_lc;

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE (beers.beer_ibu = search_ibu)
    AND LOWER(brewery.brewery_name) LIKE CONCAT('%',brewery_lc,'%');

END//

DELIMITER //

CREATE PROCEDURE get_by_ibu_type
    (
    search_ibu INT UNSIGNED,
    search_type VARCHAR(60)
    )

BEGIN

DECLARE type_lc VARCHAR(60);

SELECT LOWER(search_type)
    INTO type_lc;

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE (beers.beer_ibu = search_ibu)
    AND LOWER(styles.style_name) LIKE CONCAT('%',type_lc,'%');

END//

DELIMITER //

CREATE PROCEDURE get_by_brewery_type
    (
    search_brewery VARCHAR(60),
    search_type VARCHAR(60)
    )

BEGIN

DECLARE brewery_lc VARCHAR(60);
DECLARE type_lc VARCHAR(60);

SELECT LOWER(search_brewery)
    INTO brewery_lc;

SELECT LOWER(search_type)
    INTO type_lc;

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE LOWER(brewery.brewery_name) LIKE CONCAT('%',brewery_lc,'%')
    AND LOWER(styles.style_name) LIKE CONCAT('%',type_lc,'%');

END//

DELIMITER //

CREATE PROCEDURE get_by_abv_ibu_brewery
    (
    search_abv DECIMAL(4,2),
    search_ibu INT UNSIGNED,
    search_brewery VARCHAR(60)
    )

BEGIN

DECLARE brewery_lc VARCHAR(60);

SELECT LOWER(search_brewery)
    INTO brewery_lc;

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE (beers.beer_abv BETWEEN (search_abv - 0.5) AND (search_abv + 0.5))
    AND beers.beer_ibu = search_ibu
    AND LOWER(brewery.brewery_name) LIKE CONCAT('%',brewery_lc,'%');

END//

DELIMITER //

CREATE PROCEDURE get_by_abv_ibu_type
    (
    search_abv DECIMAL(4,2),
    search_ibu INT UNSIGNED,
    search_type VARCHAR(60)
    )

BEGIN

DECLARE type_lc VARCHAR(60);

SELECT LOWER(search_type)
    INTO type_lc;

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE (beers.beer_abv BETWEEN (search_abv - 0.5) AND (search_abv + 0.5))
    AND beers.beer_ibu = search_ibu
    AND LOWER(styles.style_name) LIKE CONCAT('%',type_lc,'%');

END//

DELIMITER //

CREATE PROCEDURE get_by_abv_brewery_type
    (
    search_abv DECIMAL(4,2),
    search_brewery VARCHAR(60),
    search_type VARCHAR(60)
    )

BEGIN

DECLARE brewery_lc VARCHAR(60);
DECLARE type_lc VARCHAR(60);

SELECT LOWER(search_brewery)
    INTO brewery_lc;

SELECT LOWER(search_type)
    INTO type_lc;

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE (beers.beer_abv BETWEEN (search_abv - 0.5) AND (search_abv + 0.5))
    AND LOWER(brewery.brewery_name) LIKE CONCAT('%',brewery_lc,'%')
    AND LOWER(styles.style_name) LIKE CONCAT('%',type_lc,'%');

END//

DELIMITER //

CREATE PROCEDURE get_by_ibu_brewery_type
    (
    search_ibu INT UNSIGNED,
    search_brewery VARCHAR(60),
    search_type VARCHAR(60)
    )

BEGIN

DECLARE brewery_lc VARCHAR(60);
DECLARE type_lc VARCHAR(60);

SELECT LOWER(search_brewery)
    INTO brewery_lc;

SELECT LOWER(search_type)
    INTO type_lc;

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE beers.beer_ibu = search_ibu
    AND LOWER(brewery.brewery_name) LIKE CONCAT('%',brewery_lc,'%')
    AND LOWER(styles.style_name) LIKE CONCAT('%',type_lc,'%');

END//

DELIMITER //

CREATE PROCEDURE get_by_abv_ibu_brewery_type
    (
    search_abv DECIMAL(4,2),
    search_ibu INT UNSIGNED,
    search_brewery VARCHAR(60),
    search_type VARCHAR(60)
    )

BEGIN

DECLARE brewery_lc VARCHAR(60);
DECLARE type_lc VARCHAR(60);

SELECT LOWER(search_brewery)
    INTO brewery_lc;

SELECT LOWER(search_type)
    INTO type_lc;

SELECT beers.beer_id, brewery.brewery_name, beers.beer_name,
		styles.style_name, beers.beer_abv, beers.beer_ibu,
        beers.is_featured, beers.light_dark, discriptions.description
FROM brewery
	JOIN beers
    	ON brewery.brewery_id = beers.brewery_id
        	 JOIN styles
            	ON beers.style_id = styles.style_id
                	LEFT JOIN discriptions
                    	ON beers.beer_id = discriptions.beer_id
WHERE (beers.beer_abv BETWEEN (search_abv - 0.5) AND (search_abv + 0.5))
    AND beers.beer_ibu = search_ibu
    AND LOWER(brewery.brewery_name) LIKE CONCAT('%',brewery_lc,'%')
    AND LOWER(styles.style_name) LIKE CONCAT('%',type_lc,'%');

END//

#End of attribute search procedures

# helper procedures

DELIMITER //

CREATE PROCEDURE setDark()

BEGIN
    UPDATE beers
SET light_dark = 'dark'
WHERE style_id IN (
    SELECT styles.style_id
    FROM styles
    WHERE LOWER(styles.style_name) LIKE '%porter%' OR
    	LOWER(styles.style_name) LIKE '%stout%' OR
    	LOWER(styles.style_name) LIKE '%barle%wine%' OR
    	LOWER(styles.style_name) LIKE '%rye%' OR
    	LOWER(styles.style_name) LIKE '%brown%' OR
    	LOWER(styles.style_name) LIKE '%double%i%');
END//

DELIMITER //

CREATE PROCEDURE setLight()

BEGIN
   UPDATE beers
SET light_dark = 'light'
WHERE style_id IN (
    SELECT styles.style_id
    FROM styles
    WHERE LOWER(styles.style_name) LIKE '%white%' OR
    	LOWER(styles.style_name) LIKE '%golden%' OR
    	LOWER(styles.style_name) LIKE '%pilse%' OR
    	LOWER(styles.style_name) LIKE '%amer%lager%' OR
    	LOWER(styles.style_name) LIKE '%wheat%' OR
    	LOWER(styles.style_name) LIKE '%blonde%'OR
    	LOWER(styles.style_name) LIKE '%light%'
);
END//

DELIMITER //

# deletes malformed rows that are not tied to a brewery
CREATE PROCEDURE del_no_brew()

BEGIN
    DELETE FROM beers
        WHERE beers.brewery_id IS NULL;
END//


