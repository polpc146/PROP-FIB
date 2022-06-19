package PresentationControllers;

import DomainControllers.*;
import Views.*;

import java.util.*;

/**
 * Controlador de la capa presentación sobre el documento.
 * Contiene los controladores de la vista del documento, dominio de documentos y las modificaciones de presentación
 * @author Sandra Buitrago Cervilla
 */
public class DocumentPresentationCtrl {
    private DocumentView ViewDoc;
    private DocumentDomainCtrl DomainDocCtrl;
    private ModifyPresentationCtrl ModPresCtrl;
    private ErrorView errorView = new ErrorView();

    /**
     * Crea la instancia del controlador de la capa presentación sobre el documento e inicializa la vista del documento
     * @param domainDocCtrl Controlador de dominio
     * @param modPresCtrl Controlador de las modificaciones de la capa presentación
     */
    public DocumentPresentationCtrl(DocumentDomainCtrl domainDocCtrl, ModifyPresentationCtrl modPresCtrl) throws Exception {
        DomainDocCtrl = domainDocCtrl;
        ModPresCtrl = modPresCtrl;
        ViewDoc = new DocumentView(this);
    }

    /**
     * Llama al controlador del dominio de documentos para que nos de la lista de documentos
     * y solicita a la vista del documento que envie mensajes por pantalla
     * @return devuelve la lista de documentos
     */
    public LinkedList<String> getDocumentList() throws Exception {

        LinkedList<String> docs = DomainDocCtrl.NamesDocs();
        for (String s: docs) CreateDocument(s);
        return docs;
    }
    /**
     * Llama al controlador del dominio de documentos para que nos cree un documento con el nombre nameDoc
     * y solicita a la vista del documento que envie mensajes por pantalla
     */
    public void CreateDocument (String nameDoc) throws Exception {

        int ErrorCode = DomainDocCtrl.CreateDocument(nameDoc);
        switch (ErrorCode) {
            case 0: break;
            case -1: errorView.error("The Document already exists"); break;
            case -2: errorView.error("You haven't written a name"); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }
    /**
     * Llama al controlador del dominio de documentos para que nos borre un documento con el nombre nameDoc
     * y solicita a la vista del documento que envie mensajes por pantalla
     */
    public void EraseDocument (String nameDoc) throws Exception {

        int ErrorCode = DomainDocCtrl.EraseDocument(nameDoc);
        switch (ErrorCode) {
            case 0: errorView.error("Document erased successfully"); break;
            case -1: errorView.error("The Document wasn't erased"); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }
    /**
     * Llama al controlador del dominio de documentos para que nos cambie el nombre del documento
     * y solicita a la vista del documento que envie mensajes por pantalla
     */
    public void ChangeNameDocument(String DocumentName, String NewName) throws Exception {

        int ErrorCode = DomainDocCtrl.ChangeNameDocument(DocumentName,NewName);
        switch (ErrorCode) {
            case 0: break;
            case -1: errorView.error("There is no Document with that name "); break;
            case -2: errorView.error("There is a Document with the new name "); break;
            default: errorView.error("Unknown Error " + ErrorCode); break;
        }
    }
    /**
     * Llama al controlador del dominio de documentos para que nos seleccione un documento
     * y solicita a la vista del documento que envie mensajes por pantalla
     */
    public void SelectDocument(String DocumentName) throws Exception {

        int index = DomainDocCtrl.GetIndexDocList(DocumentName);
        if (index != -1) {
            ModPresCtrl.ini(index, DocumentName, ViewDoc);
            ViewDoc.hideView();
        }
        else errorView.error("Unknown Error");

    }
    /**
     * Método que dependiendo de la opción escogida por el usuario llama a funciones que gestionan los documentos
     * (crear, borrar, cambiar el nombre, seleccionar documento o acceder a la lista de documentos)
     */
    public void ini() throws Exception {

        DomainDocCtrl.NamesDocs();
        ViewDoc.setVisible();
    }
}
