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
)