package org.fundacionjala.core.common;

import org.fundacionjala.core.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class Base.
 */
public class Base {
    protected WebDriver driver;
    protected WebDriverWait driverWait;

    /**
     * Method for Base for declared DriverManager.
     */
    public Base() {
        driver = DriverManager.getInstance().getDriver();
        driverWait = DriverManager.getInstance().getWaiter();
        PageFactory.initElements(driver, this);
    }
}
