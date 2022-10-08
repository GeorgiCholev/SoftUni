USE soft_uni;
DELIMITER $$

-- p01 Employees with Salary Above 35000
CREATE PROCEDURE usp_get_employees_salary_above_35000()
BEGIN
	SELECT first_name, last_name
    FROM employees
    WHERE salary > 35000
    ORDER BY first_name, last_name, employee_id;
END$$

-- p02 Employees with Salary Above Number
CREATE PROCEDURE usp_get_employees_salary_above(reference_salary DECIMAL(10, 4))
BEGIN
	SELECT first_name, last_name
    FROM employees
    WHERE salary >= reference_salary
    ORDER BY first_name, last_name, employee_id;
END$$

-- p03 Town Names Starting With
CREATE PROCEDURE usp_get_towns_starting_with(first_part_of_name VARCHAR(20))
BEGIN
	SELECT `name`
    FROM towns
    WHERE lower(`name`) LIKE lower(concat(first_part_of_name, '%'))
    ORDER BY `name`;
END$$

-- p04 Employees from Town
CREATE PROCEDURE usp_get_employees_from_town(employees_town_name VARCHAR(20))
BEGIN
	SELECT e.first_name, e.last_name
    FROM employees AS e
    JOIN addresses USING (address_id)
    JOIN towns USING (town_id)
    WHERE towns.`name` = employees_town_name
    ORDER BY e.first_name, e.last_name, e.employee_id;
END$$

-- p05 Salary Level Function
CREATE FUNCTION ufn_get_salary_level(employee_salary DECIMAL(10, 4))
RETURNS VARCHAR(7)
DETERMINISTIC
BEGIN
	DECLARE salary_level VARCHAR(7);
	IF employee_salary < 30000 THEN SET salary_level := 'Low';
    ELSEIF employee_salary <= 50000 THEN SET salary_level := 'Average';
    ELSE SET salary_level := 'High';
    END IF;
    RETURN salary_level;
END$$

-- p06 Employees by Salary Level
CREATE PROCEDURE usp_get_employees_by_salary_level(salary_level VARCHAR(7))
BEGIN
	SELECT first_name, last_name
    FROM employees
    WHERE ufn_get_salary_level(salary) = salary_level
    ORDER BY first_name DESC, last_name DESC;
END$$

-- p07 Define Function
CREATE FUNCTION ufn_is_word_comprised(set_of_letters VARCHAR(50), word VARCHAR(50))
RETURNS INT
DETERMINISTIC
BEGIN
	RETURN lower(word) REGEXP lower(concat('^[', set_of_letters, ']+$'));
END$$

-- p08 Find Full Name
 CREATE PROCEDURE usp_get_holders_full_name()
 BEGIN
	SELECT concat_ws(' ', first_name, last_name) AS 'full_name'
    FROM account_holders
    ORDER BY full_name, id;
END$$

-- p09 People with Balance Higher Than
CREATE PROCEDURE usp_get_holders_with_balance_higher_than(reference_money DECIMAL(12, 4))
BEGIN
	SELECT first_name, last_name
    FROM account_holders AS h
    JOIN accounts AS a
      ON h.id = a.account_holder_id
    GROUP BY h.id
    HAVING SUM(a.balance) > reference_money
    ORDER BY account_holder_id;
END$$

-- p10 Future Value Function
CREATE FUNCTION ufn_calculate_future_value(`sum` DECIMAL(15, 4), 
										   yearly_interest_rate DECIMAL(8, 5),
                                           number_of_years INT)
RETURNS DECIMAL(15, 4)
DETERMINISTIC
BEGIN
	DECLARE future_value DECIMAL(15, 4);
    SET future_value := `sum` * (pow(1 + yearly_interest_rate, number_of_years));
    RETURN future_value;
END$$

-- p11 Calculating Interest
CREATE PROCEDURE usp_calculate_future_value_for_account(account_id INT, interest_rate DECIMAL(7, 4))
BEGIN
	SELECT a.id AS 'account_id',
		   h.first_name,
           h.last_name,
           a.balance AS 'current_balance',
           ufn_calculate_future_value(a.balance, interest_rate, 5) AS 'balance_in_5_years'
	FROM account_holders AS h
    JOIN accounts AS a
      ON h.id = a.account_holder_id
    WHERE account_id = a.id;
END$$

-- p12 Deposit Money
CREATE PROCEDURE usp_deposit_money(account_id INT, money_amount DECIMAL(15, 4))
BEGIN
	START TRANSACTION;
    IF money_amount <= 0 THEN ROLLBACK;
    ELSE 
		UPDATE accounts 
		SET balance = balance + money_amount
		WHERE account_id = accounts.id;
    COMMIT;
    END IF;
END$$

-- p13 Withdraw Money
CREATE PROCEDURE usp_withdraw_money(account_id INT, money_amount DECIMAL(15, 4))
BEGIN
	DECLARE balance_after_withdrawal DECIMAL(15, 4);
    SET balance_after_withdrawal := ((
		SELECT balance 
        FROM accounts 
        WHERE id = account_id
        ) - money_amount);
	START TRANSACTION;
    IF money_amount > 0 AND balance_after_withdrawal >= 0
		THEN UPDATE accounts 
			 SET balance = balance_after_withdrawal
             WHERE id = account_id;
		COMMIT;
	ELSE ROLLBACK;
    END IF;
END$$

-- p14 Money Transfer
CREATE PROCEDURE 
	usp_transfer_money(from_account_id INT, to_account_id INT, amount DECIMAL(15, 4))
BEGIN
	START TRANSACTION;
    IF (amount <= 0 
		OR (SELECT count(id) FROM accounts WHERE id = from_account_id) < 1 
        OR (SELECT count(id) FROM accounts WHERE id = to_account_id) < 1
        OR from_account_id = to_account_id
        OR (SELECT balance FROM accounts WHERE id = from_account_id) < amount)
	THEN ROLLBACK;
    ELSE
		UPDATE accounts SET balance = balance - amount WHERE id = from_account_id;
        UPDATE accounts SET balance = balance + amount WHERE id = to_account_id;
        COMMIT;
	END IF;
END$$

-- p15 Log Accounts Trigger
CREATE TABLE `logs` (
	log_id INT PRIMARY KEY AUTO_INCREMENT,
    account_id INT,
    old_sum DECIMAL(15, 4),
    new_sum DECIMAL(15, 4)
)$$

CREATE TRIGGER tr_log_accounts
AFTER UPDATE
ON accounts
FOR EACH ROW
BEGIN
	INSERT INTO `logs` (account_id, old_sum, new_sum)
    VALUES(OLD.id, OLD.balance, NEW.balance);
END$$

-- p16 Emails Trigger
CREATE TABLE notification_emails (
	id INT PRIMARY KEY AUTO_INCREMENT,
    recipient INT,
    `subject` TEXT,
    body TEXT
)$$

CREATE TRIGGER tr_email_after_log
AFTER INSERT
ON `logs`
FOR EACH ROW
BEGIN
	INSERT INTO notification_emails (recipient, `subject`, body)
    VALUES (NEW.account_id, 
			concat('Balance change for account: ', NEW.account_id),
            concat('On ', date_format(now(), '%b %e %Y at %H:%i:%S %p'), 
				   ' your balance was changed from ', NEW.old_sum, ' to ', NEW.new_sum,'.'));
END$$

UPDATE accounts SET balance = 69 WHERE id = 1$$