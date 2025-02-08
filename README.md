# Aplicación de Horóscopo Chino

## Descripción

Esta es una aplicación web desarrollada en Java que permite a los usuarios registrarse, iniciar sesión y consultar su horóscopo chino basado en su fecha de nacimiento. La aplicación utiliza JSP para la interfaz de usuario y JDBC para la conexión a la base de datos.

## Características

- Registro de usuarios
- Inicio de sesión de usuarios
- Consulta de horóscopo chino por fecha de nacimiento
- Actualización y eliminación de usuarios

## Tecnologías Utilizadas

- Java
- JSP
- JDBC
- Maven
- MySQL

## Requisitos

- JDK 11 o superior
- Apache Tomcat 9 o superior
- MySQL 5.7 o superior
- Maven 3.6 o superior

## Instalación

1. Clona el repositorio:
    ```sh
    git clone https://github.com/cheruless/horoscopo-chino.git
    ```

2. Importa el proyecto en tu IDE (IntelliJ IDEA recomendado).

3. Configura la base de datos MySQL:
    ```sql
    CREATE DATABASE horoscopo_chino;
    USE horoscopo_chino;
    CREATE TABLE usuarios (
        id INT AUTO_INCREMENT PRIMARY KEY,
        nombre VARCHAR(50),
        username VARCHAR(50),
        email VARCHAR(50),
        fecha_nacimiento DATE,
        password VARCHAR(50),
        animal VARCHAR(50)
    );
    ```

4. Configura el archivo `src/main/resources/db.properties` con tus credenciales de MySQL:
    ```properties
    db.url=jdbc:mysql://localhost:3306/horoscopo_chino
    db.username=tu-usuario
    db.password=tu-contraseña
    ```

5. Compila y empaqueta la aplicación con Maven:
    ```sh
    mvn clean package
    ```

6. Despliega el archivo WAR generado en Apache Tomcat.

## Uso

1. Accede a la aplicación en tu navegador web:
    ```
    http://localhost:8080/horoscopo-chino
    ```

2. Regístrate como nuevo usuario o inicia sesión con tus credenciales.

3. Consulta tu horóscopo chino ingresando tu fecha de nacimiento.
