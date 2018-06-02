package com.boot.dasboot;


import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import java.io.File;
import java.util.logging.Logger;

import static org.rnorth.visibleassertions.VisibleAssertions.assertTrue;
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;

/**
 * Simple example of plain Selenium usage.
 */
public class SeleniumContainerTest {

    @Rule
    public BrowserWebDriverContainer chrome = new BrowserWebDriverContainer()
            .withDesiredCapabilities(DesiredCapabilities.chrome())
            .withRecordingMode(RECORD_ALL, new File("target"));

    @Test
    public void simplePlainSeleniumTest() {
        RemoteWebDriver driver = chrome.getWebDriver();

//        driver.get("https://wikipedia.org");
        driver.get("http://192.168.99.102:8080");

        checkPageIsReady(driver);
        WebElement element = driver.findElementByXPath("//*[@id=\"navbar\"]/ul/li[2]/a");

        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();

        checkPageIsReady(driver);

//        WebElement addNewShipWreck = driver.findElementById("addShipwreck");


//        WebElement addNewShipWreck = driver.findElementByLinkText("Add New Shipwreck");

//        actions.moveToElement(addNewShipWreck).click();

        checkPageIsReady(driver);


        boolean expected = driver.findElementsByCssSelector("td")
                .stream()
                .anyMatch(elem -> elem.getText().contains("Titanic"));

        assertTrue("A new shipwreck called 'Santa Maria' has been added with success!", expected);

        WebElement addLink = driver.findElementById("addShipwreck");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", addLink);

        checkPageIsReady(driver);

        //JavascriptExecutor executor2 = (JavascriptExecutor) driver;
        //executor2.executeScript("document.getElementById('name').setAttribute('value','Test');");
        checkPageIsReady(driver);

        WebElement sName = driver.findElementById("name");
        sName.sendKeys("Santa Maria");
        sName.submit();

//        WebElement submitButton = driver.findElement(By.xpath("/html/body/div/div/div/form/div/div[8]/div/input"));
//        actions.moveToElement(submitButton).click().perform();

        checkPageIsReady(driver);
        boolean expectedTextFound = driver.findElementsByCssSelector("td")
                .stream()
                .anyMatch(elem -> elem.getText().contains("Santa Maria"));

                assertTrue("A new shipwreck called 'Santa Maria' has been added with success!", expectedTextFound);


        driver.get("https://googl.com");

    }


    public void checkPageIsReady(RemoteWebDriver driver) {

        JavascriptExecutor js = (JavascriptExecutor)driver;


        //Initially bellow given if condition will check ready state of page.
        if (js.executeScript("return document.readyState").toString().equals("complete")){
            System.out.println("Page Is loaded.");
            return;
        }

        //This loop will rotate for 25 times to check If page Is ready after every 1 second.
        //You can replace your value with 25 If you wants to Increase or decrease wait time.
        for (int i=0; i<100; i++){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e) {}
            //To check page ready state.
            if (js.executeScript("return document.readyState").toString().equals("complete")){
                break;
            }
        }
    }
}