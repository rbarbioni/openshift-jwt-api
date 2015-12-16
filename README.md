Openshift Java Store-JWT-Api REST Application
=============

This is a starter template for developing Spring framework applications on OpenShift. It uses the latest Spring framework release i.e. 4.2.2.RELEASE.
The project configures Spring application using WebApplicationInitializer. There is no web.xml in this project and everything is configured using annotations.

Simple JSON Web Token (JWT) implementation

Features:
-------

* JJWT Implementation;
* Spring 4.2.2;
* Spring Data JPA
* No Spring XML configuration;
* Persistence JPA 2;
* Hibernate 5;
* Jackson REST;
* 100% Annotations configurations;
* HSQLDB;
* Initializing data SQL script;
* Logback

Instructions
-------

* Create account https://www.openshift.com/app/account/new
* Install rhc client tools https://developers.openshift.com/en/managing-client-tools.html
* rhc create-app storejwt tomcat-7 --from-code https://github.com/rbarbioni/openshift-jwt-api.git
* Import your favorite IDE compatible MAVEN projects.
* Change code, commit and push

JWT
-------

Simple Authentication [GET,POST] http://localhost:8080/api/auth?username=master&password=master
token eyJhbGciOiJub25lIn0.eyJzdWIiOiJtYXN0ZXIifQ.
Add token request parameter or header x-key
http://localhost:8080/api/product?key=eyJhbGciOiJub25lIn0.eyJzdWIiOiJtYXN0ZXIifQ.


