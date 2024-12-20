all:	up

run:
		@docker run -it -v $(PWD)/sparky_project:/app/sparky_project -p 4040:4040 sparky:0.1 bash

build:
		@docker build -t sparky:0.1 .
up:
		@docker compose -f docker-compose.yml up -d

down:
		@docker compose -f docker-compose.yml down

ps:
		@docker compose -f docker-compose.yml ps

fclean:	down
		@docker image rm  $$(docker image ls -aq)
		@docker volume rm $$(docker volume ls -q)
		docker system prune -a --force

re:
		@docker compose -f docker-compose.yml build
		docker compose -f docker-compose.yml up

.PHONY:	all up down ps fclean re