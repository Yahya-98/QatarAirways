package org.example.pages;

import com.thoughtworks.gauge.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.methods.Methods;
import org.junit.Assert;
import org.openqa.selenium.By;

public class BookPage {

    Methods methods;
    Logger logger = LogManager.getLogger(BookPage.class);
    By btnOneWay = By.xpath("//android.widget.LinearLayout[@content-desc=\"One-way\"]");
    By btnFrom = By.id("com.m.qr:id/rvmp_fragment_rtow_flight_selection_origin_holder");
    By inputDestination = By.id("com.m.qr:id/rvmp_fragment_ond_selection_filter_edittext");
    By selectAirportFromList = By.id("com.m.qr:id/rvmp_item_ond_selection_list_root_view");
    By btnTo = By.id("com.m.qr:id/rvmp_fragment_rtow_flight_selection_destination_select_destination_text_view");
    By openCalendar = By.id("com.m.qr:id/rvmp_fragment_rtow_flight_selection_date_holder_date_text_view");
    By btnCalendarConfirm = By.id("com.m.qr:id/fragment_calendar_confirm_button");
    By btnSearchFlights = By.id("com.m.qr:id/rvmp_booking_search_flights_button");
    By textFligthPageTitle = By.id("com.m.qr:id/rvmp_results_count");


    public BookPage() {
        methods = new Methods();
    }

    @Step("Tek yön secimi yap")
    public void oneWay() {
        methods.click(btnOneWay);
        logger.info("Tek yon ucus secilidi");
    }

    @Step("Kalkış <from> , iniş <to> secilir")
    public void selectRota(String from, String to) {
        methods.click(btnFrom);
        methods.sendKeys(from, inputDestination);
        methods.click(selectAirportFromList);
        logger.info("Kalkis yeri secildi");
        methods.click(btnTo);
        methods.sendKeys(to, inputDestination);
        methods.click(selectAirportFromList);
        logger.info("Inis yeri secildi");

    }

    @Step("Gidis tarihi bu günden <günsayisi> sonraki gün secilir")
    public void selectDepartureDate(int gunsayisi) {
        methods.click(openCalendar);
        By selectDepartureDay = By.xpath("//*[@text='" + methods.addDay(7) + "']");
        methods.click(selectDepartureDay);
        methods.click(btnCalendarConfirm);
        logger.info("Gidis tarihi secildi");
    }

    @Step("Ucus aranir")
    public void searchFlight() {
        methods.click(btnSearchFlights);
        methods.findElement(textFligthPageTitle);
        Assert.assertTrue(methods.getText(textFligthPageTitle).contains("results"));
        logger.info("Ucus arandi");
    }


}
