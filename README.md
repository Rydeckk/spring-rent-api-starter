# Exercice 8 :

## - Réponse 1 : 
Le paramètre cascade dans l’annotation @ManyToOne permet de propager certaines opérations (comme persist, merge, remove, etc.) de l’entité principale vers l’entité associée.

Exemple concret :
Pour RentalProperty, si la table a un champ propertyType annoté avec @ManyToOne(cascade = CascadeType.REMOVE). Si on supprime un PropertyType, alors tous les RentalProperty associés seront aussi supprimés automatiquement (effet en cascade).
Inversement, si on utilise cascade = CascadeType.PERSIST, alors lorsqu’on enregistre un nouveau RentalProperty, si le PropertyType n’est pas encore dans la base, il sera automatiquement persisté avec.


## - Réponse 2 : 

La fonctionnalité de Spring Boot qui permet cela s’appelle l’auto-configuration .

Grâce à l’auto-configuration, Spring Boot analyse automatiquement les dépendances présentes dans le classpath (comme spring-boot-starter-data-jpa) et configure les beans nécessaires à leur fonctionnement, sans que le développeur ait à les définir manuellement.

Concrètement :
	•	Lorsqu’on ajoute la dépendance spring-boot-starter-data-jpa et qu’on configure les propriétés comme spring.datasource.url, spring.datasource.username, spring.datasource.password, etc., Spring Boot :
	•	Crée automatiquement un DataSource (connexion à la base de données),
	•	Configure un EntityManagerFactory (pour JPA)

Cela permet de se concentrer sur la logique métier sans avoir à écrire tout le code de configuration habituellement nécessaire en Java EE (comme la création manuelle d’un EntityManagerFactory ou d’une source de données).