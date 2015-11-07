CREATE TABLE approvments (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  userId INTEGER,
  expenseRequestId INTEGER,
  createdAt TIMESTAMP
);
