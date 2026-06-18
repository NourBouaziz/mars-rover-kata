package com.kata.rover;

import com.kata.rover.domain.Rover;
import com.kata.rover.domain.RoverOutsidePlateauException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsable de l'orchestration : parcourt les lignes du fichier
 * (apres le plateau), construit chaque rover, execute ses commandes,
 * et logue le resultat ou les avertissements.
 */
public class MissionRunner {

    private static final Logger LOGGER = Logger.getLogger(MissionRunner.class.getName());

    private final InputReader inputReader;
    private final MissionControl missionControl;

    public MissionRunner(InputReader inputReader, MissionControl missionControl) {
        this.inputReader = inputReader;
        this.missionControl = missionControl;
    }

    public void run(List<String> lines) {
        for (int i = 1; i + 1 < lines.size(); i += 2) {
            Rover rover = inputReader.parseRover(lines.get(i));
            char[] commands = lines.get(i + 1).trim().toCharArray();
            try {
                processRover(rover, commands);
            } catch (RoverOutsidePlateauException re) {
                LOGGER.log(Level.WARNING, missionControl.getWarnings(rover));
                continue;
            }
            System.out.println(rover);
        }
    }

    private void processRover(Rover rover, char[] commands) {
        missionControl.addRover(rover);
        missionControl.executeCommands(rover, commands);
    }
}