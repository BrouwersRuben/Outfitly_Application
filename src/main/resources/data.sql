-- Dummy user.
INSERT INTO customer(customer_id, email, account_password, first_name, last_name, birth_year, phone_number, country,
                     city,
                     street_name, street_number, apartment_number, zip_code)
VALUES (1, 'testUser1@gmail.com', 'password', 'John', 'Doe', '2000-01-01', '0488112654', 'Belgium', 'Antwerp',
        'Nationalestraat', '5', DEFAULT, '2000');

INSERT INTO customer_settings(customer_id, preferred_forecast_country, preferred_forecast_city)
VALUES (1, 'Belgium', 'Antwerp');

INSERT INTO clothes(customer_id, clothing_id, clothing_name, material, clothing_type, rain_proofness, occasion, weather,
                    image_file_path)
VALUES (1, 1, 'White T-Shirt', 'Cotton', 'T-Shirts', 'Bad', 'Casual', 'Warm',
        'https://www.pngfind.com/pngs/m/77-772127_white-t-shirt-transparent-background-png-plain-t.png');

INSERT INTO clothes(customer_id, clothing_id, clothing_name, material, clothing_type, rain_proofness, occasion, weather,
                    image_file_path)
VALUES (1, 2, 'Black Sweatpants', 'Cotton', 'Trousers', 'Bad', 'Casual', 'Mild',
        'https://ae01.alicdn.com/kf/H6aac326860d84f01898376cc100032b0Y/black-pants-red-sweatpants-men-trousers-elastic-fleece-winter-casual-Jogger-brand-style-mens-white-sweatpants.png');

INSERT INTO clothes(customer_id, clothing_id, clothing_name, material, clothing_type, rain_proofness, occasion, weather,
                    image_file_path)
VALUES (1, 3, 'Yellow Raincoat', 'Synthetic', 'Jacket', 'Good', 'Casual', 'Mild',
        'https://mpng.subpng.com/20180623/krt/kisspng-raincoat-hood-bluza-jacket-sleeve-rain-coat-5b2dcc140369a6.536958161529728020014.jpg');

INSERT INTO clothes(customer_id, clothing_id, clothing_name, material, clothing_type, rain_proofness, occasion, weather,
                    image_file_path)
VALUES (1, 4, 'Brown Dress Shoes', 'Leather', 'Shoes', 'Normal', 'Elegant', 'Universal',
        'https://www.pngall.com/wp-content/uploads/5/Wedding-Men-Shoes-PNG.png');

INSERT INTO clothes(customer_id, clothing_id, clothing_name, material, clothing_type, rain_proofness, occasion, weather,
                    image_file_path)
VALUES (1, 5, 'Black Winter Jacket', 'Synthetic', 'Jacket', 'Normal', 'Casual', 'Cold',
        'https://banner2.cleanpng.com/20180927/vg/kisspng-jacket-coat-nike-winter-clothing-nike-team-winter-jacket-adult-fanatics-suppl-5bacdfb15c9405.4919081915380561133792.jpg');

INSERT INTO arduino_forecast(customer_id, forecast_timestamp, temperature, humidity)
VALUES (1, '1980-01-01', 22.0, 22.0);

INSERT INTO current_weather_forecast(forecast_timestamp, forecast_country, forecast_city, temperature, feels_like_temperature,
                                     lowest_temperature, highest_temperature, wind_speed, humidity, weather_description)
VALUES ('1980-01-01', 'Belgium', 'Antwerp', 24.0, 20.0, 22.0, 27.0, 3, 84, 'rain');