USE soft_uni;

-- p01 Count Employees by Town
DELIMITER $$
CREATE FUNCTION ufn_count_employees_by_town(town_name VARCHAR(50))
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE employee_count INT;
    SET employee_count := (
		SELECT COUNT(employee_id)
		FROM employees AS e
		JOIN addresses AS a
		USING (address_id)
		JOIN towns AS t
		USING (town_id)
		WHERE t.`name` = town_name
        );
	RETURN employee_count;
END$$

-- p02 Employees Promotion
CREATE PROCEDURE usp_raise_salaries(department_name VARCHAR(50))
BEGIN
	DECLARE dept_id INT;
    SET dept_id := (
		SELECT department_id
        FROM departments
        WHERE `name` = department_name
        );
        
	UPDATE employees 
    SET salary = salary * 1.05 
    WHERE department_id = dept_id;
END$$

-- p03 Employees Promotion by ID
CREATE PROCEDURE usp_raise_salary_by_id(id INT)
BEGIN
	START TRANSACTION;
	IF ((SELECT COUNT(employee_id) FROM employees
			WHERE employee_id = id) < 1)
	THEN ROLLBACK;
	ELSE
		UPDATE employees SET salary = salary * 1.05 WHERE employee_id = id;
	END IF;
END;

-- p04 Triggered
CREATE TABLE deleted_employees 
	(employee_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(50), 
    middle_name VARCHAR(50),
	job_title VARCHAR(50),
	department_id INT,
    salary DECIMAL(7, 2));
    
CREATE TRIGGER tr_deleted_employees
AFTER DELETE
ON employees
FOR EACH ROW
BEGIN
	INSERT INTO deleted_employees(first_name, last_name, middle_name, job_title, department_id, salary) 
    VALUES (OLD.first_name, OLD.last_name, OLD.middle_name, 
			OLD.job_title, OLD.department_id, OLD.salary);
END