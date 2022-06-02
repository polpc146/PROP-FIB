package DomainControllers;

/**
 * Controlador del dominio
 * Contiene el controlador del dominio de documentos
 * y el controlador del dominio de modificación
 * @author Sandra Buitrago Cervilla
 */
public class DomainCtrl {
    private DocumentDomainCtrl DocController;
    private ModifyDomainCtrl ModController;

    /**
     * Crea la instancia del controlador del dominio e inicializa el controlador de dominio de documento y el dominio de modificación
     */
    public DomainCtrl() {

        DocController = new DocumentDomainCtrl();
        ModController = new ModifyDomainCtrl(DocController);

    }
    /**
     * Consulta el controlador de documentos del dominio
     * @return controlador de documentos del dominio
     */
    public DocumentDomainCtrl getDocController() {
        return DocController;
    }
    /**
     * Consulta el controlador de modificacion del dominio
     * @return controlador de modificación del dominio
     */
    public ModifyDomainCtrl getModController() {
        return ModController;
    }
}
