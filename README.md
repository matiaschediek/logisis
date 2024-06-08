# Logisis

Sistema de logística para la empresa de transporte de carga.
El proyecto incluye las sentencias SQL para la configuración de la base de datos y las clases Java correspondientes al desarrollo del prototipo, según entregas 2 y 3.

## Contenido

1. En la carpeta *sql-scripts* se encuentran las sentencias:
	- 01-create-db.sql: sentencia correspondiente a la creación de la base de datos.
	- 02-create-tables.sql: sentencias correspondientes a la creación de las tablas.
	- 03-insert-data.sql: sentencias correspondientes a la inserción de datos de prueba en cada tabla.
	- 04-select.sql: sentencia correspondiente a la consulta de los datos de prueba.
	- 05-cleanup.sql: sentencias correspondientes a la eliminación de los datos de prueba.
	- 06-all-clean-check.sql: sentencia correspondiente para la verificación de los datos eliminados.

2. En la carpeta *src se encuentran las clases Java.

## Requisitos

- [Docker](https://www.docker.com/)

## Instalación

1. Clonar el repositorio
2. Ejecutar `docker-compose up -d`

## Uso

- Ejectuar `docker-compose exec app java -cp /app/out com.mchediek.logisis.Main`