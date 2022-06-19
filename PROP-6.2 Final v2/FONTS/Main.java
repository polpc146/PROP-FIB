import PresentationControllers.PresentationCtrl;

/**
 * Clase main
 * @author Pol Pérez Castillo
 */
public class Main {

    private static PresentationCtrl PresController;

    /**
     * Método main
     * @param args argumentos de la llamada main
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        javax.swing.SwingUtilities.invokeLater(

                new Runnable() {

                    public void run() {

                        PresController = new PresentationCtrl();
                        try {
                            PresController.StartController();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }
}
