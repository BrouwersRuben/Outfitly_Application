DROP TABLE IF EXISTS arduino_forecast;
DROP TABLE IF EXISTS clothes;
DROP TABLE IF EXISTS customer_accounts;
DROP TABLE IF EXISTS customer;

-- CREATE SEQUENCE customer_id_sequence START WITH 0 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS customer(
                                       customer_id INTEGER CONSTRAINT customer_customer_id_nn NOT NULL,
                                       email VARCHAR(50) CONSTRAINT customer_email_nn NOT NULL,
                                       account_password VARCHAR(50) CONSTRAINT customer_account_password_nn NOT NULL,
                                       first_name VARCHAR(50) CONSTRAINT customer_first_name_nn NOT NULL,
                                       last_name VARCHAR(50) CONSTRAINT customer_last_name_nn NOT NULL,
                                       phone_number VARCHAR(50) CONSTRAINT customer_phone_number_nn NOT NULL,
                                       country VARCHAR(50) CONSTRAINT customer_country_nn NOT NULL,
                                       city VARCHAR(50) CONSTRAINT customer_city_nn NOT NULL,
                                       street_name VARCHAR(50) CONSTRAINT customer_street_name_nn NOT NULL,
                                       street_number smallint CONSTRAINT customer_street_number_nn NOT NULL,
                                       building_number smallint DEFAULT NULL,
                                       apartment_number smallint DEFAULT NULL,
                                       zip_code VARCHAR(50) CONSTRAINT customer_zip_code_nn NOT NULL
);

ALTER TABLE customer ADD CONSTRAINT customer_customer_id_pk PRIMARY KEY(customer_id);

CREATE TABLE IF NOT EXISTS customer_accounts(
                                                customer_id INTEGER CONSTRAINT customer_accounts_customer_id_nn NOT NULL,
                                                account_id INTEGER CONSTRAINT customer_accounts_account_id_nn NOT NULL,
                                                account_name VARCHAR(50) CONSTRAINT customer_account_account_name_NN NOT NULL,
                                                is_master boolean CONSTRAINT customer_account_is_master_nn NOT NULL,
                                                latitude DECIMAL CONSTRAINT customer_account_latitude_nn NOT NULL,
                                                longitude DECIMAL CONSTRAINT customer_account_longitude_nn NOT NULL
);

ALTER TABLE customer_accounts ADD CONSTRAINT customer_accounts_account_id_pk PRIMARY KEY(account_id);
-- ALTER TABLE customer_accounts ADD CONSTRAINT customer_accounts_account_id_uq UNIQUE (account_id);
ALTER TABLE customer_accounts ADD CONSTRAINT customer_accounts_customer_id_fk FOREIGN KEY(customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE;


CREATE TABLE IF NOT EXISTS clothes(
                                      account_id INTEGER CONSTRAINT clothes_account_id_nn NOT NULL,
                                      material VARCHAR(50) CONSTRAINT clothes_material_nn NOT NULL,
                                      clothing_type VARCHAR(50) CONSTRAINT clothes_clothing_type_nn NOT NULL,
                                      rain_proofness VARCHAR(50) CONSTRAINT clothes_rain_proofness_nn NOT NULL,
                                      occasion VARCHAR(50) CONSTRAINT clothes_occasion_nn NOT NULL,
                                      weather VARCHAR(50) CONSTRAINT clothes_weather_nn NOT NULL,
                                      image_file_path VARCHAR(50) DEFAULT 'file path for placeholder' CONSTRAINT clothes_image_file_path_nn NOT NULL
);

ALTER TABLE clothes ADD CONSTRAINT clothes_account_id_fk FOREIGN KEY(account_id) REFERENCES customer_accounts(account_id) ON DELETE CASCADE;


CREATE TABLE IF NOT EXISTS arduino_forecast(
                                               customer_id INTEGER CONSTRAINT arduino_forecast_customer_id_nn NOT NULL,
                                               forecast_timestamp TIMESTAMP CONSTRAINT arduino_forecast_timestamp_nn NOT NULL,
                                               temperature DECIMAL CONSTRAINT arduino_forecast_temperature_nn NOT NULL,
                                               humidity DECIMAL CONSTRAINT arduino_forecast_humidity_nn NOT NULL
);

ALTER TABLE arduino_forecast ADD CONSTRAINT arduino_forecast_customer_id_fk FOREIGN KEY(customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE;