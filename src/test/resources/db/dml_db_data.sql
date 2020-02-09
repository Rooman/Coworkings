-- users table
insert into users (id, username, fullName, role, tel, email, city, sole, password)
values (1, 'firstUser', 'First User', 'USER', '+380630636363', 'first.user@gmail.com', 'Kiev', 'sole123', 'pass123');

insert into reviews (id, description, status, coworkingid, userid)
values (4, 'All is fine, but too loud', 'new', 2, 1);


insert into coworkings (id, name, mainimage, overview, location, reviewscount, city,
dayprice, weekprice, monthprice, rating, openinghours, containsdesk, containsoffice, containsmeetingroom)
values (1, 'TestName', 'http://testlink', 'TestOverview', 'TestLocation', 1, 'Kiev', 1, 2, 3, 5, 'TestOpeningHours', true, true, true);

insert into coworkings (id, name, mainimage, overview, location, reviewscount, city,
dayprice, weekprice, monthprice, rating, openinghours, containsdesk, containsoffice, containsmeetingroom)
values (2, 'AnotherName', 'http://testlink2', 'TestOverview2', 'TestLocation2', 2, 'Lviv', 4, 5, 6, 8, 'TestOpeningHours2', false, false, false);

