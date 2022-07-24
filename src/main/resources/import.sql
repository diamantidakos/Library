delete from loans;
delete from items;        
delete from bookauthors;
delete from books;
delete from publishers;
delete from authors;
delete from borrowers;
delete from borrowercategories;

insert into Authors (id, firstname, lastname) values (2000, 'Booch', 'Grady');
insert into Authors (id, firstname, lastname) values (2001, 'Rumbaugh', 'James');
insert into Authors (id, firstname, lastname) values (2002, 'Jacobson', 'Jacobson');
insert into Authors (id, firstname, lastname) values (2003, 'Martin', 'Fowler');

insert into Publishers(id, name, phonenumber, email) values (2004, 'Addison Wesley', null, null);

insert into books (id, isbn, title, publication, pubyear, publisherid) values (1000, '1', 'The Unified Modeling Language User Guide','2',2005,2004);
insert into books (id, isbn, title, publication, pubyear, publisherid) values (1001, '2', 'UML Distilled','3',2004,2004);
insert into books (id, isbn, title, publication, pubyear, publisherid) values (1002, '3', 'Refactoring: Improving the Design of Existing Code','1',1999,2004);

insert into bookauthors(bookid,authorid) values (1000, 2000);
insert into bookauthors(bookid,authorid) values (1000, 2001);
insert into bookauthors(bookid,authorid) values (1000, 2002);
insert into bookauthors(bookid,authorid) values (1001, 2003);
insert into bookauthors(bookid,authorid) values (1002, 2003);


insert into items (itemno, bookno, itemstate) values (1, 1000, 'AVAILABLE');
insert into items (itemno, bookno, itemstate) values (2, 1001, 'AVAILABLE');
insert into items (itemno, bookno, itemstate) values (3, 1002, 'AVAILABLE');
insert into items (itemno, bookno, itemstate) values (4, 1000, 'AVAILABLE');
insert into items (itemno, bookno, itemstate) values (5, 1001, 'AVAILABLE');

insert into borrowercategories(id,description,lendingitems,lendingdays,dailyfineamount,dailyfinecurrency) values (3000, 'Καθηγητής',6,180,0,'EUR');
insert into borrowercategories(id,description,lendingitems,lendingdays,dailyfineamount,dailyfinecurrency) values (3001, 'Φοιτητής',4,7,0,'EUR');

insert into borrowers(borrowerno,phonenumber,email,street,number,city,zipcode,country,categoryid,firstname,lastname) values (1, null, 'mgia@aueb.gr',null, null, null, null, null, 3000, 'Μανόλης', 'Γιακουμάκης');
insert into borrowers(borrowerno,phonenumber,email,street,number,city,zipcode,country,categoryid,firstname,lastname) values (2, null, 'nad@aueb.gr',null, null, null, null, null, 3001, 'Νίκος', 'Διαμαντίδης');