use fishingProject;

create table Angler(
	A_ID int primary key auto_increment,
    A_Name varchar(255),
    A_Add varchar(255),
    A_Phone varchar(255)
);

insert into Angler (A_Name, A_Add, A_Phone)
select distinct a_name, a_add, a_phone
from catch;

create table Tournament (
	T_Code varchar(10) primary key,
    T_Name varchar(255),
    T_Loc varchar(255),
    T_Date date
);

insert into Tournament (T_Code, T_Name, T_Loc, T_Date)
select distinct t_code, t_name, t_loc, t_date
from catch;

create table Catches (
	catch_id int primary key auto_increment,
    T_Code varchar(10),
    A_ID int,
    Fish_Species varchar(255),
    Fish_Class varchar(255),
    Weight int,
    Length int,
    foreign key (t_code) references Tournament(T_Code),
    foreign key (a_id) references Angler(A_ID)
);

insert into Catches (T_Code, A_ID, Fish_Species, Fish_Class, Weight, Length)
select t_code, a_id, fish_species, fish_class, weight, length
from catch;
