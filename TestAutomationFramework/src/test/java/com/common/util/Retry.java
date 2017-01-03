package com.common.util;

import com.google.common.base.Supplier;

public class Retry {
	int numberOfAttempts;
	public Retry(int numberOfAttempts){
		this.numberOfAttempts=numberOfAttempts;
		
			
	}
	
	public void untilNoException(Runnable function){
		int retryCounter=0;
		while(retryCounter<numberOfAttempts){
			retryCounter++;
			try {
				function.run();
				break;
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				if(retryCounter>=numberOfAttempts){
				e.printStackTrace();
				}
				shortSleep();
				System.out.println("Retrying");
			}
		}
	
	}
	
	private void shortSleep(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
