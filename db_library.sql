-- db_library

CREATE TABLE tb_author(
    id 		    UUID 		   NOT NULL PRIMARY KEY,
    name 		VARCHAR(255)   NOT NULL,
    birthday 	DATE 		   NOT NULL,
    nationality VARCHAR(255)   NOT NULL
)

CREATE TABLE tb_book(
    id				 UUID		  NOT NULL PRIMARY KEY,
    isbn			 VARCHAR(255) NOT NULL,
    title 			 VARCHAR(255) NOT NULL,
    publication_date DATE		  NOT NULL,
    gender 			 VARCHAR(255) NOT NULL,
    price 			 NUMERIC(18,2),
    id_author        UUID 		  NOT NULL REFERENCES tb_author(id),

    CONSTRAINT chk_gender CHECK (gender IN ('FICCAO','SUSPENSE','AVENTURA','COMEDIA','ROMANCE'))
)
