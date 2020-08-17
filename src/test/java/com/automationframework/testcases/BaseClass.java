package com.automationframework.testcases;


import com.automationframework.utilities.ReadConfig;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    ReadConfig readConfig = new ReadConfig();
    public String baseURL = readConfig.getApplicationUrl();
    public String username =readConfig.getUsername();
    public String
    password =readConfig.getPassword();
    public static WebDriver driver;
    public static Logger logger;

    @Parameters("browser")
    @BeforeClass
    public void setup(String br){
        if (br.equals("chrome")){
            // Create object of HashMap Class
            Map<String, Object> prefs = new HashMap<String, Object>();

            // Set the notification setting it will override the default setting
            prefs.put("profile.default_content_setting_values.notifications", 2);

            // Create object of ChromeOption class
            ChromeOptions options = new ChromeOptions();

            // Set the experimental option
            options.setExperimentalOption("prefs", prefs);

            System.setProperty("webdriver.chrome.driver",readConfig.getChromePath());
            driver = new ChromeDriver(options);
        }
        else if (br.equals("firefox")){
            System.setProperty("webdriver.gecko.driver",readConfig.getFireFoxPath());
            driver = new FirefoxDriver();
        }
        else if (br.equals("edge")){
            System.setProperty("webdriver.edge.driver",readConfig.getEdgePath());
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseURL);
        driver.manage().window().maximize();
        logger = Logger.getLogger("ebanking");
        PropertyConfigurator.configure("log4j.properties");
    }

    public void captureScreen(WebDriver driver, String tname) throws IOException {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE); // capture screenshot file
            File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");

            FileUtils.copyFile(source, target);
            System.out.println("screenshot captured");
    }

    boolean isAlertPresent(){
        try{
            driver.switchTo().alert();
            return true;
        }catch (NoAlertPresentException e){
            return false;
        }
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
