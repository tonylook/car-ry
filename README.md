# Car-Ry

These APIs allows users to place new car adverts, view, modify and delete existing car adverts

  - Spring Boot
  - MySQL
  - JPA - Hibernate
  - Junit For Backend Test
  - Postman for API Test and Documentation

## API Documentation
At this Link you can find Postman API Documentation: https://documenter.getpostman.com/view/6363373/S1LtzonH

## Installation and Test
Export these 3 env with connection parameters to your MySQL, let's assume localhost and advantio DB

```sh
$ export DATASOURCE=jdbc:mysql://127.0.0.1:3306/advantio
$ export USERNAME=root
$ export PASSWORD=advantio
$ export HBDDL=update
```

Now clone this repository in your folder and 
``cd`` 
into it and Build It (Junit Tests are automated)

```sh
$ cd car-ry
$ mvn install
$ mvn spring-boot:run
```

The API now are served, read Postman API Documentation to use it.
### If you want to run multiple times Junit
Before every test you have to drop from the "advantio" schema the following tables: "adverts" and "hibernate_sequence".
## For production environments...

The HBDDL env parameter is the configuration for hibernate.ddl-auto, if you want to delegate the creation of the production schema to Hibernate set this parameter to ``update``
only at the first boot (in order to create the schema), next reboot the application with the value on ``validate``

```sh
$ export HBDDL=validate
```