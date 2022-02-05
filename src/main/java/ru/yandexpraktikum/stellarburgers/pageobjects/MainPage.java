package ru.yandexpraktikum.stellarburgers.pageobjects;

import com.codeborne.selenide.Condition;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class MainPage{

    public static final String MAIN_URL = "https://stellarburgers.nomoreparties.site/";

    //кольцо загрузки
    @FindBy(how = How.CLASS_NAME, using = ".//div[@class='Modal_modal_overlay__x2ZCr'][2]")
    public SelenideElement loading;

    //оверлей модального окна
    @FindBy(how = How.XPATH, using = ".//div[@class='Modal_modal_overlay__x2ZCr'][1]")
    public SelenideElement overlay;

    //кнопка Личный кабинет в хедере
    @FindBy(how = How.XPATH, using = ".//a//p[text()='Личный Кабинет']")
    public SelenideElement buttonAccount;

    //кнопка Войти в аккаунт
    @FindBy(how = How.XPATH, using = ".//button[contains(text(),'Войти в аккаунт')]")
    public SelenideElement buttonLogin;

    //вкладка Булки
    @FindBy(how = How.XPATH, using = ".//div[span[text()='Булки']]")
    private SelenideElement linkBuns;

    //вкладка Соусы
    @FindBy(how = How.XPATH, using = ".//div[span[text()='Соусы']]")
    public SelenideElement linkSauces;

    //вкладка Начинки
    @FindBy(how = How.XPATH, using = ".//div[span[text()='Начинки']]")
    public SelenideElement linkFillings;

    //раздел Булки
    @FindBy(how = How.XPATH, using = ".//ul[@class='BurgerIngredients_ingredients__list__2A-mT'][1]")
    public SelenideElement sectionBuns;

    //раздел Соусы
    @FindBy(how = How.XPATH, using = ".//ul[@class='BurgerIngredients_ingredients__list__2A-mT'][2]")
    public SelenideElement sectionSauces;

    //раздел Начинки
    @FindBy(how = How.XPATH, using = ".//ul[@class='BurgerIngredients_ingredients__list__2A-mT'][3]")
    public SelenideElement sectionFillings;

    //заголовок Соберите бургер
    @FindBy(how = How.TAG_NAME,using = "h1")
    public SelenideElement headingMakeBurger;

    //кликнуть кнопку Войти в аккаунт
    public LoginPage clickButtonLogin(){
        Selenide.executeJavaScript("return arguments[0].click();", buttonLogin);
        return page(LoginPage.class);}

    //кликнуть кнопку Личный кабинет (неавторизованный пользователь)
    public LoginPage clickButtonAccount(){
        Selenide.executeJavaScript("return arguments[0].click();", buttonAccount);
        return page(LoginPage.class);}

    //кликнуть кнопку Личный кабинет (авторизованный пользователь)
    public ProfilePage clickButtonAccountAfterLogin(){
        buttonAccount.shouldBe(Condition.and("can be clicked", visible, enabled)).click();
        return page(ProfilePage.class);}

    //кликнуть вкладку Булки
    public MainPage clickLinkBuns(){
        overlay.shouldNotBe(Condition.visible, Duration.ofSeconds(100));
        linkBuns.shouldBe(Condition.visible).click();
        return this;}

    //кликнуть вкладку Соусы
    public MainPage clickLinkSauces(){
        overlay.shouldNotBe(Condition.visible, Duration.ofSeconds(100));
        linkSauces.shouldBe(Condition.visible).click();
        return this;}

    //кликнуть вкладку Начинки
    public MainPage clickLinkFillings(){
        overlay.shouldNotBe(Condition.visible, Duration.ofSeconds(100));
        linkFillings.shouldBe(Condition.visible).click();
        return this;}

    //пользователь авторизовался
    public boolean isUserLogin(){
        Selenide.sleep(2000);
        return url().equals(MAIN_URL);}
}
