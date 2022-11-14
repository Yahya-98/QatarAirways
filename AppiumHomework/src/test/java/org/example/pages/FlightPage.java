package org.example.pages;

import com.thoughtworks.gauge.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.methods.Methods;
import org.junit.Assert;
import org.openqa.selenium.By;

public class FlightPage {

    Methods methods;
    Logger logger = LogManager.getLogger(FlightPage.class);
    static String flightTime;
    static String landingTime;

    By selectFlight = By.id("com.m.qr:id/rvmp_item_search_result_root_view");
    By btnSelectThisFare = By.id("com.m.qr:id/rvmp_activity_flight_details_select_button");
    By textFlightTimeDetailsPage = By.id("com.m.qr:id/from_time");
    By textLandingTimeDetailsPage = By.id("com.m.qr:id/to_time");
    By textFlightTimeFlightPage = By.id("com.m.qr:id/rvmp_departure_time");
    By textLandingTimeFlightPage = By.id("com.m.qr:id/rvmp_arrival_time");
    By btnLogin = By.id("com.m.qr:id/button_continue");

    public FlightPage() {
        methods = new Methods();
    }

    @Step("Ucak secilir")
    public void selectPlane() {
        methods.findElement(selectFlight);
        flightTime = methods.getText(textFlightTimeFlightPage);
        landingTime = methods.getText(textLandingTimeFlightPage);
        methods.click(selectFlight);
        logger.info("Ucus secildi");
        methods.click(btnSelectThisFare);
    }

    @Step("Ucus saatleri kontrol edilir")
    public void checkFlightTime() {
        methods.findElement(btnLogin);
        Assert.assertEquals("Gidiş saati yanlış", flightTime, methods.getText(textFlightTimeDetailsPage));
        Assert.assertEquals("Dönüş saati yanlış", landingTime, methods.getText(textLandingTimeDetailsPage));
        logger.info("Ucus saateleri kontrol edildi");
    }
}
