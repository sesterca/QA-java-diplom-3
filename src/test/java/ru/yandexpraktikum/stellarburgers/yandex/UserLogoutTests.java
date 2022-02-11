package ru.yandexpraktikum.stellarburgers.yandex;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandexpraktikum.stellarburgers.com.UserOperations;
import ru.yandexpraktikum.stellarburgers.pageobjects.LoginPage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;

public class UserLogoutTests extends BaseTest {

    private UserOperations userOperations;
    public static String userEmail;
    public static String userPassword;

    @Before
    public void createUser(){
        userOperations = new UserOperations();
        Map<String, String> userData = userOperations.register();
        userEmail = userData.get("email");
        userPassword = userData.get("password");}

    @After
    public void deleteUser(){
        userOperations.delete();}

    @Test
    @DisplayName("Выход по кнопке Выйти в личном кабинете")
    public void userLogoutOnClickLogoutButtonAccountPageTest(){
        LoginPage loginPage = open(LoginPage.ACCOUNT_URL, LoginPage.class)
                .setLoginForm(userEmail, userPassword)
                .clickButtonAccountAfterLogin()
                .clickButtonLogout();
        Assert.assertTrue(loginPage.isLoginPageLoaded());}
}