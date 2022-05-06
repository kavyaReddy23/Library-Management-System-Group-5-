create schema LibraryManagement;
use LibraryManagement;
create table Books (
bookId int primary key,
bookType varchar(30),
bookName varchar(30),
authorName varchar(30),
issued boolean default false,
lateFeePrice double);

create table Employee(
empId int primary key,
empName varchar(30),
paymentPending double,
phoneNumber varchar(10),
noOfBooks int);

insert into Books (bookId,bookType,bookName,authorName,lateFeePrice)values
(1,"Data Analytics","Big Data","Kenneth Cukier",5),
(2,"Technology","Life 3.0","Max Tegmark",6),
(3,"Management","Getting Things Done","David Allen",7);
insert into Employee values
(1,"Sanskar Gaur",0,"6261625099",0),
(2,"Amitabh Sharma",0,"7894561478",0),
(3,"Roshini Chehtri",0,"6547891230",0),
(4,"Kavya Reddy",0,"7894561230",0),
(5,"Yash Vardhan Verma",0,"9874561230",0);

--create table transaction
create table transaction (transactionId int primary key auto_increment,bookId int  not null,
empId int  not null,issueDate Date not null,expectedReturnDate Date not null,
actualReturnDate Date,isReturned boolean,foreign key(bookId) references books(bookId),
foreign key(bookId) references books(bookId),foreign key(empId) references employee(empId));

--alter table books issued to boolean
 alter table books 
 Modify column issued boolean default false;
 
--update table books
UPDATE `librarymanagement`.`books` SET `issued` = 'false' WHERE (`bookId` = '1');
UPDATE `librarymanagement`.`books` SET `issued` = 'false' WHERE (`bookId` = '2');
UPDATE `librarymanagement`.`books` SET `issued` = 'false' WHERE (`bookId` = '3');
UPDATE `librarymanagement`.`books` SET `issued` = 'false' WHERE (`bookId` = '4');


