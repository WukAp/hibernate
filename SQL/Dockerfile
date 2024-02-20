FROM postgres:latest

ENV POSTGRES_DB hotel_db
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD 12345678

COPY init.sql /docker-entrypoint-initdb.d/

EXPOSE 5432