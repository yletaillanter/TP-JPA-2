# TP-JPA-2

TP JPA+SERVLET M1 MIAGE 
===================

Groupe : Baptiste Quéré & Yoann Le Taillanter.

Ce TP comprend : 
> La partie Servlet + JPA
> La partie Web Service (Jersey) + JPA, qui sert de backend pour le [TP AngularJS](https://github.com/yletaillanter/TP-JPA-2).

TP testé avec Eclipse Java EE et une base de données MySQL.

 <i class="icon-cog"></i>**Configuration**
-------------------------

> - Cloner le projet depuis github.
> - Modifier le fichier **persistence.xml** (**src/main/java/resources/META-INF/**) afin d'adapter les informations à votre base de données MySQL.


<i class="icon-refresh">**Lancement Servlet** 
-------------------------

> - Run **JpaTest.java** pour alimenter la base (**src/main/java/fr/istic/tpjpa/jpa/**) .
> - ``` mvn tomcat7:run```
> - Puis rendez vous sur ```http://localhost:8080/ ```
> - Les servlets sont dans **src/main/java/servlet**
> - Les pages web dans **src/main/webapp**

**API REST Jersey** 
-----------

La commande ``` mvn tomcat7:run``` démarre également le web service de JERSEY, la source se situe dans **src/main/java/fr/istic/sir/rest**


Le path de base pour Jersey est **/rest/hello**

| Method     | URL | Action   |
| :------- | ----: | :---: |
| GET    | /persons  |  retourn la liste complete des personnes   |
| GET     | /person/{id}   |  retourne la personne ayant l'id {id}  |
| GET     | /HomeByOwner/{id}   |  retourne la maison appartenant à la personne ayant l'id {id}  |
| GET    | /homes  |  retourne la liste complete des maisons   |
| POST    | /newPerson  |  Ajoute une personne dans la base de données |
