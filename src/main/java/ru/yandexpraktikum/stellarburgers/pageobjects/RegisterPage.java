package ru.yandexpraktikum.stellarburgers.pageobjects;

import com.codeborne.selenide.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class RegisterPage {

    public static final String REGISTER_URL = "https://stellarburgers.nomoreparties.site/register";

    //поле Имя формы регистрации
    @FindBy(how = How.XPATH, using = ".//form//div[label[text()='Имя']]/input")
    public SelenideElement inputName;

    //поле Email формы регистрации
    @FindBy(how = How.XPATH, using = ".//form//div[label[text()='Email']]/input")
    public SelenideElement inputEmail;

    //поле Пароль формы регистрации
    @FindBy(how = How.XPATH, using = ".//form//div[label[text()='Пароль']]/input")
    public SelenideElement inputPassword;

    //кнопка Зарегистрироваться формы регистрации
    @FindBy(how = How.XPATH, using = ".//form//button[text()='Зарегистрироваться']")
    public SelenideElement buttonRegister;

    //сообщение об ошибке ввода Некорректный пароль
    @FindBy(how = How.XPATH, using = ".//form//p[text()='Некорректный пароль']")
    public SelenideElement errorInvalidPassword;

    //заполнить поле Имя
    public void setInputName(){
        inputName.shouldBe(Condition.visible).setValue(RandomStringUtils.randomAlphabetic(5));}

    //заполнить поле Email
    public void setInputEmail(){
        inputEmail.shouldBe(Condition.visible).setValue(RandomStringUtils.randomAlphabetic(5) + "@yandex.ru");}

    //заполнить поле Пароль (валидное значение)
    public void setInputPasswordValid(){
        inputPassword.shouldBe(Condition.visible).setValue(RandomStringUtils.randomAlphabetic(6, 10));}

    //заполнить поле Пароль (невалидное значение)
    public void setInputPasswordInvalid(){
        inputPassword.shouldBe(Condition.visible).setValue(RandomStringUtils.randomAlphabetic(5));}

    //кликнуть кнопку Зарегистрироваться формы регистрации
    public void clickButtonRegister(){
        buttonRegister.shouldBe(Condition.and("can be clicked", visible, enabled), Duration.ofSeconds(3000)).click();}

    //заполнить и отправить данные формы регистрации
    public LoginPage setRegistrationForm(){
        setInputName();
        setInputEmail();
        setInputPasswordValid();
        Selenide.sleep(1000);
        clickButtonRegister();
        return page(LoginPage.class);}

    //заполнить форму с невалидным паролем и получить ошибку Некорректный пароль
    public RegisterPage setRegistrationFormWithInvalidPassword(){
        setInputName();
        setInputEmail();
        setInputPasswordInvalid();
        clickButtonRegister();
        return page(RegisterPage.class);
    }
    //получить текст ошибки
    public String getErrorText() {
        return errorInvalidPassword.text();}

    public boolean isUserRegistrationSuccess(){
        Selenide.sleep(2000);
        return url().equals(LoginPage.ACCOUNT_URL);}
}
