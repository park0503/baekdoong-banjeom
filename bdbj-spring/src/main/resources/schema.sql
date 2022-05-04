create table menu
(
    menu_id binary(16) primary key ,
    menu_name VARCHAR(30) not null ,
    category varchar(30) not null ,
    price int not null ,
    description varchar(500) default null,
    image_path varchar(255) default null,
    created_at datetime not null ,
    updated_at datetime default null,
    CONSTRAINT unq_menu_name UNIQUE (menu_name)
);

create table orders
(
    order_id binary(16) primary key,
    phone_number varchar(20) not null ,
    address varchar(100) not null ,
    detailed_address varchar(100) not null ,
    postcode varchar(200) not null ,
    order_status varchar(50) not null ,
    created_at datetime not null ,
    updated_at datetime default null
);