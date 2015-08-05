/*
 *  Aiôn, an enhanced multiple stopwatch designed for behavioral studies.
 *  Copyright (C) 2015  Elouan Poupard-Cosquer
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package aion.main.core.result;

import aion.main.core.Experience;
import aion.main.core.environment.AbstractPosition;
import aion.main.core.environment.EnvironmentSchema;
import aion.main.core.environment.Position;
import aion.main.core.time.Sequence;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fanaen
 */
public class SynthesisWriter implements Experience.ExperienceListener {
    
    // -- Attributes --
    private List<AbstractPosition> positionList = null;
    private EnvironmentSchema environmentSchema = null;
    private Experience experience = null;
    
    private FileWriter writer = null;
    
    // -- Constructor --
    public SynthesisWriter(EnvironmentSchema schema, Experience exp)
    {
        environmentSchema = schema;
        experience = exp;
        positionList = new ArrayList<>();
        
        initialisePositionList();
        initialiseFile();
    }

    private void initialisePositionList() {
        environmentSchema.reset();
        AbstractPosition position = environmentSchema.getNextAbstractPosition();
        
        while(position != null)
        {
            positionList.add(position);
            
            // -- Next Item --
            position = environmentSchema.getNextAbstractPosition();
        }
    }

    private void initialiseFile() {
        try { 
            String path = "Data/" + 
                    experience.getName() + "/" + 
                    experience.getDataset();
            File expDirectory = new File(path);
        
            // -- Check if the directory exists, and create it if not --
            if(!expDirectory.exists()) expDirectory.mkdir();
            
            // -- Open the file --
            writer = new FileWriter(path + "/" + experience.getVersion() + ".csv",false);
        
            // -- Insert the first line --
            writer.write("Sujet;");
            writer.write("Sequence;");
            for (Iterator<AbstractPosition> it = positionList.iterator(); it.hasNext();) {
                AbstractPosition abstractPosition = it.next();
                writer.write("Tps - " + abstractPosition.getName() + ";");
                writer.write("Pas - " + abstractPosition.getName() + ";");
            }
            writer.write("\n");
            writer.flush();
        }
        catch (Exception e)
        {
            Logger.getLogger("MyLog").log(Level.SEVERE, null, e);
        }
    }

    // -- Ignored events --
    public void onSubjectStarted() { } 
    public void onSequenceStarted() { } 
    public void onSubjectFinished() { }
    public void onSequenceChronoStarted() { }
    public void onEstimatedUpdate(Sequence sequence, Position position) { }   
    public void onSequenceChronoFinished() { }        
    public void onMove(long time, Position position) { }
    public void onExperienceFinished() { }
            
    public void onSequenceFinished() 
    {
        if(writer == null) return;
        
        try {
            writer.write(experience.getCurrentSubject().getName()+";");
            writer.write(experience.getCurrentSequence().getName()+";");
            DecimalFormat df = new DecimalFormat("0.00##");
            
            for (Iterator<AbstractPosition> it = positionList.iterator(); it.hasNext();) {
                AbstractPosition abstractPosition = it.next();
                double value = (double) abstractPosition.getTime() / 1000.0;
                System.out.println(value);
                writer.write(df.format(value)+ ";");
                
                writer.write(abstractPosition.getCount() + ";");
            }
            writer.write("\n");
            writer.flush();   
        }
        catch (Exception e) {
            Logger.getLogger("MyLog").log(Level.SEVERE, null, e);
        }
    }
}
