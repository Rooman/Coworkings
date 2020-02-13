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

create table reviews
(
	id bigint primary key,
	description text not null,
	status text not null constraint status_value check (status = 'new' OR status = 'approved' OR status = 'rejected'),
	coworkingid bigint not null,
	userid bigint not null references users (id)
);

create table coworkings
(
	id bigint primary key,
	name varchar(50) not null,
	mainimage text not null,
	overview text not null,
	location text not null,
	reviewscount bigint not null,
    city varchar(20) not null,
    dayprice numeric not null,
    weekprice numeric not null,
    monthprice numeric not null,
    rating numeric not null,
    openinghours text not null,
    containsdesk boolean not null,
    containsoffice boolean not null,
    containsmeetingroom boolean not null,
    hasSingleMonitors boolean not null,
    hasDualMonitors boolean not null,
    hasVideoRec boolean not null,
    hasPrinter boolean not null,
    hasScanner boolean not null,
    hasProjector boolean not null,
    hasMicrophone boolean not null
);
