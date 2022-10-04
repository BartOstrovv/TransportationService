insert into users(first_name, last_name, email, password, created, role) VALUES ('Taras', 'Bart','tapasx@gmail.com', '$2a$10$T1dzz6qQ97qa8x81XnMDkewTucn91FxrIIF.9pl6qtmwYKnVOUVMq', '2010-10-10', 'CUSTOMER');
insert into customers(phone, id) VALUES ('22222', 1);
insert into users(first_name, last_name, email, password, created, role) VALUES ('Taras', 'Bart2', 'adm@gmail.com', '$2a$10$T1dzz6qQ97qa8x81XnMDkewTucn91FxrIIF.9pl6qtmwYKnVOUVMq', '2010-10-10', 'ADMIN');
insert into admins(id) VALUES (2);
insert into users(first_name, last_name, email, password, created, role) VALUES ('Ivan', 'Oxford', 'ivax@gmail.com', '$2a$10$T1dzz6qQ97qa8x81XnMDkewTucn91FxrIIF.9pl6qtmwYKnVOUVMq', '2008-12-10', 'TRANSPORTER');
insert into transporters(founded, logo, description, id) VALUES ('2004-01-02', 'APPLE', 'Buy your best smart phone and other products', 3);

insert into deliveries(created_date, description, arrival_date, arrival_build, arrival_city, arrival_country, arrival_street, departure_date, departure_build, departure_city, departure_country, departure_street, status, title, customer_id) VALUES ('2020-10-12', 'Need to transfer some items', '2020-11-11', 2, 'Interloper', 'Ukraine', 'Lev', '2020-12-11', 22, 'New-York', 'USA', 'Jon st', 'UNCONFIRMED_OFFERS', 'Find the bus', 1);
insert into deliveries(created_date, description, arrival_date, arrival_build, arrival_city, arrival_country, arrival_street, departure_date, departure_build, departure_city, departure_country, departure_street, status, title, customer_id) VALUES ('2020-10-12', 'DELIVERY WITHOUT OFFER', '2020-11-11', 2, 'Interloper', 'Ukraine', 'Lev', '2020-12-11', 22, 'New-York', 'USA', 'Foxy st', 'CREATED', 'Find the bus', 1);

insert into delivery_items(number, position, unit, delivery_id) VALUES (10, 'Gasoline', 'CUBIC_METER', 1);
insert into delivery_items(number, position, unit, delivery_id) VALUES (150, 'Paper', 'NUMBER', 1);
insert into delivery_items(number, position, unit, delivery_id) VALUES (150, 'Paper', 'NUMBER', 2);
insert into delivery_items(number, position, unit, delivery_id) VALUES (150, 'Pen', 'NUMBER', 2);

insert into offers(price, delivery_id, transporter_id) VALUES (10000, 1, 3);
