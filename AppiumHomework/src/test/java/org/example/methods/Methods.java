package org.example.methods;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.example.driver.DriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Methods extends DriverSetup {

    AppiumDriver driver;

    FluentWait<AppiumDriver> wait;

    public Methods() {
        driver = DriverSetup.driver;
        wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class);
    }

    public MobileElement findElement(By by) {
        return (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void click(By by) {
        findElement(by).click();
    }

    public void sendKeys(String key, By by) {
        findElement(by).sendKeys(key);
    }

    public String getText(By by) {
        return driver.findElement(by).getText();
    }

    public String addDay(int day) {
        DateFormat dateFormat = new SimpleDateFormat("d");
        Date date = new Date(new Date().getTime() + 86400000L * day); // one day = 1000 * 60 * 60 * 24 = 86400000
        return dateFormat.format(date);
    }
}
