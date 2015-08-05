package aion.main.core;

import aion.main.core.environment.EnvironmentSchema;
import aion.main.core.environment.Position;
import aion.main.core.result.RealtimeWriter;
import aion.main.core.result.SynthesisWriter;
import aion.main.core.subject.Subject;
import aion.main.core.subject.SubjectSchema;
import aion.main.core.time.Chrono;
import aion.main.core.time.ChronoMaster;
import aion.main.core.time.Sequence;
import aion.main.core.time.TimeSchema;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 *
 * @author Fanaen
 */
public class Experience implements Serializable, Chrono.Listener{
    
    // -- Attributes --
    private TimeSchema timeSchema;
    private SubjectSchema subjectSchema;
    private EnvironmentSchema environmentSchema;
    private boolean chronoEnabled;
    
    private String name;
    private String dataset;
    private String version = "Original";
    
    private boolean started;
    
    private transient Subject currentSubject = null;
    private transient Sequence currentSequence = null;
    private transient Chrono currentChrono = null;
    private transient Position currentPosition = null;
    private transient Timer timer = null;
    
    private transient boolean sequenceDone = false;
    private transient boolean sequenceInProgress = false;
    
    private List<ExperienceListener> listenerList;

    // -- Constructor --
    public Experience()
    {
        listenerList = new LinkedList<>();
        started = false;
    }
    
    public void prepareExperience(boolean realtime) throws EndExperienceException
    {
        addListener(new SynthesisWriter(environmentSchema, this));
        if(realtime) addListener(new RealtimeWriter(this));
        subjectSchema.reset();
        timeSchema.reset();
        nextStep();
    }
    
    // -- Management methods --
    public void nextAction() throws EndExperienceException 
    {
        if(!sequenceInProgress)
        {
            if(!sequenceDone) 
            {
                runSequence();
            }
            else
            {
                nextStep();
                sequenceDone = false;
            }
        }
        
    }
    
    public void skipSequence() throws EndExperienceException // EXPERIMENTAL METHOD --
    {
        runSequenceSimulation();
        waitEndSequenceSimulation(currentSequence.getNbSecond() * 1000);
        nextStep();
    }
    
    public void nextStep() throws EndExperienceException
    {
        if(!started)
        {
            nextSubject();
            nextSequence(true);
            started = true;
        }
        else
        {
            nextSequence(true);
        }
    }
    
    protected void nextSubject() throws EndExperienceException
    {
        // -- Get the next subject --
        Subject subject = subjectSchema.getNextItem();
        
        if(subject == null)
        {
            for (ExperienceListener listener : listenerList) { listener.onExperienceFinished(); }
            throw new EndExperienceException();
        }
        else
        {
            currentSubject = subject;
            for (ExperienceListener listener : listenerList) { listener.onSubjectStarted(); }
        }
    }
    
    protected void nextSequence(boolean finishedSequence) throws EndExperienceException
    {
        // -- Finish previous sequence --
        if(started && finishedSequence)
        {
            for (ExperienceListener listener : listenerList) { listener.onSequenceFinished(); }
            cleanSequence();
        }
        
        // -- Get the next one --
        Sequence sequence = timeSchema.getNextItem();
            
        if(sequence == null) // -- End of this subject --
        {
            for (ExperienceListener listener : listenerList) { listener.onSubjectFinished(); }
            
            // Start the new one --
            nextSubject();
            timeSchema.reset();
            nextSequence(false);
        }
        else
        {
            currentSequence = sequence;
            for (ExperienceListener listener : listenerList) { listener.onSequenceStarted(); }
        }
    }

    public void moveToPosition(Position position) {
        
        long time = System.currentTimeMillis();
        
        if(sequenceInProgress) 
        {
            position.incrementCount();
            currentPosition.addTime(currentChrono.getElapsedTime(time));
            long cursor = currentChrono.getElapsedTimeSinceBeginning(time);
            for (ExperienceListener listener : listenerList) { listener.onMove(cursor, position); }
        }
        currentPosition = position;
    }
    
    // -- Simulation --
    public void runSequenceSimulation()
    {
        sequenceInProgress = true;
        
        currentChrono = new Chrono();
        currentChrono.setSequence(currentSequence);
        currentChrono.setBeginTime(0);
        
        // -- Listeners --
        for (ExperienceListener listener : listenerList) { listener.onSequenceChronoStarted(); }
    }
    
    public void moveToPositionSimulation(Position position, long elapsedTime) throws EndExperienceException
    {
        if(sequenceInProgress)
        {
            // Transfer time in subsequence if needed --
            long surplusTime = currentChrono.addTime(elapsedTime);
            while(surplusTime > 0) {
                skipSequenceSimulation(elapsedTime - surplusTime);
                elapsedTime = surplusTime;
                surplusTime = currentChrono.addTime(elapsedTime);
            }
            
            // Apply last part --
            currentPosition.addTime(elapsedTime);
            
            position.incrementCount();
        }
        
        currentPosition = position;
    }

    private void skipSequenceSimulation( long elapsedTime) throws EndExperienceException {
        currentPosition.addTime(elapsedTime);
        nextStep();
        runSequenceSimulation();
    }
    
    public void waitEndSequenceSimulation(long remainingTime) throws EndExperienceException
    {
        // Transfert in subsequence if needed --
        long sequenceRemainingTime = currentChrono.getRemainingTimeChrono();
        long skippedTime = 0 ;
        
        while(sequenceRemainingTime + skippedTime < remainingTime)
        {
            skipSequenceSimulation(sequenceRemainingTime);
            skippedTime += sequenceRemainingTime;
            sequenceRemainingTime = currentChrono.getRemainingTimeChrono();
        }
        currentPosition.addTime(sequenceRemainingTime);
        sequenceInProgress = false;
    }
    
    // -- Chrono methods --
    public void runSequence() {
        sequenceInProgress = true;
        
        // -- Accurate Chrono --
        ChronoMaster master = ChronoMaster.getChronoMaster();
        currentChrono = master.addChrono(this, currentSequence);
        
        // -- Estimated Chrono --
        timer = new Timer();
        timer.scheduleAtFixedRate(new EstimatedTimer(), (long) 1000, (long) 1000);
        
        // -- Listeners --
        for (ExperienceListener listener : listenerList) { listener.onSequenceChronoStarted(); }
    }

    @Override
    public Sequence onSequenceFinished(Sequence sequence) {
        timer.cancel();
        // -- Complete the precise count --
        currentPosition.addTime(currentChrono.getRemainingTime());
        
        for (ExperienceListener listener : listenerList) { listener.onSequenceChronoFinished(); }
        
        sequenceInProgress = false;
        sequenceDone = true;
        return null;
    }

    private void cleanSequence() {
        currentSequence.resetEstimatedTime();
        environmentSchema.cleanResultData();
    }

    public List<Position> getPositions() {
        List<Position> positions = new ArrayList<>();
        environmentSchema.reset();
        Position position = environmentSchema.getNextItem();
        
        while(position != null)
        {
            positions.add(position);
            position = environmentSchema.getNextItem();
        }
        environmentSchema.reset();
        
        return positions;
    }
    
    public List<Subject> getSubjects() {
        List<Subject> subjects = new ArrayList<>();
        environmentSchema.reset();
        Subject subject = subjectSchema.getNextItem();
        
        while(subject != null)
        {
            subjects.add(subject);
            subject = subjectSchema.getNextItem();
        }
        subjectSchema.reset();
        return subjects;
    }
    
    public List<Sequence> getSequences() {
        List<Sequence> sequences = new ArrayList<>();
        environmentSchema.reset();
        Sequence sequence = timeSchema.getNextItem();
        
        while(sequence != null)
        {
            sequences.add(sequence);
            sequence = timeSchema.getNextItem();
        }
        environmentSchema.reset();
        
        return sequences;
    }
    
    private class EstimatedTimer extends TimerTask
    {        
        @Override
        public void run() {
            estimatedUpdate();
        }
    }
    
    public void estimatedUpdate() 
    {
        currentPosition.incrementEstimatedTime();
        currentSequence.incrementEstimatedTime();
        
        for (ExperienceListener listener : listenerList) { listener.onEstimatedUpdate(currentSequence, currentPosition); }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Listener and Getters/Setters">
    // -- Listener methods --
    public void addListener(ExperienceListener listener)
    {
        listenerList.add(listener);
    }
    
    // -- Getters and setters --
    public boolean isChronoEnabled() {
        return chronoEnabled;
    }

    public void setChronoEnabled(boolean chronoEnabled) {
        this.chronoEnabled = chronoEnabled;
    }   
    
    public TimeSchema getTimeSchema()
    {
        return timeSchema;
    }

    public void setTimeSchema(TimeSchema timeSchema)
    {
        this.timeSchema = timeSchema;
    }

    public SubjectSchema getSubjectSchema()
    {
        return subjectSchema;
    }

    public void setSubjectSchema(SubjectSchema subjectSchema)
    {
        this.subjectSchema = subjectSchema;
    }

    public EnvironmentSchema getEnvironmentSchema()
    {
        return environmentSchema;
    }

    public void setEnvironmentSchema(EnvironmentSchema environmentSchema)
    {
        this.environmentSchema = environmentSchema;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataset() {
        return dataset;
    }
    
    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Subject getCurrentSubject() {
        return currentSubject;
    }

    public Sequence getCurrentSequence() {
        return currentSequence;
    }

    public Chrono getCurrentChrono() {
        return currentChrono;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }
    // </editor-fold>
    
    // -- Listener interface --
    public interface ExperienceListener 
    {
        public void onSubjectStarted();
        public void onSubjectFinished();
        public void onSequenceStarted();
        public void onSequenceFinished();
        public void onSequenceChronoStarted();
        public void onEstimatedUpdate(Sequence sequence, Position position);
        public void onSequenceChronoFinished();
        public void onMove(long time, Position position);

        public void onExperienceFinished();
    }
}
