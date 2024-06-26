package utils;


import io.cucumber.datatable.DataTable;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class DataDrivenExcel {

    public static Map<String, String> leerExcel(Excel excel) {
        Map<String, String> datosExcel = new HashMap<>();
        DecimalFormat df = new DecimalFormat("0.###");
        try {
            FileInputStream arcExcel = new FileInputStream(new File(excel.getRutaExcel()));
            Workbook libroExcel = new XSSFWorkbook(arcExcel);
            Sheet hojaArcExcel = libroExcel.getSheet(excel.getHojaExcel());
            Iterator<Row> iterator = hojaArcExcel.iterator();
            ArrayList<String> cabeceras = new ArrayList<String>();
            while (iterator.hasNext()) {
                Row filaActual = iterator.next();
                Iterator<Cell> iteratorCelda = filaActual.iterator();
                int numFila = filaActual.getRowNum();
                if ((excel.isContieneCabecera() && numFila == 0) || numFila == excel.getFilaLeer()) {
                    while (iteratorCelda.hasNext()) {
                        Cell celdaActual = iteratorCelda.next();
                        String valorCelda;
                        switch (celdaActual.getCellType()) {
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(celdaActual)) {
                                    valorCelda = "" + celdaActual.getDateCellValue().getTime();
                                } else {
                                    valorCelda = df.format(celdaActual.getNumericCellValue());
                                }
                                break;
                            default:
                                valorCelda = celdaActual.getStringCellValue();
                                break;
                        }
                        if (excel.isContieneCabecera()) {
                            if (numFila == 0) {
                                cabeceras.add(valorCelda);
                            } else {
                                datosExcel.put(cabeceras.get(celdaActual.getColumnIndex()), valorCelda);
                            }
                        } else {
                            if (numFila == excel.getFilaLeer()) {
                                datosExcel.put("" + celdaActual.getColumnIndex() + "", valorCelda);
                            }
                        }
                    }
                }
            }
            libroExcel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datosExcel;
    }

    public static Excel deHoja(DataTable dataExcel, int row){
        List<Map<String, String>> dataFeature = dataExcel.asMaps(String.class, String.class);
        Excel excel = new Excel(dataFeature.get(0).get("Ruta Excel"), dataFeature.get(0).get("Pestana"), true, row);
        return excel;
    }

    public DataDrivenExcel() {
    }
}
