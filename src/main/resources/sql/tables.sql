create table product (
product_id number primary key,
product_name varchar2(200),
description varchar2(300),
amount number,
category varchar2(200)
);

create table meal (
meal_id number primary key,
meal_name varchar2(200),
description varchar2(300),
category varchar2(200),
products other
);

create table meal_products (
meal_meal_id number,
products_key number,
products number
);