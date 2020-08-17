package com.automationframework.testcases;

import com.automationframework.pageobjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_LoginTest_001 extends BaseClass{

    @Test
    public void loginTest() throws IOException, InterruptedException {
        logger.info("Url is opened");
        LoginPage lp = new LoginPage(driver);

        lp.setUsername(username);
        logger.info("Entered username");
        lp.setPassword(password);
        logger.info("Entered password");
        lp.clickSubmit();
        // verify if login is successful
        if(driver.getTitle().equals("Swag Labs")) {
            Assert.assertTrue(true);
            logger.info("Test Case passed");
        }
        else {
            captureScreen(driver,"loginTest");
            Assert.assertTrue(false);
            logger.info("Test Case failed");
        }
    }
}
