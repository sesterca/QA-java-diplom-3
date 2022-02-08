package ru.yandexpraktikum.stellarburgers.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ForgetPasswordPage {

    //адрес страницы
    public static final String FORGET_PASSWORD_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    //ссылка Войти (вспомнили пароль)
    @FindBy(how = How.LINK_TEXT, using = "Войти")
    private SelenideElement linkLogin;

    //кликнуть по ссылке Войти (вспомнили пароль)
    public LoginPage clickLinkLogin(){
        linkLogin.shouldBe(Condition.visible).click();
        return Selenide.page(LoginPage.class);}
}
