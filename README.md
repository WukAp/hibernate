запустить бд:
cd SQL&&docker build -t postgres-docker . && docker run --rm -d -p 5432:5432 --name postgres-container postgres-docker

остановить бд:
docker stop postgres-container

