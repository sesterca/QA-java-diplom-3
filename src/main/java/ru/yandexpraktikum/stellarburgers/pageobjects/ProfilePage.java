package ru.yandexpraktikum.stellarburgers.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class ProfilePage {

    public static final String PROFILE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    //кольцо загрузки
    @FindBy(how = How.XPATH, using = ".//div[@class='Modal_modal_overlay__x2ZCr'][2]")
    public SelenideElement loading;

    //оверлей модального окна
    @FindBy(how = How.XPATH, using = ".//div[@class='Modal_modal_overlay__x2ZCr'][1]")
    public SelenideElement overlay;

    //кнопка Выйти
    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    public SelenideElement buttonLogout;

    //ссылка StellarBurgers в хедере
    @FindBy(how = How.XPATH, using = ".//div[@class='AppHeader_header__logo__2D0X2']/a")
    public SelenideElement logoBurger;

    //кнопка Конструктор
    @FindBy(how = How.XPATH, using = ".//a[p[text()='Конструктор']]")
    public SelenideElement buttonConstructor;

    @Step("Клик по кнопке Выход страницы Личный кабинет")
    public LoginPage clickButtonLogout(){
        buttonLogout.shouldBe(Condition.and("can be clicked", visible, enabled)).click();
        return Selenide.page(LoginPage.class);}

    @Step("Клик по логотипу StellarBurgers страницы Личный кабинет авторизованным пользователем")
    public MainPage clickLogoBurger(){logoBurger.shouldBe(visible).click();
        return Selenide.page(MainPage.class);}

    @Step("Клик по кнопке Конструктор со страницы Личный кабинет авторизованным пользователем")
    public MainPage clickButtonConstructor(){
        buttonConstructor.click();
        return Selenide.page(MainPage.class);}

    //авторизованный пользователь вошел в Личный кабинет
    @Step("Проверка загрузки Личного кабинета")
    public boolean isProfilePageLoaded(){
        loading.shouldBe(hidden);
        buttonLogout.shouldBe(visible);
        return url().equals(PROFILE_URL);}
}