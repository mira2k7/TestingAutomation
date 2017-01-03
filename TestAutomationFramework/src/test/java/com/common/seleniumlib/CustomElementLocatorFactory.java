package com.common.seleniumlib;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

public final class CustomElementLocatorFactory implements ElementLocatorFactory {
  private final SearchContext searchContext;

  public CustomElementLocatorFactory(SearchContext searchContext) {
    this.searchContext = searchContext;
  }

  public ElementLocator createLocator(Field field) {
    return new CustomElementLocator(searchContext, field);
  }
}
