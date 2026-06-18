# mars-rover-kata
Implémentation Java du kata "Mars Rover" : un ensemble de rovers explore
un plateau rectangulaire sur Mars en suivant des instructions de déplacement et de
rotation (`L`, `R`, `M`).

## Build

```bash
mvn clean package
```

## Exécution

```bash
java -jar target/rover.jar input.txt
```

### Format du fichier d'entrée

5 5 -> Position upper right du plateau
1 2 N -> Position initiale du rover
LMLMLMLMM -> mouvements consécutifs du rover
3 3 E -> position initiale du second rover
MMRMMRMRRM -> mouvements consécutifs du 2 ème rover

### Sortie attendue
1 3 N
5 1 E

## Gestion des cas limites

Le kata original ne précise pas le comportement attendu si un rover démarre hors
du plateau, ou si une commande `M` le ferait sortir des limites. Pour rester
robuste, le programme ne lève pas d'exception et n'interrompt pas le traitement
dans ces cas : il continue d'exécuter le rover concerné, ignore simplement le
déplacement invalide, et associe un avertissement consultable via `MissionControl.getWarnings(rover)`.
Dans le cas d'un faux mouvement, une exception `Illegal Argument Exception est levée`