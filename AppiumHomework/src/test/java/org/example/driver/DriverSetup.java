package org.example.driver;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverSetup {

    public static AppiumDriver<MobileElement> driver;
    protected boolean localAndroid = true;

    Logger logger = LogManager.getLogger(DriverSetup.class);

    @BeforeScenario
    public void setUp() throws MalformedURLException {

        if (localAndroid) {
            logger.info("==============Test Basliyor==============");
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "samsung SM-A528B");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.m.qr.home.onboarding.ui.OnBoardingActivity");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.m.qr");
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3000);
            URL url = new URL("http:127.0.0.1:4723/wd/hub");
            logger.info("===================Uygulama Aciliyor===================");
            driver = new AndroidDriver<MobileElement>(url, desiredCapabilities);

        }

    }

    @AfterScenario
    public void tearDown() {
        logger.info("Uygulama kapatiliyor");
        driver.quit();
    }
}
