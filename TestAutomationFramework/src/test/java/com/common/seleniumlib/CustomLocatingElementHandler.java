package com.common.seleniumlib;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import com.common.util.Retry;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CustomLocatingElementHandler implements InvocationHandler {
  private final ElementLocator locator;

  public CustomLocatingElementHandler(ElementLocator locator) {
    this.locator = locator;
  }

  public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
    WebElement element;
    try {
      new Retry(5).untilNoException(new Runnable(){  
    	  public void run()
    	  {
    		  locator.findElement();}
    	  }
    	  );
      element = locator.findElement();
    } catch (NoSuchElementException e) {
      if ("toString".equals(method.getName())) {
        return "Proxy element for: " + locator.toString();
      }
      else throw e;
    }

    if ("getWrappedElement".equals(method.getName())) {
      return element;
    }

    
      Object result=null;
      for(int i=0;i<5;i++){
    	  
      try {
		result=method.invoke(element, objects);
		break;
	} catch (Throwable e) {
		
		if(i>=5)
			throw e.getCause();
		// TODO Auto-generated catch block
		try {
			Thread.sleep(1000);
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
      }
      return result;
    
  }
  
  public By getElementLocator(){
	  return ((CustomElementLocator)this.locator).getByObject();
	  
  }
}

