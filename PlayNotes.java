import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JToggleButton;

public class PlayNotes extends SetUp{

    public TeclaTeclado tecla;
    public ArrayList<JToggleButton> checkboxListTeclasBlancas;
    public ArrayList<JToggleButton> checkboxListTeclasNegras;
    static int lol = 0;
    public int[][] trackList;
    public ArrayList<JPanel> listaDePaneles;
    public JFrame frame;
    public JPanel panel1;
    
    public void setUpGui() { //Llena una ventana emergente con botones JToogleButton de teclas blancas funcionales y teclas negras no funcionales
        frame = new JFrame("Simulación de piano");// Dentro de la ventana se encuentran los botones negros sin función y encima un panel que están puestos las teclsa blancas funcionales 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); //Para poder poner los botones en la posición que yo quiera

        checkboxListTeclasNegras = new ArrayList<JToggleButton>();
        for(int i=0;i<10;i++){
            tecla = new TeclaTeclado();
            if(i==2 || i == 6 || i == 9){ //acomoda de manera estética, es decir, un paquete de dos y luego un paquete de 3
                checkboxListTeclasNegras.add(tecla.getTeclaNegra(i*128 +68,15));
            }else{
                checkboxListTeclasNegras.add(tecla.getTeclaNegra(i*65 +68,15));
            }
            frame.add(checkboxListTeclasNegras.get(i));
        }

        for(int i=0; i<26;i++){ //copie y pegue del método setUpListaDePaneles
            int[] posiciones = new int[11];
            posiciones = getArrayEstadoTeclasBlancas(i);
            panel1 = getGuiPanelTeclasBlancas(posiciones);
            if(lol == i){
                frame.add(panel1);
            }
        }
        frame.setSize(770,300);
        frame.setVisible(true);          
    }

    public PlayNotes(ArrayList<JToggleButton> checkboxList) {
        super(checkboxList);
        lol=0; //inicializa la variable estática en cero para poder empezar a contar de nuevo las columnas desde un inicio/
    }

    public JPanel getGuiPanelTeclasBlancas(int[] pos){ //regresa un panel que contiene las teclas en forma de JToggleButton inicializado en falso
        panel1 = new JPanel();
        panel1.setBackground(new Color(0,0,0,0));
        panel1.setBounds(5,0,750,200);
        panel1.setLayout(null);
        checkboxListTeclasBlancas = new ArrayList<JToggleButton>();
        
        for(int i=0;i<11;i++){
            TeclaTeclado tecla = new TeclaTeclado();
            checkboxListTeclasBlancas.add(tecla.getTeclaBlanca(i*65 +15,15));
            panel1.add(checkboxListTeclasBlancas.get(i)); //se le agrega directamente al panel las teclas blancas
        }
        
        for(int i=0;i<pos.length;i++){
            if(pos[i] != -1){
                checkboxListTeclasBlancas.get(pos[i]).setSelected(true); 
            }
        }
        return panel1;
    }

    private int[] getArrayEstadoTeclasBlancas(int j){ //este método regresa un array con los estados de cada columnas de la partitura, dependiendo la variable estática lol 
        int[] estadoTeclasBlancas = new int[11];
        int z=0;
        for(int i=10;i>=0;i--){
            if(checkboxList.get((i*26) + j).isSelected() == true){
                estadoTeclasBlancas[i] = z;
            }else{
                estadoTeclasBlancas[i] = -1;
            }
            z++;
        }
        return estadoTeclasBlancas;
    }
}


