# CRUD REST API

API REST para Crud de Clientes

## Documentación de la API

Puedes encontrar la documentación de la API en el siguiente enlace: [Documentación de la API](https://documenter.getpostman.com/view/28586175/2sA2xiYCvT)

## Levantar un servidor local

1. **Clonar el repositorio**

   Abre una terminal y ejecuta el siguiente comando:

   ```bash
   git clone https://github.com/Ernestolop/rest_spring_crud.git

2. **Modifica el aplication.properties**
    Crea una base de datos local y establece las credenciales en el aplication.properties
    > **Observaciones**: Ten en cuenta que el archivo `application.properties` publicado en este repositorio contiene solo datos de configuración locales.

3. **Corre la aplicacion en modo de desarrollo**
   ```bash
   mvn spring-boot:run

## Generar build de producción y correr el servidor

1. **Genera el jar**
    En la raiz del proyecto, ejecuta el siguiente comando:
    ```bash
    mvn package

2. **Iniciar la el servidor**
    En el directorio target, verifica el nombre del jar generado, reemplazalo en el siguiente comando y ejecutalo
    ```bash
    java -jar target/name-of-the-project.jar