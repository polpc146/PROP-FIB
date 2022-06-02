package DomainControllers;

import Persistencia.CtrlPersistency;
import Structure.*;

import java.io.IOException;
import java.util.*;
/**
 * Controlador de dominio de documentos.
 * Controla las operaciones que requieran gestionar documentos, contiene una lista de documentos
 * @author Sandra Buitrago Cervilla
 */
public class DocumentDomainCtrl {
    private LinkedList<Document> DocumentList;
    private CtrlPersistency PersController;

    /**
     * Crea una instancia del controlador e inicializa una lista de documentos
     */
    public DocumentDomainCtrl(CtrlPersistency ctrlPersistency) {
        DocumentList = new LinkedList<>();
        PersController = ctrlPersistency;
    }

    /**
     * Consulta la lista de documentos
     * @return los nombres de los documentos
     */
    public Vector<String> getDocumentList() {
        Vector<String> Docnames = new Vector<>();
        for (Document document : DocumentList) Docnames.add(document.getName());
        return Docnames;
    }
    /**
     * Llama a la clase documento para que elimine un documento con un nombre determinado
     * @param DocumentName nombre del documento a borrar
     * @return 0 si se ha borrado correctamente. -1 si ha habido algún error.
     */
    public int EraseDocument(String DocumentName) throws IOException {
        for(int i = 0; i < DocumentList.size() ; ++i) {
            if (DocumentList.get(i).getName().equalsIgnoreCase(DocumentName)) {
                DocumentList.remove(i);
                deleteDocument(DocumentName);
                return 0;
            }
        }
        return -1;
    }
    /**
     * Llama a la clase documento para que cree un documento nuevo con un nombre determinado
     * @param DocumentName Nombre del nuevo documento
     * @return 0 si se ha creado correctamente. -1 si ha habido algún error.
     */
    public int CreateDocument(String DocumentName) {
        if (!ExistsDocument(DocumentName)) {
            Document NewDoc = new Document(DocumentName);
            DocumentList.add(NewDoc);
            return 0;
        }
        //Ya existe un documento con este nombre
        else return -1;
    }
    /**
     * Llama a la clase documento para que modifique el nombre de un documento por otro nombre
     * @param DocumentName nombre antiguo del documento
     * @param NewName nombre nuevo del documento
     * @return 0 si el método ha funcionado correctamente. -1 si ha habido algún error.
     */
    public int ChangeNameDocument(String DocumentName, String NewName) {
        int index = GetIndexDocList(DocumentName);
        if (index != -1) {
            if (!ExistsDocument(NewName)) {
                DocumentList.get(index).setName(NewName);
                return 0;
            }
            //Existe un documento con el nombre NewName
            else return -2;
        }
        //No hay ningún documento con el nombre DocumentName
        else return -1;
    }
    /**
     * Llama a la clase documento para que compruebe si existe un documento con el nombre DocumentName
     * @param DocumentName nombre del documento
     * @return true si el método ha funcionado correctamente. false si ha habido algún error.
     */
    public boolean ExistsDocument(String DocumentName) {
        for (Document document : DocumentList) {
            if (document.getName().equalsIgnoreCase(DocumentName)) return true;
        }
        return false;
    }
    /**
     * Mira la posición del documento DocumentName en la lista de documentos
     * @param DocumentName nombre del documento
     * @return i si el método ha funcionado correctamente. -1 si ha habido algún error.
     */
    public int GetIndexDocList(String DocumentName) {
        for (int i = 0; i < DocumentList.size(); ++i) {
            if (DocumentList.get(i).getName().equalsIgnoreCase(DocumentName)) return i;
        }
        return -1;
    }
    /**
     * Llama a la clase documento para que esta le de un documento con la posición de index.
     * @param index posición del documento en la lista de documentos
     * @return El documento con la posición index.
     */
    public Document GetDocument(int index) {
        return DocumentList.get(index);
    }

    /**
     * Consulta todos los documentos que hayan guardados
     * @return todos los documentos que esten guardados
     * @throws IOException
     */
    public LinkedList<String> NamesDocs() throws IOException {
        return PersController.AllListDoc();
    }

    /**
     * Borra el documento y todas sus hojas
     * @param nameDoc nombre del documento que se quiere borrar
     * @throws IOException
     */
    public void deleteDocument(String nameDoc) throws IOException {
        LinkedList<String> docs = NamesDocs();;
        LinkedList<String> aux = new LinkedList<String>();

        for (int i = 0; i < docs.size()-1; ++i) {

            char[] n = docs.get(i).toCharArray();
            String name1 = "";

            for (int it = 0; it < n.length; ++it) {
                if ((n[it] >= 'a' && n[it] <= 'z') || (n[it] >= 'A' && n[it] <= 'Z')) {
                    name1 += n[it];
                }
            }
            aux.add(name1);

            PersController.deleteDocument(nameDoc, aux);
        }
    }
}