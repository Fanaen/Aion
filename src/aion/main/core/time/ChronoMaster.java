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
