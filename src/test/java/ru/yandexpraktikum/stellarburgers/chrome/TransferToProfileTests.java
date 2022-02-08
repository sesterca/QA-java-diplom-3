package ru.yandexpraktikum.stellarburgers.chrome;

import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import ru.yandexpraktikum.stellarburgers.com.UserOperations;
import ru.yandexpraktikum.stellarburgers.pageobjects.LoginPage;
import ru.yandexpraktikum.stellarburgers.pageobjects.MainPage;
import ru.yandexpraktikum.stellarburgers.pageobjects.ProfilePage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class TransferToProfileTests {

    private UserOperations userOperations;
    public static String userEmail;
    public static String userPassword;

    @Before
    public void registerUser(){
        userOperations = new UserOperations();
        Map<String, String> userData = userOperations.register();
        userEmail = userData.get("email");
        userPassword = userData.get("password");
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");
        System.setProperty("selenide.timeout", "4000");}

    @After
    public void deleteUser(){
        userOperations.delete();}

    @Test
    @DisplayName("Переход по клику на Личный кабинет c главной страницы (неавторизованный пользователь)")
    public void transferToLoginPageOnClickButtonAccountTest(){
        MainPage mainPage = open(MainPage.MAIN_URL, MainPage.class);
        mainPage.clickButtonAccount();
        Assert.assertEquals(url(), LoginPage.ACCOUNT_URL);
    }

    @Test
    @DisplayName("Переход по клику на Личный кабинет с главной страницы (авторизованный пользователь)")
    public void transferToProfilePageOnClickButtonAccountTest(){
        MainPage mainPage = open(LoginPage.ACCOUNT_URL, LoginPage.class)
                .setLoginForm(userEmail, userPassword);
        Selenide.sleep(4000);
        mainPage.clickButtonAccountAfterLogin();
        Assert.assertEquals(url(), ProfilePage.PROFILE_URL);
    }
}
