CREATE TABLE expenseRequests (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  userId INTEGER,
  amount INTEGER,
  createdAt TIMESTAMP
);

CREATE TABLE recipes (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  description VARCHAR(255),
  expenseRequestItemId INTEGER
);

CREATE TABLE expenseRequestItems (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  expenseRequestId INTEGER,
  categoryId INTEGER,
  amount INTEGER,
  description VARCHAR(255)
)