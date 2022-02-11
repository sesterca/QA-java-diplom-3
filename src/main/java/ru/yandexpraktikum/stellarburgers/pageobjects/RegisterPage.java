package ru.yandexpraktikum.stellarburgers.pageobjects;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class RegisterPage {

    public static final String REGISTER_URL = "https://stellarburgers.nomoreparties.site/register";

    //кольцо загрузки
    @FindBy(how = How.XPATH, using = ".//div[@class='Modal_modal_overlay__x2ZCr'][2]")
    public SelenideElement loading;

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
    @FindBy(how = How.XPATH, using = ".//form//button")
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
    public LoginPage clickButtonRegister(){
        buttonRegister.shouldBe(Condition.and("can be clicked", visible, enabled)).click();
        return page(LoginPage.class);}

    //заполнить и отправить данные формы регистрации
    @Step("Заполнение и отправка валидных данных формы регистрации пользователя")
    public LoginPage setRegistrationForm(){
        setInputName();
        setInputEmail();
        setInputPasswordValid();
        clickButtonRegister();
        inputName.should(disappear);
        return page(LoginPage.class);}

    //заполнить форму с невалидным паролем и получить ошибку Некорректный пароль
    @Step("Заполнение и отправка формы регистрации пользователя с невалидным паролем")
    public RegisterPage setRegistrationFormWithInvalidPassword(){
        setInputName();
        setInputEmail();
        setInputPasswordInvalid();
        clickButtonRegister();
        return this;
    }
    //получить текст ошибки
    public String getErrorText() {
        return errorInvalidPassword.text();}
}
