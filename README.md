Conception et implémentation d'une application Windows de consultation de résultats d’un examen national. L’utilisateur pourra consulter son résultat à partir de son matricule seulement. 
Après la recherche par matricule, l’application affiche le résultat de l’examen (Succès ou Échec) et un bouton "Afficher les détails".
Ce bouton affiche un écran qui montre la moyenne obtenue sur 20 et les informations sur l’étudiant (Matricule, Nom, Prénom, Date de naissance, Ecole). 
Stocker les informations de l’examen dans une base de données MySQL. L’application sera développée sous Eclipse avec JavaFx et JDBC.

Fonctions et resultats fourni :

Une base de données MySQL simple.
Une base de données  créée en ligne pour le stockage des données des étudiants.
Cette base de données a été utilisée pour l’application,elle contient une table "etudiant" constituée de huit colonnes
suivantes:
-IDetudiant
-Nometudiant
-Prenometudiant
-DateNaisetudiant
-Ecoleetudiant
-Moyetudiant
-Decisionetudiant
-Matriculeetudiant


Il y a :
*Un lien Github du projet Eclipse.
*Des commits sur le projet à chaque implémentation d’une fonctionnalité.
*Une gestion des exceptions.
*Les bonnes pratiques pour nommer les classes, les variables, les fonctions etc. en camelCase.
*Quelques commentaires pour aider à comprendre le code.
*Cinq tests unitaires avec Junit et cinq test unitaires avec AssertJ pour des verifictions constantes du comportement de l'application,
l'amélioration du code métier et la détection automatique des éventuelles erreurs.
