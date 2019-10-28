create table employee (
    dni char(8) primary key,
    name varchar(50) not null,
    lastNameFather varchar(50) not null,
    lastNameMother varchar(50) not null,
    password varchar(25) not null,
    rol varchar(25) not null
);
create table product (
    sku char(9) primary key,
    name varchar(50) not null,
    description varchar(150) not null,
    price numeric(50) not null,
    imgProduct varchar(35) not null,
    category varchar(25) not null
);
