USE `book_library`;

-- p01
SELECT `title` FROM `books`
WHERE `title` LIKE 'The %';

SELECT `title` FROM `books`
WHERE LOCATE('The', `title`, 1) = 1;

-- p02
SELECT REPLACE(`title`, 'The', '***')
FROM `books`
WHERE `title` LIKE 'The%';

-- p03
SELECT ROUND(SUM(`cost`), 2)
FROM `books`;

-- p04
SELECT CONCAT_WS(' ', `first_name`, `last_name`) AS "Full Name",
	   TIMESTAMPDIFF(DAY, `born`, `died`)
FROM `authors`;       

-- p05
SELECT `title` FROM `books`
WHERE `title` LIKE 'Harry Potter%';