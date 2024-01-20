import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.*;
import java.util.ArrayList;
import static javax.sound.midi.ShortMessage.*;

public class SetUp extends SetUpInterface{
    private TeclasBlancas ventana;
    private Sequencer sequencer;
    private Sequence sequence;
    private Track track;
    public int[][] trackList;
    public ArrayList<JToggleButton> checkboxList;
    
    public void playNotes(ArrayList<JToggleButton> checkboxList) throws InterruptedException, MidiUnavailableException{ 
        setUpMidi(); //empieza a definir los conceptos que se usarán a lo largo del código con respecto a su sequencia
        buildTrackList(checkboxList); //empieza rellenando a una matriz todos los unos y ceros lógicos para facilitar su procesamiento
    }

    private void buildTrackList(ArrayList<JToggleButton> checkboxList) throws InterruptedException, MidiUnavailableException{
        trackList = new int[11][26]; // Recibe una lista de botone JToggleButtons para después pasarlo a una matriz que lo simplifica
        track = sequence.createTrack(); 
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 26; j++){
                if(checkboxList.get((26*i)+j).isSelected()){
                    trackList[i][j] = 1;
                    
                }else{
                    trackList[i][j] = 0;
                }
            }
        }
        makeTracks(trackList); //una vez finalizada el llenado, mandarselo a que haga los tracks para después leerlos
    }
   
    private void makeTracks(int[][] list) throws MidiUnavailableException, InterruptedException{ 
        int j=0, x=0, i=0; //recibe una matriz de unos y ceros para saber cuáles son las notas a tocas con su respectiva frecuencia
        sequencer.setTempoInBPM(30); 
        for(i=0;i<26;i++){
            x=60;
            for( j = 0; j <11; j++){ 
                if(list[j][i] == 1){
                    track.add(makeEvent(NOTE_ON, 1, x, 100, i));
                    track.add(makeEvent(NOTE_OFF, 1, x, 100, i+2));   
                } 
                x = x-3;
            }
            track.add(makeEvent(CONTROL_CHANGE, 1, 127, 0, i)); //Con esta herramienta podemos controlar cualquier clase que implemente ControllerEventListener            
        }
        try{
            sequencer.setSequence(sequence);
            sequencer.setTempoInBPM(30);
            sequencer.start(); //empieza la sequencia
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static MidiEvent makeEvent(int command, int channel, int one, int two, int tick) { //crea el evento dado un canal y su tick
        MidiEvent event = null;
        try {
             
            ShortMessage msg = new ShortMessage();
            msg.setMessage(command, channel, one, two);
            event = new MidiEvent(msg, tick);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

    public SetUp(ArrayList<JToggleButton> checkboxList){
        this.checkboxList = checkboxList;
    }

    private void setUpMidi(){
        try{
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            ventana =  new TeclasBlancas(checkboxList);
            sequencer.addControllerEventListener(ventana, new int[] {127});
            sequence = new Sequence(Sequence.PPQ, 2);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(30); //mantiene un tempo de 30 bpm para la facilitación del estudiante a aprender
        }catch (Exception e){
            e.printStackTrace();
        }
    }

            
}


