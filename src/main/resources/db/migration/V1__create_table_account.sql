
CREATE TABLE account (
    name varchar(255) NOT NULL,
    credentials varchar(255)
);

ALTER TABLE account ADD CONSTRAINT account_pkey PRIMARY KEY (name);
