package aion.main.core.time;

/**
 *
 * @author Fanaen
 */
public class Chrono {
    
    // -- Attributes --
    private Sequence sequence;
    private Chrono.Listener listener;
    private long timeBegin = 0;
    private long timeChrono = 0;
    private long timeSequence = 0;
    private long cursor = 0;
    
    // -- Getters and setters --
    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
    
    public Sequence getSequence() {
        return sequence;
    }

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
        timeSequence = sequence.getNbSecond() * 1000;
    }

    // -- Methods --
    public long getElapsedTime(long time) {
        long elapsedTime = time - cursor;
        cursor = time;
        return elapsedTime;
    }

    public long getElapsedTimeSinceBeginning(long time) {
        long elapsedTime = time - timeBegin;
        return elapsedTime;
    }
    
    public long addTime(long timeDifference) {
        timeChrono += timeDifference;
        return (timeChrono > timeSequence) ? timeChrono - timeSequence : 0;
    }

    public void setBeginTime(long currentTimeMillis) {
        timeBegin = currentTimeMillis;
        cursor = timeBegin;
    }

    public long getRemainingTime() {
        return timeSequence - (cursor - timeBegin);
    }

    public long getRemainingTimeChrono() {
        return timeSequence - timeChrono;
    }
    
    public interface Listener {
        public Sequence onSequenceFinished(Sequence sequence);
    }
}
