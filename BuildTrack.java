import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.border.*;

public class BuildTrack {    
    Boolean bandera = true;
    int j=0;
    int z=0;
    ImageIcon i1 = new ImageIcon("imagenes\\raya_sinNota.png"); //inicializamos la primer imagen con una nota sin raya.
    JPanel pane;
    ArrayList<JToggleButton> checkboxList;
    
    public BuildTrack(JPanel pane, ArrayList<JToggleButton> checkboxList) {
        this.pane = pane;
        this.checkboxList = checkboxList;
    }
    
    public void start(){ //inicializa el tablero de la partitura con características default
        checkboxList = new ArrayList<JToggleButton>();
        for(int i = 0; i < 286; i++){
            if(bandera){ //cada 25 notas se cambia de fila en una nueva imagen, una vacía
                i1 = new ImageIcon("imagenes\\raya_sinNota.png");
                    JToggleButton b = new JToggleButton(i1);
                    b.setBorder(new EmptyBorder(0, 0, 0, 0));
                    ActionListener oyenteDeAccion = new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            if(b.isSelected()){
                                ImageIcon i1 = new ImageIcon("imagenes\\punto_sinNota.png");
                                b.setIcon(i1);
                            }else{
                                ImageIcon i1 = new ImageIcon("imagenes\\raya_sinNota.png");
                                b.setIcon(i1);
                            }
                        }
                    };
                    b.setContentAreaFilled(false);
                    b.addActionListener(oyenteDeAccion); //le agregamos a cada imagen de tipo JToggleButton su acción 
                    pane.add(b); //lo agregamos a la interfaz
                    checkboxList.add(b); //lo agregamos a la lista que contiene los falsos y verdaderos de los botones
                }else{
                    i1 = new ImageIcon("imagenes\\vacio_sinNota.png"); //esta condición entra cuando se quiere rellenar los JToggleButton de las dos últimas filas, abajo de la partitura
                    JToggleButton b = new JToggleButton(i1);
                    
                    b.setBorder(new EmptyBorder(0, 0, 0, 0));
                    ActionListener oyenteDeAccion = new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            if(b.isSelected()){
                                ImageIcon i1 = new ImageIcon("imagenes\\punto_sinRaya.png");
                                b.setIcon(i1);
                            }else{
                                ImageIcon i1 = new ImageIcon("imagenes\\vacio_sinNota.png");
                                b.setIcon(i1);
                            }
                        }
                    };
                    b.setContentAreaFilled(false);
                    b.addActionListener(oyenteDeAccion);
                    pane.add(b); //lo agregamos a la interfaz
                    checkboxList.add(b); //lo agregamos a la lista que contiene los falsos y verdaderos de los botones
                }
            j++;
            if(z>25){
                z=0;
            }
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

    public ArrayList<JToggleButton> getCheckboxList() {
        return checkboxList;
    }
    public JPanel getPane() {
        return pane;
    }
    
}
