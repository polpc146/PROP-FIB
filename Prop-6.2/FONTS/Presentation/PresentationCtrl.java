package Presentation;

import DomainControllers.*;

/**
 * Controlador de la capa presentación.
 * Contiene los controladores del dominio de documentos, dominio,
 * presentación de documentos, modificación de presentación y el dominio de modificación
 * @author Sandra Buitrago Cervilla
 */
public class PresentationCtrl {
    private DocumentDomainCtrl DocController;
    private DomainCtrl DomController;
    private DocumentPresentationCtrl DocPresController;

    private ModifyPresentationCtrl ModPresController;

    private ModifyDomainCtrl ModController;

    /**
     * Crea la instancia del controlador de la capa presentación e inicializa los controladores de dominio, del dominio de documentos
     * y el dominio de modificación
     */
    public PresentationCtrl() {
        DomController = new DomainCtrl();
        DocController = DomController.getDocController();
        ModController = DomController.getModController();
    }

    /**
     * Inicializa los controladores relacionados con la capa de presentación
     * @throws Exception
     */
    public void StartController() throws Exception {
        ModPresController = new ModifyPresentationCtrl(ModController);
        DocPresController = new DocumentPresentationCtrl(DocController,ModPresController);
        DocPresController.ini();
    }



}
