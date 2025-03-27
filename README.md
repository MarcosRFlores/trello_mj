# Gestor de Tareas (Java JSF con PostgreSQL)

Este proyecto es un gestor de tareas sencillo que maneja tres tablas en una base de datos PostgreSQL. Está construido con Maven, utiliza JavaServer Faces (JSF) para la interfaz de usuario y Docker para levantar un contenedor con la base de datos.

---

## Estructura del Proyecto

El proyecto consta de las siguientes tablas:

1. **Usuario**: Almacena información de los usuarios.
2. **Estado**: Almacena los estados posibles de las tareas (ej. "Pendiente", "En progreso", "Completada").
3. **Tarea**: Almacena las tareas creadas por los usuarios, con relaciones a las tablas `Usuario` y `Estado`.

Cada tabla está representada por una clase en los paquetes correspondientes:

- **Usuario**: `com.crud.trello_mj.usuario.Usuario`
- **Estado**: `com.crud.trello_mj.estado.Estado`
- **Tarea**: `com.crud.trello_mj.tarea.Tarea`

---

## Requisitos Previos

Antes de comenzar, asegúrate de tener instaladas las siguientes herramientas:

- **[Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html)**: Versión 1.8 o superior.
- **[Maven](https://maven.apache.org/download.cgi)**: Versión 3.6 o superior.
- **[Docker](https://www.docker.com/get-started)**: Para levantar la base de datos PostgreSQL.
- **[Docker Compose](https://docs.docker.com/compose/install/)**: Opcional, pero recomendado para gestionar el contenedor de la base de datos.

---

## Configuración de la Base de Datos

El proyecto utiliza una base de datos PostgreSQL. Para levantar la base de datos en un contenedor Docker, sigue estos pasos:

### 1. Archivo `docker-compose.yaml`

Crea o utiliza el siguiente archivo `docker-compose.yaml` para configurar el contenedor de PostgreSQL:

```yaml
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
```

### 2. Levantar el contenedor de la base de datos
Ejecuta el siguiente comando en la raíz del proyecto donde se encuentra el archivo docker-compose.yml:
```bash
docker-compose up -d
```
Esto levantará un contenedor de PostgreSQL con la base de datos trello_mj_db y las credenciales especificadas

## 3. Configurar la conexión a la base de datos
Asegúrate de que el archivo persistence.xml (o la configuración equivalente) tenga la configuración correcta para conectarse a la base de datos:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence" version="2.0">
    <persistence-unit name="jsf-crud-unit" transaction-type="JTA">
        <jta-data-source>java:/PostgresDS</jta-data-source>
        <class>com.crud.trello_mj.estado.Estado</class>
        <class>com.crud.trello_mj.usuario.Usuario</class>
        <class>com.crud.trello_mj.tarea.Tarea</class>

        <properties>
            <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect"/>
            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.format_sql" value="true"/>
        </properties>
    </persistence-unit>
</persistence>
```

## Compilar el proyecto

```bash
mvn clean install
```

# Ejecutar el Proyecto

Una vez que el proyecto se ha compilado correctamente, puedes desplegarlo en un servidor de aplicaciones como **Apache Tomcat** o **WildFly**.

# Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal.
- **JavaServer Faces (JSF)**: Framework para la interfaz de usuario web.
- **Maven**: Herramienta para la gestión y construcción del proyecto.
- **PostgreSQL**: Sistema de gestión de bases de datos relacionales.
- **Docker**: Plataforma para levantar contenedores de aplicaciones y servicios.

