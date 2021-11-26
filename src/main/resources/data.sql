INSERT INTO customer(customer_id, email, account_password, first_name, middle_name, last_name, phone_number, country, city, street_name, street_number, building_number, apartment_number, zip_code) VALUES
    (1, 'email@email.com', 'password', 'FirstName', 'MiddleName', 'LastName', '2229292922', 'Belgium', 'Antwerp', 'Nationalestraat', 19, DEFAULT, DEFAULT, 'ZIPCODE');

INSERT INTO customer_accounts(customer_id, account_id, account_name, is_master, preferred_forecast_country, preferred_forecast_city) VALUES (1, 1, 'FirstName', TRUE, 22.1, 22.3);

INSERT INTO clothes(account_id, material, clothing_type, rain_proofness, occasion, weather, image_file_path) VALUES (1, 'material', 'type', 'rain_proofness', 'occasion', 'weather', 'file_path');

INSERT INTO arduino_forecast(customer_id, forecast_timestamp, temperature, humidity) VALUES (1, '1980-01-01', 22.0, 22.0);