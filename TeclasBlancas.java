import javax.swing.*;
import java.util.ArrayList;
import javax.sound.midi.*;

public class TeclasBlancas extends PlayNotes implements ControllerEventListener{ //clase manipulada por cada evento que suceda en la clase SetUp con respecto a su sequencia
    private boolean msg = false;
    
    public TeclasBlancas(ArrayList<JToggleButton> checkboxList) {
        super(checkboxList);
    }

    public void controlChange(ShortMessage event){ //recibe el "pulso" de que hubo un nuevo evento en la clase que heredó ControllerEventListener
        msg = true;
        buildTeclasBlancas();
    }

    public void buildTeclasBlancas() {
        if (msg) { 
            setUpGui();//se abrirá una nueva ventana para desplegar las teclas que son presionadas de acuerdo a las columnas de la partitura
            lol++; //variable que se incrementa cada vez que lee una columna
            msg = false;
        }
    }
}
