sudo -u postgres psql -c "create user test;"
sudo -u postgres psql -c "ALTER USER test WITH ENCRYPTED PASSWORD 'test';"
sudo -u postgres psql -c "create database webletter owner test;"
