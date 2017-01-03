package com.common.seleniumlib;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class CustomLocatingElementListHandler implements InvocationHandler {
  private final ElementLocator locator;

  public CustomLocatingElementListHandler(ElementLocator locator) {
    this.locator = locator;
  }

  public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
	  
	for(int i=0;i<10;i++){
		if(locator.findElements().size()>0)
			break;
		try{Thread.sleep(1000);}catch(Throwable e){}
	}
    List<WebElement> elements = locator.findElements();

    try {
      return method.invoke(elements, objects);
    } catch (InvocationTargetException e) {
      // Unwrap the underlying exception
      throw e.getCause();
    }
  }
}
