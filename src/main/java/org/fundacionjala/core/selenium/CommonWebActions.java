package org.fundacionjala.core.selenium;

import org.fundacionjala.core.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class CommonWebActions.
 */
public final class CommonWebActions {

    private static final Actions ACTIONS = new Actions(DriverManager.getInstance().getDriver());
    private static final WebDriverWait WAITER = DriverManager.getInstance().getWaiter();
    private static final WebDriver WEB_DRIVER = DriverManager.getInstance().getDriver();
    private static final JavascriptExecutor JAVASCRIPT_EXECUTOR = (JavascriptExecutor) WEB_DRIVER;

    /**
     * Private constructor because it is a util class.
     */
    private CommonWebActions() {
    }

    /**
     * Method for wait Web element.
     *
     * @param webElement WebElement.
     * @return the element received in param.
     */
    public static WebElement waitWebElementVisible(final WebElement webElement) {
        return WAITER.until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * Method for wait Web element.
     *
     * @param element WebElement.
     * @return the element received in param.
     */
    public static WebElement waitWebElementToBeClickable(final WebElement element) {
        return WAITER.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Method to get any element but wait until it is visible.
     *
     * @param element to wait for.
     * @return the element.
     */
    public static WebElement getElement(final WebElement element) {
        waitWebElementVisible(element);
        return element;
    }

    /**
     * This method generates a wait for a fixed time.
     *
     * @param time time.
     */
    public static void waitTime(int time) {
        DriverManager.getInstance().setUpdateWait(time);
    }

    /**
     * This method generates a wait for a fixed time.
     */
    public static void resetWaitTime() {
        DriverManager.getInstance().setUpdateWait(0);
    }

    /**
     * Method to click any element.
     *
     * @param element to click.
     */
    public static void clickElement(final WebElement element) {
        waitWebElementVisible(element);
        scrollPage(element);
        element.click();
    }

    /**
     * Method to JClick any element.
     *
     * @param element to click.
     */
    public static void jsClickElement(final WebElement element) {
        waitWebElementToBeClickable(element);
        JAVASCRIPT_EXECUTOR.executeScript("arguments[0].click();", element);
    }

    /**
     * Method to click any element.
     *
     * @param locator to click.
     */
    public static void jsClickByElementLocator(final String locator) {
        WAITER.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
        WAITER.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
        WebElement element = WEB_DRIVER.findElement(By.cssSelector(locator));
        scrollPage(element);
        jsClickElement(element);
    }

    /**
     * Method to scroll down page.
     *
     * @param element the element we want to choose.
     */
    public static void scrollPage(final WebElement element) {
        JAVASCRIPT_EXECUTOR.executeScript("arguments[0].scrollIntoView();", element);
        waitWebElementToBeClickable(element);
        ACTIONS.moveToElement(element);
    }

    /**
     * @param element Any WebElement.
     * @param text    set a text on a field.
     */
    public static void setTextElement(final WebElement element, final String text) {
        waitWebElementVisible(element);
        clearTextField(element);
        element.sendKeys(text);
    }

    /**
     * @param element Any WebElement.
     * @param text    set a text on a field.
     */
    public static void setDateOnCalendar(final WebElement element, final String text) {
        setTextElement(element, text);
        element.sendKeys(Keys.TAB);
    }

    /**
     * @param element a checkbox element.
     * @param value   a boolean value.
     */
    public static void setCheckBox(final WebElement element, boolean value) {
        if (!element.isSelected() && value) {
            clickElement(element);
        }
        if (element.isSelected() && !value) {
            clickElement(element);
        }
    }

    /**
     * Waits and clear the WebElement.
     *
     * @param element WebElement to wait and clear.
     */
    public static void clearTextField(final WebElement element) {
        element.clear();
    }

    /**
     * @param element        is the content parameter.
     * @param listOfElements list of elements.
     * @return WebElement .
     */
    public static boolean istWebElementPresentOnList(final List<WebElement> listOfElements,
                                                     final String element) {
        return listOfElements
                .stream()
                .anyMatch(elementOnList -> elementOnList.getText().equalsIgnoreCase(element));
    }

    /**
     * @param element        is the content parameter.
     * @param listOfElements list of elements.
     * @return WebElement .
     */
    public static WebElement getWebElementFromAList(final List<WebElement> listOfElements,
                                                    final String element) {
        return listOfElements
                .stream()
                .filter(elementOnList -> elementOnList.getText().equalsIgnoreCase(element))
                .findFirst()
                .orElseThrow(NullPointerException::new);

    }

    /**
     * Method to close message displayed.
     */
    public static void closeMessageLighting() {
        try {
            if (WEB_DRIVER.findElement(By.id("lexNoThanks")).isDisplayed()) {
                WEB_DRIVER.findElement(By.id("lexNoThanks")).click();
                WEB_DRIVER.findElement(By.id("tryLexDialogX")).click();
            }
        } catch (NoSuchElementException e) {

        }
    }

    /**
     * This method press enter key to web element.
     *
     * @param webElement is the WebElement.
     */
    public static void pressEnter(final WebElement webElement) {
        webElement.sendKeys(Keys.ENTER);
    }

}
