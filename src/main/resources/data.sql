drop table if exists batch_stock cascade;
drop table if exists batch_stock_item cascade;
drop table if exists in_bound_order cascade;
drop table if exists product cascade;
drop table if exists representante cascade;
drop table if exists section cascade;
drop table if exists seller cascade;
drop table if exists warehouse cascade;

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

create table in_bound_order (
                                id bigserial not null,
                                order_number  int not null,
                                order_date date,
                                representative_id int8,
                                section_id int8,
                                primary key (id)
);
--insert into in_bound_order(order_number, order_date, representative_id, section_id) values ( 123, '2021-01-01', 1, 1);

create table product (
                         id  bigserial not null,
                         stock_type varchar(20) not null,
                         description varchar(255),
                         name varchar(255),
                         primary key (id)
);
insert into product(stock_type ,description, name) values('FRESH', 'carne seca',  'descricao da carne seca'), ('NATURAL', 'banana',  'desc'),  ('NATURAL', 'Bacon',  'desc');

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
values (1, 'FRESH', '12', 150,  1), (2, 'NATURAL', '12', 150,  1), (3, 'FRESH', '12', 150,  1);


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
insert into warehouse (code, address, name, size, representante_id) values(1, 'endereco do armazem', 'armazem central',  10000, 1);

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
        foreign key (representative_id)
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