package com.common.datareader;


import java.util.List;

import com.open.dataobjects.BaseDataObject;

public interface ITestDataReader {
     List<BaseDataObject> getTestDataForTestMethod(Class<BaseDataObject> classToFill, String testName);
     void validateDataReader();
}



