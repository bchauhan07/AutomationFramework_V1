package com.automationframework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver ldriver;
    public LoginPage(WebDriver rdriver){
        ldriver= rdriver;
        PageFactory.initElements(rdriver,this);
    }

    // Element Locators
    @FindBy(id ="user-name")
    WebElement txtUserName;

    @FindBy(id ="password")
    @CacheLookup
    WebElement txtPassword;

    @FindBy(id ="login-button")
    WebElement btnLogin;

    @FindBy(xpath ="//div[@class='bm-burger-button']/button")
    @CacheLookup
    WebElement btnOpenMenu;

    @FindBy(xpath ="//a[@id='logout_sidebar_link']")
    WebElement lnkLogoutButton;


    // setter functions
    public void setUsername(String uname){
        txtUserName.sendKeys(uname);
    }

    public void setPassword(String pwd){
        txtPassword.sendKeys(pwd);
    }

    public void clickSubmit(){
        btnLogin.click();
    }
    public void clickLogout(){
        lnkLogoutButton.click();
    }
    public void clickOpenMenu(){
        btnOpenMenu.click();
    }

    public boolean isElementPresentByID(String locator) {
        try {
            ldriver.findElement(By.id(locator));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
