USE `soft_uni`;
-- p01 Find Names of All Employees by First Name
SELECT `first_name`, `last_name` FROM `employees`
WHERE `first_name` LIKE 'Sa%'
ORDER BY `employee_id`;

-- p02 Find Names of All Employees by Last Name
SELECT `first_name`, `last_name` FROM `employees`
WHERE `last_name` LIKE '%ei%'
ORDER BY `employee_id`;

-- p03 Find First Names of All Employees
SELECT `first_name` FROM `employees`
WHERE `department_id` IN(3, 10) AND
	  EXTRACT(YEAR FROM `hire_date`) BETWEEN 1995 AND 2005
ORDER BY `employee_id`;

-- p04 Find All Employees Except Engineers
SELECT `first_name`, `last_name` FROM `employees`
WHERE NOT `job_title` LIKE '%Engineer%'
ORDER BY `employee_id`;  

-- p05 Find Towns with Name Length
SELECT `name` FROM `towns`
WHERE CHAR_LENGTH(`name`) IN (5, 6)
ORDER BY `name`;

-- p06 Find Towns Starting With
SELECT * FROM `towns`
WHERE LEFT(`name`, 1) IN ('M', 'K', 'B', 'E')
ORDER BY `name`;

-- p07 Find Towns Not Starting With
SELECT * FROM `towns`
WHERE NOT LEFT(`name`, 1) REGEXP '[RBD]'
-- WHERE NOT REGEXP_LIKE(LEFT(`name`, 1), '[RBD]')
ORDER BY `name`;

-- p08 Create View Employees Hired After 2000 Year
CREATE VIEW `v_employees_hired_after_2000` AS
	SELECT `first_name`, `last_name` FROM `employees`
    WHERE YEAR(`hire_date`) > 2000;
    
SELECT * FROM `v_employees_hired_after_2000`;   
 
-- p09 Length of Last Name
SELECT `first_name`, `last_name` FROM `employees`
WHERE CHAR_LENGTH(`last_name`) = 5;

USE `geography`;
-- p10 Countries Holding 'A' 3 or More Times
SELECT `country_name`, `iso_code` FROM `countries`
WHERE `country_name` REGEXP '(.*a.*){3,}'
ORDER BY `iso_code`;

-- p11 Mix of Peak and River Names
SELECT `peak_name`, `river_name`, 
	   LOWER(CONCAT_WS('', `peak_name`, SUBSTR(`river_name`, 2))) AS 'mix'
FROM `peaks` INNER JOIN `rivers`
WHERE RIGHT(`peak_name`, 1) = LEFT(`river_name`, 1)
ORDER BY `mix`; 

USE `diablo`;      
-- p12 Games from 2011 and 2012 Year
SELECT `name`, DATE_FORMAT(DATE(`start`), '%Y-%m-%d') AS `start`
FROM `games`
WHERE YEAR(`start`) IN (2011, 2012)
ORDER BY `start`, `name`
LIMIT 50;

-- p13 User Email Providers
SELECT `user_name`, SUBSTR(`email`, LOCATE('@', `email`) + 1) 
AS 'email provider'
FROM `users`
ORDER BY `email provider`, `user_name`;

-- p14 Get Users with IP Address Like Pattern
SELECT `user_name`, `ip_address` FROM `users`
WHERE `ip_address` LIKE '___.1%.%.___'
ORDER BY `user_name`;

-- p15 Show All Games with Duration and Part of the Day
SELECT `name` AS 'game',
CASE
	WHEN HOUR(`start`) < 12 THEN 'Morning'
    WHEN HOUR(`start`) BETWEEN 12 AND 17 THEN 'Afternoon'
    WHEN HOUR(`start`) BETWEEN 18 AND 23 THEN 'Evening'
END AS 'Part of the Day',
CASE
	WHEN `duration` <= 3 THEN 'Extra Short'
    WHEN `duration` BETWEEN 3 AND 6 THEN 'Short'
    WHEN `duration` BETWEEN 6 AND 10 THEN 'Long'
    ELSE 'Extra Long'
END AS 'Duration'
FROM `games`;