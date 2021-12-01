package ru.sf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//Имплементация паттерна Page Object Model
public record ChooseCityPage(WebDriver webDriver) {
    private static final String CITY_CHOOSE_FIELD_XPATH = "//span[@class='location-text top-navbar-link ng-tns-c266-2']";
    private static final String CITY_SEARCH_FIELD_XPATH = "//input[@class='input__field' and @id='8']";
    private static final String CITY_NOTFOUND_MESSAGE_SPAN_XPATH = "//p[@class='location-select__location unselectable ng-star-inserted']";

    public void go(String url) {
        webDriver.get(url);
    }

    public void searchCity(String city) {
        webDriver.findElement(By.xpath(CITY_CHOOSE_FIELD_XPATH)).click();
        final var searchInput = webDriver.findElement(By.xpath(CITY_SEARCH_FIELD_XPATH));
        searchInput.sendKeys(city);
    }

    public String getCityNotFoundMessage() {
        return webDriver.findElement(By.xpath(CITY_NOTFOUND_MESSAGE_SPAN_XPATH)).getText();
    }
}