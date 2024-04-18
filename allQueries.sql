use hospitalmanagementsystem;

show tables;

truncate table doctor; 
truncate table medicine;
truncate table employee;
truncate table ward;
truncate table feedback;
truncate table pharmacy;
truncate table appointment;
truncate table logincredentials;
truncate table patient;
truncate table patientrecord;
truncate table resident;
truncate table paymentrecord;

alter table loginCredentials modify column userID varchar(255);
alter table employee modify column employeeID varchar(255);
alter table resident modify column residentID varchar(255);
alter table resident modify column doctorID varchar(255);
alter table patient modify column patientID varchar(255);
alter table patientrecord modify column patientID varchar(255);
alter table patientrecord modify column employeeID varchar(255);

insert into loginCredentials values("d001", "123");
insert into loginCredentials values("d002", "123");
insert into loginCredentials values("d003", "123");
insert into loginCredentials values("d004", "123");
insert into loginCredentials values("d005", "123");
insert into loginCredentials values("r001", "123");
insert into loginCredentials values("r002", "123");
insert into loginCredentials values("r003", "123");
insert into loginCredentials values("r004", "123");
insert into loginCredentials values("r005", "123");
insert into loginCredentials values("a001", "123");
insert into loginCredentials values("p001", "123");
insert into loginCredentials values("p002", "123");

insert into patient values("TJ Mayo", 20, "p001", "House Number 1", 5, "Male");
insert into patient values("Jane Smith", 45, "p002", "House Number 2", 2, "Female");

insert into employee values("Chris Pine", 35, "d001", 50000, "Doctor", "cp@gmail.com");
insert into employee values("John Snow", 36, "d002", 60000, "Doctor", "js@gmail.com");
insert into employee values("Glen Ben", 25, "d003", 70000, "Doctor", "gb@gmail.com");
insert into employee values("Aditya Patil", 26, "d004", 80000, "Doctor", "ap@gmail.com");
insert into employee values("Jason Johnson", 40, "d005", 90000, "Doctor", "jj@gmail.com");
insert into employee values("Vance Lance", 20, "r001", 10000, "Resident", "vl@gmail.com");
insert into employee values("Maria Greg", 21, "r002", 10000, "Resident", "mg@gmail.com");
insert into employee values("Frank Finley", 22, "r003", 10000, "Resident", "ff@gmail.com");
insert into employee values("Jolly Joseph", 23, "r004", 10000, "Resident", "jjfr@gmail.com");
insert into employee values("Timmy W", 24, "r005", 10000, "Resident", "tw@gmail.com");
insert into employee values("Vin Diesel", 19, "a001", 2500000, "Administrator", "vd@gmail.com");

insert into doctor values("Cardiologist", "d001", "Chris Pine", 50000);
insert into doctor values("Oncologist", "d002", "John Snow", 60000);
insert into doctor values("Neurologist", "d003", "Glen Ben", 70000);
insert into doctor values("Psychologist", "d004", "Jason Johnson", 80000);
insert into doctor values("Pediatrician", "d005", "Aditya Patil", 90000);


insert into medicine values(1, "Paracetamol", "Painkiller", 3);
insert into medicine values(2, "Rantac", "Antibiotic", 4);
insert into medicine values(3, "Dettol", "Antiseptic", 1);
insert into medicine values(4, "Band-Aid", "First-Aid", 1);
insert into medicine values(5, "Citrazine", "Painkiller", 5);
insert into medicine values(6, "Levothyroxin", "Painkiller", 3);
insert into medicine values(7, "Azithromycin", "Antibiotic", 2);
insert into medicine values(8, "Savlon", "Antiseptic", 1);
insert into medicine values(9, "Insubinsu", "Painkiller", 2);
insert into medicine values(10, "Cephalexin", "Antibiotic", 4);

insert into resident values("Vance Lance", "r001", "d001", 10000);
insert into resident values("Maria Greg", "r002", "d002", 10000);
insert into resident values("Frank Finley", "r003", "d003", 10000);
insert into resident values("Jolly Joseph", "r004", "d004", 10000);
insert into resident values("Timmy W", "r005", "d005", 10000);

insert into ward values("Psychology", 1, false);
insert into ward values("Psychology", 2, false);
insert into ward values("Psychology", 3, false);
insert into ward values("Psychology", 4, false);
insert into ward values("Psychology", 5, false);
insert into ward values("Psychology", 6, false);
insert into ward values("Neurology", 7, false);
insert into ward values("Neurology", 8, false);
insert into ward values("Neurology", 9, false);
insert into ward values("Neurology", 10, false);
insert into ward values("Neurology", 11, false);
insert into ward values("Neurology", 12, false);
insert into ward values("Pediatrics", 13, false);
insert into ward values("Pediatrics", 14, false);
insert into ward values("Pediatrics", 15, false);
insert into ward values("Pediatrics", 16, false);
insert into ward values("Pediatrics", 17, false);
insert into ward values("Pediatrics", 18, false);
insert into ward values("Oncology", 19, false);
insert into ward values("Oncology", 20, false);
insert into ward values("Oncology", 21, false);
insert into ward values("Oncology", 22, false);
insert into ward values("Oncology", 23, false);
insert into ward values("Oncology", 24, false);
insert into ward values("Cardiology", 25, false);
insert into ward values("Cardiology", 26, false);
insert into ward values("Cardiology", 27, false);
insert into ward values("Cardiology", 28, false);
insert into ward values("Cardiology", 29, false);
insert into ward values("Cardiology", 30, false);

insert into patientrecord values(1, "p001", "d001", curdate(),2 , 1, 1);
insert into patientrecord values(2, "p002", "d002", curdate(),1 , 11, 2);

insert into paymentrecord values(1, 23489.99);
insert into paymentrecord values(2, 26999.47);







