package com.open.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.common.config.ExecutionParameters;
import com.common.context.ExecutionContext;
import com.common.datareader.ITestDataReader;
import com.common.datareader.TestDataReaderException;
import com.common.datareader.TestReaderFactory;
import com.common.seleniumlib.CustomPageFactory;
import com.common.seleniumlib.DriverProvider;
import com.open.dataobjects.AllKriDataObjects;
import com.open.dataobjects.BaseDataObject;
import com.open.dataobjects.LoginDataObjects;
import com.open.pages.LoginPage;



public class BaseTest {

    @BeforeSuite
    public void beforeSuite(ITestContext context){
        try {
			ExecutionParameters testParameters = new ExecutionParameters(context);
			ExecutionContext.initialize( new DriverProvider(testParameters).getDriver(),testParameters);
       	} catch(Throwable e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	
    }

    @BeforeMethod
    public void doLogout(){
    	LoginPage login=getPageObject(LoginPage.class);
		login.logout();
    }
    
    @AfterSuite
    public void afterSuite(){
    	ExecutionContext.getInstance().getWebdriver().quit();
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getPageObject(Class<T> pageObjectClass){
    	Object page = null;
		try {
			page = pageObjectClass.getConstructor(WebDriver.class).newInstance(ExecutionContext.getInstance().getWebdriver());
			 Method method = page.getClass().getMethod("waitForPageLoad");
			 method.invoke(page);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	CustomPageFactory.initElements(ExecutionContext.getInstance().getWebdriver(), page);
    	return (T)page;
    	
    }
    
    @SuppressWarnings("unchecked")
	@DataProvider(name="readFromDataSource")
    public Iterator<Object[]> createData(ITestContext context, Method methodObj) throws Throwable{

		ITestDataReader sReader = new TestReaderFactory().getDataReaderInstance();
    	//Read Input from Excel File into an Object of type Iterator
    	Collection<Object[]> columnValuesFromExcel = new ArrayList<Object[]>();
    	List<BaseDataObject> results=new ArrayList();

    	try{
    		results=sReader.getTestDataForTestMethod(getDataObjectType(),methodObj.getName());

    	}catch(Throwable e){
    			throw new TestDataReaderException("BaseTest.createData():Error reading test data for test method "+methodObj.getName(),e);
    	}
    	
    		for(BaseDataObject result : results){
    			columnValuesFromExcel.add(new Object[]{result});
    		}
    		return columnValuesFromExcel.iterator();
    	} 
    

    //Can be converted to  Enum when we have many data objects created
    private Class getDataObjectType(){
    	if(ExecutionContext.getInstance().getTestParameters().dataObject.equalsIgnoreCase("KRIDataObject")){
    		return AllKriDataObjects.class;
    	}else if(ExecutionContext.getInstance().getTestParameters().dataObject.equalsIgnoreCase("LoginDataObject")){
    		return LoginDataObjects.class;
    	}else{
    		throw new RuntimeException("Expected Data Object not found" +ExecutionContext.getInstance().getTestParameters().dataObject);
    	}
    }
    			
}

