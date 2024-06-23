# Usar una imagen base de Java
FROM openjdk:23-slim

RUN apt-get update && apt-get install -y findutils

# Crear un directorio de trabajo
WORKDIR /app

COPY libs/mysql-connector-j-8.4.0.jar /app/mysql-connector-j-8.4.0.jar

# Copiar el script de compilación y los archivos fuente
COPY src /app/src

# Compilar la aplicación
RUN mkdir -p out
RUN javac -d out $(find src -name "*.java")


# Mantener el contenedor en ejecución
CMD ["tail", "-f", "/dev/null"]
