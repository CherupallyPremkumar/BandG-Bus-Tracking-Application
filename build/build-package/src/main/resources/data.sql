create schema BandGBus;
use BandGBus;

INSERT INTO BandGBus.college
(college_address, college_email, college_name, college_phone, created_by, created_time, id, last_modified_by, last_modified_time, tenant, test_entity, version)
VALUES
('123 Main St, College Town, TX', 'info@collegetown.edu', 'College Town University', '123-456-7890', 'admin', '2024-12-01 08:00:00', 1, 'admin', '2024-12-01 09:00:00', 'default', false, 1);

INSERT INTO BandGBus.college
(college_address, college_email, college_name, college_phone, created_by, created_time, id, last_modified_by, last_modified_time, tenant, test_entity, version)
VALUES
('456 Elm St, Cityville, TX', 'contact@cityville.edu', 'Cityville College', '234-567-8901', 'admin', '2024-12-02 10:00:00', 2, 'admin', '2024-12-02 11:00:00', 'default', false, 1);

INSERT INTO BandGBus.college
(college_address, college_email, college_name, college_phone, created_by, created_time, id, last_modified_by, last_modified_time, tenant, test_entity, version)
VALUES
('789 Oak Rd, Suburbia, TX', 'hello@suburbia.edu', 'Suburbia Institute', '345-678-9012', 'admin', '2024-12-03 12:00:00', 3, 'admin', '2024-12-03 13:00:00', 'default', false, 1);

INSERT INTO BandGBus.college
(college_address, college_email, college_name, college_phone, created_by, created_time, id, last_modified_by, last_modified_time, tenant, test_entity, version)
VALUES
('101 Pine Blvd, Metroville, TX', 'contact@metroville.edu', 'Metroville University', '456-789-0123', 'admin', '2024-12-04 14:00:00', 4, 'admin', '2024-12-04 15:00:00', 'default', false, 1);

INSERT INTO BandGBus.college
(college_address, college_email, college_name, college_phone, created_by, created_time, id, last_modified_by, last_modified_time, tenant, test_entity, version)
VALUES
('202 Maple Ave, Uptown, TX', 'support@uptown.edu', 'Uptown College', '567-890-1234', 'admin', '2024-12-05 16:00:00', 5, 'admin', '2024-12-05 17:00:00', 'default', false, 1);
-- Insert data into ROUTE table
INSERT INTO ROUTE (ID,ROUTE_CODE, test_entity,ROUTE_NAME, ROUTE_TYPE, ROUTE_DESC, SLA_LATE, SLA_TENDING_LATE, SLA_RED_DATE, SLA_YELLOW_DATE, STATE_ENTRY_TIME, VERSION, CREATED_TIME, LAST_MODIFIED_TIME, CREATED_BY, LAST_MODIFIED_BY, STATE_ID, TENANT)
VALUES
('1','R001',false, 'Route A', 'Express', 'Main road route with limited stops', TRUE, FALSE, '2024-12-15', '2024-12-10', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', 1, 'Tenant1'),
('2','R002', false,'Route B', 'Local', 'Route covering local streets and neighborhoods', FALSE, TRUE, '2024-12-18', '2024-12-12', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', 2, 'Tenant2'),
('3','R003',false, 'Route C', 'Express', 'Route connecting city center to the suburbs', TRUE, TRUE, '2024-12-20', '2024-12-15', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', 3, 'Tenant3'),
('4','R004', false,'Route D', 'Local', 'Route through the downtown area with multiple stops', FALSE, FALSE, '2024-12-25', '2024-12-22', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', 4, 'Tenant4'),
('5','R005', false,'Route E', 'Express', 'Route connecting two major cities', TRUE, FALSE, '2024-12-30', '2024-12-28', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', 5, 'Tenant5');

-- Insert data into BUSSTOP table
INSERT INTO BUSSTOP (ID,BUSSTOP_CODE,test_entity, BUSSTOP_TYPE, BUSSTOP_NAME, LATITUDE, LONGITUDE, SLA_LATE, SLA_TENDING_LATE, SLA_RED_DATE, SLA_YELLOW_DATE, STATE_ENTRY_TIME, VERSION, CREATED_TIME, LAST_MODIFIED_TIME, CREATED_BY, LAST_MODIFIED_BY, STATE_ID, TENANT)
VALUES
('1','BS001',false, 'Standard', 'Main Street Station', 40.7128, -74.0060, TRUE, FALSE, '2024-12-15', '2024-12-10', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', 1, 'Tenant1'),
('2','BS002', false,'Standard', 'Central Park Stop', 40.7851, -73.9683, FALSE, TRUE, '2024-12-18', '2024-12-12', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', 2, 'Tenant2'),
('3','BS003', false,'Standard', 'City Mall Stop', 40.7484, -73.9857, TRUE, TRUE, '2024-12-20', '2024-12-15', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', 3, 'Tenant3'),
('4','BS004', false,'Express', 'Downtown Center', 40.7306, -73.9352, FALSE, FALSE, '2024-12-25', '2024-12-22', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', 4, 'Tenant4'),
('5','BS005', false,'Express', 'Suburb Junction', 40.6536, -73.9082, TRUE, FALSE, '2024-12-30', '2024-12-28', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', 5, 'Tenant5'),
('6','BS006', false,'Standard', 'North Station', 40.7480, -73.9850, TRUE, FALSE, '2024-12-10', '2024-12-05', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', 1, 'Tenant1'),
('7','BS007', false,'Standard', 'West End Stop', 40.6784, -73.9447, FALSE, TRUE, '2024-12-12', '2024-12-08', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', 2, 'Tenant2'),
('8','BS008', false,'Express', 'Central Avenue Junction', 40.7220, -73.9955, TRUE, TRUE, '2024-12-17', '2024-12-14', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', 3, 'Tenant3'),
('9','BS009', false,'Express', 'Southend Plaza', 40.7416, -73.9333, FALSE, FALSE, '2024-12-22', '2024-12-18', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', 4, 'Tenant4'),
('10','BS010',false, 'Standard', 'East Ridge Stop', 40.6633, -73.9385, TRUE, FALSE, '2024-12-28', '2024-12-25', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', 5, 'Tenant5');
-- Insert data into BUS table
INSERT INTO BUS (ID,LICENSE_PLATE,test_entity, CAPACITY, STATE_ID, TENANT, CREATED_TIME, LAST_MODIFIED_TIME, CREATED_BY, LAST_MODIFIED_BY,SLA_LATE, SLA_TENDING_LATE, SLA_RED_DATE, SLA_YELLOW_DATE, STATE_ENTRY_TIME, VERSION)
VALUES
('1','ABC123',false, 50,  1,  'Tenant1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', FALSE, TRUE, '2024-12-18', '2024-12-12', CURRENT_TIMESTAMP, 1),
('2','XYZ456',false, 45,  2,  'Tenant2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', FALSE, TRUE, '2024-12-18', '2024-12-12', CURRENT_TIMESTAMP, 1),
('3','LMN789',false, 60,  3,  'Tenant3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', FALSE, TRUE, '2024-12-18', '2024-12-12', CURRENT_TIMESTAMP, 1),
('4','PQR012',false, 55,  4,  'Tenant4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', FALSE, TRUE, '2024-12-18', '2024-12-12', CURRENT_TIMESTAMP, 1),
('5','DEF345',false, 50,  5, 'Tenant5', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', FALSE, TRUE, '2024-12-18', '2024-12-12', CURRENT_TIMESTAMP, 1);

-- Insert data into ROUTE_BUS_STOPS table (Relationship between ROUTE and BUSSTOP)
INSERT INTO ROUTE_BUS_STOPS (id,college_id,ROUTE_ID, BUSSTOP_ID, BUS_ID, BUSSTOP_ORDER, CREATED_TIME, LAST_MODIFIED_TIME, CREATED_BY, LAST_MODIFIED_BY, TENANT,test_entity,version)
VALUES
(1,1,1, 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin',  'Tenant1',false,1),
(2,1,1, 2, 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin',  'Tenant1',false,1),
(3,1,2, 2, 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin',  'Tenant2',false,1),
(4,1,3, 3, 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin',  'Tenant3',false,1),
(5,1,1, 5, 1, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin',  'Tenant1',false,1),
(6,1,1, 6, 1, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin',  'Tenant1',false,1),
(7,1,1, 7, 1, 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin',  'Tenant1',false,1),
(8,1,1, 8, 1, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin',  'Tenant',false,1),
(9,1,1, 9, 1, 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin',  'Tenant',false,1),
(10,1,1, 10, 1, 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin',  'Tenant',false,1);

-- Insert data into STUDENT table
INSERT INTO STUDENT (ID,test_entity,STUDENT_NAME, STUDENT_EMAIL, STUDENT_PHONE, STUDENT_ADDRESS, STUDENT_GENDER, STUDENT_SCHOOL, STUDENT_PASSWORD, STUDENT_BIRTHDAY, SLA_LATE, SLA_TENDING_LATE, SLA_RED_DATE, SLA_YELLOW_DATE, STATE_ENTRY_TIME, VERSION, CREATED_TIME, LAST_MODIFIED_TIME, CREATED_BY, LAST_MODIFIED_BY, STATE_ID, TENANT)
VALUES
('1',false,'John Doe', 'john.doe@school.com', '555-1234', '123 Elm Street', 'Male', 'School A', 'password123', '2005-06-15', TRUE, FALSE, '2024-12-15', '2024-12-10', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', 1, 'Tenant1'),
('2',false,'Jane Smith', 'jane.smith@school.com', '555-5678', '456 Oak Avenue', 'Female', 'School B', 'password456', '2006-07-20', FALSE, TRUE, '2024-12-18', '2024-12-12', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', 2, 'Tenant2'),
('3',false,'Mike Johnson', 'mike.johnson@school.com', '555-8765', '789 Pine Road', 'Male', 'School C', 'password789', '2007-08-10', TRUE, TRUE, '2024-12-20', '2024-12-15', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', 3, 'Tenant3'),
('4',false,'Emily Brown', 'emily.brown@school.com', '555-4321', '101 Maple Lane', 'Female', 'School D', 'password101', '2008-09-05', FALSE, FALSE, '2024-12-25', '2024-12-22', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', 4, 'Tenant4'),
('5',false,'David Lee', 'david.lee@school.com', '555-8764', '202 Birch Street', 'Male', 'School E', 'password202', '2009-10-12', TRUE, FALSE, '2024-12-30', '2024-12-28', CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Admin', 'Admin', 5, 'Tenant5');


-- Insert sample data for ATTENDANCE
INSERT INTO ATTENDANCE (ID,test_entity,BUS_ID,  CURRENT_attendance, SLA_LATE, SLA_TENDING_LATE, SLA_RED_DATE, SLA_YELLOW_DATE, STATE_ENTRY_TIME, VERSION, CREATED_TIME, LAST_MODIFIED_TIME, CREATED_BY, LAST_MODIFIED_BY, STATE_ID, TENANT)
VALUES
('1',false,1,   30, FALSE, FALSE, '2024-12-01', '2024-12-05', NOW(), 1, NOW(), NOW(), 'admin', 'admin', 1, 'tenantA'),
('2',false,1,   30, TRUE, FALSE, '2024-12-02', '2024-12-06', NOW(), 1, NOW(), NOW(), 'admin', 'admin', 1, 'tenantA'),
('3',false,2,   45, FALSE, TRUE, '2024-12-03', '2024-12-07', NOW(), 1, NOW(), NOW(), 'admin', 'admin', 1, 'tenantA'),
('4',false,3,   20, FALSE, FALSE, '2024-12-04', '2024-12-08', NOW(), 1, NOW(), NOW(), 'admin', 'admin', 1, 'tenantB'),
('5',false,3,   20, TRUE, TRUE, '2024-12-05', '2024-12-09', NOW(), 1, NOW(), NOW(), 'admin', 'admin', 2, 'tenantB'),
('6',false,4,   50, FALSE, FALSE, '2024-12-06', '2024-12-10', NOW(), 1, NOW(), NOW(), 'admin', 'admin', 2, 'tenantC'),
('7',false,5,   10, FALSE, FALSE, '2024-12-07', '2024-12-11', NOW(), 1, NOW(), NOW(), 'admin', 'admin', 2, 'tenantC');