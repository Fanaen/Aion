/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aion.main.presentation.generic.experience;

import aion.main.core.EndExperienceException;
import aion.main.core.Experience;
import aion.main.core.environment.Position;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextArea;

/**
 *
 * @author Fanaen
 */
public class SimulationCore {
    
    // -- Attributes --
    private Experience experience;
    private BufferedReader reader;
    private JTextArea output;
    private String text;
    private int lineNumber = 0;
    private boolean end = false;
    private boolean firstSequence = true;
    
    private long sequenceLenght;
    private long lastTime = 0;
    
    private List<Position> positions;
    
    // -- Constructor --
    public SimulationCore(Experience experience)
    {
        this.experience = experience;
    }
    
    public void simulation() throws EndExperienceException
    {
        println("Début de la simulation --");
        
        // -- Initialisation --
        println("Préparation de l'experience");
        experience.prepareExperience(false);
        positions = experience.getPositions();
        
        // -- Run the simulation --
        if(!initialiseFile()) return;
        readFile();
    }
    
    // -- Read Attributes and methods --
    private void readLine(String line) throws EndExperienceException {
        if(end) return; // End-File Lock --
        
        Pattern p;
        Matcher m;
        int i, j, k;
        
        // Ignore human-oriented lines --
        //if(line.trim().isEmpty()) return;
        //if(line.matches("^Experience:") || line.matches("^Version:") || line.matches("^Dataset:")) return;
        
        if(line.matches("^BeginSequence.*"))
        {
            m = Pattern.compile("^BeginSequence \\[(\\d+)\\].").matcher(line);
            
            if(m.find()) {
                sequenceLenght = Long.parseLong(m.group(1));
                println("Nouvelle séquence de "+ sequenceLenght +" secondes");
            }
            else
            {
                println("Erreur : instruction de séquence incorrecte");
            }
        }
        else if(line.matches("^InitialPosition:.*"))
        {
            
            m = Pattern.compile("^InitialPosition: (\\d+).").matcher(line);
            
            if(m.find()) {
                k = Integer.parseInt(m.group(1));
                println("Position initiale en "+ k);
                
                if(!firstSequence) 
                {
                    experience.waitEndSequenceSimulation(sequenceLenght * 1000 - lastTime); // Remaining time --
                    experience.nextStep();
                }
                firstSequence = false;
                    
                experience.moveToPositionSimulation(positions.get(k-1), 0);
                experience.runSequenceSimulation();
                lastTime = 0;
            }
            else {
                println("Erreur : position initiale manquante");
            }
            
        }
        else if(line.matches("^\\[\\d+\\] MoveTo:.*"))
        {
            
            m = Pattern.compile("^\\[(\\d+)\\] MoveTo: (\\d+).").matcher(line);
            
            if(m.find()) {
                i = Integer.parseInt(m.group(1));
                j = Integer.parseInt(m.group(2));
                println("Déplacement en " + j + " à "+ i);
                experience.moveToPositionSimulation(positions.get(j-1), i - lastTime);
                lastTime = i;
            }
            else {
                println("Erreur : ordre de déplacement incorrect");
            }
            
        }
        else if(line.matches("^EndExperience$"))
        {
            experience.waitEndSequenceSimulation(sequenceLenght * 1000 - lastTime); // Remaining time --
            experience.nextStep();
            println("Instruction de fin reçue");
            println("Fin de la simulation --");
            end = true;
        }
    }
    
    private void readFile() throws EndExperienceException {
        try {
            lineNumber = 1;
            String line = null;
            while ((line = reader.readLine()) != null) {
                readLine(line);
                lineNumber++;
            }
        } catch (IOException ex) 
        {
            Logger.getLogger("MyLog").log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean initialiseFile() {
        try {
            
            String path = "Data/" + 
                    experience.getName() + "/" + 
                    experience.getDataset();
            
            // -- Open the file --
            FileReader fileReader = new FileReader(path + "/Original.aion");
            reader = new BufferedReader(fileReader);
        }
        catch (Exception ex)
        {
            Logger.getLogger("MyLog").log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public void setOutput(JTextArea output) {
        this.output = output;
    }
    
    private void println(String text)
    {
        output.append("[" + String.format("%03d", lineNumber) + "] " + text + "\n");
    }
    
    
}
