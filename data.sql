CREATE TABLE IF NOT EXISTS person (
    id INTEGER PRIMARY KEY,
    name INTEGER,
    phone VARCHAR,
    email VARCHAR,
    gender BOOLEAN,
    birthday DATE
);

INSERT INTO person (name, phone, email, gender, birthday) VALUES
("Hans", "08123456789", "hans@gmail.com", 0, "1955-01-23"),
("Ruth", "08123456789", "ruth@gmail.com", 1, "1967-12-21"),
("Heinz", "08123456789", "heinz@gmail.com", 0, "1978-10-10"),
("Cornelia", "08123456789", "cornelia@gmail.com", 1, "1925-12-12"),
("Werner", "08123456789", "werner@gmail.com", 0, "1983-07-22"),
("Lydia", "08123456789", "lydia@gmail.com", 1, "1991-04-02"),
("Anna", "08123456789", "anna@gmail.com", 1, "1945-01-23"),
("Stefan", "08123456789", "stefan@gmail.com", 0, "1935-03-28"),
("Martin", "08123456789", "martin@gmail.com", 0, "1998-09-19");
