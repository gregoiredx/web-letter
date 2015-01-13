
CREATE TABLE letter (
    id varchar(255) NOT NULL primary key,
    senderName varchar(255),
    senderAddress varchar(255),
    receiverName varchar(255),
    receiverAddress varchar(255),
    date date,
    salutation varchar(255),
    closing varchar(255),
    body varchar(255),
    account_name varchar(255)references account(name)
);
