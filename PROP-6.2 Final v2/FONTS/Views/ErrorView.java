package Views;

import javax.swing.*;
import java.awt.*;

/**
 * Vista de errores y notificaciones por pantalla
 * @author Pol Pérez Castillo
 */
public class ErrorView {

    /**
     * Notifica un mensaje por pantalla
     * @param message mensaje a mostrar por pantalla
     */
    public void error (String message) {

        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(null, message);
    }
}
