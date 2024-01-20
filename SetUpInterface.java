import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SetUpInterface extends JPanel{
    
    public ArrayList<JToggleButton> checkboxList;

    public void setUpInterface() { //crea una ventana que contendrá una lista de botones a la derecha con sus respectivos Listeners y la partitura dinámica
        JFrame frame = new JFrame("Partitura de un piano");         
        BorderLayout layout = new BorderLayout(); 
        JPanel background = new JPanel(layout);  
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        GridLayout grid = new GridLayout(11, 26);
        grid.setVgap(0);
        grid.setHgap(0);         
        JPanel pane = new JPanel(grid);                                 
        BuildTrack setUp = new BuildTrack(pane, checkboxList);
        setUp.start();
        checkboxList = setUp.getCheckboxList(); 
        
        Box buttonBox = new Box(BoxLayout.Y_AXIS);
        JButton serializeIt = new JButton("Serialize It"); //Botón para serializar junto con su ActionListener
        serializeIt.setBackground(new Color(100,100,100));
        serializeIt.setForeground(Color.WHITE);
        serializeIt.addActionListener(e -> writeFile());
        buttonBox.add(serializeIt);
        
        JButton restore = new JButton("Restore"); //Botón para restaurar el patrón de partitura previamente serializado
        restore.setBackground(new Color(100,100,100));
        restore.setForeground(Color.WHITE);
        restore.addActionListener(e -> readFile());
        buttonBox.add(restore);

        JButton play = new JButton("Play it!"); //Botón que desplega un interfaz nuevo dando alusión que se está presionando las teclas del piano.
        play.setBackground(new Color(100,100,100));
        play.setForeground(Color.WHITE);
        SetUp playSetUp = new SetUp(checkboxList);
        play.addActionListener(e -> {
            try {
                playSetUp.playNotes(checkboxList);
            } catch (InterruptedException | MidiUnavailableException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        buttonBox.add(play);
        
        JPanel panelLabel = new JPanel();
        panelLabel.setLayout(new GridLayout(11, 1));

        JLabel fa2 = new JLabel("fa2");
        fa2.setForeground(Color.GRAY);
        panelLabel.add(fa2);
        JLabel mi2 = new JLabel("mi2");
        mi2.setForeground(Color.GRAY);
        panelLabel.add(mi2);
        JLabel re2 = new JLabel("re2");
        re2.setForeground(Color.GRAY);
        panelLabel.add(re2);
        JLabel do2 = new JLabel("do2");
        do2.setForeground(Color.GRAY);
        panelLabel.add(do2);
        JLabel si = new JLabel("si");
        si.setForeground(Color.GRAY);
        panelLabel.add(si);
        JLabel la = new JLabel("la");
        la.setForeground(Color.GRAY);
        panelLabel.add(la);
        JLabel sol = new JLabel("sol");
        sol.setForeground(Color.GRAY);
        panelLabel.add(sol);
        JLabel fa = new JLabel("fa");
        fa.setForeground(Color.GRAY);
        panelLabel.add(fa);
        JLabel mi = new JLabel("mi");
        mi.setForeground(Color.GRAY);
        panelLabel.add(mi);
        JLabel re = new JLabel("re");
        re.setForeground(Color.GRAY);
        panelLabel.add(re);
        JLabel do1 = new JLabel("do");
        do1.setForeground(Color.GRAY);
        panelLabel.add(do1);

        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, panelLabel);
        frame.getContentPane().add(background);  
                     
        background.add(BorderLayout.CENTER, setUp.getPane());
                                  
        frame.pack();
        frame.setBounds(250, 300, 650, 270);                                       
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void writeFile(){
        boolean[] checkboxState1 = new boolean[287]; //arreglo booleano de las notas musicales

        for(int i = 0; i < 286; i++){
            JToggleButton check = checkboxList.get(i);
            if(check.isSelected()){
                checkboxState1[i] = true;
            }
        }

        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(new File("Checkbox.ser")))){
            os.writeObject(checkboxState1); //guardamos objeto que apunta a un arreglo de los estados de la lista de la partitura
        }catch (IOException e){
            e.printStackTrace();
        }  
    }

    public void readFile(){
        boolean[] checkboxState1 = null;
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(new File("Checkbox.ser")))){
            checkboxState1 = (boolean[]) is.readObject(); //regresa un objeto de tipo booleano que era el arreglo de los estados de la lista de la partitura después de hacer serialización
        }catch (Exception e){
            e.printStackTrace();
        }
        
        Boolean bandera = true;
        int j=0;
        ImageIcon i1 = new ImageIcon("imagenes\\raya_sinNota.png");
        for(int i = 0; i < 286; i++){ //regresa, dependiendo el estado después de la serealización, a la normalidad
            JToggleButton check = checkboxList.get(i);
            check.setSelected(checkboxState1[i]); 
            if(check.isSelected()){
                if(bandera){ //cada 25 notas se cambia de fila en una nueva imagen, una vacía
                    i1 = new ImageIcon("imagenes\\punto_sinNota.png");
                    check.setIcon(i1);
                }else{
                    i1 = new ImageIcon("imagenes\\punto_sinRaya.png");
                    check.setIcon(i1);
                }            
            }else{
                if(bandera){ //cada 25 notas se cambia de fila en una nueva imagen, una vacía
                    i1 = new ImageIcon("imagenes\\raya_sinNota.png");
                    check.setIcon(i1);
                }else{
                    i1 = new ImageIcon("imagenes\\vacio_sinRaya.png");
                    check.setIcon(i1);
                }
            }
            j++;
                
            if(i<250){
                if(j>25 && j<52){ 
                    bandera = false;    
                }
                if(j>=52){
                    j=0;
                    bandera = true;  
                }  
            }else{
                bandera=false;
            }
        }
    }
    
    public ArrayList<JToggleButton> getCheckboxList(){
        return checkboxList;
    }
}


