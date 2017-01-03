package com.open.dataobjects;

import java.lang.reflect.Field;

import org.testng.Reporter;

public class BaseDataObject {
	public String TestCaseName;
	public String TestCaseDescription;
	
	public void reportData(Boolean... logTestDetails ){
		if(logTestDetails==null || logTestDetails.length==0){
		Reporter.log("TestCaseName :- " + TestCaseName);
		Reporter.log("TestCaseDescription :- " + TestCaseDescription);
		}
		Field[] allFields=this.getClass().getDeclaredFields();
		for(Field field:allFields){
			try {
				  if(field.getType().getSuperclass().getSimpleName().equals("BaseDataObject")){
					  
					((BaseDataObject)field.get(this)).reportData(false);
				}
				else{
					if(field.get(this)!=null)
						Reporter.log(field.getName()+" : "+field.get(this));
				}
				
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}

}
