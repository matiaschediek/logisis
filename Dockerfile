# Usar una imagen base de Java
FROM openjdk:23-slim

RUN apt-get update && apt-get install -y findutils

# Crear un directorio de trabajo
WORKDIR /app

# Copiar el script de compilación y los archivos fuente
COPY src /app/src

# Compilar la aplicación
RUN mkdir -p out
RUN javac -d out $(find src -name "*.java")


# Mantener el contenedor en ejecución
CMD ["tail", "-f", "/dev/null"]
