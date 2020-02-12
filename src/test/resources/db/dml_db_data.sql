-- users table
insert into users (id, username, fullName, role, tel, email, city, sole, password)
values (1, 'firstUser', 'First User', 'USER', '+380630636363', 'first.user@gmail.com', 'Kiev', 'sole123', 'pass123');


--review table
insert into reviews (id, description, status, coworkingid, userid)
values (4, 'All is fine, but too loud', 'new', 2, 1);


--coworking table
insert into coworkings (id, name, mainimage, overview, location, reviewscount, city,
dayprice, weekprice, monthprice, rating, openinghours, containsdesk, containsoffice, containsmeetingroom)
values (1, 'TestName', 'http://testlink', 'TestOverview', 'TestLocation', 1, 'Kiev', 1, 2, 3, 5, 'TestOpeningHours', true, true, true);

insert into coworkings (id, name, mainimage, overview, location, reviewscount, city,
dayprice, weekprice, monthprice, rating, openinghours, containsdesk, containsoffice, containsmeetingroom)
values (2, 'AnotherName', 'http://testlink2', 'TestOverview2', 'TestLocation2', 2, 'Lviv', 4, 5, 6, 8, 'TestOpeningHours2', false, false, false);

insert into coworkings (id, name, mainimage, overview, location, reviewscount, city,
dayprice, weekprice, monthprice, rating, openinghours, containsdesk, containsoffice, containsmeetingroom)
values (3, 'AnotherName2', 'http://testlink3', 'TestOverview3', 'TestLocation3', 1, 'Lviv', 7, 8, 9, 11, 'TestOpeningHours3', false, true, true);

insert into coworkings (id, name, mainimage, overview, location, reviewscount, city,
dayprice, weekprice, monthprice, rating, openinghours, containsdesk, containsoffice, containsmeetingroom)
values (4, 'AnotherName3', 'http://testlink4', 'TestOverview4', 'TestLocation4', 1, 'Lviv', 7, 8, 9, 11, 'TestOpeningHours4', true, false, true);


