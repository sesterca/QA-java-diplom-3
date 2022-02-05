package ru.yandexpraktikum.stellarburgers.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.yandexpraktikum.stellarburgers.com.model.User;
import ru.yandexpraktikum.stellarburgers.com.model.UserRegisterResponse;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;
import java.util.Map;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.WebDriverRunner.url;

public class LoginPage {

    public static final String ACCOUNT_URL = "https://stellarburgers.nomoreparties.site/login";

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

    public MainPage clickButtonAuth(){
        buttonAuth.shouldBe(Condition.and("can be clicked", visible, enabled), Duration.ofSeconds(3000)).click();
        return Selenide.page(MainPage.class);}

    public void clickLinkRegister(){linkRegister.click();}

    public void clickLinkForgotPassword(){linkForgotPassword.click();}

    public void clickButtonConstructor(){
        buttonConstructor.shouldBe(Condition.and("can be clicked", visible, enabled), Duration.ofSeconds(3000)).click();}

    public void clickLogoBurger(){logoBurger.shouldBe(visible).click();}

    public MainPage setLoginForm(String userEmail, String userPassword){
        setInputEmail(userEmail);
        setInputPassword(userPassword);
        clickButtonAuth();
        return Selenide.page(MainPage.class);
    }

    public boolean isUserLogout(){
        Selenide.sleep(2000);
        return url().equals(MainPage.MAIN_URL);}
}