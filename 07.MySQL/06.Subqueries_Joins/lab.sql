USE `soft_uni`;

-- p01 Managers
SELECT `e`.`employee_id`,
	   CONCAT_WS(' ', `e`.`first_name`, `e`.`last_name`) AS 'full_name',
       `d`.`department_id`,
       `d`.`name` AS 'department_name'
FROM `employees` AS `e` JOIN `departments` AS `d`
ON `d`.`manager_id` = `e`.`employee_id`
ORDER BY `e`.`employee_id`
LIMIT 5;

-- p02 Towns Addresses
SELECT `t`.`town_id`, `t`.`name` AS 'town_name', `a`.`address_text`
FROM `addresses` AS `a` JOIN `towns` AS `t`
ON `a`.`town_id` = `t`.`town_id`
WHERE `t`.`town_id` IN(9, 15, 32)
ORDER BY `town_id`, `address_id`;  

-- p03 Employees Without Managers
SELECT `employee_id`, `first_name`, `last_name`, `department_id`, `salary`
FROM `employees`
WHERE `manager_id` IS NULL;

-- p04 Higher Salary
SELECT COUNT(`salary`) AS 'count'
FROM `employees`
WHERE `salary` > (SELECT AVG(`salary`) 
                  FROM `employees`);
