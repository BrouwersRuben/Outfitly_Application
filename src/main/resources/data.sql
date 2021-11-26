INSERT INTO customer(customer_id, email, account_password, first_name, last_name, phone_number, country, city,
                     street_name, street_number, apartment_number, zip_code)
VALUES (1, 'email@email.com', 'password', 'FirstName', 'LastName', '2229292922', 'Belgium', 'Antwerp',
        'Nationalestraat', '19', DEFAULT, 'ZIPCODE');

INSERT INTO customer_accounts(customer_id, account_id, account_name, is_master, preferred_forecast_country,
                              preferred_forecast_city)
VALUES (1, 1, 'FirstName', TRUE, 22.1, 22.3);

INSERT INTO clothes(account_id, clothing_id, material, clothing_type, rain_proofness, occasion, weather, image_file_path)
VALUES (1, 't-shirt', 'material', 'type', 'rain_proofness', 'occasion', 'weather', 'file_path');

INSERT INTO arduino_forecast(customer_id, forecast_timestamp, temperature, humidity)
VALUES (1, '1980-01-01', 22.0, 22.0);


-- -- Dummy user.
-- INSERT INTO customer(customer_id, email, account_password, first_name, last_name, phone_number, country, city,
--                      street_name, street_number, apartment_number, zip_code)
-- VALUES (1, 'testUser1@gmail.com', 'password', 'John', 'Doe', '0488112654', 'Belgium', 'Antwerp', 'Nationalestraat', '5', DEFAULT, '2000');
--
-- INSERT INTO customer_accounts(customer_id, account_id, account_name, is_master, preferred_forecast_country,
--                               preferred_forecast_city)
-- VALUES (1, 1, 'John', TRUE, 'Belgium', 'Antwerp');
--
-- INSERT INTO clothes(account_id, clothing_id, material, clothing_type, rain_proofness, occasion, weather, image_file_path)
-- VALUES (1, 'White T-Shirt', 'Cotton', 'T-Shirts', 'Bad', 'Casual', 'Warm', 'https://www.pngfind.com/pngs/m/77-772127_white-t-shirt-transparent-background-png-plain-t.png');
--
-- INSERT INTO clothes(account_id, clothing_id, material, clothing_type, rain_proofness, occasion, weather, image_file_path)
-- VALUES (1, 'White T-Shirt', 'Cotton', 'T-Shirts', 'Bad', 'Casual', 'Warm', 'https://www.pngfind.com/pngs/m/77-772127_white-t-shirt-transparent-background-png-plain-t.png');


