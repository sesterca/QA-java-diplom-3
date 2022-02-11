package ru.yandexpraktikum.stellarburgers.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LoginPage {

    public static final String ACCOUNT_URL = "https://stellarburgers.nomoreparties.site/login";

    //кольцо загрузки
    @FindBy(how = How.XPATH, using = ".//div[@class='Modal_modal_overlay__x2ZCr'][2]")
    public SelenideElement loading;

    //оверлей модального окна
    @FindBy(how = How.XPATH, using = ".//div[@class='Modal_modal_overlay__x2ZCr'][1]")
    public SelenideElement overlay;

    //заголовок Вход
    @FindBy(how = How.XPATH, using = ".//main//h2")
    public SelenideElement headingLogin;

    //поле Email
    @FindBy(how = How.NAME, using = "name")
    public SelenideElement inputEmail;

    //поле Пароль
    @FindBy(how = How.NAME, using = "Пароль")
    public SelenideElement inputPassword;

    //кнопка Войти
    @FindBy(how = How.XPATH, using = ".//button[contains(text(),'Войти')]")
    public SelenideElement buttonAuth;

    //ссылка Зарегистрироваться
    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "register")
    public SelenideElement linkRegister;

    //ссылка Восстановить пароль
    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "forgot-password")
    public SelenideElement linkForgotPassword;

    //кнопка Конструктор в хедере
    @FindBy(how = How.XPATH, using = ".//p[contains (text(), 'Конструктор')]")
    public SelenideElement buttonConstructor;

    //ссылка StellarBurgers в хедере
    @FindBy(how = How.XPATH, using = ".//div[@class='AppHeader_header__logo__2D0X2']/a")
    public SelenideElement logoBurger;

    //заполнить поле Email
    public void setInputEmail(String userEmail){
        inputEmail.shouldBe(visible).setValue(userEmail);}

    //заполнить поле Пароль
    public void setInputPassword(String userPassword){
        inputPassword.shouldBe(visible).setValue(userPassword);}

    @Step("Клик по кнопке Войти страницы Личный кабинет")
    public MainPage clickButtonAuth(){
        buttonAuth.shouldBe(Condition.and("can be clicked", visible, enabled)).click();
        return Selenide.page(MainPage.class);}

    public void clickLinkRegister(){linkRegister.click();}

    public void clickLinkForgotPassword(){linkForgotPassword.click();}

    @Step("Клик по кнопке Конструктор страницы Личный кабинет неавторизованным пользователем")
    public MainPage clickButtonConstructor(){
        buttonConstructor.shouldBe(Condition.and("can be clicked", visible, enabled)).click();
        return Selenide.page(MainPage.class);
    }

    @Step("Клик по логотипу StellarBurgers страницы Личный кабинет неавторизованным пользователем")
    public MainPage clickLogoBurger(){logoBurger.shouldBe(visible).click();
        return Selenide.page(MainPage.class);}

    @Step("Заполнение и отправка данных формы авторизации на странице Личный кабинет")
    public MainPage setLoginForm(String userEmail, String userPassword){
        setInputEmail(userEmail);
        setInputPassword(userPassword);
        clickButtonAuth();
        return Selenide.page(MainPage.class);}

    @Step("Проверка загрузки страницы авторизации")
    public boolean isLoginPageLoaded(){
        loading.shouldBe(hidden);
        headingLogin.shouldBe(visible);
        return url().equals(LoginPage.ACCOUNT_URL);}
}