package ru.yandexpraktikum.stellarburgers.chrome;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import ru.yandexpraktikum.stellarburgers.com.UserOperations;
import ru.yandexpraktikum.stellarburgers.pageobjects.ForgetPasswordPage;
import ru.yandexpraktikum.stellarburgers.pageobjects.LoginPage;
import ru.yandexpraktikum.stellarburgers.pageobjects.MainPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserLoginTests {

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
    @DisplayName("Вход по кнопке Войти в аккаунт на главной")
    public void userLoginOnClickButtonLoginTest(){
        MainPage mainPage = open(MainPage.MAIN_URL, MainPage.class);
        LoginPage loginPage = mainPage.clickButtonLogin();
        MainPage mainPageAfterLogin = loginPage.setLoginForm(userEmail, userPassword);
        assertTrue(mainPageAfterLogin.isUserLogin());
    }

    @Test
    @DisplayName("Вход через кнопку Личный кабинет на главной")
    public void userLoginOnClickAccountButtonTest(){
        MainPage mainPage = open(MainPage.MAIN_URL, MainPage.class);
        MainPage mainPageAfterLogin = mainPage.clickButtonAccount()
                .setLoginForm(userEmail, userPassword);
        assertTrue(mainPageAfterLogin.isUserLogin());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void userLoginOnClickRegistrationFormButtonTest(){
        MainPage mainPage = open(LoginPage.ACCOUNT_URL, LoginPage.class)
                .setLoginForm(userEmail, userPassword);
        assertTrue(mainPage.isUserLogin());}

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void userLoginThroughForgetPasswordPageTest(){
        ForgetPasswordPage forgetPasswordPage = open(ForgetPasswordPage.FORGET_PASSWORD_URL, ForgetPasswordPage.class);
        LoginPage loginPage = forgetPasswordPage.clickLinkLogin();
        MainPage mainPageAfterLogin = loginPage.setLoginForm(userEmail, userPassword);
        assertTrue(mainPageAfterLogin.isUserLogin());
    }
}
