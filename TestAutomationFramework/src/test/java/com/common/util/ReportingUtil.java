package com.common.util;

import org.testng.Reporter;

public class ReportingUtil {
	
	public static void logWithLineBreaks(String[] logContents){
		for(String logContent: logContents){
			Reporter.log(logContent);
		}
		
	}

}
