package DomainControllers;

import Structure.*;

import java.util.*;
/**
 * Controlador de modificación de la capa dominio.
 * Contiene el documento y el controlador del dominio de documentos
 * @author Sandra Buitrago Cervilla
 */
public class ModifyDomainCtrl {
    private Document myDocument;
    private DocumentDomainCtrl DocDomCtrl;

    /**
     * Crea la instancia del controlador de modificación de la capa de dominio y la inicializa
     * @param docDomCtrl Controlador de dominio de documentos
     */
    public ModifyDomainCtrl(DocumentDomainCtrl docDomCtrl) {
        DocDomCtrl = docDomCtrl;
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
    public int EraseSheet(String NameSheet) {
        if(myDocument.removeSheetByName(NameSheet)) return 0;
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
     * Llama al documento para comprobar si existe la celda
     * @param NameSheet nombre de la hoja
     * @param RowCell fila de la celda
     * @param ColumnCell columna de la celda
     * @return 0 si se ha comprobado correctamente la existencia de la celda. -1 si no existe ninguna hoja con el nombre NameSheet.
     * -2 la celda no existe
     */
    public int ExistsCell(String NameSheet, int RowCell, int ColumnCell) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (index != -1) {
            if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index,ColumnCell)) return 0;
            //No existe la celda inicial con esos parámetros
            else return -2;
        }
        //No hay ninguna hoja con el nombre NameSheet
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
     * Llama al documento para truncar el contenido de una fila o columna o una celda
     * @param NameSheet nombre de la hoja
     * @param Row fila de la celda
     * @param Col columna de la celda
     * @param TruncRow true si se trunca la fila entera, false no se trunca la fila
     * @param TruncColumn true trunca la columna entera, false no trunca la columna
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido truncar por un error desconocido
     */
    public int Truncate(String NameSheet, int Row, int Col, boolean TruncRow,  boolean TruncColumn) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if(myDocument.Truncate(index,Row,Col,TruncRow,TruncColumn)) return 0;
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
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la conversión por un error desconocido
     */
    public int Conversion(String NameSheet, int Row, int Col, boolean ConvRow,  boolean ConvColumn, String Criterium) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if(myDocument.Conversion(index,Row,Col,ConvRow,ConvColumn, Criterium)) return 0;
            //No se ha podido hacer la conversión
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
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la extracción por un error desconocido
     */
    public int ExtractElement(String NameSheet, int Row, int Col, boolean ExtractRow,  boolean ExtractColumn, String Criterium) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if(myDocument.ExtractElement(index,Row,Col,ExtractRow,ExtractColumn, Criterium)) return 0;
            //No se ha podido hacer la extracción de elementos.
        else return -1;
    }
    /**
     * Llama al documento para poner el dia de la semana en una fila o columna o una celda
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
     * Llama al documento para reemplazar el contenido de una celda o varias celdas con alguna palabra
     * @param NameSheet nombre de la hoja
     * @param ElementToReplace String que hay que cambiar
     * @param ElementReplaced String que hay que poner en la celda
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la modificación por un error desconocido
     */
    public int Replace(String NameSheet, String ElementToReplace,  String ElementReplaced) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if(myDocument.Replace(index,ElementToReplace,ElementReplaced)) return 0;
            //No se ha podido poner la longitud del contenido.
        else return -1;
    }
    /**
     * Llama al documento para buscar el contenido de una celda o varias celdas con alguna palabra
     * @param NameSheet nombre de la hoja
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la modificación por un error desconocido
     */
    public int Search(String NameSheet, String SearchS) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if(myDocument.Search(index,SearchS)) return 0;
            //No se ha podido poner la longitud del contenido.
        else return -1;
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
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la suma por un error desconocido.
     * -2 No existe la celda resultado
     */

    public int Sum (String NameSheet, int RowCell, int ColumnCell, int RowCell2, int ColumnCell2,  int RowRes, int ColRes, boolean BoolRow, boolean BoolColumn) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index,ColumnCell)) {
            if (myDocument.ExistsRow(index, RowCell2) && myDocument.ExistsColumn(index,ColumnCell2)) {
                if (myDocument.ExistsRow(index, RowRes) && myDocument.ExistsColumn(index, ColRes)) {
                    if (myDocument.Sum(index, RowCell, ColumnCell, RowCell2, ColumnCell2, RowRes, ColRes, BoolRow, BoolColumn))
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
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la resta por un error desconocido.
     * -2 No existe la celda resultado
     */

    public int Rest(String NameSheet, int RowCell, int ColumnCell, int RowCell2, int ColumnCell2,  int RowRes, int ColRes, boolean BoolRow, boolean BoolColumn) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index,ColumnCell)) {
            if (myDocument.ExistsRow(index, RowCell2) && myDocument.ExistsColumn(index,ColumnCell2)) {
                if (myDocument.ExistsRow(index, RowRes) && myDocument.ExistsColumn(index, ColRes)) {
                    if (myDocument.Rest(index, RowCell, ColumnCell, RowCell2, ColumnCell2, RowRes, ColRes, BoolRow, BoolColumn))
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
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la multiplicación por un error desconocido.
     * -2 No existe la celda resultado
     */

    public int Multiplication(String NameSheet, int RowCell, int ColumnCell, int RowCell2, int ColumnCell2,  int RowRes, int ColRes, boolean BoolRow, boolean BoolColumn) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index,ColumnCell)) {
            if (myDocument.ExistsRow(index, RowCell2) && myDocument.ExistsColumn(index,ColumnCell2)) {
                if (myDocument.ExistsRow(index, RowRes) && myDocument.ExistsColumn(index, ColRes)) {
                    if (myDocument.Multiplication(index, RowCell, ColumnCell, RowCell2, ColumnCell2, RowRes, ColRes, BoolRow, BoolColumn))
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
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la división por un error desconocido.
     * -2 No existe la celda resultado
     */

    public int Division(String NameSheet, int RowCell, int ColumnCell, int RowCell2, int ColumnCell2,  int RowRes, int ColRes, boolean BoolRow, boolean BoolColumn) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        if (myDocument.ExistsRow(index, RowCell) && myDocument.ExistsColumn(index,ColumnCell)) {
            if (myDocument.ExistsRow(index, RowCell2) && myDocument.ExistsColumn(index,ColumnCell2)) {
                if (myDocument.ExistsRow(index, RowRes) && myDocument.ExistsColumn(index, ColRes)) {
                    if (myDocument.Division(index, RowCell, ColumnCell, RowCell2, ColumnCell2, RowRes, ColRes, BoolRow, BoolColumn))
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
     * Llama al documento para imprimir el contenido de las celdas.
     * @param NameSheet nombre de la hoja
     * @return 0 si se ha ejecutado correctamente. -1 si no se ha podido hacer la media por un error desconocido.
     * -2 No existe la celda resultado
     */
    public Vector<String> Print (String NameSheet) {
        int index = myDocument.GetIndexSheetList(NameSheet);
        Vector<String> Cells = new Vector<>();
        if (index != -1) {
            Cells = myDocument.Print(index);
            return Cells;
        }
        return Cells;
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

}
