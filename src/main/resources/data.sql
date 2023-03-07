--Queries to generate static data in the department table--
INSERT INTO employee_department (id, department_name, manager_Id) VALUES (1, 'Front-End', 1);

INSERT INTO employee_department (id, department_name, manager_Id) VALUES (2, 'Back-End', 2);

INSERT INTO employee_department (id, department_name, manager_Id) VALUES (3, 'Sales', 3);

INSERT INTO employee_department (id, department_name, manager_Id) VALUES (4, 'QA', 4);

INSERT INTO employee_department (id, department_name, manager_Id) VALUES (5, 'Game-Development', 5);

INSERT INTO employee_department (id, department_name, manager_Id) VALUES (6, 'Design', 6);

INSERT INTO employee_department (id, department_name, manager_Id) VALUES (7, 'Infra', 7);


--Queries to generate static data in the employee table--
INSERT INTO employee_personal_details (id,first_name,last_name,email,official_email,employee_gender,contact_number,designation,role,department_id) VALUES (1,"Ali","Afroze","afroze@gmail.com","afroze@flyerssoft.com","MALE","9003218899","MANAGER","ADMIN",1);

INSERT INTO employee_personal_details (id,first_name,last_name,email,official_email,employee_gender,contact_number,designation,role,department_id) VALUES (2,"Chakravarthy","chowdry","mourya@flyerssoft.com","mourya@flyerssoft.com","MALE","9003218899","MANAGER","ADMIN",2);

INSERT INTO employee_personal_details (id,first_name,last_name,email,official_email,employee_gender,contact_number,role,designation,department_id) VALUES (3,"Abhinav","abhinav","abhinav@gmail.com","abhinav@flyerssoft.com","MALE","9003218899","MANAGER","ADMIN",3);

INSERT INTO employee_personal_details (id,first_name,last_name,email,official_email,employee_gender,contact_number,role,designation,department_id) VALUES (4,"Subhash","subhash","subhash@gmail.com","subhash@flyerssoft.com","MALE","9003218899","MANAGER","ADMIN",4);

INSERT INTO employee_personal_details (id,first_name,last_name,email,official_email,employee_gender,contact_number,role,designation,department_id) VALUES (5,"Vignesh","sekhar","vsekhar@gmail.com","vsekhar@flyerssoft.com","MALE","9003218899","MANAGER","ADMIN",5);

INSERT INTO employee_personal_details (id,first_name,last_name,email,official_email,employee_gender,contact_number,role,designation,department_id) VALUES (6,"Ananthu","ananthu","ananthu@gmail.com","ananthu@flyerssoft.com","MALE","9003218899","MANAGER","ADMIN",6);

INSERT INTO employee_personal_details (id,first_name,last_name,email,official_email,employee_gender,contact_number,designation,role,department_id) VALUES (7,"Arivan","ramesh","aramesh@gmail.com","aramesh@flyerssoft.com","MALE","9003218899","MANAGER","ADMIN",7);
