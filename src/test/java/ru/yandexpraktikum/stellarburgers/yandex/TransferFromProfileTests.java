package ru.yandexpraktikum.stellarburgers.yandex;

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

public class TransferFromProfileTests {

    private UserOperations userOperations;
    public static String userEmail;
    public static String userPassword;

    @Before
    public void registerUser(){
        userOperations = new UserOperations();
        Map<String, String> userData = userOperations.register();
        userEmail = userData.get("email");
        userPassword = userData.get("password");
        System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver.exe");
        System.setProperty("selenide.timeout", "4000");}

    @After
    public void deleteUser(){
        userOperations.delete();}

    @Test
    @DisplayName("Переход из личного кабинета (авторизованный пользователь) по клику на логотип Stellar Burgers")
    public void switchToMainFromProfilePageByClickLogoTest(){
        MainPage mainPage = open(LoginPage.ACCOUNT_URL, LoginPage.class)
                .setLoginForm(userEmail, userPassword);
        Selenide.sleep(4000);
        ProfilePage profilePage = mainPage.clickButtonAccountAfterLogin();
        Selenide.sleep(4000);
        profilePage.clickLogoBurger();
        Assert.assertEquals(url(), MainPage.MAIN_URL);
    }

    @Test
    @DisplayName("Переход из личного кабинета (авторизованный пользователь) по клику на Конструктор")
    public void switchToMainFromProfilePageByClickConstructorButtonTest(){
        MainPage mainPage = open(LoginPage.ACCOUNT_URL, LoginPage.class)
                .setLoginForm(userEmail, userPassword);
        Selenide.sleep(4000);
        ProfilePage profilePage = mainPage.clickButtonAccountAfterLogin();
        Selenide.sleep(4000);
        profilePage.clickButtonConstructor();
        Assert.assertEquals(url(), MainPage.MAIN_URL);
    }

    @Test
    @DisplayName("Переход из личного кабинета (неавторизованный пользователь) по клику на логотип Stellar Burgers")
    public void switchToMainFromLoginPageByClickLogoTest(){
        LoginPage loginPage = open(LoginPage.ACCOUNT_URL, LoginPage.class);
        loginPage.clickLogoBurger();
        Assert.assertEquals(url(), MainPage.MAIN_URL);
    }

    @Test
    @DisplayName("Переход из личного кабинета (неавторизованный) по клику на Конструктор")
    public void switchToMainFromLoginPageByClickConstructorButtonTest(){
        LoginPage loginPage = open(LoginPage.ACCOUNT_URL, LoginPage.class);
        loginPage.clickButtonConstructor();
        Assert.assertEquals(url(), MainPage.MAIN_URL);
    }

}
