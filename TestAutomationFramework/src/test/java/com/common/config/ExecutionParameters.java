package com.common.config;
import org.testng.ITestContext;
import org.testng.Reporter;

import java.lang.reflect.Field;

public class ExecutionParameters {


	public String testurl;
	public String chromeDriverPath;
	public String ffDriverPath;
	public String ieDriverPath;
	public String browserType;
	public String inputDataFile;
	public String sheetName;
	public String dataObject;
	public String dataReaderType;
	private ITestContext testContext;


	public ExecutionParameters(ITestContext context){
       this.testContext = context;
	   assignFieldValues();
	   System.out.println("new testing");
	}


	private void assignFieldValues(){
		Field[] allFields=this.getClass().getDeclaredFields();
		for(Field field:allFields){
			try{

				if(field.getType()!=String.class)
					continue;
				field.setAccessible(true);
				field.set(this,this.testContext.getCurrentXmlTest().getParameter(field.getName()) );
			}
			catch(Throwable e){
				throw new RuntimeException("ExecutionParameters:assignFieldValues():Error assigning value for the field "+field.getName());
			}
		}

	}
	


}
