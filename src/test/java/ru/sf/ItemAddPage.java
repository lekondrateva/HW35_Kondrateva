package ru.sf;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public record ItemAddPage(WebDriver webDriver) {

    private static final String SEARCH_ITEM_FIELD_XPATH = "//*[@id=\"1\"]";
    private static final String FIRST_FOUND_ITEM_XPATH = "(// * [@ class = 'button ng-star-inserted' and @ title = 'Добавить в корзину'])[1]";

    public void searchItem(String item) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        final var searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SEARCH_ITEM_FIELD_XPATH)));
        searchInput.sendKeys(item, Keys.ENTER);
    }

    public void addFirstItem() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FIRST_FOUND_ITEM_XPATH))).click();
    }
}