sudo -u postgres psql "create user test; ALTER USER test WITH ENCRYPTED PASSWORD 'test'; create database webletter owner test;"
