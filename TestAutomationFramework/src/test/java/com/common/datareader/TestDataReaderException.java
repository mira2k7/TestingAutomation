package com.common.datareader;


public class TestDataReaderException extends  RuntimeException{

    public TestDataReaderException(String msg,Throwable cause){
        super(msg,cause);
    }

    public TestDataReaderException(String msg){
        super(msg);
    }


}
