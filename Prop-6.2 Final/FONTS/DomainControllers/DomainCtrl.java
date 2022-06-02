package DomainControllers;

import Persistencia.CtrlPersistency;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Controlador del dominio
 * Contiene el controlador del dominio de documentos
 * y el controlador del dominio de modificación
 * @author Sandra Buitrago Cervilla
 */
public class DomainCtrl {
    private DocumentDomainCtrl DocController;
    private ModifyDomainCtrl ModController;

    private CtrlPersistency PersConstroller;

    /**
     * Crea la instancia del controlador del dominio e inicializa el controlador de dominio de documento y el dominio de modificación
     */
    public DomainCtrl() {

        PersConstroller = CtrlPersistency.getInstance();
        DocController = new DocumentDomainCtrl(PersConstroller);
        ModController = new ModifyDomainCtrl(DocController, PersConstroller);

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