# README #

## Préambule ##

Le projet LawnMower, contenu dans ce *repository*, constitue ma réponse au test technique que je me suis vu proposé.

La version actuelle est la `1.0`.

---

## Démarrage rapide ##
Deux possibilités s'offrent à vous:

1. Utiliser le JAR *standalone* proposé;
2. cloner ce *repository*, construire l'application et la lancer.

### Solution n°1: JAR *standalone* ###
#### Pré-requis ####
Vous devez disposer d'un JRE en version 8 ou supérieure.
#### Etape n°1: télécharger l'application ####
Cf Piece jointe du mail
#### Etape n°2: lancer l'application ####
Placez-vous dans le dossier où se trouve le JAR téléchargé (`lawnmower-core-1.0-jar-with-dependencies.jar`) que vous pouvez lancer avec la commande suivante:

	java -jar lawnmower-core-1.0-jar-with-dependencies.jar <chemin vers le fichier de specification des mower>


### Solution n°2: construction de l'application ###
#### Pré-requis ####
Vous devez disposer:

* d'un JDK en version 8 ou supérieure;
* de Maven en version 3 ou supérieure;
* de Git.

#### Etape n°1: cloner l'application ####
Clonez l'application à l'aide de la commande suivante:

	https://github.com/msellekAhmed/Lawn_Mower.git

#### Etape n°2: construire l'application ####
Entrez dans le répertoire de l'application:

	cd lawnmower/

Puis, lancez la construction de l'application via:

	mvn clean install

#### Etape n°3: lancer l'application ####
Une fois la construction terminée, rendez-vous dans le dossier `target` du module `lawn-mower-core`:

	cd lawnmower-core/target/

Vous pouvez y trouver le JAR `lawn-mower-cli-1.0-SNAPSHOT-jar-with-dependencies.jar`que vous pouvez lancer avec la commande suivante:

	java -jar lawnmower-core-1.0-jar-with-dependencies.jar <chemin vers le fichier de specification des mower>

---
## Guide d'utilisation ##
L'usage de l'application est le suivant:

	Usage: lawn-mower-cli <file>

  		<file>
        	source file path

Un seul argument est attendu en entrée; ce dernier doit être un fichier existant. Cet usage vous est affiché pour rappel en cas d'erreur. Le libellé `lawn-mower-core`est bien évidemment à remplacer par le nom du JAR.
### Fichiers de test
Quelques exemples vous sont fournis pour vous familiariser avec l'application:

Fichier | Cas d'usage
--------|------------
[singleMower](https://github.com/msellekAhmed/Lawn_Mower/blob/master/lawnmower-core/src/main/resources/singleMower)|Une tondeuse seule.
[singleMowerAtLawnBounds](https://github.com/msellekAhmed/Lawn_Mower/blob/master/lawnmower-core/src/main/resources/singleMowerAtLawnBounds)|Une tondeuse seule qui tente de franchir les limites de la pelouse.
[multipleMowersWithoutCrash](https://github.com/msellekAhmed/Lawn_Mower/blob/master/lawnmower-core/src/main/resources/multipleMowersWithoutCrash)|Plusieurs tondeuses qui n'entrent pas en collision.

---
## Notes techniques ##
### Technologies utilisées ###

* Maven;
* Scala;
* [Scopt](https://github.com/scopt/scopt) pour la CLI.

### Architecture ###
L'application est découpée en modules selon la correspondance suivante:

Module            | Description
------------------|------
lawnmower-core    | contient l'interface de ligne de commande et la main class permettant de faire usage de l'application.
lawnmower-model   | contient le model et la logique "pure" de l'application; fonctionnement des pelouses et des tondeuses.
lawnmower-utils   | contient la logique de *parsing* des fichiers sources.


Il est important de noter que les modules `lawnmower-model`et `lawnmower-utils` n'ont pas de dépendance entre eux. C'est le module `lawnmower-core`qui fait le lien.
