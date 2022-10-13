CREATE DATABASE softuni_imdb;
USE softuni_imdb;

-- p01 Table Design
CREATE TABLE countries (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL UNIQUE,
    continent VARCHAR(30) NOT NULL,
    currency VARCHAR(5) NOT NULL
);

CREATE TABLE movies_additional_info (
	id INT PRIMARY KEY AUTO_INCREMENT,
    rating DECIMAL(10, 2) NOT NULL,
    runtime INT NOT NULL,
    picture_url VARCHAR(80) NOT NULL,
    budget DECIMAL(10, 2),
    release_date DATE NOT NULL,
    has_subtitles BOOLEAN,
    `description` TEXT
);

CREATE TABLE movies (
	id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(70) NOT NULL UNIQUE,
    country_id INT NOT NULL,
    movie_info_id INT NOT NULL UNIQUE,
    CONSTRAINT `fk_movies_countries`
		FOREIGN KEY (country_id)
        REFERENCES countries (id),
    CONSTRAINT `fk_movies_moviesAdditionalInfo`
		FOREIGN KEY (movie_info_id)
        REFERENCES movies_additional_info (id)
);

CREATE TABLE actors (
	id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birthdate DATE NOT NULL,
    height INT,
    awards INT,
    country_id INT NOT NULL,
    CONSTRAINT `fk_actors_countries`
		FOREIGN KEY (country_id)
        REFERENCES countries (id)
);

CREATE TABLE movies_actors (
	movie_id INT,
    actor_id INT,
    CONSTRAINT `fk_map_to_movies`
		FOREIGN KEY (movie_id)
        REFERENCES movies (id),
	CONSTRAINT `fk_map_to+actors`
		FOREIGN KEY (actor_id)
        REFERENCES actors (id)
);

CREATE TABLE genres (
	id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE genres_movies (
	genre_id INT,
    movie_id INT,
    CONSTRAINT `fk_map_to_genres`
		FOREIGN KEY (genre_id)
        REFERENCES genres (id),
	CONSTRAINT `fk_map_movies`
		FOREIGN KEY (movie_id)
        REFERENCES movies (id)
);

-- p02 Insert
INSERT INTO actors (first_name, last_name, birthdate, height, awards, country_id)
(SELECT
	reverse(first_name) AS 'first_name',
    reverse(last_name) AS 'last_name',
    DATE_FORMAT(birthdate, concat('%Y-%m-', (DAY(birthdate) - 2))) AS 'birthdate',
    height + 10 AS 'height',
    country_id AS 'awards',
    3 AS 'country_id'
FROM
	actors
WHERE id <= 10);

-- p03 Update
UPDATE movies_additional_info SET runtime = runtime - 10
WHERE id BETWEEN 15 AND 25;

-- p04 Delete
DELETE FROM countries
WHERE id IN (
	SELECT c.id FROM (
	SELECT c.id 
    FROM countries AS c
    LEFT JOIN movies AS m
      ON m.country_id = c.id
	WHERE m.id IS NULL) AS c
);

-- p05 Countries
SELECT
	id, `name`, continent, currency
FROM
	countries
ORDER BY currency DESC, id ASC;

-- p06 Old movies
SELECT
	m.id, m.title, nfo.runtime, nfo.budget, nfo.release_date
FROM movies AS m
JOIN movies_additional_info AS nfo
  ON m.movie_info_id = nfo.id
WHERE YEAR(nfo.release_date) BETWEEN 1996 AND 1999
ORDER BY nfo.runtime, m.id
LIMIT 20;

-- p07 Movie casting
SELECT
	concat_ws(' ', first_name, last_name) AS 'full_name',
    concat(reverse(last_name), char_length(last_name), '@cast.com') AS 'email',
    2022 - YEAR(birthdate) AS 'age',
    height
FROM actors
WHERE id NOT IN (
	SELECT actor_id FROM movies_actors)
ORDER BY height;

-- p08 International festival
SELECT
	c.`name`,
    count(m.id) AS 'movies_count'
FROM countries AS c
JOIN movies AS m
  ON c.id = m.country_id
GROUP BY c.id
HAVING movies_count >= 7
ORDER BY c.`name` DESC;

-- p09 Rating system
SELECT
	m.title,
    (CASE 
		WHEN nfo.rating <= 4 THEN 'poor'
		WHEN nfo.rating <= 7 THEN 'good'
		ELSE 'excellent'
	END) AS 'rating',
    (CASE
		WHEN nfo.has_subtitles = 1 THEN 'english'
        ELSE '-'
	 END) AS 'subtitles',
    nfo.budget
FROM movies AS m
JOIN movies_additional_info AS nfo
  ON m.movie_info_id = nfo.id
ORDER BY nfo.budget DESC;

-- p10 History movies
DELIMITER $$
CREATE FUNCTION udf_actor_history_movies_count(full_name VARCHAR(50))
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE number_of_history_movies INT;
    SET number_of_history_movies := (
		SELECT
			count(m.id)
		FROM actors AS a
		JOIN movies_actors AS ma
		  ON a.id = ma.actor_id
		JOIN movies AS m
		  ON ma.movie_id = m.id
		JOIN genres_movies AS gm
		  ON m.id = gm.movie_id
		JOIN genres AS g
		  ON gm.genre_id = g.id
		WHERE g.id = 12 AND concat_ws(' ', first_name, last_name) = full_name
		GROUP BY a.id
	);
    RETURN number_of_history_movies;
END$$

-- p11 Movie awards
CREATE PROCEDURE udp_award_movie(movie_title VARCHAR(50))
BEGIN  
    UPDATE actors AS a
		JOIN movies_actors AS ma
		  ON a.id = ma.actor_id
		JOIN movies AS m
		  ON ma.movie_id = m.id
	SET a.awards = a.awards + 1
    WHERE m.title = movie_title;
END$$
