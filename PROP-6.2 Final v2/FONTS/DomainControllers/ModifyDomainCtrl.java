package DomainControllers;

import Persistencia.CtrlPersistency;
import PresentationControllers.ModifyPresentationCtrl;
import Structure.*;
import Structure.Cells.Cell;

import java.io.IOException;
import java.util.*;
/**
 * Controlador de modificación de la capa dominio.
 * Contiene el documento y el controlador del dominio de documentos
 * @author Sandra Buitrago Cervilla
 */
public class ModifyDomainCtrl {
    private Document myDocument;
    private DocumentDomainCtrl DocDomCtrl;
    private CtrlPersistency PersController;

    private ModifyPresentationCtrl PresCtrl;

    /**
     * Inicializa la instancia del controlador de modificación de la capa de presentación
     * @param presCtrl Controlador de modificación de la capa presentación
     */

    public void setPresCtrl(ModifyPresentationCtrl presCtrl) {
        PresCtrl = presCtrl;
    }

    /**
     * Crea la instancia del controlador de modificación de la capa de dominio y la inicializa
     * @param docDomCtrl Controlador de dominio de documentos
     */
    public ModifyDomainCtrl(DocumentDomainCtrl docDomCtrl, CtrlPersistency ctrlPersistency) {
        DocDomCtrl = docDomCtrl;
        PersController = ctrlPersistency;
    }

    /**
     * Consulta el documento
     * @return documento
     */
    public Document getMyDocument() {
        return myDocument;
    }

    /**
     * Modifica el documento en el que estamos y coge el que tiene la posición index.
     * @param index posición del documento en la lista de documentos.
     * @return true
     */
    public boolean setMyDocument(int index) {
        myDocument = DocDomCtrl.GetDocument(index);
        return true;
    }
    /**
     * Llama al documento para que cree una hoja
     * @return 0 si se ha añadido correctamente la hoja. -1 si ha habido algún error.
     */
    public int CreateSheet() {
        if (myDocument.addSheet()) return 0;
        return -1;
    }

    /**
     * Llama al documento para cambie el nombre de una hoja
     * @param NameSheet nombre de la hoja que queremos cambiar
     * @param NewName nuevo nombre de la hoja
     * @return 0 si se ha añadido correctamente la hoja. -1 si no hay ninguna hoja con el nombre NameSheet.
     * -2 si existe una hoja con el nombre NewSheet. -3 No se ha podido cambiar el nombre por algún error
     */
    public int ChangeNameSheet(String NameSheet, String NewName) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (index != -1) {
            if (!myDocument.ExistsSheet(NewName)) {
                if (myDocument.SetSheetName(index,NewName)) return 0;
                    //No se ha podido cambiar el nombre de la hoja
                else return -3;
            }
            //Existe una hoja con el nombre NewName
            else return -2;
        }
        //No hay ninguna hoja con el nombre NameSheet
        else return -1;
    }
    /**
     * Llama al documento para borre una hoja
     * @param NameSheet nombre de la hoja
     * @return 0 si se ha borrado correctamente la hoja. -1 si no se ha podido borrar por algún error desconocido
     */
    public int EraseSheet(String NameSheet) throws IOException {
        if(myDocument.removeSheetByName(NameSheet)) {
            PersController.deleteSheet(myDocument.getName(), NameSheet);
            return 0;
        }
        return -1;
    }
    /**
     * Llama al documento para añadir una o más filas a la hoja
     * @param NameSheet nombre de la hoja
     * @param NumRows número de filas para añadir
     * @param IndexRow posición donde vamos a añadir las filas
     * @return 0 si se han añadido correctamente las filas. -1 si no hay ninguna hoja con el nombre NameSheet.
     * -2 no existe una fila con la posición IndexRow. -3 No se han podido añadir las filas por algún error desconocido
     */
    public int AddRow(String NameSheet, int NumRows, int IndexRow) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (index != -1) {
            if (myDocument.ExistsRow(index, IndexRow)) {
                if (myDocument.addRow(index, NumRows, IndexRow)) return 0;
                    //No se han podido añadir las filas
                else return -3;
            }
            //No existe la fila con ese indice
            else return -2;
        }
        //No hay ninguna hoja con el nombre NameSheet
        else return -1;
    }
    /**
     * Llama al documento para añadir una o más columnas a la hoja
     * @param NameSheet nombre de la hoja
     * @param NumColumns número de filas para añadir
     * @param IndexColumn posición donde vamos a añadir las columnas
     * @return 0 si se han añadido correctamente las columnas. -1 si no hay ninguna hoja con el nombre NameSheet.
     * -2 no existe una columna con la posición IndexColumn. -3 No se han podido añadir las columnas por algún error desconocido
     */
    public int AddColumn (String NameSheet, int NumColumns, int IndexColumn) {

        int index = myDocument.GetIndexSheetList(NameSheet);
        if (index != -1) {
            if (myDocument.ExistsColumn(index, IndexColumn)) {
                if (myDocument.addColumn(index, NumColumns, IndexColumn)) return 0;
                    //No se han podido añadir las columnas
                else return -3;
            }
            //No existe la columna con ese indice
            else return -2;
        }
        //No hay ninguna hoja con el nombre NameSheet
        else return -1;
    }
    /**
     * Llama al documento para consultar el nombre de las hojas que contiene
     * @return un vector con el nombre de las hojas
     */
    public Vector<String> getSheetList() {

        Vector<String> SheetNames = myDocument.getSheetNames();
        return SheetNames;
    }
    /**
     * Llama al documento para eliminar una o más filas a la hoja
     * @param NameSheet nombre de la hoja
     * @param NumRows número de filas para eliminar
     * @param IndexRow posición donde vamos a borrar la/s fila/s
     * @return 0 si se han eliminado correctamente las filas. -1 si no hay ninguna hoja con el nombre NameSheet.
     * -2 no existe una fila con la posición IndexRow. -3 No se han podido eliminar la/s fila/s por algún error desconocido.
     * -4 se ha intentado eliminar más filas de las que hay a partir de esa posición.
     */
    public int EraseRow(String NameSheet, int NumRows, int IndexRow) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (index != -1) {
            if (myDocument.ExistsRow(index, IndexRow)) {
                if (IndexRow+NumRows <= myDocument.getSheetSize(index)) {
                    if (myDocument.EraseRow(index, NumRows, IndexRow)) return 0;
                        //No se han podido eliminar las filas
                    else return -3;
                }
                //Intentas eliminar mas filas de las que hay a partir de IndexRow
                else return -4;
            }
            //No existe la fila con ese indice
            else return -2;
        }
        //No hay ninguna hoja con el nombre NameSheet
        else return -1;
    }
    /**
     * Llama al documento para eliminar una o más columnas a la hoja
     * @param NameSheet nombre de la hoja
     * @param NumColumns número de columnas para eliminar
     * @param IndexColumn posición donde vamos a borrar la/s columna/s
     * @return 0 si se han eliminado correctamente las columnas. -1 si no hay ninguna hoja con el nombre NameSheet.
     * -2 no existe una columna con la posición IndexRow. -3 No se han podido eliminar la/s columna/s por algún error desconocido.
     * -4 se han intentado eliminar más columnas de las que hay a partir de esa posición.
     */
    public int EraseColumn(String NameSheet, int NumColumns, int IndexColumn) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (index != -1) {
            if (myDocument.ExistsColumn(index, IndexColumn)) {
                if (IndexColumn+NumColumns <= myDocument.getSheetColSize(index)) {
                    if (myDocument.EraseColumn(index, NumColumns, IndexColumn)) return 0;
                        //No se han podido eliminar las columnas
                    else return -3;
                }
                //Intentas eliminar mas columnas de las que hay a partir de IndexRow
                else return -4;
            }
            //No existe la columna con ese indice
            else return -2;
        }
        //No hay ninguna hoja con el nombre NameSheet
        else return -1;
    }
    /**
     * Llama al documento para seleccionar un rango de celdas
     * @param NameSheet nombre de la hoja
     * @param RowCellIni fila de la celda inicial a seleccionar
     * @param ColumnCellIni columna de la celda inicial a seleccionar
     * @param RowCellFin fila de la celda final a seleccionar
     * @param ColumnCellFin columna de la celda final a seleccionar
     * @return 0 si se ha seleccionado correctamente el rango. -1 si no hay ninguna hoja con el nombre NameSheet.
     * -2 no existe la celda inicial con esos parámetros. -3 No se ha podido seleccionar el rango por algún error desconocido.
     * -4 no existe la celda final con esos parámetros.
     */
    public int SelectRank(String NameSheet, int RowCellIni, int ColumnCellIni, int RowCellFin, int ColumnCellFin, int rank) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (index != -1) {
            if (myDocument.ExistsRow(index, RowCellIni) && myDocument.ExistsColumn(index,ColumnCellIni)) {
                if(myDocument.ExistsRow(index, RowCellFin) && myDocument.ExistsColumn(index,ColumnCellFin)) {
                    if (myDocument.SelectRank(index, RowCellIni, ColumnCellIni, RowCellFin, ColumnCellFin, rank)) return 0;
                        //No se ha podido seleccionar el rango.
                    else return -3;
                }
                //No existe la celda final con esos parámetros
                else return -4;
            }
            //No existe la celda inicial con esos parámetros
            else return -2;
        }
        //No hay ninguna hoja con el nombre NameSheet
        else return -1;
    }
    /**
     * Llama al documento para ordenar un rango de celdas
     * @param NameSheet nombre de la hoja
     * @param Criterium criterio para ordenar el rango de celdas (ascendente / descendente)
     * @return 0 si se ha seleccionado correctamente el rango. -1 no se ha podido ordenar el rango por algún error desconocido.
     */

    public int SortRank(String NameSheet, String Criterium) {
        if(myDocument.SortRank(NameSheet, Criterium)) return 0;
        else return -1;
    }

    /**
     * Llama al documento para mover un rango de celdas
     * @param NameSheet nombre de la hoja
     * @param RowCellIni fila de la celda inicial donde moveremos el rango
     * @param ColumnCellIni columna de la celda inicial donde moveremos el rango
     * @return 0 si se ha movido correctamente el rango. -1 si no existe la celda inicial con esos parámetros para pegarla.
     * -2 la celda no está en la hoja. -3 No se ha podido mover el rango por algún error desconocido.
     */
    public int MoveRank(String NameSheet, int RowCellIni, int ColumnCellIni) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (myDocument.ExistsRow(index, RowCellIni) && myDocument.ExistsColumn(index,ColumnCellIni)) {
            int RowSheet = myDocument.getSheetSize(index);
            int ColumnsSheet = myDocument.getSheetColSize(index);
            int RowRank = myDocument.getRankSize(index);
            int ColumnRank = myDocument.getRankColSize(index);
            if (RowSheet > RowRank + RowCellIni && ColumnsSheet > ColumnCellIni + ColumnRank) {
                if (myDocument.PasteRank(index,RowCellIni,ColumnCellIni,RowRank,ColumnRank)) return 0;
                    //No se ha podido enganchar la celda.
                else return -3;
            }
            //No cabe en la hoja.
            else return -2;
        }
        //No existe la celda inicial con esos parámetros para pegarla
        else return -1;
    }
    /**
     * Llama al documento para copiar un rango de celdas
     * @param NameSheet nombre de la hoja
     * @param RowCellIni fila de la celda inicial a copiar
     * @param ColumnCellIni columna de la celda inicial a copiar
     * @return 0 si se ha copiado correctamente el rango. -1 si no existe la celda inicial con esos parámetros para pegarla.
     * -2 la celda no está en la hoja. -3 No se ha podido mover el rango por algún error desconocido.
     */
    public int CopyRank(String NameSheet, int RowCellIni, int ColumnCellIni) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (myDocument.ExistsRow(index, RowCellIni) && myDocument.ExistsColumn(index,ColumnCellIni)) {
            int RowSheet = myDocument.getSheetSize(index);
            int ColumnsSheet = myDocument.getSheetColSize(index);
            int RowRank = myDocument.getRankSize(index);
            int ColumnRank = myDocument.getRankColSize(index);
            if (RowSheet > RowRank + RowCellIni && ColumnsSheet > ColumnCellIni + ColumnRank) {
                if (myDocument.CopyRank(index,RowCellIni,ColumnCellIni,RowRank,ColumnRank)) return 0;
                    //No se ha podido enganchar la celda.
                else return -3;
            }
            //No cabe en la hoja.
            else return -2;
        }
        //No existe la celda inicial con esos parámetros para pegarla
        else return -1;
    }

    /**
     * Llama al documento para modificar una celda con un string
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda
     * @param ColumnCell columna de la celda
     * @param Content futuro contenido de la celda
     * @return 0 si se ha modificado correctamente. -1 si no se ha podido modificar por un error desconocido
     */
    public int ModifyString(String NameSheet, int RowCell, int ColumnCell, String Content) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if(myDocument.ModifyString(index,RowCell,ColumnCell,Content)) return 0;
            //No se ha podido modificar la celda
        else return -1;
    }
    /**
     * Llama al documento para modificar una celda con un double
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda
     * @param ColumnCell columna de la celda
     * @param Content futuro contenido de la celda
     * @return 0 si se ha modificado correctamente. -1 si no se ha podido modificar por un error desconocido
     */
    public int ModifyDouble(String NameSheet, int RowCell, int ColumnCell, Double Content) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if(myDocument.ModifyDouble(index,RowCell,ColumnCell,Content)) return 0;
            //No se ha podido modificar la celda
        else return -1;
    }
    /**
     * Llama al documento para modificar una celda con un date
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda
     * @param ColumnCell columna de la celda
     * @param Day futuro contenido de la celda
     * @param Month futuro contenido de la celda
     * @param Year futuro contenido de la celda
     * @return 0 si se ha modificado correctamente. -1 si no se ha podido modificar por un error desconocido
     */
    public int ModifyDate(String NameSheet, int RowCell, int ColumnCell, int Day, int Month, int Year) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if(myDocument.ModifyDate(index,RowCell,ColumnCell,Day,Month,Year)) return 0;
            //No se ha podido modificar la celda
        else return -1;
    }


    /**
     * Llama al documento para truncar el contenido de una fila o columna o una celda (botón)
     * @param NameSheet nombre de la hoja
     * @param Row fila de la celda
     * @param Col columna de la celda
     * @param TruncRow true si se trunca la fila entera, false no se trunca la fila
     * @param TruncColumn true trunca la columna entera, false no trunca la columna
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido truncar por un error desconocido
     */

    public int Truncate(String NameSheet, int Row, int Col, boolean TruncRow,  boolean TruncColumn) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        //Si se ha podido hacer el truncate entonces modificamos las referencias de las celdas
        if(myDocument.Truncate(index,Row,Col,TruncRow,TruncColumn)) return 0;
            //No se ha podido hacer el truncate
        else return -1;
    }

    /**
     * Llama al documento para truncar el contenido de una fila o columna o una celda
     * @param NameSheet nombre de la hoja
     * @param Row fila de la celda
     * @param Col columna de la celda
     * @param TruncRow true si se trunca la fila entera, false no se trunca la fila
     * @param TruncColumn true trunca la columna entera, false no trunca la columna
     * @param Rowfinal fila de la celda donde se insertará el resultado
     * @param Colfinal columna de la celda donde se insertará el resultado
     * @param formula formula que estamos utilizando
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido truncar por un error desconocido
     */

    public int Truncate(String NameSheet, int Row, int Col, boolean TruncRow,  boolean TruncColumn, int Rowfinal, int Colfinal, String formula) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        //Si se ha podido hacer el truncate entonces modificamos las referencias de las celdas
        if(myDocument.Truncate(index,Row,Col,TruncRow,TruncColumn, Rowfinal, Colfinal, formula)) return 0;
            //No se ha podido hacer el truncate
        else return -1;
    }
    /**
     * Llama al documento para incrementar el contenido de una fila o columna o una celda
     * @param NameSheet nombre de la hoja
     * @param Row fila de la celda
     * @param Col columna de la celda
     * @param IncRow true si se incrementa la fila entera, false no se incrementa la fila
     * @param IncColumn true incrementa la columna entera, false no incrementa la columna
     * @param Rowfinal fila de la celda donde se insertará el resultado
     * @param Colfinal columna de la celda donde se insertará el resultado
     * @param formula formula que estamos utilizando
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido incrementar por un error desconocido
     */

    public int Increase(String NameSheet, int Row, int Col, boolean IncRow,  boolean IncColumn, int Rowfinal, int Colfinal, String formula) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if(myDocument.Increase(index,Row,Col,IncRow,IncColumn,Rowfinal,Colfinal,formula)) return 0;
            //No se ha podido hacer el truncate
        else return -1;
    }
    /**
     * Llama al documento para incrementar el contenido de una fila o columna o una celda (botón)
     * @param NameSheet nombre de la hoja
     * @param Row fila de la celda
     * @param Col columna de la celda
     * @param IncRow true si se incrementa la fila entera, false no se incrementa la fila
     * @param IncColumn true incrementa la columna entera, false no incrementa la columna
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido incrementar por un error desconocido
     */
    public int Increase(String NameSheet, int Row, int Col, boolean IncRow,  boolean IncColumn) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if(myDocument.Increase(index,Row,Col,IncRow,IncColumn)) return 0;
            //No se ha podido hacer el truncate
        else return -1;
    }

    /**
     * Llama al documento para hacer el valor absoluto del contenido de una fila o columna o una celda
     * @param NameSheet nombre de la hoja
     * @param Row fila de la celda
     * @param Col columna de la celda
     * @param IncRow true si se hace el valor absoluto en la fila entera, false no se hace en la fila
     * @param IncColumn true si se hace el valor absoluto en la columna entera, false no se hace en la columna
     * @param Rowfinal fila de la celda donde se situará el resultado de la operación
     * @param Colfinal columna de la celda donde se situará el resultado de la operación
     * @param formula formula que estamos utilizando
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la operación por un error desconocido
     */
    public int Absolut(String NameSheet, int Row, int Col, boolean IncRow,  boolean IncColumn, int Rowfinal, int Colfinal, String formula) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if(myDocument.Absolut(index,Row,Col,IncRow,IncColumn,Rowfinal,Colfinal,formula)) return 0;
            //No se ha podido hacer el truncate
        else return -1;
    }

    /**
     * Llama al documento para hacer el valor absoluto del contenido de una fila o columna o una celda (botón)
     * @param NameSheet nombre de la hoja
     * @param Row fila de la celda
     * @param Col columna de la celda
     * @param IncRow true si se hace el valor absoluto en la fila entera, false no se hace en la fila
     * @param IncColumn true si se hace el valor absoluto en la columna entera, false no se hace en la columna
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la operación por un error desconocido
     */
    public int Absolut(String NameSheet, int Row, int Col, boolean IncRow,  boolean IncColumn) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if(myDocument.Absolut(index,Row,Col,IncRow,IncColumn)) return 0;
            //No se ha podido hacer el truncate
        else return -1;
    }
    /**
     * Llama al documento para hacer una conversión del contenido de una fila o columna o una celda (hexadecimal o binario)
     * @param NameSheet nombre de la hoja
     * @param Row fila de la celda
     * @param Col columna de la celda
     * @param ConvRow true hace la conversión en la fila entera, false no se hace la conversión en la fila
     * @param ConvColumn true hace la conversión en la columna entera, false no se hace la conversión en la columna
     * @param Criterium string que indica si es la conversión en hexadecimal o en binario
     * @param Rowfinal fila de la celda donde se situará el resultado de la operación
     * @param Colfinal columna de la celda donde se situará el resultado de la operación
     * @param formula formula que estamos utilizando
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la conversión por un error desconocido
     */
    public int Conversion(String NameSheet, int Row, int Col, boolean ConvRow,  boolean ConvColumn, String Criterium, int Rowfinal, int Colfinal, String formula) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if(myDocument.Conversion(index,Row,Col,ConvRow,ConvColumn, Criterium, Rowfinal, Colfinal,formula)) return 0;
            //No se ha podido hacer la conversión
        else return -1;
    }

    /**
     * Llama al documento para hacer una conversión del contenido de una fila o columna o una celda (hexadecimal o binario) (botón)
     * @param NameSheet nombre de la hoja
     * @param Row fila de la celda
     * @param Col columna de la celda
     * @param ConvRow true hace la conversión en la fila entera, false no se hace la conversión en la fila
     * @param ConvColumn true hace la conversión en la columna entera, false no se hace la conversión en la columna
     * @param Criterium string que indica si es la conversión en hexadecimal o en binario
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la conversión por un error desconocido
     */
    public int Conversion(String NameSheet, int Row, int Col, boolean ConvRow,  boolean ConvColumn, String Criterium) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if(myDocument.Conversion(index,Row,Col,ConvRow,ConvColumn, Criterium))return 0;
            //No se ha podido hacer la conversión
        else return -1;
    }

    /**
     * Llama al documento para hacer una extracción de algún elemento del contenido de una fila o columna o una celda (botón)
     * @param NameSheet nombre de la hoja
     * @param Row fila de la celda
     * @param Col columna de la celda
     * @param ExtractRow true hace la extracción de elementos en la fila entera, false no se hace la extracción en la fila
     * @param ExtractColumn true hace la extracción de elementos en la columna entera, false no se hace la extracción en la columna
     * @param Criterium string que indica si es la extracción del dia, mes o año
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la extracción por un error desconocido
     */
    public int ExtractElement(String NameSheet, int Row, int Col, boolean ExtractRow,  boolean ExtractColumn, String Criterium) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if(myDocument.ExtractElement(index,Row,Col,ExtractRow,ExtractColumn, Criterium)) return 0;
            //No se ha podido hacer la extracción de elementos.
        else return -1;
    }

    /**
     * Llama al documento para hacer una extracción de algún elemento del contenido de una fila o columna o una celda
     * @param NameSheet nombre de la hoja
     * @param Row fila de la celda
     * @param Col columna de la celda
     * @param ExtractRow true hace la extracción de elementos en la fila entera, false no se hace la extracción en la fila
     * @param ExtractColumn true hace la extracción de elementos en la columna entera, false no se hace la extracción en la columna
     * @param Criterium string que indica si es la extracción del dia, mes o año
     * @param Rowfinal fila de la celda donde se insertará el resultado
     * @param Colfinal columna de la celda donde se insertará el resultado
     * @param formula formula que estamos utilizando
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la extracción por un error desconocido
     */
    public int ExtractElement(String NameSheet, int Row, int Col, boolean ExtractRow,  boolean ExtractColumn, String Criterium, int Rowfinal, int Colfinal, String formula) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if(myDocument.ExtractElement(index,Row,Col,ExtractRow,ExtractColumn, Criterium, Rowfinal, Colfinal, formula)) return 0;
            //No se ha podido hacer la extracción de elementos.
        else return -1;
    }
    /**
     * Llama al documento para poner el dia de la semana en una fila o columna o una celda (botón)
     * @param NameSheet nombre de la hoja
     * @param Row fila de la celda
     * @param Col columna de la celda
     * @param DayRow true hace la función en la fila entera, false no se hace la función en la fila
     * @param DayColumn true hace la función en la columna entera, false no se hace la función en la columna
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la función por un error desconocido
     */
    public int DayOfTheWeek(String NameSheet, int Row, int Col, boolean DayRow,  boolean DayColumn) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if(myDocument.DayOfTheWeek(index,Row,Col,DayRow,DayColumn)) return 0;
            //No se ha podido poner el dia de la semana.
        else return -1;
    }
    /**
     * Llama al documento para poner el dia de la semana en una fila o columna o una celda
     * @param NameSheet nombre de la hoja
     * @param Row fila de la celda
     * @param Col columna de la celda
     * @param DayRow true hace la función en la fila entera, false no se hace la función en la fila
     * @param DayColumn true hace la función en la columna entera, false no se hace la función en la columna
     * @param rowfinal fila de la celda donde estará el resultado
     * @param colfinal columna de la celda donde estará el resultado
     * @param formula formula que estamos utilizando
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la función por un error desconocido
     */
    public int DayOfTheWeek(String NameSheet, int Row, int Col, boolean DayRow,  boolean DayColumn, int rowfinal, int colfinal, String formula) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if(myDocument.DayOfTheWeek(index,Row,Col,DayRow,DayColumn, rowfinal, colfinal, formula)) return 0;
            //No se ha podido poner el dia de la semana.
        else return -1;
    }
    /**
     * Llama al documento para indicar la longitud del contenido de una celda
     * @param NameSheet nombre de la hoja
     * @param Row fila de la celda
     * @param Col columna de la celda
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la longitud por un error desconocido
     */
    public int Size(String NameSheet, int Row, int Col) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if(myDocument.Size(index,Row,Col)) return 0;
            //No se ha podido poner la longitud del contenido.
        else return -1;
    }
    /**
     * Llama al documento para indicar la longitud del contenido de una celda
     * @param NameSheet nombre de la hoja
     * @param Row fila de la celda
     * @param Col columna de la celda
     * @param Rowfinal fila de la celda que tendrá el resultado
     * @param Colfinal columna de la celda que tendrá el resultado
     * @param formula formula que estoy utilizando
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la longitud por un error desconocido
     */
    public int Size(String NameSheet, int Row, int Col, int Rowfinal, int Colfinal, String formula) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if(myDocument.Size(index,Row,Col,Rowfinal,Colfinal, formula)) return 0;
            //No se ha podido poner la longitud del contenido.
        else return -1;
    }
    /**
     * Llama al documento para reemplazar el contenido de una celda o varias celdas con alguna palabra
     * @param NameSheet nombre de la hoja
     * @param ElementToReplace String que hay que cambiar
     * @param ElementReplaced String que hay que poner en la celda
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la modificación por un error desconocido
     */
    public int Replace(String NameSheet, String ElementToReplace,  String ElementReplaced) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (SelectRank(NameSheet, 0, 0, myDocument.getSheetSize(index)-1, myDocument.getSheetColSize(index)-1, 1) != 0) return -1;
        if(myDocument.Replace(index,ElementToReplace,ElementReplaced)) return 0;
            //No se ha podido poner la longitud del contenido.
        else return -1;
    }

    /**
     * Llama al documento para reemplazar el contenido de una celda o varias celdas con alguna palabra
     * @param NameSheet nombre de la hoja
     * @param ElementToFind elemento a buscar
     * @return -1 si no se ha podido hacer la busqueda por un error desconocido
     */
    public int[] Search(String NameSheet, String ElementToFind) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        //if (SelectRank(NameSheet, 0, 0, myDocument.getSheetSize(index)-1, myDocument.getSheetColSize(index)-1, 1) != 0) return new int[]{-1,-1};
        return myDocument.Search(index,ElementToFind);
    }
    /**
     * Llama al documento para hacer la media
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda resultado
     * @param ColumnCell columna de la celda resultado
     * @param formula formula que estoy utilizando
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la media por un error desconocido.
     * -2 No existe la celda resultado
     */
    public int Average (String NameSheet, int RowCell, int ColumnCell, String formula) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index,ColumnCell)) {
            if (myDocument.Average(index, RowCell, ColumnCell, formula)) return 0;
                //No se ha podido hacer la media
            else return -1;
        }
        //No existe la celda  con esos parámetros
        else return -2;
    }

    /**
     * Llama al documento para hacer la media
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda resultado
     * @param ColumnCell columna de la celda resultado
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la media por un error desconocido.
     * -2 No existe la celda resultado
     */
    public int Average (String NameSheet, int RowCell, int ColumnCell) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index,ColumnCell)) {
            if (myDocument.Average(index, RowCell, ColumnCell)) return 0;
                //No se ha podido hacer la media
            else return -1;
        }
        //No existe la celda  con esos parámetros
        else return -2;
    }

    /**
     * Llama al documento para hacer la mediana
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda resultado
     * @param ColumnCell columna de la celda resultado
     * @param formula formula que estoy utilizando
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la mediana por un error desconocido.
     * -2 No existe la celda resultado
     */
    public int Median(String NameSheet, int RowCell, int ColumnCell, String formula) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index,ColumnCell)) {
            if (myDocument.Median(index, RowCell, ColumnCell, formula)) return 0;
                //No se ha podido hacer la mediana
            else return -1;
        }
        //No existe la celda  con esos parámetros
        else return -2;
    }

    /**
     * Llama al documento para hacer la mediana
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda resultado
     * @param ColumnCell columna de la celda resultado
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la mediana por un error desconocido.
     * -2 No existe la celda resultado
     */
    public int Median(String NameSheet, int RowCell, int ColumnCell) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index,ColumnCell)) {
            if (myDocument.Median(index, RowCell, ColumnCell)) return 0;
                //No se ha podido hacer la mediana
            else return -1;
        }
        //No existe la celda  con esos parámetros
        else return -2;
    }
    /**
     * Llama al documento para hacer la varianza
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda resultado
     * @param ColumnCell columna de la celda resultado
     * @param formula formula que estoy utilizando
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la varianza por un error desconocido.
     * -2 No existe la celda resultado
     */
    public int Variance(String NameSheet, int RowCell, int ColumnCell, String formula) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index,ColumnCell)) {
            if (myDocument.Variance(index, RowCell, ColumnCell, formula)) return 0;
                //No se ha podido hacer la varianza
            else return -1;
        }
        //No existe la celda  con esos parámetros
        else return -2;
    }

    /**
     * Llama al documento para hacer la varianza (funcion para el boton)
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda resultado
     * @param ColumnCell columna de la celda resultado
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la varianza por un error desconocido.
     * -2 No existe la celda resultado
     */
    public int Variance(String NameSheet, int RowCell, int ColumnCell) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index,ColumnCell)) {
            if (myDocument.Variance(index, RowCell, ColumnCell)) return 0;
                //No se ha podido hacer la varianza
            else return -1;
        }
        //No existe la celda  con esos parámetros
        else return -2;
    }

    /**
     * Llama al documento para hacer la covarianza
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda resultado
     * @param ColumnCell columna de la celda resultado
     * @param RowCellIni fila de la celda inicial del rango
     * @param ColumnCellIni columna de la celda inicial del rango
     * @param RowCellFin fila de la celda final del rango
     * @param ColumnCellFin columna de la celda final del rango
     * @param formula formula que estoy utilizando
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la covarianza por un error desconocido.
     * -2 No existe la celda resultado
     */
    public int Covariance(String NameSheet, int RowCell, int ColumnCell, int RowCellIni, int ColumnCellIni, int RowCellFin, int ColumnCellFin, String formula) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (SelectRank(NameSheet, RowCellIni, ColumnCellIni, RowCellFin, ColumnCellFin, 2) == 0) {
            if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index, ColumnCell)) {
                if (myDocument.Covariance(index, RowCell, ColumnCell, formula)) return 0;
                    //No se ha podido hacer la covarianza
                else return -1;
            }
            //No existe la celda  con esos parámetros
            else return -2;
        }
        else return -3;
    }

    /**
     * Llama al documento para hacer la covarianza (funcion para el boton)
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda resultado
     * @param ColumnCell columna de la celda resultado
     * @param RowCellIni fila de la celda inicial del rango
     * @param ColumnCellIni columna de la celda inicial del rango
     * @param RowCellFin fila de la celda final del rango
     * @param ColumnCellFin columna de la celda final del rango
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la covarianza por un error desconocido.
     * -2 No existe la celda resultado
     */
    public int Covariance(String NameSheet, int RowCell, int ColumnCell, int RowCellIni, int ColumnCellIni, int RowCellFin, int ColumnCellFin) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (SelectRank(NameSheet, RowCellIni, ColumnCellIni, RowCellFin, ColumnCellFin, 2) == 0) {
            if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index, ColumnCell)) {
                if (myDocument.Covariance(index, RowCell, ColumnCell)) return 0;
                    //No se ha podido hacer la covarianza
                else return -1;
            }
            //No existe la celda  con esos parámetros
            else return -2;
        }
        else return -3;
    }

    /**
     * Llama al documento para hacer la desviación estandard
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda resultado
     * @param ColumnCell columna de la celda resultado
     * @param formula formula que estoy utilizando
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la desviación por un error desconocido.
     * -2 No existe la celda resultado
     */
    public int StandardDeviation(String NameSheet, int RowCell, int ColumnCell, String formula) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index,ColumnCell)) {
            if (myDocument.StandardDeviation(index, RowCell, ColumnCell, formula)) return 0;
                //No se ha podido hacer la desviacion
            else return -1;
        }
        //No existe la celda  con esos parámetros
        else return -2;
    }
    /**
     * Llama al documento para hacer la desviación estandard (funcion para el boton)
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda resultado
     * @param ColumnCell columna de la celda resultado
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la desviación por un error desconocido.
     * -2 No existe la celda resultado
     */
    public int StandardDeviation(String NameSheet, int RowCell, int ColumnCell) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index,ColumnCell)) {
            if (myDocument.StandardDeviation(index, RowCell, ColumnCell)) return 0;
                //No se ha podido hacer la desviacion
            else return -1;
        }
        //No existe la celda  con esos parámetros
        else return -2;
    }
    /**
     * Llama al documento para hacer el coeficiente de correlación Pearson
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda resultado
     * @param ColumnCell columna de la celda resultado
     * @param RowCellIni fila de la celda inicial del rango
     * @param ColumnCellIni columna de la celda inicial del rango
     * @param RowCellFin fila de la celda final del rango
     * @param ColumnCellFin columna de la celda final del rango
     * @param formula formula que estoy utilizando
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer el coeficiente por un error desconocido.
     * -2 No existe la celda resultado
     */
    public int Pearson(String NameSheet, int RowCell, int ColumnCell, int RowCellIni, int ColumnCellIni, int RowCellFin, int ColumnCellFin, String formula) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (SelectRank(NameSheet, RowCellIni, ColumnCellIni, RowCellFin, ColumnCellFin, 2) == 0) {
            if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index, ColumnCell)) {
                if (myDocument.Pearson(index, RowCell, ColumnCell, formula)) return 0;
                    //No se ha podido hacer la pearson
                else return -1;
            }
            //No existe la celda  con esos parámetros
            else return -2;
        }
        //No existe el segundo rango
        else return -3;
    }
    /**
     * Llama al documento para hacer el coeficiente de correlación Pearson (funcion para el boton)
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda resultado
     * @param ColumnCell columna de la celda resultado
     * @param RowCellIni fila de la celda inicial del rango
     * @param ColumnCellIni columna de la celda inicial del rango
     * @param RowCellFin fila de la celda final del rango
     * @param ColumnCellFin columna de la celda final del rango
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer el coeficiente por un error desconocido.
     * -2 No existe la celda resultado
     */
    public int Pearson(String NameSheet, int RowCell, int ColumnCell, int RowCellIni, int ColumnCellIni, int RowCellFin, int ColumnCellFin) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (SelectRank(NameSheet, RowCellIni, ColumnCellIni, RowCellFin, ColumnCellFin, 2) == 0) {
            if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index, ColumnCell)) {
                if (myDocument.Pearson(index, RowCell, ColumnCell)) return 0;
                    //No se ha podido hacer la pearson
                else return -1;
            }
            //No existe la celda  con esos parámetros
            else return -2;
        }
        //No existe el segundo rango
        else return -3;
    }

    /**
     * Llama al documento para hacer la suma
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda resultado
     * @param ColumnCell columna de la celda
     * @param RowCell2 fila de la celda
     * @param ColumnCell2 columna de la celda
     * @param RowRes fila de la celda
     * @param ColRes columna de la celda
     * @param BoolRow boolean para saber si hay que hacer la suma en toda la fila
     * @param BoolColumn boolean para saber si hay que hacer la suma en toda la columna
     * @param formula formula que estoy utilizando
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la suma por un error desconocido.
     * -2 No existe la celda resultado
     */

    public int Sum (String NameSheet, int RowCell, int ColumnCell, int RowCell2, int ColumnCell2,  int RowRes, int ColRes, boolean BoolRow, boolean BoolColumn, String formula) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index,ColumnCell)) {
            if (myDocument.ExistsRow(index, RowCell2) && myDocument.ExistsColumn(index,ColumnCell2)) {
                if (myDocument.ExistsRow(index, RowRes) && myDocument.ExistsColumn(index, ColRes)) {
                    if (myDocument.Sum(index, RowCell, ColumnCell, RowCell2, ColumnCell2, RowRes, ColRes, BoolRow, BoolColumn,formula))
                        return 0;
                        //No se ha podido hacer la suma
                    else return -1;
                }
                //No existe la celda resultado
                else return -4;
            }
            //No existe la celda 2
            else return -3;
        }
        //No existe la celda1  con esos parámetros
        else return -2;
    }

    /**
     * Llama al documento para hacer la suma
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda resultado
     * @param ColumnCell columna de la celda
     * @param var valor variable
     * @param RowRes fila de la celda
     * @param ColRes columna de la celda
     * @param BoolRow boolean para saber si hay que hacer la suma en toda la fila
     * @param BoolColumn boolean para saber si hay que hacer la suma en toda la columna
     * @param formula formula que estoy utilizando
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la suma por un error desconocido.
     * -2 No existe la celda resultado
     */

    public int Sum (String NameSheet, int RowCell, int ColumnCell, int var, int RowRes, int ColRes, boolean BoolRow, boolean BoolColumn, String formula) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index,ColumnCell)) {
            if (myDocument.ExistsRow(index, RowRes) && myDocument.ExistsColumn(index, ColRes)) {
                if (myDocument.Sum(index, RowCell, ColumnCell, var, RowRes, ColRes, BoolRow, BoolColumn,formula))
                    return 0;
                    //No se ha podido hacer la suma
                else return -1;
            }
            //No existe la celda 2
            else return -3;
        }
        //No existe la celda1  con esos parámetros
        else return -2;
    }

    /**
     * Llama al documento para hacer la resta
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda resultado
     * @param ColumnCell columna de la celda
     * @param RowCell2 fila de la celda
     * @param ColumnCell2 columna de la celda
     * @param RowRes fila de la celda
     * @param ColRes columna de la celda
     * @param BoolRow boolean para saber si hay que hacer la operación en toda la fila
     * @param BoolColumn boolean para saber si hay que hacer la operación en toda la columna
     * @param formula formula que estoy utilizando
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la resta por un error desconocido.
     * -2 No existe la celda resultado
     */

    public int Rest(String NameSheet, int RowCell, int ColumnCell, int RowCell2, int ColumnCell2,  int RowRes, int ColRes, boolean BoolRow, boolean BoolColumn, String formula) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index,ColumnCell)) {
            if (myDocument.ExistsRow(index, RowCell2) && myDocument.ExistsColumn(index,ColumnCell2)) {
                if (myDocument.ExistsRow(index, RowRes) && myDocument.ExistsColumn(index, ColRes)) {
                    if (myDocument.Rest(index, RowCell, ColumnCell, RowCell2, ColumnCell2, RowRes, ColRes, BoolRow, BoolColumn, formula))
                        return 0;
                        //No se ha podido hacer la resta
                    else return -1;
                }
                //No existe la celda resultado
                else return -4;
            }
            //No existe la celda 2
            else return -3;
        }
        //No existe la celda1  con esos parámetros
        else return -2;
    }

    /**
     * Llama al documento para hacer la resta
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda resultado
     * @param ColumnCell columna de la celda
     * @param var valor variable
     * @param RowRes fila de la celda
     * @param ColRes columna de la celda
     * @param BoolRow boolean para saber si hay que hacer la suma en toda la fila
     * @param BoolColumn boolean para saber si hay que hacer la suma en toda la columna
     * @param formula formula que estoy utilizando
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la suma por un error desconocido.
     * -2 No existe la celda resultado
     */

    public int Rest (String NameSheet, int RowCell, int ColumnCell, int var, int RowRes, int ColRes, boolean BoolRow, boolean BoolColumn, String formula) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index,ColumnCell)) {
            if (myDocument.ExistsRow(index, RowRes) && myDocument.ExistsColumn(index, ColRes)) {
                if (myDocument.Rest(index, RowCell, ColumnCell, var, RowRes, ColRes, BoolRow, BoolColumn,formula))
                    return 0;
                    //No se ha podido hacer la suma
                else return -1;
            }
            //No existe la celda 2
            else return -3;
        }
        //No existe la celda1  con esos parámetros
        else return -2;
    }

    /**
     * Llama al documento para hacer la multiplicación
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda resultado
     * @param ColumnCell columna de la celda
     * @param RowCell2 fila de la celda
     * @param ColumnCell2 columna de la celda
     * @param RowRes fila de la celda
     * @param ColRes columna de la celda
     * @param BoolRow boolean para saber si hay que hacer la operación en toda la fila
     * @param BoolColumn boolean para saber si hay que hacer la operación en toda la columna
     * @param formula formula que estoy utilizando
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la multiplicación por un error desconocido.
     * -2 No existe la celda resultado
     */

    public int Multiplication(String NameSheet, int RowCell, int ColumnCell, int RowCell2, int ColumnCell2,  int RowRes, int ColRes, boolean BoolRow, boolean BoolColumn, String formula) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index,ColumnCell)) {
            if (myDocument.ExistsRow(index, RowCell2) && myDocument.ExistsColumn(index,ColumnCell2)) {
                if (myDocument.ExistsRow(index, RowRes) && myDocument.ExistsColumn(index, ColRes)) {
                    if (myDocument.Multiplication(index, RowCell, ColumnCell, RowCell2, ColumnCell2, RowRes, ColRes, BoolRow, BoolColumn, formula))
                        return 0;
                        //No se ha podido hacer la multiplicación
                    else return -1;
                }
                //No existe la celda resultado
                else return -4;
            }
            //No existe la celda 2
            else return -3;
        }
        //No existe la celda1  con esos parámetros
        else return -2;
    }

    /**
     * Llama al documento para hacer la multiplicación
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda resultado
     * @param ColumnCell columna de la celda
     * @param var valor variable
     * @param RowRes fila de la celda
     * @param ColRes columna de la celda
     * @param BoolRow boolean para saber si hay que hacer la suma en toda la fila
     * @param BoolColumn boolean para saber si hay que hacer la suma en toda la columna
     * @param formula formula que estoy utilizando
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la suma por un error desconocido.
     * -2 No existe la celda resultado
     */

    public int Multiplication (String NameSheet, int RowCell, int ColumnCell, int var, int RowRes, int ColRes, boolean BoolRow, boolean BoolColumn, String formula) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index,ColumnCell)) {
            if (myDocument.ExistsRow(index, RowRes) && myDocument.ExistsColumn(index, ColRes)) {
                if (myDocument.Multiplication(index, RowCell, ColumnCell, var, RowRes, ColRes, BoolRow, BoolColumn,formula))
                    return 0;
                    //No se ha podido hacer la suma
                else return -1;
            }
            //No existe la celda 2
            else return -3;
        }
        //No existe la celda1  con esos parámetros
        else return -2;
    }

    /**
     * Llama al documento para hacer la división
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda resultado
     * @param ColumnCell columna de la celda
     * @param RowCell2 fila de la celda
     * @param ColumnCell2 columna de la celda
     * @param RowRes fila de la celda
     * @param ColRes columna de la celda
     * @param BoolRow boolean para saber si hay que hacer la operación en toda la fila
     * @param BoolColumn boolean para saber si hay que hacer la operación en toda la columna
     * @param formula formula que estoy utilizando
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la división por un error desconocido.
     * -2 No existe la celda resultado
     */

    public int Division(String NameSheet, int RowCell, int ColumnCell, int RowCell2, int ColumnCell2,  int RowRes, int ColRes, boolean BoolRow, boolean BoolColumn, String formula) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index,ColumnCell)) {
            if (myDocument.ExistsRow(index, RowCell2) && myDocument.ExistsColumn(index,ColumnCell2)) {
                if (myDocument.ExistsRow(index, RowRes) && myDocument.ExistsColumn(index, ColRes)) {
                    if (myDocument.Division(index, RowCell, ColumnCell, RowCell2, ColumnCell2, RowRes, ColRes, BoolRow, BoolColumn, formula))
                        return 0;
                        //No se ha podido hacer la división
                    else return -1;
                }
                //No existe la celda resultado
                else return -4;
            }
            //No existe la celda 2
            else return -3;
        }
        //No existe la celda1  con esos parámetros
        else return -2;
    }

    /**
     * Llama al documento para hacer la división
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda resultado
     * @param ColumnCell columna de la celda
     * @param var valor variable
     * @param RowRes fila de la celda
     * @param ColRes columna de la celda
     * @param BoolRow boolean para saber si hay que hacer la suma en toda la fila
     * @param BoolColumn boolean para saber si hay que hacer la suma en toda la columna
     * @param formula formula que estoy utilizando
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la suma por un error desconocido.
     * -2 No existe la celda resultado
     */

    public int Division (String NameSheet, int RowCell, int ColumnCell, int var, int RowRes, int ColRes, boolean BoolRow, boolean BoolColumn, String formula) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index,ColumnCell)) {
            if (myDocument.ExistsRow(index, RowRes) && myDocument.ExistsColumn(index, ColRes)) {
                if (myDocument.Division(index, RowCell, ColumnCell, var, RowRes, ColRes, BoolRow, BoolColumn,formula))
                    return 0;
                    //No se ha podido hacer la suma
                else return -1;
            }
            //No existe la celda 2
            else return -3;
        }
        //No existe la celda1  con esos parámetros
        else return -2;
    }

    /**
     * Llama al documento para consultar las filas de la hoja.
     * @param NameSheet nombre de la hoja
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la media por un error desconocido.
     * -2 No existe la celda resultado
     */
    public int getSheetSize(String NameSheet) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        return myDocument.getSheetSize(index);
    }

    /**
     * Llama al documento para consultar las columnas de la hoja.
     * @param NameSheet nombre de la hoja
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la media por un error desconocido.
     * -2 No existe la celda resultado
     */
    public int getSheetColSize(String NameSheet) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        return myDocument.getSheetColSize(index);
    }

    /**
     * Carga el contenido de las hojas en la capa de presentación
     */
    public void LoadSheet() throws Exception {
        String nameDoc = myDocument.getName();
        LinkedList<String> sheets = PersController.Sheets(nameDoc);
        LinkedList<LinkedList<String>> load;
        String data;
        for (int k = 0; k < sheets.size(); k++) {
            load =  PersController.LoadSheet(nameDoc, sheets.get(k));
            int rows = load.size();
            int cols = load.get(0).size();
            int rows2 = myDocument.getSheetSize(k);
            int cols2 = myDocument.getSheetColSize(k);

                if (rows < rows2) {

                    EraseRow(sheets.get(k), rows2 - rows, 0);
                } else if (rows > rows2) {

                    AddRow(sheets.get(k), rows - rows2, 0);
                }

                if (cols < cols2) {
                    EraseColumn(sheets.get(k), cols2 - cols, 0);
                } else if (cols > cols2) {
                    AddColumn(sheets.get(k), cols - cols2, 0);
                }

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {

                    data = load.get(i).get(j);

                    int type = checkType(data);

                    if (type == 0) { //es double
                        double dob = Double.parseDouble(data);
                        ModifyDouble(sheets.get(k), i, j, dob);
                    }
                    else if (type == 1) { //es string
                        ModifyString(sheets.get(k), i, j, data);
                    }
                    else if (type == 2) { //es date
                        char num1 = data.charAt(6);
                        String num11= String.valueOf(num1);
                        char num2 = data.charAt(7);
                        String num22= String.valueOf(num2);
                        char num3 = data.charAt(8);
                        String num33= String.valueOf(num3);
                        char num4 = data.charAt(9);
                        String num44= String.valueOf(num4);
                        String numS = num11+num22+num33+num44;
                        int year = Integer.parseInt(numS);
                        num1 = data.charAt(0);
                        num11= String.valueOf(num1);
                        num2 = data.charAt(1);
                        num22= String.valueOf(num2);
                        numS = num11+num22;
                        int day = Integer.parseInt(numS);
                        num1 = data.charAt(3);
                        num11= String.valueOf(num1);
                        num2 = data.charAt(4);
                        num22= String.valueOf(num2);
                        numS = num11+num22;

                        int month = Integer.parseInt(numS);
                        ModifyDate(sheets.get(k), i, j, day, month, year);
                    }
                    else if (type == 3) { //es formula
                        PresCtrl.ModCell(sheets.get(k),i,j,data);
                    }
                }
            }

        }
    }

    /**
     * Devuelve de que tipo es el contenido de una celda
     * @param data Contenido de las celdas
     * @return devuelve el tipo de dato que es mediante un integer
     */
    public int checkType(String data) {
        if (!data.equals("")) {
            if (data.charAt(0) == '=') return 3;
            boolean num = true;
            int i = 0;
            int size = data.length();
            if (data.charAt(i) == '-') ++i;
            int first = i;
            int cont = 0;
            while (num && i < size) {

                if (data.charAt(i) == '.') {

                    ++cont;
                    if (cont > 1) num = false;
                    else {

                        if (i == first) {

                            if (size == first + 1) num = false;
                        }
                    }
                } else if (data.charAt(i) < '0' || data.charAt(i) > '9') num = false;
                ++i;
            }
            if (num) return 0;
            if (isDate(data)) return 2;
            return 1;

        }
        return -1;
    }

    /**
     * Devuelve si el contenido de una celda es una fecha
     * @param data Contenido de una celda
     * @return true si es una fecha y devuelve false si no es una fecha
     */
    public boolean isDate(String data) {

        if (data.length() != 10) return false;
        if (data.charAt(2) != '/' || data.charAt(5) != '/') return false;


        if (data.charAt(0) < '0' || data.charAt(1) < '0' || data.charAt(3) < '0' || data.charAt(4) < '0'
                || data.charAt(6) < '0' || data.charAt(7) < '0' || data.charAt(8) < '0' || data.charAt(9) < '0'
                || data.charAt(0) > '9' || data.charAt(1) > '9' || data.charAt(3) > '9' || data.charAt(4) > '9'
                || data.charAt(6) > '9' || data.charAt(7) > '9' || data.charAt(8) > '9' || data.charAt(9) > '9') return false;

        char num1 = data.charAt(6);
        String num11= String.valueOf(num1);

        char num2 = data.charAt(7);
        String num22= String.valueOf(num2);

        char num3 = data.charAt(8);
        String num33= String.valueOf(num3);

        char num4 = data.charAt(9);
        String num44= String.valueOf(num4);

        String numS = num11+num22+num33+num44;

        int num = Integer.parseInt(numS);
        if (num < 1900 || num > 3000) return false;
        boolean bisiesto = num % 4 == 0;
        num1 = data.charAt(3);
        num11= String.valueOf(num1);
        num2 = data.charAt(4);
        num22= String.valueOf(num2);
        numS = num11+num22;
        num = Integer.parseInt(numS);

        if (num < 1 || num > 12) return false;
        if (num == 1 || num == 3 || num == 5 || num == 7 || num == 8 || num == 10 || num == 12) {

            num1 = data.charAt(0);
            num11= String.valueOf(num1);
            num2 = data.charAt(1);
            num22= String.valueOf(num2);
            numS = num11+num22;
            num = Integer.parseInt(numS);
            return num >= 1 && num <= 31;
        }
        else if (num == 2) {

            num1 = data.charAt(0);
            num11= String.valueOf(num1);
            num2 = data.charAt(1);
            num22= String.valueOf(num2);
            numS = num11+num22;
            num = Integer.parseInt(numS);
            if (bisiesto) {

                return num >= 1 && num <= 29;
            }
            else {

                return num >= 1 && num <= 28;
            }
        }
        else {

            num1 = data.charAt(0);
            num11= String.valueOf(num1);
            num2 = data.charAt(1);
            num22= String.valueOf(num2);
            numS = num11+num22;
            num = Integer.parseInt(numS);
            return num >= 1 && num <= 30;
        }
    }

    /**
     * Guarda en documento con el que estamos trabajando
     */
    public void Save() throws IOException {

        //Guardar las hojas
        String nameDoc = myDocument.getName();
        Vector<String> sheets = getSheetList();
        for (int i = 0; i < sheets.size(); ++i) {
            String[][] table = getRawTable(sheets.get(i), true);
            for(int j = 0; j < table.length; ++j) {
                System.out.print(Arrays.toString(table[j]));
            }
            PersController.SaveSheets(nameDoc, sheets.get(i), table);
        }

        PersController.newFile();
        //actualizar el "index.csv"
        LinkedList<String> nameDocs = DocDomCtrl.NamesDocs();

        boolean change = false;
        boolean find = false;

        for (int i = 0; i < nameDocs.size()-1; ++i) {

            char[] n = nameDocs.get(i).toCharArray();
            char[] m = nameDoc.toCharArray();
            String name1 = "";
            String name2 = "";

            for (int it = 0; it < n.length; ++it) {
                if ((n[it] >= 'a' && n[it] <= 'z') || (n[it] >= 'A' && n[it] <= 'Z')) {
                    name1 += n[it];
                }
            }

            for (int it = 0; it < m.length; ++it) {
                if ((m[it] >= 'a' && m[it] <= 'z') || (m[it] >= 'A' && m[it] <= 'Z')) {
                    name2 += m[it];
                }
            }

            if (name1.equals(name2)) {
                LinkedList<String> aux = PersController.Sheets(name2);
                if (aux.size() == sheets.size()) {
                    for (int j = 0; j < aux.size() && !change; ++j) {
                        if (aux.get(j) != sheets.get(j)) change = true;
                    }
                } else {
                    change = true;
                }
                find = true;
            }

            else {
                LinkedList<String> aux = PersController.Sheets(name1);
                Vector<String> aux2 = new Vector<String>();
                for (int j = 0; j < aux.size(); ++j) {
                    aux2.add(aux.get(j));
                }
                PersController.SaveInIndex(name1, aux2);
            }
        }
        if (!find || change) PersController.SaveInIndex(nameDoc, sheets);
        PersController.deleteOldFile();
    }

    /**
     * Devuelve el contenido de una hoja para mandarlo a la capa de persistencia
     * @param sheetName Nombre de la hoja
     * @param showFormulas 1 para ver las formulas, 0 para ver el resultado de las formulas
     * @return devuelve la tabla de una hoja en formato string
     */
    public String[][] getRawTable (String sheetName, Boolean showFormulas){
        return myDocument.getRawTable(sheetName, showFormulas);
    }

    /**
     * Consulta el contenido de una celda en una hoja concreta
     * @param sheetName nombre de la hoja
     * @param row fila de la celda que queremos consultar
     * @param col columna de la celda que queremos consultar
     * @return el contenido de la celda
     */
    public String getRawContent(String sheetName, int row, int col){
        return myDocument.getRawContent(sheetName,row, col);
    }

    /**
     * Consulta una celda en una hoja
     * @param sheetName nombre de la hoja
     * @param row fila de la celda que queremos consultar
     * @param col columna de la celda que queremos consultar
     * @return Devuelve la celda que buscabamos
     */
    public Cell getCell(String sheetName, int row, int col) {
        return myDocument.getCell(sheetName,row, col);
    }
}