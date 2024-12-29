#!/bin/bash

service mariadb start

while ! mysqladmin ping -hlocalhost --silent; do
    sleep 1
done

# mysql -u root < /tmp/create_db.sql
echo "SOURCE /tmp/database.sql;" | mariadb
echo "CREATE USER '$DB_USER'@'%' IDENTIFIED BY '$DB_USER_PASSWD';" | mariadb
echo "GRANT ALL PRIVILEGES ON *.* TO '$DB_USER'@'%';" | mariadb
echo "FLUSH PRIVILEGES;" | mariadb
echo "ALTER USER 'root'@'localhost' IDENTIFIED BY '$DB_ROOT_PASSWD';" | mariadb

mysqladmin -u root -p"$DB_ROOT_PASSWD" shutdown

service mariadb start