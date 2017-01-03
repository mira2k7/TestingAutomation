package com.common.datareader;


import com.common.config.ExecutionParameters;
import com.common.context.ExecutionContext;

public class TestReaderFactory {

     public ITestDataReader getDataReaderInstance(){
         ExecutionParameters parameters = ExecutionContext.getInstance().getTestParameters();
         if(parameters.dataReaderType.contains("Excel") || parameters.dataReaderType.contains("excel")){
             return new ExcelSheetReader();
         }
         return new ExcelSheetReader();
     }

}
