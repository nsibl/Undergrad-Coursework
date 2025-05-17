drop schema if exists finalGUIProject;

create schema finalGUIProject;
use finalGUIProject;

create table users(
	id integer not null unique auto_increment,
    username varchar(255) not null unique,
    pass varchar(255) not null,
    userRole integer not null,
    primary key (id)
);

create table product(
	id integer not null unique auto_increment,
    prodName varchar(255) not null,
    price decimal(10,2) not null,
    primary key (id)
);

create table sale(
	saleID integer not null unique auto_increment,
    prodID integer not null,
    userID integer not null,
    qty integer not null,
    total decimal(10,2) not null,
    primary key (saleID),
    foreign key (prodID) references product(id),
    foreign key (userID) references users(id)
);

insert into users (username, pass, userRole) VALUES ("Admin", "Admin", 1);

insert into users (username, pass, userRole) VALUES ("guest", "guest", 2);

insert into product (prodName, price) VALUES ("Cereal", 4.99);
insert into product (prodName, price) VALUES ("Milk", 3.00);

DELIMITER //

CREATE PROCEDURE finalGUIProject.LoginWithCreds(IN usernameInput VARCHAR(255), IN passwordInput VARCHAR(255))
BEGIN
    DECLARE userCount INT;
    SELECT id, userRole, username FROM users WHERE username = usernameInput AND pass = passwordInput;
END //

CREATE PROCEDURE finalGUIProject.GetAllProducts()
BEGIN
	SELECT id, prodName, price FROM product;
END //

CREATE PROCEDURE finalGUIProject.GetSalesTotal()
BEGIN
	SELECT SUM(total) from sale;
END//

CREATE PROCEDURE finalGUIProject.SubmitOrder(IN s_UserID INT, IN s_prodID INT, IN s_qty int)
BEGIN
	DECLARE prod_price DECIMAL(10,2);
    DECLARE total_order DECIMAL(10,2);
	SELECT price into prod_price FROM product WHERE id = s_prodID;
    SET total_order = (s_qty * prod_price);
    
    INSERT INTO sale (prodID, userID, qty, total) VALUES (s_prodID, s_UserID, s_qty, total_order);
    END//
    
CREATE PROCEDURE finalGUIProject.SubmitNewProduct(IN p_prodName VARCHAR(255), IN p_price DECIMAL(10,2))
BEGIN
	INSERT INTO product (prodName, price) VALUES (p_prodName, p_price);
END//

DELIMITER ;

CALL SubmitOrder(2, 2, 3);

SELECT * FROM sale;