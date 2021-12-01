package ru.sf;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.Assert.assertEquals;


/**
 * Варинаты запуска сценариев:
 * Команда 'mvn clean test' в консоли
 * Через UI intellij IDEA
 */
public class StepDefinitions {

    public static final WebDriver webDriver;
    public static final ChooseCityPage chooseCityPage;
    public static final CartPage cartPage;
    public static final ItemAddPage itemAddPage;

    //Процесс инициализации необходимых ресурсов
    static {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\LenOK\\IdeaProjects\\HW35_Kondrateva\\src\\test\\resources\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().window().maximize();
        chooseCityPage = new ChooseCityPage(webDriver);
        cartPage = new CartPage(webDriver);
        itemAddPage = new ItemAddPage(webDriver);
    }

    //Перейти на сайт
    @Given("url {string}")
    public void url(String url) {
        chooseCityPage.go(url);
    }

    //Найти товар
    @Then("find an item {string}")
    public void find_an_item(String item) {
        itemAddPage.searchItem(item);
    }

    //Добавить первый найденный товар
    @Then("add first item")
    public void add_first_item() {
        itemAddPage.addFirstItem();
    }

    //Подождать
    @Then("waiting")
    public void waiting() throws InterruptedException {
        Thread.sleep(3000); //товар в корзине появляется с задержкой, корзина сразу кликабельна, поэтому принудительное ожидание использую
        //webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100)); // функция не дала результата, корзина оказывается пустой каждый 2-3 тест
    }

    //Перейти в корзину
    @Then("follow to cart")
    public void follow_to_cart() {
        cartPage.followToCart();
    }

    //Проверить, что в корзине 1 товар
    @Then("assert that item added {string}")
    public void assert_that_item_added(String ExpectedCartMessage) {
        final var ActualCartMessage = cartPage.getActualCartMessage();
        assertEquals(ExpectedCartMessage, ActualCartMessage);
    }

    //Выбрать город
    @Then("choose the city {string}")
    public void choose_the_city(String city) {
        chooseCityPage.searchCity(city);
    }

    //Проверить наличие сообщения о некорректном вводе названия города
    @Then("assert that user got message {string}")
    public void assert_that_user_got_message(String errorMessage) {
        final var cityNotFoundMessage = chooseCityPage.getCityNotFoundMessage();
        assertEquals(errorMessage, cityNotFoundMessage);
    }
}