package ru.sf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public record CartPage(WebDriver webDriver) {

    private static final String ACTUAL_CART_MESSAGE_SPAN_XPATH = "(//span[@class='c-cost-line__title'])[1]";
    private static final String CART_XPATH = "//p[@ class = 'title' and text()='Корзина']";

    public String getActualCartMessage() {
        return webDriver.findElement(By.xpath(ACTUAL_CART_MESSAGE_SPAN_XPATH)).getText();
    }

    public void followToCart() {
        webDriver.findElement(By.xpath(CART_XPATH)).click();
        //WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(100)); такое ожидание не дало результата, товар в корзине отсутствует каждый 2-3 тест
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CART_XPATH))).click();
    }
}