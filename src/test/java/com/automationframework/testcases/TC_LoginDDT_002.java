package com.automationframework.testcases;

import com.automationframework.pageobjects.LoginPage;
import com.automationframework.utilities.XLUtils;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;


public class TC_LoginDDT_002 extends BaseClass {

    @Test(dataProvider = "LoginData")
    public void loginDDT(String user, String pwd) throws InterruptedException, IOException {
        LoginPage lp = new LoginPage(driver);
        driver.navigate().refresh();
        lp.setUsername(user);
        logger.info("Entered username");
        lp.setPassword(pwd);
        logger.info("Entered password");
        lp.clickSubmit();
        Thread.sleep(2000);
        // verify if login is successful
        if(driver.getTitle().equals("Swag Labs")&& driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html")) {
            Assert.assertTrue(true);
            logger.info("Login passed");
            Thread.sleep(2000);
            // Logout
            lp.clickOpenMenu();
            lp.clickLogout();
        }
        else {
            captureScreen(driver,"loginTest");
            Assert.assertTrue(false);
            logger.info("Login failed - Invalid username or password");

        }



    }


    @DataProvider(name = "LoginData")
    String[][] getData() throws IOException {
        String path = System.getProperty("user.dir")+"/src/test/java/com/automationframework/testdata/LoginData.xlsx";
        int rownum = XLUtils.getRowCount(path,"Sheet1");
        int colcount = XLUtils.getCellCount(path,"Sheet1",1);
        String logindata[][] = new String[rownum][colcount];
        for (int i=1;i<=rownum;i++)
        {
            for (int j=0; j<colcount;j++){
                logindata[i-1][j]= XLUtils.getCellData(path,"Sheet1",i,j);
            }
        }
        return logindata;
    }
}
