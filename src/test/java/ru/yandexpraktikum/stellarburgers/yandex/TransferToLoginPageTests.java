package ru.yandexpraktikum.stellarburgers.yandex;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.yandexpraktikum.stellarburgers.pageobjects.LoginPage;
import ru.yandexpraktikum.stellarburgers.pageobjects.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class TransferToLoginPageTests extends BaseTest {

    @Test
    @DisplayName("Переход по клику на Личный кабинет c главной страницы (неавторизованный пользователь)")
    public void transferToLoginPageOnClickButtonAccountTest(){
        LoginPage loginPage = open(MainPage.MAIN_URL, MainPage.class)
                .clickButtonAccount();
        Assert.assertTrue(loginPage.isLoginPageLoaded());}
}
