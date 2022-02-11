package ru.yandexpraktikum.stellarburgers.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgetPasswordPage {

    //адрес страницы
    public static final String FORGET_PASSWORD_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    //оверлей модального окна
    @FindBy(how = How.XPATH, using = ".//div[@class='Modal_modal_overlay__x2ZCr'][1]")
    public SelenideElement overlay;

    //ссылка Войти (вспомнили пароль)
    @FindBy(how = How.LINK_TEXT, using = "Войти")
    private SelenideElement linkLogin;

    //кликнуть по ссылке Войти (вспомнили пароль)
    @Step("Клик по ссылке Войти страницы Восстановление пароля")
    public LoginPage clickLinkLogin(){
        linkLogin.shouldBe(Condition.visible).click();
        return Selenide.page(LoginPage.class);}
}
