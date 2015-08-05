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

package aion.main.core.time;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fanaen
 */
public class ChronoMaster extends Thread {
    
    // -- Singleton --
    static private ChronoMaster chronoMaster;
    static public void enableChronoMaster() {
        chronoMaster = new ChronoMaster();
        chronoMaster.start();
    }
    
    static public ChronoMaster getChronoMaster() {
        if(chronoMaster == null) enableChronoMaster();
        return chronoMaster;
    }
    
    // -- Attributes --
    private List<Chrono> chronoList;
    private List<Chrono> chronoRemoveList;
    private long timeFromAppStart;
    private boolean running;
    
    // -- Constructors --
    public ChronoMaster () {
        timeFromAppStart = System.currentTimeMillis();
        running = true;
        chronoList = new LinkedList<>();
        chronoRemoveList = new LinkedList<>();
    }
    
    // -- Methods --
    @Override
    public void run() {
        long lastCycleTime = System.currentTimeMillis();
        long currentCycleTime = lastCycleTime;
        
        while(running) {
            currentCycleTime = System.currentTimeMillis();
            long timeDifference = currentCycleTime - lastCycleTime;
            
            // -- Update every chrono --
            for (Chrono chrono : chronoList) {
                long surplusTimeBuffer = chrono.addTime(timeDifference);
                
                if(surplusTimeBuffer > 0) {
                    System.out.println("Chrono fini !");
                    Sequence sequence = chrono.getListener().onSequenceFinished(chrono.getSequence());
                    if(sequence != null) addChrono(chrono.getListener(), sequence);
                    chronoRemoveList.add(chrono);
                }
            }
            
            // -- Safe-removing of old chronos -- 
            for (Chrono chrono : chronoRemoveList) {
                chronoList.remove(chrono);
                chronoRemoveList.remove(chrono);
            }
            
            lastCycleTime = currentCycleTime;
            
            try { 
                sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger("MyLog").log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public Chrono addChrono(Chrono.Listener listener, Sequence sequence) {
        Chrono chrono = new Chrono();
        chrono.setBeginTime(System.currentTimeMillis());
        chrono.setListener(listener);
        chrono.setSequence(sequence);
        chronoList.add(chrono);
        return chrono;
    }

    public void removeChrono(Sequence sequence) {
        for (Chrono chrono : chronoList) {
            if(chrono.getSequence() == sequence) {
                chronoRemoveList.add(chrono);
                return;
            }
        }
    }
}
