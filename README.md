Gestor de Tareas con 3 Tablas (Java JSF)

Este proyecto es un sistema de gestión de tareas basado en un CRUD (Create, Read, Update, Delete) utilizando JavaServer Faces (JSF) para la interfaz de usuario y PostgreSQL como base de datos. El sistema permite gestionar tareas con estados y asignarlas a usuarios.

Estructura del Proyecto

El proyecto consta de las siguientes tablas:

Tarea: Almacena información sobre las tareas; su: titulo, descripción, clave foránea al usuario creador de la tarea, y una clave foránea a su estado.

Usuario: Almacena información de los usuarios como su: Nombre, Correo y contraseña.

Estado: Define los diferentes estados que pueden tener las tareas: Pendiente, En curso y Finalizado.

Requisitos Previos

Antes de comenzar, asegúrate de tener instaladas las siguientes herramientas:

Java JDK: Versión 1.8 o superior.

Maven: Versión 3.6 o superior.

Docker: Para levantar la base de datos PostgreSQL.

Docker Compose: Opcional, pero recomendado para gestionar el contenedor de la base de datos.

Configuración de la Base de Datos

El proyecto utiliza PostgreSQL. Para levantar la base de datos en un contenedor Docker, sigue estos pasos:

1. Crear el archivo docker-compose.yml

El archivo docker-compose.yml define la configuración del contenedor de PostgreSQL:

version: '3.8'

services:
  postgres:
    image: postgres:13
    container_name: trello_mj_postgres
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
      POSTGRES_DB: trello_mj_db
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:

2. Levantar el contenedor de la base de datos

Ejecuta el siguiente comando en la raíz del proyecto donde se encuentra el archivo docker-compose.yml:

docker-compose up -d

Esto levantará un contenedor de PostgreSQL con la base de datos trello_mj_db y las credenciales especificadas.

Configurar la conexión a la base de datos

Asegúrate de que el archivo persistence.xml tenga la configuración correcta para conectarse a la base de datos:

<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence" version="2.0">
    <persistence-unit name="jsf-crud-unit" transaction-type="JTA">
        <jta-data-source>java:/PostgresDS</jta-data-source>
        <class>com.crud.trello_mj.estado.Estado</class>
        <class>com.crud.trello_mj.usuario.Usuario</class>

        <properties>
            <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect"/>
            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.format_sql" value="true"/>
        </properties>
    </persistence-unit>
</persistence>

Compilar el proyecto

mvn clean install

Ejecutar el Proyecto

Una vez que el proyecto se ha compilado correctamente, puedes desplegarlo en un servidor de aplicaciones como Apache Tomcat o WildFly. Sigue estos pasos:

1. Desplegar en un servidor de aplicaciones

Copia el archivo WAR generado (gestor_tareas-1.0-SNAPSHOT.war) en la carpeta webapps de tu servidor de aplicaciones (por ejemplo, Apache Tomcat).

Inicia el servidor de aplicaciones.

2. Acceder a la aplicación

Una vez desplegado, la aplicación estará disponible en la URL del servidor. Por ejemplo, si estás utilizando Tomcat en localhost:8080, la aplicación estará en:

Acceder a la aplicación

Tecnologías Utilizadas

Java: Lenguaje de programación principal.

JavaServer Faces (JSF): Framework para la interfaz de usuario web.

Maven: Herramienta para la gestión y construcción del proyecto.

PostgreSQL: Sistema de gestión de bases de datos relacionales.

Docker: Plataforma para levantar contenedores de aplicaciones y servicios.

Este README proporciona una guía clara para instalar y ejecutar el gestor de tareas. Si necesitas más detalles o modificaciones, avísame. 🚀

