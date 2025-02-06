-- Create the cities table
CREATE TABLE IF NOT EXISTS cities (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,  -- Add UNSIGNED for consistency
    name VARCHAR(100) NOT NULL,
    latitude DECIMAL(9,6),
    longitude DECIMAL(9,6)
);

-- Insert Florida cities
INSERT INTO cities (name, latitude, longitude) VALUES
('Miami', 25.7617, -80.1918),
('Orlando', 28.5383, -81.3792),
('Tampa', 27.9506, -82.4572),
('Jacksonville', 30.3322, -81.6557),
('Tallahassee', 30.4383, -84.2807);

-- Create the roads table for Dijkstra's algorithm
CREATE TABLE IF NOT EXISTS roads (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,   -- Add UNSIGNED for consistency
    city_from_id BIGINT UNSIGNED,                    -- Add UNSIGNED to match cities.id
    city_to_id BIGINT UNSIGNED,                      -- Add UNSIGNED to match cities.id
    distance DECIMAL(6,2) NOT NULL,
    FOREIGN KEY (city_from_id) REFERENCES cities(id),
    FOREIGN KEY (city_to_id) REFERENCES cities(id)
);

-- Insert data into roads table
INSERT INTO roads (city_from_id, city_to_id, distance) VALUES
(1, 2, 233.0),
(2, 3, 84.0),
(3, 4, 199.0),
(4, 5, 164.0),
(5, 1, 480.0),
(1, 5, 900.0);

-- Create users table (if authentication is needed)
CREATE TABLE IF NOT EXISTS users (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,  -- Add UNSIGNED for consistency
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

-- Create trips table
CREATE TABLE IF NOT EXISTS trips (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,  -- Add UNSIGNED for consistency
    user_id BIGINT UNSIGNED,                        -- Add UNSIGNED to match users.id
    city_start BIGINT UNSIGNED,                     -- Add UNSIGNED to match cities.id
    city_end BIGINT UNSIGNED,                       -- Add UNSIGNED to match cities.id
    total_distance DECIMAL(6,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (city_start) REFERENCES cities(id),
    FOREIGN KEY (city_end) REFERENCES cities(id)
);

