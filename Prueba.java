import javax.sound.midi.MidiUnavailableException;

public class Prueba extends SetUpInterface{
    
    public static void main(String[] args) throws MidiUnavailableException, InterruptedException {
        Prueba gui = new Prueba();
        gui.go();
    }
    
    public void go() throws MidiUnavailableException, InterruptedException {        
        
        setUpInterface();
        
    }

}
