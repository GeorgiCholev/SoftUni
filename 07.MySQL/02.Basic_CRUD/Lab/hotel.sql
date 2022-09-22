USE `hotel`;

-- p01
SELECT `id`, `first_name`, `last_name`, `job_title`
FROM `employees`
ORDER BY `id`;

-- p02
SELECT `id`, concat_ws(' ', `first_name`, `last_name`) AS 'Full Name',
 `job_title`, `salary`
FROM `employees`
WHERE `salary` > 1000.00
ORDER BY `id`;

-- p03
UPDATE `employees`
SET `salary` = `salary` + 100
WHERE `job_title` = 'Manager';

SELECT `salary`
FROM `employees`;

-- p04
CREATE VIEW `v_top_paid_empl` AS
	SELECT * FROM `employees`
    ORDER BY `salary` DESC
    LIMIT 1;
    
SELECT * FROM `v_top_paid_empl`;
    
-- p05
SELECT * FROM `employees`
WHERE `department_id` = 4 AND `salary` >= 1000
ORDER BY `id`;

-- p06
DELETE FROM `employees`
WHERE `department_id` IN(1, 2);

SELECT * FROM `employees`
ORDER BY `id`;

DROP SCHEMA `hotel`;