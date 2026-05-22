
CREATE TABLE IF NOT EXISTS author (
    id serial NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL,
    cpf varchar(11) NOT NULL,
    date_of_birth date NULL
);

CREATE TABLE IF NOT EXISTS book (
    id serial NOT NULL PRIMARY KEY,
    title varchar(255) NOT NULL,
    description varchar(511) NULL
);

CREATE TABLE IF NOT EXISTS author_book (
    author_id integer NOT NULL REFERENCES author,
    book_id integer NOT NULL REFERENCES book,
    CONSTRAINT AuthorBook_PK PRIMARY KEY (Author_id, Book_id)
);