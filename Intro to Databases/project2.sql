create schema sibleySubmission;
use sibleySubmission;

create table customer (
	c_id integer unique not null auto_increment primary key,
    fName varchar(255) not null,
    lName varchar(255) not null,
    phone char(10),
    email varchar(255) not null
);

create table rewards (
	rewardsNum integer unique not null primary key,
    c_id integer not null,
    foreign key (c_id) references customer(c_id),
    points integer
);

create table product (
	p_id integer unique not null auto_increment primary key,
    prodName varchar(255) unique not null,
    price decimal(5,2) not null,
    aisle char(2),
    department varchar(255),
    QOH integer not null
);

create table employee (
	e_id integer unique not null auto_increment primary key,
    fName varchar(255) not null,
    lName varchar(255) not null
);

create table sale (
	c_id integer not null, 
    p_id integer not null,
    e_id integer not null,
    primary key(c_id, p_id, e_id),
    foreign key (c_id) references customer(c_id),
    foreign key (p_id) references product(p_id),
    foreign key (e_id) references employee(e_id),
    saleDate datetime not null,
    quantity integer not null
);

insert into customer (fName, lName, phone, email)
values
("Aaron", "Aaronson", "5551231224", "aaron.aaronson@email.internet");


insert into rewards (c_id, points)
values
("1", "0");

insert into customer (fName, lName, email)
values
("Bob", "Bert", "bob.bert@email.internet");

insert into customer (fName, lName, email)
values
("Christa", "Candy", "c.candy@email.internet");

insert into rewards (c_id, points)
values
("3", "100");

insert into employee (fName, lName)
values
("Dolores", "Dot");

insert into employee (fName, lName)
values
("Ernest", "Engles");

insert into employee (fName, lName)
values
("Fran", "Foxtrot");

insert into product (prodName, price, department, QOH)
values
("Crunchy Crunch", "3.99", "Breakfast", "10");

insert into product (prodName, price, QOH)
values
("Lemon Aid", "4.49", "7");

insert into product (prodName, price, aisle, QOH)
values
("Xtreme Shampoo", "5.99", "A2", "10");

insert into product (prodName, price, QOH)
values
("Chips-o-rama", "9.99", "7");


insert into sale (c_id, e_id, p_id, saleDate, quantity)
values
("1", "1", "3", "2023-10-11", "1");



update product set QOH = 9 where p_id = 3;

insert into sale (c_id, e_id, p_id, saleDate, quantity)
values
("2", "1", "3", "2023-11-12", "1");

insert into sale (c_id, e_id, p_id, saleDate, quantity)
values
("2", "1", "4", "2023-11-12", "1");

insert into rewards (c_id, points)
values
("2", "5");

update product set QOH = 8 where p_id = 3;

update product set QOH = 6 where p_id = 4;


insert into sale (c_id, e_id, p_id, saleDate, quantity)
values
("3", "2", "2", "2023-11-15", "1");

insert into sale (c_id, e_id, p_id, saleDate, quantity)
values
("3", "2", "1", "2023-11-15", "2");

insert into sale (c_id, e_id, p_id, saleDate, quantity)
values
("3", "2", "4", "2023-11-15", "3");

update product set QOH = 6 where p_id = 2;
update product set QOH = 8 where p_id = 1;
update product set QOH = 3 where p_id = 4;

update rewards set points = 113 where c_id = 3;

insert into sale (c_id, e_id, p_id, saleDate, quantity)
values
("1", "2", "1", "2023-11-15", "3");

update rewards set points = 8 where c_id = 1;

update product set QOH = 5 where p_id = 1;

insert into sale (c_id, e_id, p_id, saleDate, quantity)
values
("2", "1", "1", "2023-12-16", "1");

insert into sale (c_id, e_id, p_id, saleDate, quantity)
values
("2", "1", "2", "2023-11-12", "2");

update rewards set points = 10 where c_id = 2;

update product set QOH = 4 where p_id = 1;
update product set QOH = 4 where p_id = 2;

delete from sale 
where c_id = 2 and p_id = 3;

update rewards set points = 112 where c_id = 3;
update product set QOH = 4 where p_id = 4;
update sale set quantity = 2 where c_id = 3 and p_id = 4;

update product set aisle = "B5" where p_id = 2;
update product set department = "Drinks" where p_id = 2;

delete from employee where e_id = 3;

update product set prodName = "Ultra Xtreme Shampoo" where p_id = 3;

select * from product where QOH <= 5;

select p.prodName, sum(s.quantity) as total_sold
from product p 
inner join sale s on p.p_id = s.p_id 
group by p.p_id 
order by total_sold desc
limit 1;

select e.fName, count(*) as total_sales
from employee e
inner join sale s on e.e_id = s.e_id
group by e.e_id
order by total_sales desc
limit 1;

select s.c_id, c.fName, sum(p.price * s.quantity) as total_spent
from sale s 
join product p on s.p_id = p.p_id
join customer c on s.c_id = c.c_id
group by s.c_id, c.fName
order by total_spent desc
limit 1;

select sum(p.price * s.quantity) as total_spent_november
from sale s
join product p on s.p_id = p.p_id
where month(s.saleDate) = 11;

select distinct p.prodName
from product p
inner join sale s on p.p_id = s.p_id
where s.c_id = 1;