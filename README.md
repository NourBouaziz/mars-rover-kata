
Readme · MD
# Mars Rover Kata

Implémentation Java du kata classique "Mars Rover" : un ensemble de rovers explore
un plateau rectangulaire sur Mars en suivant des instructions de déplacement et de
rotation (`L`, `R`, `M`).

## Prérequis

- Java 21
- Maven 3.9+ (ou utiliser le wrapper `mvnw` / `mvnw.cmd` fourni)
## Build

```bash
mvn clean package
```

Génère `target/rover.jar`.

## Exécution

```bash
java -jar target/rover.jar input.txt
```

### Format du fichier d'entrée

```
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
```

- La première ligne donne les coordonnées du coin supérieur droit du plateau
  (le coin inférieur gauche est toujours `0 0`).
- Chaque rover est décrit par deux lignes : sa position de départ
  (`x y direction`, ex. `1 2 N`), puis la séquence de commandes à exécuter.
### Sortie attendue

```
1 3 N
5 1 E
```

## Gestion des cas limites

Le kata original ne précise pas le comportement attendu si un rover démarre hors
du plateau, ou si une commande `M` le ferait sortir des limites.

Pour rester cohérent et explicite, le programme lève une exception métier dédiée,
`RoverOutsidePlateauException`, dans les deux cas :

- **Position de départ invalide** : `MissionControl.addRover` vérifie la position
  initiale du rover avant tout traitement et lève l'exception immédiatement si
  elle est hors des limites du plateau.
- **Déplacement invalide** : `MissionControl.attemptMove` calcule la position
  suivante du rover avant de l'appliquer ; si elle sort du plateau, le mouvement
  est rejeté et l'exception est levée.
  Ce choix permet de signaler clairement et au plus tôt une entrée invalide plutôt
  que de laisser le programme continuer silencieusement avec un état incohérent.

Au niveau de l'orchestration, `MissionRunner` intercepte cette exception pour
chaque rover individuellement : le rover en erreur est ignoré (il n'apparaît pas
dans la sortie), un avertissement est loggé via `MissionControl.getWarnings(rover)`,
et le traitement continue normalement avec le rover suivant — une erreur sur un
rover n'interrompt jamais le traitement des autres.