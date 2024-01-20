import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TeclaTeclado extends JPanel {
   
    public JToggleButton getTeclaBlanca(int posX, int posY){ //regresa una tecla de color blanco de tipo JToggleButton que contienen una acción que es si es verdadero convertirlo a falso, y viceversa.
        JToggleButton tecla = new JToggleButton();
        tecla.setBackground(Color.WHITE);
        tecla.setBorder(new EmptyBorder(15, 0, 0, 0));
        tecla.setBounds(posX,posY,60,200);
        tecla.setEnabled(false);
        tecla.setOpaque(true);
        tecla.setVisible(true);
        return tecla;
    }

    public JToggleButton getTeclaNegra(int posX, int posY){ //regresa una tecla de color negro de tipo JToogleButton, la cual solamente están por la estática
        JToggleButton tecla = new JToggleButton();
        tecla.setBackground(Color.BLACK);
        tecla.setBorder(new EmptyBorder(0, 0, 0, 0));
        tecla.setBounds(posX,posY,30,125);
        tecla.setEnabled(false);
        tecla.setOpaque(true);
        tecla.setVisible(true);
        return tecla;
    }

}
