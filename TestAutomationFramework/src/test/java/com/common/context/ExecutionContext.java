package com.common.context;


import com.common.config.ExecutionParameters;
import org.openqa.selenium.WebDriver;

public class ExecutionContext {

        private static ExecutionContext instance = null;
        private ExecutionParameters testParameters;
        private WebDriver webdriver;

        private ExecutionContext(WebDriver driver,ExecutionParameters parameters) {
            this.testParameters = parameters;
            this.webdriver=driver;
        }

        public static void initialize(WebDriver driver,ExecutionParameters parameters) {
            if(instance==null) {
                instance = new ExecutionContext(driver,parameters);
                
            }
        }

        public static ExecutionContext getInstance() {
            return instance;
        }

        public WebDriver getWebdriver(){
            return webdriver;
        }

        public void setTestParameters(ExecutionParameters parameters){
            this.testParameters = parameters;
        }

        public ExecutionParameters getTestParameters(){
            return testParameters;
        }






}
