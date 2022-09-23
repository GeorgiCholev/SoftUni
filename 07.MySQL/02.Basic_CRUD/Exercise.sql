USE `soft_uni`;

-- p01
SELECT * FROM `departments` ORDER BY `department_id`;

-- p02
SELECT `name` FROM `departments` ORDER BY `department_id`;

-- p03
SELECT `first_name`, `last_name`, `salary` FROM `employees` ORDER BY `employee_id`;

-- p04
SELECT `first_name`, `middle_name`, `last_name` FROM `employees` ORDER BY `employee_id`;

-- p05
SELECT concat(`first_name`, '.', `last_name`, '@softuni.bg')
AS `full_ email_address`
FROM `employees`;

-- p06
SELECT DISTINCT `salary` FROM `employees`;

-- p07
SELECT * FROM `employees`
WHERE `job_title` = 'Sales Representative'
ORDER BY `employee_id`;

-- p08
SELECT `first_name`, `last_name`, `job_title` FROM `employees`
WHERE `salary` BETWEEN 20000 AND 30000
ORDER BY `employee_id`;
 
 -- p09
 SELECT concat_ws(' ',`first_name`,`middle_name`,`last_name`) AS 'Full Name'
 FROM `employees`
 WHERE `salary` IN(25000, 14000, 12500, 23600);
 
 -- p10
 SELECT `first_name`, `last_name` FROM `employees`
 WHERE `manager_id` IS NULL;
 
 -- p11
 SELECT `first_name`, `last_name`, `salary` FROM `employees`
 WHERE `salary` > 50000
 ORDER BY `salary` DESC;
 
 -- p12
 SELECT `first_name`, `last_name`, `salary` FROM `employees`
 ORDER BY `salary` DESC
 LIMIT 5;
 
 -- p13
 SELECT `first_name`, `last_name` FROM `employees`
 WHERE NOT `department_id` = 4;
 
 -- p14
 SELECT * FROM `employees`
 ORDER BY `salary` DESC, `first_name` ASC, `last_name` DESC, `middle_name` ASC;
 
 -- p15
 CREATE VIEW `v_employees_salaries` AS
	SELECT `first_name`, `last_name`, `salary` FROM `employees`;
    
SELECT * FROM `v_employees_salaries`;

-- p16  
CREATE VIEW `v_employees_job_titles` AS
    SELECT concat_ws(' ', `first_name`, `middle_name`,`last_name`) AS 'Full Name', `job_title`
    FROM `employees`;
    
SELECT * FROM `v_employees_job_titles`;

-- p17
SELECT DISTINCT `job_title`
FROM `employees`
ORDER BY `job_title`;

-- p18
SELECT * FROM `projects`
ORDER BY `start_date`, `name`
LIMIT 10;

-- p19
SELECT `first_name`, `last_name`, `hire_date` FROM `employees`
ORDER BY `hire_date` DESC
LIMIT 7;

-- p20
UPDATE `employees` SET `salary` = `salary` * 1.12
WHERE `department_id` IN(1, 2, 4, 11);

SELECT `salary` FROM `employees`;