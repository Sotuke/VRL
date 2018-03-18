DROP TABLE IF EXISTS tweets;
CREATE TABLE tweets(
  id serial PRIMARY KEY,
  text VARCHAR(255),
  dates TIMESTAMP);