package Presentation;

import DomainControllers.*;

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

    /**
     * Crea la instancia del controlador de la capa presentación sobre el documento e inicializa la vista del documento
     * @param domainDocCtrl Controlador de dominio
     * @param modPresCtrl Controlador de las modificaciones de la capa presentación
     */
    public DocumentPresentationCtrl(DocumentDomainCtrl domainDocCtrl, ModifyPresentationCtrl modPresCtrl) {
        DomainDocCtrl = domainDocCtrl;
        ModPresCtrl = modPresCtrl;
        ViewDoc = new DocumentView();
    }

    /**
     * Llama al controlador del dominio de documentos para que nos de la lista de documentos
     * y solicita a la vista del documento que envie mensajes por pantalla
     * @throws  Exception
     */
    private void getDocumentList() throws Exception {
        ViewDoc.ShowMessage('-',"List of the Documents:");
        Vector<String> res = DomainDocCtrl.getDocumentList();
        ViewDoc.ShowDocumentList(res);
    }
    /**
     * Llama al controlador del dominio de documentos para que nos cree un documento con el nombre nameDoc
     * y solicita a la vista del documento que envie mensajes por pantalla
     * @throws  Exception
     */
    private void CreateDocument() throws Exception {
        ViewDoc.ShowMessage('-',"Creation of the Document:");
        ViewDoc.ShowMessage('-',"Tell me the name of the Document you want to create:");
        String nameDoc = ViewDoc.getDocumentName();
        int ErrorCode = DomainDocCtrl.CreateDocument(nameDoc);
        switch (ErrorCode) {
            case 0: ViewDoc.ShowMessage('-',"Document created successfully"); break;
            case -1: ViewDoc.ShowMessage('*', "The Document already exists"); break;
            default: ViewDoc.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }
    /**
     * Llama al controlador del dominio de documentos para que nos borre un documento con el nombre nameDoc
     * y solicita a la vista del documento que envie mensajes por pantalla
     * @throws  Exception
     */
    private void EraseDocument() throws Exception {
        ViewDoc.ShowMessage('-',"Removal of the Document:");
        ViewDoc.ShowMessage('-',"Tell me the name of the Document you want to remove:");
        String nameDoc = ViewDoc.getDocumentName();
        int ErrorCode = DomainDocCtrl.EraseDocument(nameDoc);
        switch (ErrorCode) {
            case 0: ViewDoc.ShowMessage('-', "Document erased successfully"); break;
            case -1: ViewDoc.ShowMessage('*', "The Document wasn't erased"); break;
            default: ViewDoc.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }
    /**
     * Llama al controlador del dominio de documentos para que nos cambie el nombre del documento
     * y solicita a la vista del documento que envie mensajes por pantalla
     * @throws  Exception
     */
    private void ChangeNameDocument() throws Exception {
        ViewDoc.ShowMessage('-',"Changing the name of a document:");
        ViewDoc.ShowMessage('-',"Tell me the name of the Document you want to change:");
        String DocumentName = ViewDoc.getDocumentName();
        ViewDoc.ShowMessage('-',"Tell me the new name of the Document:");
        String NewName = ViewDoc.getDocumentName();
        int ErrorCode = DomainDocCtrl.ChangeNameDocument(DocumentName,NewName);
        switch (ErrorCode) {
            case 0: ViewDoc.ShowMessage('-', "Name changed successfully"); break;
            case -1: ViewDoc.ShowMessage('*', "There is no Document with that name "); break;
            case -2: ViewDoc.ShowMessage('*', "There is a Document with the new name "); break;
            default: ViewDoc.ShowMessage('*', "Unknown Error " + ErrorCode); break;
        }
    }
    /**
     * Llama al controlador del dominio de documentos para que nos seleccione un documento
     * y solicita a la vista del documento que envie mensajes por pantalla
     * @throws  Exception
     */
    private void SelectDocument() throws Exception {
        ViewDoc.ShowMessage('-', "Selecting a document:");
        ViewDoc.ShowMessage('-', "Tell me the name of the Document you want to select:");
        String DocumentName = ViewDoc.getDocumentName();
        int index = DomainDocCtrl.GetIndexDocList(DocumentName);
        if (index != -1) {
            ModPresCtrl.ini(index);
        }
        else ViewDoc.ShowMessage('*', "Unknown Error");

    }
    /**
     * Método que dependiendo de la opción escogida por el usuario llama a funciones que gestionan los documentos
     * (crear, borrar, cambiar el nombre, seleccionar documento o acceder a la lista de documentos)
     * @throws  Exception
     */
    public void ini() throws Exception {
        int option = -1;
        while(option != 0) {
            option = ViewDoc.GetOption();
            switch(option) {
                case 1: getDocumentList(); break;
                case 2: CreateDocument(); break;
                case 3: EraseDocument(); break;
                case 4: ChangeNameDocument(); break;
                case 5: SelectDocument(); break;
                default: break;
            }
        }
    }
}
