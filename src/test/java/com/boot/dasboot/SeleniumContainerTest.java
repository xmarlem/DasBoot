package com.boot.dasboot;


import com.thoughtworks.selenium.Selenium;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.HostPortWaitStrategy;
import org.testcontainers.containers.PostgreSQLContainer;
import org.junit.ClassRule;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testcontainers.containers.wait.strategy.Wait;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.rnorth.visibleassertions.VisibleAssertions.assertTrue;
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;
import java.time.Duration;

/**
 * Simple example of plain Selenium usage.
 */
public class SeleniumContainerTest {

    private Logger log = Logger.getLogger("TEST LOGGER");

    @Rule
    public BrowserWebDriverContainer chrome = new BrowserWebDriverContainer()
            .withDesiredCapabilities(DesiredCapabilities.chrome())
            .withRecordingMode(RECORD_ALL, new File("target"));


    @ClassRule
    public static DockerComposeContainer environment =
            new DockerComposeContainer(new File("docker-compose.yml"))
                    .withLocalCompose(true)
                    .withExposedService("db", 5432, Wait.forListeningPort())
                    .withExposedService("app", 8080, Wait.forListeningPort());

//    @ClassRule
//    public static PostgreSQLContainer postgreSql = new PostgreSQLContainer()
//            .withUsername("postgres")
//            .withPassword("postgres@123")
//            .withDatabaseName("dasboot")
//            .withStartupTimeout(Duration.ofSeconds(600);



//
//    @ClassRule
//    public static GenericContainer springBootApp = new GenericContainer("openjdk:8-jdk-alpine")
//            //.withEnv("JAVA_OPTS", "-Djava.security.egd=file:/dev/./urandom ")
//            .waitingFor(Wait.forListeningPort())
//            .withEnv("JAVA_OPTS", "-Dspring.profiles.active=")
//            .withExposedPorts(8080);

    @Test
    public void simplePlainSeleniumTest() {
        //INIT

        RemoteWebDriver driver = chrome.getWebDriver();

        String appUrl = environment.getServiceHost("app_1", 8080);
        driver.get(appUrl);


//        driver.get("https://wikipedia.org");
//        driver.get("http://192.168.99.102:8080");

        checkPageIsReady(driver);
        WebElement element = driver.findElementByXPath("//*[@id=\"navbar\"]/ul/li[2]/a");

        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();

        checkPageIsReady(driver);

//        WebElement addNewShipWreck = driver.findElementById("addShipwreck");


//        WebElement addNewShipWreck = driver.findElementByLinkText("Add New Shipwreck");

//        actions.moveToElement(addNewShipWreck).click();

        checkPageIsReady(driver);


//        boolean expected = driver.findElementsByCssSelector("td")
//                .stream()
//                .anyMatch(elem -> elem.getText().contains("Titanic"));

//        assertTrue("A new shipwreck called 'Santa Maria' has been added with success!", expected);

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
        checkPageIsReady(driver);
        boolean expectedTextFound = driver.findElementsByCssSelector("td")
                .stream()
                .anyMatch(elem -> elem.getText().contains("Santa Maria"));

                assertTrue("A new shipwreck called 'Santa Maria' has been added with success!", expectedTextFound);


        driver.get("https://google.com");

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
        for (int i=0; i<200; i++){
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