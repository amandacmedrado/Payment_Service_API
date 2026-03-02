create table product (
    id bigint auto_increment primary key,
    uuid char(36) not null,
    name varchar(100) not null,
    price decimal(10,2) not null
);

create table payment (
    id bigint auto_increment primary key,
    uuid char(36) not null,
    original_amount decimal(10,2) not null,
    final_amount decimal(10,2) not null,
    cashback_amount decimal(10,2),
    applied_discount decimal(10,2),
    method_type varchar(30) not null,
    status varchar(30) not null,
    created_at timestamp default current_timestamp
);

