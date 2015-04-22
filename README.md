# TP-JPA-2

TP JPA+SERVLET M1 MIAGE 
===================

Groupe : Baptiste Quéré & Yoann Le Taillanter.

Ce TP comprend : 
> - La partie Servlet + JPA
> - La partie Web Service (Jersey) + JPA, qui sert de backend pour le [TP AngularJS](https://github.com/yletaillanter/TP-JPA-2).

TP testé avec Eclipse Java EE et une base de données MySQL.

**Configuration**
-------------------------

> - Cloner le projet depuis github.
> - Modifier le fichier **persistence.xml** afin d'adapter les informations à votre base de données MySQL.


**Lancement Servlet** 
-------------------------

> - Run **JpaTest.java** pour alimenter la base de données.
> - Lancer le serveur Tomcat 7 avec maven : ``` mvn tomcat7:run```
> - Rendez vous sur ```http://localhost:8080/ ```
> - Les servlets sont dans **src.main.java.servlet**
> - Les pages web dans **src/main/webapp**
> - L'application permet de visualiser et de créer des Personnes et des Maisons pour ces personnes. 


**API REST Jersey** 
-----------

La commande ``` mvn tomcat7:run``` démarre également le web service de JERSEY, la source se situe dans **src.main.java.fr.istic.sir.rest**

A utiliser avec le frontend [AngularJS](https://github.com/yletaillanter/TP-JPA-2)..

Le path de base pour Jersey est **/rest/hello**.

| Method     | URL | Action   |
| :------- | ----: | :---: |
| GET    | /persons  |  retourn la liste complete des personnes   |
| GET     | /person/{id}   |  retourne la personne ayant l'id {id}  |
| GET     | /HomeByOwner/{id}   |  retourne la maison appartenant à la personne ayant l'id {id}  |
| GET    | /homes  |  retourne la liste complete des maisons   |
| POST    | /newPerson  |  Ajoute une personne dans la base de données |
