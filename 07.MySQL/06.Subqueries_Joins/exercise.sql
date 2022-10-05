USE `soft_uni`;

-- p01 Employee Address
SELECT `e`.`employee_id`, `e`.`job_title`, 
	   `a`.`address_id`, `a`.`address_text`
FROM `employees` AS `e` 
JOIN `addresses` AS `a`
ON `e`.`address_id` = `a`.`address_id`
ORDER BY `a`.`address_id`
LIMIT 5;

-- p02 Addresses with Towns
SELECT `e`.`first_name`, `e`.`last_name`, 
	   `t`.`name` AS 'town', `a`.`address_text`
FROM `employees` AS `e`
JOIN `addresses` AS `a` 
ON `e`.`address_id` = `a`.`address_id`
JOIN `towns` AS `t`
ON `a`.`town_id` = `t`.`town_id`
ORDER BY `first_name`, `last_name` 
LIMIT 5;

-- p03 Sales Employee
SELECT `e`.`employee_id`, 
	   `e`.`first_name`, 
       `e`.`last_name`, 
	   `d`.`name` AS 'department_name'
FROM `employees` AS `e`
JOIN `departments` AS `d`
ON `e`.`department_id` = `d`.`department_id`
WHERE `d`.`name` = 'Sales'
ORDER BY `e`.`employee_id` DESC;

-- p04 Employee Departments
SELECT `e`.`employee_id`, `e`.`first_name`, `e`.`salary`,
	`d`.`name` AS 'department_name'
FROM `employees` AS `e`
JOIN `departments` AS `d`
ON `e`.`department_id` = `d`.`department_id`
WHERE `e`.`salary` > 15000
ORDER BY `d`.`department_id` DESC
LIMIT 5;

-- p05 Employees Without Project
SELECT `e`.`employee_id`, `e`.`first_name`
FROM `employees` AS `e`
LEFT JOIN `employees_projects` AS `emp_proj`
ON `e`.`employee_id` = `emp_proj`.`employee_id`
WHERE `emp_proj`.`project_id` IS NULL
ORDER BY `e`.`employee_id` DESC
LIMIT 3;

-- p06 Employees Hired After
SELECT `e`.`first_name`, `e`.`last_name`,
	   `e`.`hire_date`, `d`.`name` AS 'dept_name'
FROM `employees` AS `e`
JOIN `departments` AS `d`
ON `e`.`department_id` = `d`.`department_id`
WHERE `e`.`hire_date` > '1999-01-01' 
AND `d`.`name` IN('Sales', 'Finance')
ORDER BY `e`.`hire_date` ASC;

-- p07 Employees with Project
SELECT `e`.`employee_id`, `e`.`first_name`, `p`.`name` AS 'project_name'
FROM `employees` AS `e`
JOIN `employees_projects` AS `emp_p`
ON `e`.`employee_id` = `emp_p`.`employee_id`
JOIN `projects` AS `p`
ON `emp_p`.`project_id` = `p`.`project_id`
WHERE DATE(`p`.`start_date`) > '2002-08-13' AND `p`.`end_date` IS NULL
ORDER BY `e`.`first_name`, `p`.`name`
LIMIT 5;

-- p08 Employee 24
SELECT e.`employee_id`, 
	   e.`first_name`, 
	   CASE 
	      WHEN YEAR(p.`start_date`) < '2005' THEN p.`name`
          ELSE NULL
       END AS 'project_name'
FROM `employees` AS e
JOIN `employees_projects` USING (`employee_id`)
JOIN `projects` AS p USING (`project_id`)
WHERE e.`employee_id` = 24
ORDER BY p.`name`;

-- p09 Employee Manager
SELECT e1.`employee_id`, e1.`first_name`, e1.`manager_id`, 
	   e2.`first_name` AS 'manager_name'
FROM `employees` AS e1
JOIN `employees` AS e2 
ON e1.`manager_id` = e2.`employee_id`
WHERE e2.`employee_id` IN(3, 7)
ORDER BY e1.`first_name` ASC;

-- p10 Employee Summary
SELECT e1.`employee_id`,
	   CONCAT_WS(' ', e1.`first_name`, e1.`last_name`) AS 'employee_name',
       CONCAT_WS(' ', e2.`first_name`, e2.`last_name`) AS 'manager_name',
       d.`name` AS 'department_name'
FROM `employees` AS e1
JOIN `employees` AS e2
  ON e1.`manager_id` = e2.`employee_id`
JOIN `departments` AS d
  ON e1.`department_id` = d.`department_id`
ORDER BY e1.`employee_id`
LIMIT 5;

-- p11 Min Average Salary
SELECT AVG(`salary`) AS 'min_average_salary'
FROM `employees` AS e
GROUP BY `department_id`
ORDER BY `min_average_salary`
LIMIT 1;

USE `geography`;
-- p12 Highest Peaks in Bulgaria
SELECT c.`country_code`,
	   m.`mountain_range`,
       p.`peak_name`,
       p.`elevation`
FROM `countries` AS c
JOIN `mountains_countries` AS m_c
  USING(`country_code`)
JOIN `mountains` AS m
  ON m_c.`mountain_id` = m.`id`
JOIN `peaks` AS p
  ON m.`id` = p.`mountain_id`
WHERE c.`country_code` = 'BG' AND p.`elevation` > 2835
ORDER BY p.`elevation` DESC;

-- p13 Count Mountain Ranges
SELECT c.`country_code`,
	   COUNT(m.`mountain_range`) AS 'mountain_range'
FROM `countries` AS c
JOIN `mountains_countries` AS m_c
  USING(`country_code`)
JOIN `mountains` AS m
  ON m_c.`mountain_id` = m.`id`
GROUP BY c.`country_code`
HAVING c.`country_code` IN('BG', 'US', 'RU')
ORDER BY `mountain_range` DESC;

-- p14 Countries with Rivers
SELECT c.`country_name`, r.`river_name`
FROM `countries` AS c
LEFT JOIN `countries_rivers` AS c_r
  USING(`country_code`)
LEFT JOIN `rivers` AS r
  ON c_r.`river_id` = r.`id`
WHERE c.`continent_code` = 'AF'
ORDER BY c.`country_name`
LIMIT 5;

-- p15 Continents and Currencies
SELECT `continent_code`, 
	   `currency_code`,
       COUNT(`currency_code`) AS 'currency_usage'
FROM `countries` c1
GROUP BY `continent_code`, `currency_code`
HAVING `currency_usage` = (
	SELECT COUNT(`currency_code`) AS 'count'
    FROM `countries` c2
    WHERE c2.`continent_code` = c1.`continent_code`
    GROUP BY c2.`currency_code`
    ORDER BY `count` DESC
    LIMIT 1
    ) AND `currency_usage` > 1
ORDER BY `continent_code`, `currency_code`;

-- p16 Countries Without Any Mountains
SELECT COUNT(c.country_code) AS 'country_count'
FROM countries AS c
LEFT JOIN mountains_countries AS m_c
  USING(country_code)
WHERE m_c.mountain_id IS NULL;

-- p17 Highest Peak and Longest River by Country
SELECT c.country_name,
	   (
       SELECT MAX(elevation)
       FROM peaks AS p
       JOIN mountains_countries AS m_c
         USING(mountain_id)
       WHERE m_c.country_code = c.country_code
       ) AS 'highest_peak_elevation',
       (
       SELECT MAX(length)
       FROM rivers AS r
       JOIN countries_rivers AS c_r
         ON r.id = c_r.river_id
       WHERE c_r.country_code = c.country_code
       ) AS 'longest_river_length'
FROM countries AS c
ORDER BY highest_peak_elevation DESC, longest_river_length DESC, country_name
LIMIT 5;
