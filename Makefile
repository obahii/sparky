all:	up

up:
		@docker compose -f srcs/docker-compose.yml up -d

down:
		@docker compose -f srcs/docker-compose.yml down

ps:
		@docker compose -f srcs/docker-compose.yml ps

fclean:	down
		@docker image rm  $$(docker image ls -aq)
		@docker volume rm $$(docker volume ls -q)
		docker system prune -a --force

re:
		@docker compose -f srcs/docker-compose.yml build
		docker compose -f srcs/docker-compose.yml up

.PHONY:	all up down ps fclean re