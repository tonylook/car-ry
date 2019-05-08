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
in src/main/resources edit DB URI, Username and Password. 
On application-create and application-prod set the Production credentials and in application-dev set the Development credentials.

When you are in development set your environment variable to dev

```sh
$ export spring_profiles_active=dev
```
When you're in production
!PAY ATTENTION!
Database and System where the application runs MUST have the same Timezone, let's assume ``Etc/GMT-2``

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
Before every test you have to drop from the "advantio" schema the following tables: "adverts" and "hibernate_sequence", or you have to set spring profile on create (it will recreate the entire schema from scratch at every boot).
```sh
$ export spring_profiles_active=create
```
## For production environments...

The configuration for hibernate.ddl-auto is for when you want to delegate the creation of the production schema to Hibernate; in this case set this parameter to ``update``
only at the first boot (in order to create the schema), next reboot the application with the value on ``validate``, so if you want to create the schema with Hibernate at the first boot set the profile on create


Next this boot, stop the application and run the application with the prod profile.

```sh
$ export spring_profiles_active=prod
```
