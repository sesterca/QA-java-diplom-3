package ru.yandexpraktikum.stellarburgers.yandex;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.yandexpraktikum.stellarburgers.pageobjects.LoginPage;
import ru.yandexpraktikum.stellarburgers.pageobjects.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class TransferFromLoginPageTests extends BaseTest {

    @Test
    @DisplayName("Переход из личного кабинета (неавторизованный пользователь) по клику на логотип Stellar Burgers")
    public void switchToMainFromLoginPageByClickLogoTest(){
        MainPage mainPage = open(LoginPage.ACCOUNT_URL, LoginPage.class)
                .clickLogoBurger();
        Assert.assertTrue(mainPage.isMainPageLoaded());}

    @Test
    @DisplayName("Переход из личного кабинета (неавторизованный) по клику на Конструктор")
    public void switchToMainFromLoginPageByClickConstructorButtonTest(){
        MainPage mainPage = open(LoginPage.ACCOUNT_URL, LoginPage.class)
                .clickButtonConstructor();
        Assert.assertTrue(mainPage.isMainPageLoaded());}
}
