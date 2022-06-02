import Presentation.PresentationCtrl;

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

        PresController = new PresentationCtrl();
        PresController.StartController();
    }
}
