FROM debian:bullseye-slim

RUN apt update \
	&& apt upgrade -y \
	&& apt-get install -y  mariadb-server

COPY tools/script.sh /tmp/

COPY database/database.sql /tmp/

RUN chmod +x /tmp/script.sh

ENTRYPOINT /tmp/script.sh

