# TP-JPA-2

Bienvenue sur le TP JPA
===================

Baptiste Quéré & Yoann Le Taillanter.

TP testé avec Eclipse Java EE avec une base de données MySQL.

 <i class="icon-cog"></i>**Configuration**
-------------------------

> - Cloner le projet depuis github.
> - Modifier le fichier **persistence.xml** (src/main/java/resources/META-INF/) afin d'adapter les informations à votre base de données MySQL.


<i class="icon-refresh">**Lancement** 
-------------------------

> - Run **JpaTest.java** pour alimenter la base.
> - ``` mvn tomcat7:run```
> 

**API** 
-----------
Le path best /rest/hello

| Method     | URL | Action   |
| :------- | ----: | :---: |
| GET    | /persons  |  retourn la liste complete des personnes   |
| GET     | /person/{id}   |  retourne la personne ayant l'id {id}  |
| GET     | /HomeByOwner/{id}   |  retourne la maison appartenant à la personne ayant l'id {id}  |
| GET    | /homes  |  retourne la liste complete des maisons   |
| POST    | /newPerson  |  Ajoute une personne dans la base de données |
