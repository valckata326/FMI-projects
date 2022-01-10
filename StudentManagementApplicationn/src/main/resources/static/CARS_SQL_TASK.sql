DROP TABLE IF EXISTS cars_calendar;
DROP TABLE IF EXISTS car;

CREATE TABLE car (
                     id BIGINT PRIMARY KEY,
                     brand varchar(30),
                     model varchar(50),
                     car_number varchar(8)
);

CREATE TABLE cars_calendar (
                               id BIGINT PRIMARY KEY ,
                               car_id BIGINT REFERENCES car(id),
                               start_date date,
                               end_date date
);

ALTER TABLE cars_calendar
    Add CHECK (start_date <= cars_calendar.end_date);

INSERT INTO car
VALUES ('1', 'Opel', 'Astra', 'E1234DA'),
       ('2', 'Mazda', '3', 'PK4555KE'),
       ('3', 'Mitsu', 'Eclipse', 'KN9889DA'),
       ('4', 'Volkswagen', 'Golf', 'PK3333PK'),
       ('5', 'Subaru', 'Impreza', 'E2338YE');

INSERT INTO cars_calendar
VALUES ( '1', '1', '2021-06-06', '2021-06-15'),
       ('2', '1', '2021-06-16', '2021-06-28'),
       ('3', '1', '2021-06-29', '2021-07-01'),
       ('4', '2', '2021-06-15', '2021-06-16'),
       ('5', '2', '2021-06-25', '2021-06-27'),
       ('6', '3', '2021-05-22', '2021-07-21'),
       ('7', '4', '2021-06-16', '2021-06-18'),
       ('8', '4', '2021-06-18', '2021-06-21'),
       ('9', '5', '2021-06-08', '2021-06-12'),
       ('10', '5', '2021-06-24', '2021-06-26');


SELECT DISTINCT car_id, brand,model,car_number
FROM CARS_CALENDAR JOIN car c on c.id = cars_calendar.car_id
WHERE car_id NOT IN (SELECT car_id
                     From CARS_CALENDAR
                     WHERE
                         ('2021-06-14' < start_date
                             AND '2021-06-22' > start_date
                             AND '2021-06-22' < end_date)
                        OR
                         ('2021-06-14' > start_date
                             AND '2021-06-22' < end_date)
                        OR
                         ('2021-06-14' > start_date
                             AND '2021-06-14' < end_date
                             AND '2021-06-22' > end_date)
                        OR
                           ('2021-06-14' < start_date
                         AND '2021-06-22' > end_date))

