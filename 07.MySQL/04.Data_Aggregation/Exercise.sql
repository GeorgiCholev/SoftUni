USE `gringotts`;

-- p01 Records' Count
SELECT COUNT(`id`) FROM `wizzard_deposits`;

-- p02 Longest Magic Wand
SELECT MAX(`magic_wand_size`)  AS 'longest_magic_wand' FROM `wizzard_deposits`;

-- p03 Longest Magic Wand Per Deposit Groups
SELECT `deposit_group`, 
MAX(`magic_wand_size`)  AS 'longest_magic_wand' 
FROM `wizzard_deposits`
GROUP BY `deposit_group`
ORDER BY `longest_magic_wand`, `deposit_group`;

-- p04 Smallest Deposit Group Per Magic Wand Size
SELECT `deposit_group`
FROM `wizzard_deposits`
GROUP BY `deposit_group`
ORDER BY MIN(`magic_wand_size`)
LIMIT 1;

-- p05 Deposits Sum
SELECT `deposit_group`, SUM(`deposit_amount`) AS 'total_sum'
FROM `wizzard_deposits`
GROUP BY `deposit_group`
ORDER BY `total_sum`;

-- p06 Deposits Sum for Ollivander Family
SELECT `deposit_group`, SUM(`deposit_amount`) AS 'total_sum'
FROM `wizzard_deposits`
WHERE `magic_wand_creator` = 'Ollivander family'
GROUP BY `deposit_group`
order by `deposit_group`;

-- p07 Deposits Filter
SELECT `deposit_group`, SUM(`deposit_amount`) AS 'total_sum'
FROM `wizzard_deposits`
WHERE `magic_wand_creator` = 'Ollivander family'
GROUP BY `deposit_group`
HAVING `total_sum` < 150000
ORDER BY `total_sum` DESC;

-- p08 Deposit Charge
SELECT `deposit_group`, `magic_wand_creator`, 
MIN(`deposit_charge`) AS 'min_deposit_charge'
FROM `wizzard_deposits`
GROUP BY `deposit_group`, `magic_wand_creator`
ORDER BY `magic_wand_creator`, `deposit_group`;

-- p09 Age Groups
SELECT 
	CASE
		WHEN `age` <= 10 THEN '[0-10]'
        WHEN `age` <= 20 THEN '[11-20]'
        WHEN `age` <= 30 THEN '[21-30]'
        WHEN `age` <= 40 THEN '[31-40]'
        WHEN `age` <= 50 THEN '[41-50]'
        WHEN `age` <= 60 THEN '[51-60]'
        ELSE '[61+]'
	END AS 'age_group',
COUNT(`id`) AS 'wizard_count'
FROM `wizzard_deposits`
GROUP BY `age_group`
ORDER BY `age_group`;

-- p10 First Letter
SELECT DISTINCT LEFT(`first_name`, 1) AS 'First Letter'
FROM `wizzard_deposits`
WHERE `deposit_group` = 'Troll Chest'
ORDER BY `First Letter`;

-- p11 Average Interest
SELECT `deposit_group`, `is_deposit_expired`,
AVG(`deposit_interest`) AS 'average_interest'
FROM `wizzard_deposits`
WHERE `deposit_start_date` > '1985-01-01'
GROUP BY `deposit_group`, `is_deposit_expired`
ORDER BY `deposit_group` DESC, `is_deposit_expired` ASC;

USE `soft_uni`;
-- p12 Employees Minimum Salaries
SELECT `department_id`, MIN(`salary`) AS 'minimum_salary'
FROM `employees`
WHERE `hire_date` > '2000-01-01'
GROUP BY `department_id`
HAVING `department_id` IN(2, 5, 7)
ORDER BY `department_id`;

-- p13 Employees Average Salaries
CREATE TABLE `more_than_30000` AS
	SELECT * FROM `employees`
    WHERE `salary` > 30000;

DELETE FROM `more_than_30000` 
WHERE `manager_id` = 42;

UPDATE `more_than_30000`
SET `salary` = `salary` + 5000
WHERE `department_id` = 1;

SELECT `department_id`,
AVG(`salary`) AS 'avg_salary'
FROM `more_than_30000`
GROUP BY `department_id`  
ORDER BY `department_id`;  

-- p14 Employees Maximum Salaries
SELECT `department_id`,
MAX(`salary`) AS 'max_salary'
FROM `employees`
GROUP BY `department_id`
HAVING NOT `max_salary` BETWEEN 30000 AND 70000
ORDER BY `department_id`;

-- p15 Employees Count Salaries
SELECT COUNT(`employee_id`) AS 'employees_without_manager'
FROM `employees`
GROUP BY `manager_id`
HAVING `manager_id` IS NULL;

-- p16 3rd Highest Salary
SELECT `department_id`, 
	(SELECT DISTINCT `salary`
     FROM `employees` e
     WHERE `e`.`department_id` = `employees`.`department_id`
     ORDER BY `salary` DESC
     LIMIT 1 OFFSET 2
) AS 'third_highest_salary'
FROM `employees`
GROUP BY `department_id`
HAVING `third_highest_salary` IS NOT NULL
ORDER BY `department_id`;

-- p17 Salary Challenge
SELECT `first_name`, `last_name`, `department_id`
FROM `employees` e1
WHERE (SELECT AVG(`salary`) AS 'avg'
	   FROM `employees` e2
       GROUP BY `department_id`
       HAVING `e1`.`department_id` = `e2`.`department_id`) < `e1`.`salary`
ORDER BY `e1`.`department_id`, `e1`.`employee_id`
LIMIT 10;       

-- p18 Departments Total Salaries
SELECT `department_id`, SUM(`salary`) AS 'total_salary'
FROM `employees`
GROUP BY `department_id`
ORDER BY `department_id`;

