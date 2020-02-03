create table users
(
	id bigint not null
		constraint users_pkey
			primary key,
	username varchar(40) not null,
	fullName varchar(120),
	role varchar(10) not null,
	tel varchar(15),
	email varchar(150) not null,
	city varchar(100),
	sole varchar(50) not null,
	password varchar(50) not null
);
