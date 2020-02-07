-- users table
insert into users (id, username, fullName, role, tel, email, city, sole, password)
values (1, 'firstUser', 'First User', 'USER', '+380630636363', 'first.user@gmail.com', 'Kiev', 'sole123', 'pass123');

insert into reviews (id, description, status, coworkingid, userid)
values (4, 'All is fine, but too loud', 'new', 2, 1);