drop table if exists batch_stock cascade;
drop table if exists batch_stock_item cascade;
drop table if exists in_bound_order cascade;
drop table if exists product cascade;
drop table if exists representante cascade;
drop table if exists section cascade;
drop table if exists seller cascade;
drop table if exists warehouse cascade;
drop table IF EXISTS users_perfis;
drop table IF EXISTS users;
drop table IF EXISTS perfil;

create table batch_stock (
                             id bigserial not null,
                             batch_number  int not null,
                             current_quality varchar(255),
                             current_temperature float8,
                             due_date date,
                             initial_quality varchar(255),
                             manufacturing_time timestamp,
                             minimum_temperature float8,
                             inbound_order_id int8,
                             seller_id int8,
                             volume float8,
                             maximum_temperature float8,
                             quantity int not null,
                             primary key (id)
);
INSERT INTO batch_stock(
     batch_number, current_quality, current_temperature, due_date, initial_quality, manufacturing_time, minimum_temperature,
                         seller_id, volume, maximum_temperature, quantity)
VALUES (1,3, 10, '2022-12-03', 1, '2016-09-21 13:43:27', 10, 1, 20.5, 20, 20), (2,3, 10, '2022-12-03', 1, '2016-09-21 13:43:27', 10, 2, 20.5, 20, 20);


create table batch_stock_item (
                                  id  bigserial not null,
                                  maximum_temperature float8 not null,
                                  minimum_temperature float8 not null,
                                  quantity int4 not null,
                                  volume float8 not null,
                                  batch_stock_id int8,
                                  product_id int8,
                                  primary key (id)
);
insert into batch_stock_item(maximum_temperature, minimum_temperature, quantity, volume, batch_stock_id, product_id)
values (20, 10, 20, 20.5, 1, 1), (20, 10, 20, 20.5, 2, 2);

create table in_bound_order (
                                id bigserial not null,
                                order_number  int not null,
                                order_date date,
                                representante_id int8,
                                section_id int8,
                                primary key (id)
);


create table product (
                         id  bigserial not null,
                         stock_type varchar(20) not null,
                         description varchar(255),
                         name varchar(255),
                         primary key (id)
);
insert into product(name ,description, stock_type) values('Alcatra', 'Carne Bovina', 'FRESH'), ('Banana Prata', 'Sítio Vinhático', 'NATURAL'),
                                                         ('Laranja Pera', 'Sítio Juizeiro', 'NATURAL'),('Laranja Pera', 'Caxias', 'NATURAL') ;

create table representante (
                               id  bigserial not null,
                               cpf varchar(255) not null,
                               name varchar(255) not null,
                               primary key (id)
);
insert into representante(cpf, name) values ( '234.098.109-20',  'Kenyo'),( '234.098.109-22',  'Jeff');

create table section (
                         id  bigserial not null,
                         code bigint not null,
                         stock_type varchar(20) not null,
                         minimum_temperature varchar(255),
                         capacity bigint,
                         warehouse_id int8,
                         primary key (id)
);
insert into section (code, stock_type , minimum_temperature, capacity, warehouse_id)
values (1, 'FRESH', '12', 150,  1), (2, 'NATURAL', '12', 150,  2), (3, 'FRESH', '12', 150,  1);


create table seller (
                        id  bigserial not null,
                        cpf varchar(255),
                        email varchar(255),
                        name varchar(255),
                        primary key (id)
);
insert into seller (cpf, email, name) values ( '161.453.010-66',  'mail@mail.com',  'hugo'),( '161.453.010-66',  'mail@mail.com',  'Carlos');

create table warehouse (
                           id  bigserial not null,
                           code bigserial not null,
                           address varchar(255) not null,
                           name varchar(255) not null,
                           size varchar(255) not null,
                           representante_id int8,
                           primary key (id)
);
insert into warehouse (code, address, name, size, representante_id) values(1, 'Sao Paulo', 'Osasco',  10000, 1), (2, 'Florianopolis', 'armazem central',  10000, 2);

alter table batch_stock
    add constraint FKk2737y022ijpd4y2w7cixv1ew
        foreign key (inbound_order_id)
            references in_bound_order;

alter table batch_stock
    add constraint FKma2q4ejn7s29c5j6fem7rk0uh
        foreign key (seller_id)
            references seller;

alter table batch_stock_item
    add constraint FK4hnjcjx6g4cdxcs625scjydsb
        foreign key (batch_stock_id)
            references batch_stock;

alter table batch_stock_item
    add constraint FKduqhpc9ga6vcjohx44ac363el
        foreign key (product_id)
            references product;

alter table in_bound_order
    add constraint FKisau45ihrn98bmlwfnlo23axk
        foreign key (representante_id)
            references representante;

alter table in_bound_order
    add constraint FKsr5yci0eowgmpim69tgp4g1h
        foreign key (section_id)
            references section;

alter table section
    add constraint FKlimr8jdiu8ur1jp722gvhsb9t
        foreign key (warehouse_id)
            references warehouse;

alter table warehouse
    add constraint FKiabr4g8lokk0f3x2f6sck68rx
        foreign key (representante_id)
            references representante;



create table users(
                      username varchar(50) not null primary key,
                      password varchar(500) not null,
                      enabled boolean not null
);

insert into users values('admin', '$2a$10$FnSsqc9hnfZ.HLR0HDZ0gOGbNnd1yit.sZitZVibdCgle1E6cwL4a', '1');
insert into users values('super', '$2a$10$rSq5gJbuvmosmSQpqkyIBeHrP05Av/qqUtsY0MQT1n750nDEX8AHe', '1');

create table perfil(
                       id serial not null primary key,
                       nome varchar(20) not null
);

insert into perfil(nome) values ('ADMIN');
insert into perfil(nome) values ('CUSTOMER');
insert into perfil(nome) values ('SELLER');

create table users_perfis(
                             user_username  varchar(50) not null,
                             perfis_id integer not null,
                             constraint pk_usuario_perfil primary key (user_username , perfis_id)
);

insert into users_perfis values ('admin',1);
insert into users_perfis values ('super',3);