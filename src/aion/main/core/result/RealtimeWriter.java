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
import aion.main.core.environment.Position;
import aion.main.core.subject.Subject;
import aion.main.core.time.Sequence;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fanaen
 */
public class RealtimeWriter implements Experience.ExperienceListener {
    // -- Attributes --
    private Experience experience = null;
    private FileWriter writer = null;
    
    // -- Constructor --
    public RealtimeWriter(Experience experience) {
        this.experience = experience;
        initialiseFile();
    }

    private void initialiseFile() {
        try { 
            
            // -- Open the file --
            writer = new FileWriter(
                    "Data/" + 
                    experience.getName() + "/" + 
                    experience.getDataset() + "/" + 
                    experience.getVersion() + ".aion",
                    false);
        
        
            // -- Insert first lines --
            writer.write("Experience: "+ experience.getName() +"\n");
            writer.write("Version: "+ experience.getVersion() +"\n");
            writer.write("Dataset: "+ experience.getDataset() +"\n");
            writer.write("\n");
            writer.flush();
        }
        catch (Exception ex)
        {
            Logger.getLogger("MyLog").log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onSubjectStarted() {
        if(writer == null) return;
        try {
            Subject item = experience.getCurrentSubject();
            writer.write("BeginSubject: "+ item.getNumber() + ". "+ item.getName() +"\n");
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger("MyLog").log(Level.SEVERE, null, ex);
        }
    }
    
    public void onSubjectFinished() {
        if(writer == null) return;
        try {
            writer.write("EndSubject\n\n");
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger("MyLog").log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onSequenceStarted() {
        
    }

    @Override
    public void onSequenceFinished() {
        if(writer == null) return;
        try {
            writer.write("EndSequence\n\n");
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger("MyLog").log(Level.SEVERE, null, ex);
        }
    }

    public void onSequenceChronoStarted() {
        if(writer == null) return;
        try {
            Sequence item = experience.getCurrentSequence();
            writer.write("BeginSequence ["+ item.getNbSecond() +"] "+ item.getNumber() + ". "+ item.getName() +"\n");
            
            Position position = experience.getCurrentPosition();
            writer.write("InitialPosition: "+ position.getNumber() + ". "+ position.getName() +" \n");
            writer.flush();
        } catch (IOException ex) {    
            Logger.getLogger("MyLog").log(Level.SEVERE, null, ex);
        }
    }
    public void onEstimatedUpdate(Sequence sequence, Position position) { }
    public void onSequenceChronoFinished() { }

    @Override
    public void onMove(long time, Position position) {
        if(writer == null) return;
        try {
            Position item = position;
            writer.write("["+ time +"] MoveTo: "+ item.getNumber() + ". "+ item.getName() +"\n");
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger("MyLog").log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onExperienceFinished() {
        try {
            writer.write("EndExperience\n");
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger("MyLog").log(Level.SEVERE, null, ex);
        }
    }
    
}
