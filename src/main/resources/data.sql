--Queries to generate static data in the employee_bank_details table--
INSERT INTO employee_bank_details(id, aadhar_number, bank_account_number, bank_branch_location, bank_ifsc_code, bank_name, employee_bank_account_type, pan_number)
VALUES(1,"232343434543","3434567","CHENNAI","IOB0004256","IOB","PERSONAL","66666666666"),
      (2,"232343434543","3434567","CHENNAI","IOB0004256","IOB","PERSONAL","66666666666"),
      (3,"232343434543","3434567","CHENNAI","IOB0004256","IOB","PERSONAL","66666666666"),
      (4,"232343434543","3434567","CHENNAI","IOB0004256","IOB","PERSONAL","66666666666"),
      (5,"232343434543","3434567","CHENNAI","IOB0004256","IOB","PERSONAL","66666666666"),
      (6,"232343434543","3434567","CHENNAI","IOB0004256","IOB","PERSONAL","66666666666"),
      (7,"232343434543","3434567","CHENNAI","IOB0004256","IOB","PERSONAL","66666666666"),
      (8,"232343434543","3434567","CHENNAI","IOB0004256","IOB","PERSONAL","66666666666");


--Queries to generate static data in the department table--
INSERT INTO employee_department (id, department_name, manager_Id)
VALUES (1, 'Front-End', 2),
       (2, 'Back-End', 3),
       (3, 'Sales', 4),
       (4, 'QA', 5),
       (5, 'Game-Development', 6),
       (6, 'Design', 7),
       (7, 'Infra', 8);




--Queries to generate static data in the employee table--
INSERT INTO employee_personal_details (id,first_name,last_name,email,official_email,employee_gender,contact_number,emergency_contact_number,employee_martial_status,primary_reporting_manager,primary_reporting_manager_name,reporting_manager,reporting_manager_name,designation,role,department_id,employee_bank_details_id)
VALUES (1,"Uday","Kanth","udaykanth@gmail.com","udaykanth@flyerssoft.com","MALE","9003277899","9874887262","MARRIED",null,null,null,null,"MANAGER","ADMIN",null,1),
       (2,"Ali","Afroze","afroze@gmail.com","afroze@flyerssoft.com","MALE","9003218899","9874627262","SINGLE",1,"Uday",null,null,"MANAGER","ADMIN",1,2),
       (3,"Chakravarthy","chowdry","mourya@flyerssoft.com","mourya@flyerssoft.com","MALE","9003218899","7898765671","SINGLE",1,"Uday",null,null,"MANAGER","ADMIN",2,3),
       (4,"Abhinav","abhinav","abhinav@gmail.com","abhinav@flyerssoft.com","MALE","9003218899","9898989898","SINGLE",1,"Uday",null,null,"MANAGER","ADMIN",3,4),
       (5,"Subhash","subhash","subhash@gmail.com","subhash@flyerssoft.com","MALE","9003218899","9893546767","SINGLE",1,"Uday",null,null,"MANAGER","ADMIN",4,5),
       (6,"Vignesh","sekhar","vsekhar@gmail.com","vsekhar@flyerssoft.com","MALE","9003218899","5656786567","SINGLE",1,"Uday",null,null,"MANAGER","ADMIN",5,6),
       (7,"Ananthu","ananthu","ananthu@gmail.com","ananthu@flyerssoft.com","MALE","9003218899","9345264567","SINGLE",1,"Uday",null,null,"MANAGER","ADMIN",6,7),
       (8,"Arivan","ramesh","aramesh@gmail.com","aramesh@flyerssoft.com","MALE","9003218899","9890990890","SINGLE",1,"Uday",null,null,"MANAGER","ADMIN",7,8);

----Queries to generate static data in the employee table--
--INSERT INTO employee_personal_detail  (id,first_name,last_name,email,official_email,employee_gender,contact_number,emergency_contact_number,employee_martial_status,primary_reporting_manager,primary_reporting_manager_name,reporting_manager,reporting_manager_name,designation,role,department_id,employee_bank_details_id)
--VALUES (1,"Uday","Kanth","udaykanth@gmail.com","udaykanth@flyerssoft.com","MALE","9003277899","9874887262","MARRIED",null,null,null,null,"MANAGER","ADMIN",null,1),
--        (2,"Ali","Afroze","afroze@gmail.com","afroze@flyerssoft.com","MALE","9003218899","9874627262","SINGLE",1,"Uday",null,null,"MANAGER","ADMIN",1,2),
--        (3,"Chakravarthy","chowdry","mourya@flyerssoft.com","mourya@flyerssoft.com","MALE","9003218899","7898765671","SINGLE",1,"Uday",null,null,"MANAGER","ADMIN",2,3),
--        (4,"Abhinav","abhinav","abhinav@gmail.com","abhinav@flyerssoft.com","MALE","9003218899","9898989898","SINGLE",1,"Uday",null,null,"MANAGER","ADMIN",3,4),
--        (5,"Subhash","subhash","subhash@gmail.com","subhash@flyerssoft.com","MALE","9003218899","9893546767","SINGLE",1,"Uday",null,null,"MANAGER","ADMIN",4,5),
--        (6,"Vignesh","sekhar","vsekhar@gmail.com","vsekhar@flyerssoft.com","MALE","9003218899","5656786567","SINGLE",1,"Uday",null,null,"MANAGER","ADMIN",5,6),
--        (7,"Ananthu","ananthu","ananthu@gmail.com","ananthu@flyerssoft.com","MALE","9003218899","9345264567","SINGLE",1,"Uday",null,null,"MANAGER","ADMIN",6,7),
--        (8,"Arivan","ramesh","aramesh@gmail.com","aramesh@flyerssoft.com","MALE","9003218899","9890990890","SINGLE",1,"Uday",null,null,"MANAGER","ADMIN",7,8);


--Queries to generate static data in the job history table--
INSERT INTO employee_job_history(id,company,job_start_date,job_end_date,role,employee_details_id)
VALUES(1,"HCL",'2020-03-07 14:30:00','2023-03-07 14:30:00',"junior",1),
       (2,"HCL",'2020-03-07 14:30:00','2023-03-07 14:30:00',"junior",2),
       (3,"HCL",'2020-03-07 14:30:00','2023-03-07 14:30:00',"junior",3),
       (4,"HCL",'2020-03-07 14:30:00','2023-03-07 14:30:00',"junior",4),
       (5,"HCL",'2020-03-07 14:30:00','2023-03-07 14:30:00',"junior",5),
       (6,"HCL",'2020-03-07 14:30:00','2023-03-07 14:30:00',"junior",6),
       (7,"HCL",'2020-03-07 14:30:00','2023-03-07 14:30:00',"junior",7),
       (8,"HCL",'2020-03-07 14:30:00','2023-03-07 14:30:00',"junior",8);



--Queries to generate static data in the educational_details table--
INSERT INTO employee_educational_details(id,course_start_date,course_end_date,employee_educational_type,percentage,qualification,stream,employee_details_id)
VALUES(1,'2015-03-01 14:30:00','2019-03-10 14:30:00',"UNDERGRADUATE","80","BE","CSE",1),
      (2,'2015-03-01 14:30:00','2019-03-10 14:30:00',"UNDERGRADUATE","80","BE","CSE",2),
      (3,'2015-03-01 14:30:00','2019-03-10 14:30:00',"UNDERGRADUATE","80","BE","CSE",3),
      (4,'2015-03-01 14:30:00','2019-03-10 14:30:00',"UNDERGRADUATE","80","BE","CSE",4),
      (5,'2015-03-01 14:30:00','2019-03-10 14:30:00',"UNDERGRADUATE","80","BE","CSE",5),
      (6,'2015-03-01 14:30:00','2019-03-10 14:30:00',"UNDERGRADUATE","80","BE","CSE",6),
      (7,'2015-03-01 14:30:00','2019-03-10 14:30:00',"UNDERGRADUATE","80","BE","CSE",7),
      (8,'2015-03-01 14:30:00','2019-03-10 14:30:00',"UNDERGRADUATE","80","BE","CSE",8);


--Queries to generate static data in the employee_address table--
INSERT INTO employee_address(id, line1, line2, address_type, city, pin_code, state, employee_details_id)
VALUES(1,"23","chennai-600028","CURRENT","CHENNAI","600028","TamilNadu",1),
       (2,"23","chennai-600028","CURRENT","CHENNAI","600028","TamilNadu",2),
       (3,"23","chennai-600028","CURRENT","CHENNAI","600028","TamilNadu",3),
       (4,"23","chennai-600028","CURRENT","CHENNAI","600028","TamilNadu",4),
       (5,"23","chennai-600028","CURRENT","CHENNAI","600028","TamilNadu",5),
       (6,"23","chennai-600028","CURRENT","CHENNAI","600028","TamilNadu",6),
       (8,"23","chennai-600028","CURRENT","CHENNAI","600028","TamilNadu",8);

