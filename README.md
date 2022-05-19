Mendel Code Challege
---
Diseñar un servicio web RESTful que almacene transacciones (en memoria) y
devuelva información sobre esas transacciones.

### Pre-requisitos
* Maven 3
* Java 11
* Docker

## Ejecución  💻

Ejecutar

    mvn spring-boot:run

## Despliegue 📦
1) posicionarse en el root del proyecto, donde se encuentra el archivo _Dockerfile_
2) construir la imagen:
   
        sudo docker build -t mendel-app:1.0 .

3) correr el contenedor

        docker run -d -p 8080:8080 -t mendel-app:1.0

Nota: Es necesario compilar la app con maven _mvn clean install_, antes de poder generar la imagen. De lo contrario el archivo ejecutable no estará disponible.

## Doc 📖️
- [API REST Contrato](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#)


## Test ✏️
Para el desarrollo y la ejecución de los tests, se hace uso de la herramienta Spock.
En este [repo](https://github.com/ewatemberg/acceptance-test-spock) existe información sobre una capacitación acerca de su uso y sus virtudes en la legibilidad de los test y entendimiento.

Para correr los test

    mvn test

_En la carpeta **src/main/test/resources/atdd** se encuentran los test de Aceptación._


## Herramientas 🔧
* [Spring boot](https://spring.io/projects/spring-boot) - Framework de java
* [Spock](http://spockframework.org/) - Tests

## Notas 📋
(1) _Se recomienda [IntelliJ Community](https://www.jetbrains.com/idea/download/) o [Eclipse IDE for Enterprise Java Developers](https://www.eclipse.org/downloads/packages/)_

##### Instalar Lombok en el IDE

_Esto depende del IDE(1) que utilices, seguir indicaciones de la web de [Project Lombok](https://projectlombok.org/)_
