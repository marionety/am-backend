# Ambulances Manager Backend

Servicio realizada con el framework Spring Boot, que ofrece una API REST para la administración de ambulancias y grupos.

## Detalle
Se han utilizado las siguientes librerías y herramientas:
* Spring Boot como framework de desarrollo.
* Lombok y JavaX para validación y reducir boilerplate code.
* Mockito y JUnit para testing.
* Jackson para mapeo de objetos.
* JPA/Hibernate como ORM.
* H2 como base de datos en memoria.
* Swagger para documentación de la API.

## Pasos para ejecutar el servicio
### Ejecución del servicio en entorno sin docker.
#### Ejecución del servicio
* Ejecutar el siguiente comando para realizar la construcción del servicio
> ./gradlew build

* Para ejecutar los tests del servicio.
> ./gradlew test

* Para ejecutar directamente el servicio
> ./gradlew bootRun

Por defecto, el servicio se levanta en el puerto _8085_, definido en el fichero _application.properties_.

#### Acceso a la documentación de la API en swagger.
Para acceder a la documentación de swagger, navegar a [http://localhost:8085/swagger-ui/index.html](http://localhost:8085/swagger-ui/index.html), 
copiando _http://localhost:8085/v3/api-docs_ en el cuadro de búsqueda superior y haciendo click en el botón _Explore_.

Para acceder a la consola de H2 (facilitada para testing), acceder a [http://localhost:8085/h2](http://localhost:8085/h2), usando las credenciales 
facilitadas en el fichero _application.properties_.

### Ejecución del servicio como contenedor de docker.
El servicio está preparado para correr en un contenedor docker.

Por sencillez y testing, se facilita un fichero _application.properties_ donde se encuentra el puerto expuesto, por defecto _8085_ y diversas opciones
de configuración del servicio.

Para crear la imagen, ejecutar la siguiente instrucción en la raíz del proyecto:
> docker build -t mrc/am-backend . 

El servicio está expuesto por defecto en el puerto _8085_, y lanzando la siguiente instrucción:
> docker run -p 8085:8085 mrc/am-backend

Ya tendríamos nuestro contenedor ejecutándose, para confirmarlo podemos ejecutar en otro terminal:
> docker ps
