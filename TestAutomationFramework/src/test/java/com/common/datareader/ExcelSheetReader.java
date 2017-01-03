package com.common.datareader;

import com.common.config.ExecutionParameters;
import com.common.context.ExecutionContext;
import com.open.dataobjects.BaseDataObject;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.*;


public class ExcelSheetReader implements ITestDataReader {


    private HSSFSheet workSheet;
    private HSSFWorkbook workBook;
    private HashMap<String, Integer> columnIndexes = new HashMap<String, Integer>();
    private FileInputStream fileInputStream;
    private String fileName;
    private String sheetName;
    private ExecutionParameters parameters;

    public ExcelSheetReader() {
        this.parameters = ExecutionContext.getInstance().getTestParameters();
        this.fileName = parameters.inputDataFile;
        this.sheetName = parameters.sheetName;
        initialiseWorkbookAndWorksheet();
        setColumnIndexes();
    }



    private void setCellValue(int rowIndex,Field fieldToSet,Object classToFillInstance){
       try {
           int colIndex = columnIndexes.get(fieldToSet.getName());
           if (workSheet.getRow(rowIndex).getCell(colIndex).getCellType() == Cell.CELL_TYPE_NUMERIC) {
               fieldToSet.set(classToFillInstance, Double.toString(workSheet.getRow(rowIndex).getCell(colIndex).getNumericCellValue()));
           } else if (workSheet.getRow(rowIndex).getCell(colIndex).getCellType() == Cell.CELL_TYPE_STRING) {
               fieldToSet.set(classToFillInstance, workSheet.getRow(rowIndex).getCell(colIndex).getStringCellValue());
           } else if (workSheet.getRow(rowIndex).getCell(colIndex).getCellType() == Cell.CELL_TYPE_BLANK) {
               fieldToSet.set(classToFillInstance, null);
           }
       }catch (Throwable e){
          //Making the data assignment flexible
          //Log warning saying the data is not found or some exception for the field namd and classToFill Instance Alss Name abd rowIndex
       }
    }

    private List<Field> getAllFields(Class<BaseDataObject> classToFill){
        Field[] dataFields = classToFill.getDeclaredFields();
        Field[] superClassFields = classToFill.getSuperclass().getDeclaredFields();
        List<Field> allFieldsAsList = new ArrayList<Field>(dataFields.length + superClassFields.length);
        Collections.addAll(allFieldsAsList, superClassFields);
        Collections.addAll(allFieldsAsList, dataFields);
        return allFieldsAsList;
    }


    private boolean getExecutionStatus(int row){

        try{
            return workSheet.getRow(row).getCell(columnIndexes.get("runFlag")).getStringCellValue().equalsIgnoreCase("Y");
        }catch(Throwable e){
           //Log the message here to inform that the row does not have execution status or execution column
           return false;
        }

    }


    //We could use Recursive Functions
    public List<BaseDataObject> getTestDataForTestMethod(Class<BaseDataObject> classToFill,String testMethodName) {
        List<BaseDataObject> resultObjects = new ArrayList();
        //Get Row Index and store as a List
        List<Integer> rowIndices = getRowIndexForTestMethodName(testMethodName);
        if (rowIndices == null || rowIndices.isEmpty())
            throw new TestDataReaderException("ExcelSheetReader.getTestDataForTestMethod():Test data Rows not specified in the excelsheet for the testMethod" + testMethodName);

        for (Integer rowIndex : rowIndices) {
              if(!getExecutionStatus(rowIndex))
                  continue;
            try {
                Object classToFillInstance = classToFill.newInstance();
                List<Field> allFieldsAsList = getAllFields(classToFill);
                for (Field field : allFieldsAsList) {
                    try {
                        field.setAccessible(true);
                        if(field.getType().getSuperclass().getSimpleName().equals("BaseDataObject")){
                            Object subClassToFillInstance = field.getType().newInstance();
                            List<Field> allSubClassFieldsAsList = getAllFields((Class<BaseDataObject>)field.getType());
                            for (Field subClassField : allSubClassFieldsAsList) {
                                field.setAccessible(true);
                                setCellValue(rowIndex,subClassField,subClassToFillInstance);
                            }
                          field.set(classToFillInstance,subClassToFillInstance);
                        }else{
                            setCellValue(rowIndex,field,classToFillInstance);
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                        //Not handling exception here as we are making the data reader flexible for time being.
                    }
                }
                resultObjects.add((BaseDataObject) classToFillInstance);
            } catch (Throwable e) {
                throw new TestDataReaderException("ExcelSheetReader.getTestDataForTestMethod():Error Retrieving Test Data for the Test Method Named - " + testMethodName, e);
            }
        }
        return resultObjects;
    }

    public void validateDataReader() {
        if (!fileName.contains("xlsx") || !fileName.contains("xls")) {
            throw new TestDataReaderException("ExcelSheetReader.validateDataReader(): Input File to an Excel Reader must be an excel file.");
        }
        if (!new File(fileName).exists())
            throw new TestDataReaderException("ExcelSheetReader.validateDataReader(): Input File doesn't exist with location" + new File(fileName).getAbsolutePath());
        if (sheetName == null || sheetName.trim().length() < 1)
            throw new TestDataReaderException("ExcelSheetReader.validateDataReader(): Sheet Name must be specified " + new File(fileName).getAbsolutePath());

    }

    private void setColumnIndexes() {
        HSSFRow colHeader = workSheet.getRow(0);
        Iterator<Cell> cellIterator = colHeader.cellIterator();
        int cellIndex = 0;
        while (cellIterator.hasNext()) {
            columnIndexes.put(cellIterator.next().getStringCellValue(), cellIndex++);
        }
    }


    private List<Integer> getRowIndexForTestMethodName(String testName) {

        Iterator<Row> rows = workSheet.rowIterator();
        int rowIndex = 0;
        List<Integer> rowIndexes = new ArrayList<Integer>();
        while (rows.hasNext()) {
            HSSFRow row = (HSSFRow) rows.next();
            String testCaseName =  workSheet.getRow(rowIndex).getCell(columnIndexes.get("TestCaseName")).getStringCellValue() ;
            if (testCaseName.equalsIgnoreCase(testName)) {
                rowIndexes.add(rowIndex);
            }
            rowIndex += 1;
        }
        return rowIndexes;
    }

    private void initialiseWorkbookAndWorksheet() {
        fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(fileName);
            workBook = new HSSFWorkbook(fileInputStream);
            workSheet = workBook.getSheet(sheetName);
        } catch (Throwable e) {
            Reporter.log("Error in");
            throw new RuntimeException(e);
        }
    }

}
