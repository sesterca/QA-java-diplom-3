package ru.yandexpraktikum.stellarburgers.chrome;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandexpraktikum.stellarburgers.com.UserOperations;
import ru.yandexpraktikum.stellarburgers.pageobjects.ForgetPasswordPage;
import ru.yandexpraktikum.stellarburgers.pageobjects.LoginPage;
import ru.yandexpraktikum.stellarburgers.pageobjects.MainPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;


public class UserLoginTests extends BaseTest{

    private UserOperations userOperations;
    public String userEmail;
    public String userPassword;


    @Before
    public void registerUser(){
        userOperations = new UserOperations();
        Map<String, String> userData = userOperations.register();
        userEmail = userData.get("email");
        userPassword = userData.get("password");}

    @After
    public void deleteUser(){
        userOperations.delete();}

    @Test
    @DisplayName("Вход по кнопке Войти в аккаунт на главной")
    public void userLoginOnClickButtonLoginTest(){
        MainPage mainPage = open(MainPage.MAIN_URL, MainPage.class);
        LoginPage loginPage = mainPage.clickButtonLogin();
        MainPage mainPageAfterLogin = loginPage.setLoginForm(userEmail, userPassword);
        Assert.assertTrue(mainPage.isMainPageLoaded());
    }

    @Test
    @DisplayName("Вход через кнопку Личный кабинет на главной")
    public void userLoginOnClickAccountButtonTest(){
        MainPage mainPage = open(MainPage.MAIN_URL, MainPage.class);
        MainPage mainPageAfterLogin = mainPage.clickButtonAccount()
                .setLoginForm(userEmail, userPassword);
        Assert.assertTrue(mainPageAfterLogin.isMainPageLoaded());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void userLoginOnClickRegistrationFormButtonTest(){
        MainPage mainPage = open(LoginPage.ACCOUNT_URL, LoginPage.class)
                .setLoginForm(userEmail, userPassword);
        Assert.assertTrue(mainPage.isMainPageLoaded());}

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void userLoginThroughForgetPasswordPageTest(){
        ForgetPasswordPage forgetPasswordPage = open(ForgetPasswordPage.FORGET_PASSWORD_URL, ForgetPasswordPage.class);
        LoginPage loginPage = forgetPasswordPage.clickLinkLogin();
        MainPage mainPageAfterLogin = loginPage.setLoginForm(userEmail, userPassword);
        Assert.assertTrue(mainPageAfterLogin.isMainPageLoaded());}
}
