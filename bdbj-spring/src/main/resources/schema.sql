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

create table order_item
(
    seq bigint not null primary key auto_increment,
    order_id binary(16) NOT NULL,
    menu_id binary(16) NOT NULL ,
    category varchar(50) not null ,
    menu_name varchar(30) not null,
    price bigint not null ,
    quantity int not null ,
    created_at datetime not null ,
    updated_at datetime default null,
    index(order_id),
    constraint fk_order_items_to_order foreign key (order_id) references orders (order_id) on delete cascade ,
    constraint fk_order_items_to_menu foreign key (menu_id) references menu (menu_id)
);